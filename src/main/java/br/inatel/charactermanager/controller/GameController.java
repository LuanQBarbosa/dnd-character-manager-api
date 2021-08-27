package br.inatel.charactermanager.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.inatel.charactermanager.config.validation.ErrorFormDto;
import br.inatel.charactermanager.controller.dto.GameDto;
import br.inatel.charactermanager.controller.form.GameForm;
import br.inatel.charactermanager.controller.form.UpdateGameForm;
import br.inatel.charactermanager.controller.repository.CharacterRepository;
import br.inatel.charactermanager.controller.repository.GameRepository;
import br.inatel.charactermanager.controller.repository.UserRepository;
import br.inatel.charactermanager.exception.InvalidPropertyException;
import br.inatel.charactermanager.model.Character;
import br.inatel.charactermanager.model.Game;
import br.inatel.charactermanager.model.Job;
import br.inatel.charactermanager.model.Race;
import br.inatel.charactermanager.model.User;

@RestController
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CharacterRepository characterRepository;
	
	@GetMapping
	@Cacheable(value = "gamesList")
	public ResponseEntity<?> list(@RequestParam(required = false) Long gameMasterId, Pageable pageable) {
		
		Page<Game> gamesList;
		if (gameMasterId == null) {
			gamesList = gameRepository.findAll(pageable);
		} else {
			gamesList = gameRepository.findAllByGameMasterId(gameMasterId, pageable);
			
			if (gamesList.getContent().size() == 0) {
				return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body(new ErrorFormDto("gameMasterId", "No games found for the informed game master id"));
			}
		}
		
		return ResponseEntity.ok(GameDto.convertList(gamesList));
	}
	
	@GetMapping("/{gameId}")
	public ResponseEntity<?> getByGameId(@PathVariable Long gameId) {
		Optional<Game> game = gameRepository.findById(gameId);
		
		if(!game.isPresent()) {
			return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(new ErrorFormDto("gameId", "No game found with the informed id"));
		}
		
		return ResponseEntity.ok(new GameDto(game.get()));
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "gamesList", allEntries = true)
	public ResponseEntity<?> create(Authentication authentication, @RequestBody @Valid GameForm form, UriComponentsBuilder uriBuilder) {
		User authenticatedUser = (User) authentication.getPrincipal();
		Game newGame = form.convert(authenticatedUser.getId(), userRepository);
		
		Game game;
		try {
			game = gameRepository.save(newGame);			
		} catch(InvalidPropertyException e) {
			return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorFormDto("gameMasterId", e.getMessage()));
		}
		
		URI uri = uriBuilder.path("/game/{id}").buildAndExpand(game.getId()).toUri();
		return ResponseEntity.created(uri).body(new GameDto(game));
	}
	
	@PutMapping("/{gameId}")
	@Transactional
	@CacheEvict(value = "gamesList", allEntries = true)
	public ResponseEntity<?> update(Authentication authentication, @PathVariable Long gameId, @RequestBody @Valid UpdateGameForm form) {
		User authenticatedUser = (User) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId(); 
		
		Game game;
		try {
			game = form.update(gameId, authenticatedUserId, gameRepository, userRepository, characterRepository);			
		} catch(InvalidPropertyException e) {
			return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorFormDto("", e.getMessage()));
		}
		
		return ResponseEntity.ok(new GameDto(game));
	}
	
	@DeleteMapping("/{gameId}")
	@Transactional
	@CacheEvict(value = "gamesList", allEntries = true)
	public ResponseEntity<?> delete(Authentication authentication, @PathVariable Long gameId) {
		User authenticatedUser = (User) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();
		
		Optional<Game> optional = gameRepository.findById(gameId);
		if(!optional.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ErrorFormDto("gameId", "No game found with the informed id"));
		}
		
		Game game = optional.get();
		if (authenticatedUserId != game.getGameMaster().getId()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorFormDto("", "Logged user is not this game's game master"));
		}
		
		gameRepository.deleteById(gameId);
		
		return ResponseEntity.ok().build();
	}
	
}
