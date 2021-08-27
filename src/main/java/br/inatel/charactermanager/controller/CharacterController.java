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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import br.inatel.charactermanager.controller.dto.CharacterDto;
import br.inatel.charactermanager.controller.dto.GameDto;
import br.inatel.charactermanager.controller.form.CharacterForm;
import br.inatel.charactermanager.controller.form.GameForm;
import br.inatel.charactermanager.controller.form.UpdateCharacterEquipmentForm;
import br.inatel.charactermanager.controller.form.UpdateCharacterForm;
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
import br.inatel.charactermanager.service.EquipmentService;
import br.inatel.charactermanager.service.dto.Armor;
import br.inatel.charactermanager.service.dto.EquipmentCategory;
import br.inatel.charactermanager.service.dto.Item;
import br.inatel.charactermanager.service.dto.Weapon;

@RestController
@RequestMapping("/character")
public class CharacterController {
	
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CharacterRepository characterRepository;
	@Autowired
	private EquipmentService equipmentService;
	
	@GetMapping
	@Cacheable(value="charactersList")
	public ResponseEntity<?> list(Authentication authentication, @RequestParam(required = false) Long gameId) {
		User authenticatedUser = (User) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();
		
		List<Character> charactersList;
		if (gameId != null) {
			Optional<Game> game = gameRepository.findById(gameId);
			if (!game.isPresent()) {
				return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body(new ErrorFormDto("", "No game found with the informed id"));
			}
			
			Long gameMasterId = game.get().getGameMaster().getId();
			if (authenticatedUserId == gameMasterId) {
				charactersList = characterRepository.findAllByGameId(authenticatedUserId);
			} else {
				charactersList = characterRepository.findAllByGameIdAndOwnerId(gameId, authenticatedUserId);
			}
		} else {
			charactersList = characterRepository.findAllByOwnerId(authenticatedUserId);
		}
		
		if (charactersList.size() == 0) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ErrorFormDto("", "No characters found"));
		}
		
		return ResponseEntity.ok(CharacterDto.convertList(charactersList, equipmentService));
	}
	
	@GetMapping("/{characterId}")
	public ResponseEntity<?> getByCharacterId(Authentication authentication, @PathVariable Long characterId) {
		User authenticatedUser = (User) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();
		
		Optional<Character> character = characterRepository.findById(characterId);
		
		if (!character.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ErrorFormDto("", "No character found with the informed id"));
		}
		
		Long characterOwnerId = character.get().getOwner().getId();
		if (characterOwnerId != authenticatedUserId) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorFormDto("", "Logged user is not this character's owner"));
		}
		
		return ResponseEntity.ok(new CharacterDto(character.get(), equipmentService));
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "charactersList", allEntries = true)
	public ResponseEntity<?> create(Authentication authentication, @RequestBody @Valid CharacterForm form, UriComponentsBuilder uriBuilder) {
		User authenticatedUser = (User) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();
		
		Character newCharacter;
		try {
			newCharacter = form.convert(authenticatedUserId, userRepository, gameRepository);			
		} catch(InvalidPropertyException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorFormDto("", e.getMessage()));
		}
		
		Character character = characterRepository.save(newCharacter);
		
		URI uri = uriBuilder.path("/character/{id}").buildAndExpand(character.getId()).toUri();
		return ResponseEntity.created(uri).body(new CharacterDto(character, equipmentService));
	}
	
	@PutMapping("/{characterId}")
	@Transactional
	@CacheEvict(value = "charactersList", allEntries = true)
	public ResponseEntity<?> update(Authentication authentication, @PathVariable Long characterId, @RequestBody @Valid UpdateCharacterForm form) {
		User authenticatedUser = (User) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();
		
		Character character;
		try {
			character = form.update(characterId, authenticatedUserId, characterRepository);			
		} catch (InvalidPropertyException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorFormDto("", e.getMessage()));
		}
		
		return ResponseEntity.ok(new CharacterDto(character, equipmentService));
	}
	
	@PutMapping("/{characterId}/equipment")
	@Transactional
	@CacheEvict(value = "charactersList", allEntries = true)
	public ResponseEntity<?> updateEquipment(Authentication authentication, @PathVariable Long characterId, @RequestBody @Valid UpdateCharacterEquipmentForm form) {
		User authenticatedUser = (User) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();
		
		Character character;
		try {
			character = form.update(characterId, authenticatedUserId, characterRepository, equipmentService);			
		} catch (InvalidPropertyException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorFormDto("", e.getMessage()));
		}
		
		return ResponseEntity.ok(new CharacterDto(character, equipmentService));
	}
	
	@DeleteMapping("/{characterId}")
	@Transactional
	@CacheEvict(value = "charactersList", allEntries = true)
	public ResponseEntity<?> delete(Authentication authentication, @PathVariable Long characterId) {
		User authenticatedUser = (User) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();
		
		Optional<Character> optional = characterRepository.findById(characterId);
		if(!optional.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ErrorFormDto("characterId", "No character found with the informed id"));
		}
		
		Character character = optional.get();
		if (authenticatedUserId != character.getOwner().getId()) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorFormDto("", "Logged user is not this character's owner"));
		}
		
		characterRepository.deleteById(characterId);
		
		return ResponseEntity.ok().build();
	}
	
}
