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
			gamesList = gameRepository.findByGameMasterId(gameMasterId, pageable);
		}
		
		return ResponseEntity.ok(GameDto.convertList(gamesList));
	}
	
	@GetMapping("/{gameId}")
	public ResponseEntity<?> getByGameId(@PathVariable Long gameId) {
		Optional<Game> game = gameRepository.findById(gameId);
		
		if(!game.isPresent()) {
			return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(new ErrorFormDto("", "No game found with the informed id"));
		}
		
		return ResponseEntity.ok(new GameDto(game.get()));
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "gamesList", allEntries = true)
	public ResponseEntity<?> create(@RequestBody @Valid GameForm form, UriComponentsBuilder uriBuilder) {
		Game newGame = form.convert(userRepository);
		
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
	public ResponseEntity<?> update(@PathVariable Long gameId, @RequestBody @Valid UpdateGameForm form) {
		Game game;
		try {
			game = form.update(gameId, gameRepository, userRepository, characterRepository);			
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
	public ResponseEntity<?> delete(@PathVariable Long gameId) {
		gameRepository.deleteById(gameId);
		
		return ResponseEntity.ok().build();
	}
	
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void populateDatabase() {
		User player1 = new User();
		player1.setName("player1");
		player1.setEmail("player1@email.com");
		player1.setPassword(new BCryptPasswordEncoder().encode("123456"));
		
		User player2 = new User();
		player2.setName("player2");
		player2.setEmail("player2@email.com");
		player2.setPassword(new BCryptPasswordEncoder().encode("123456"));
		
		List<User> playersList = new ArrayList<>(Arrays.asList(player1, player2));
		
		Game game1 = new Game();
		game1.setName("game1");
		game1.setGameMaster(player1);
		game1.setPlayers(playersList);
		
		Game game2 = new Game();
		game2.setName("game2");
		game2.setGameMaster(player2);
		game2.setPlayers(playersList);
		
		Character character1 = new Character();
		character1.setName("character1");
		character1.setRace(Race.ELF);
		character1.setJob(Job.RANGER);
		character1.setStrength(5);
		character1.setCharisma(5);
		character1.setDexterity(5);
		character1.setIntelligence(5);
		character1.setWisdom(5);
		character1.setConstitution(5);
		character1.getWeaponsIndex().add("shortsword");
		character1.getArmorsIndex().add("padded");
		character1.getItemsIndex().add("backpack");
		character1.setOwner(player1);
		character1.setGame(game1);
		
		Character character2 = new Character();
		character2.setName("character2");
		character2.setRace(Race.HUMAN);
		character2.setJob(Job.PALADIN);
		character2.setStrength(5);
		character2.setCharisma(5);
		character2.setDexterity(5);
		character2.setIntelligence(5);
		character2.setWisdom(5);
		character2.setConstitution(5);
		character2.setOwner(player2);
		character2.setGame(game1);
		
		List<Character> gameCharactersList = new ArrayList<>(Arrays.asList(character1, character2));
		game1.setCharacters(gameCharactersList);
		
		List<Game> playingGamesList = new ArrayList<>(Arrays.asList(game1, game2));
		
		List<Character> charactersList1 = new ArrayList<>(Arrays.asList(character1));
		player1.setCharacters(charactersList1);
		List<Game> ownedGames1 = new ArrayList<>(Arrays.asList(game1));
		player1.setOwnedGames(ownedGames1);
		player1.setPlayingGames(playingGamesList);
		
		List<Character> charactersList2 = new ArrayList<>(Arrays.asList(character2));
		player2.setCharacters(charactersList2);
		List<Game> ownedGames2 = new ArrayList<>(Arrays.asList(game2));
		player2.setOwnedGames(ownedGames2);
		player2.setPlayingGames(playingGamesList);
		
		userRepository.save(player1);
		userRepository.save(player2);
		
		characterRepository.save(character1);
		characterRepository.save(character2);
		
		gameRepository.save(game1);
		gameRepository.save(game2);
	}
	
}
