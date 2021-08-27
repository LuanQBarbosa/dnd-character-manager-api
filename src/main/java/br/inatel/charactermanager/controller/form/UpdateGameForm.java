package br.inatel.charactermanager.controller.form;

import java.util.List;
import java.util.Optional;

import br.inatel.charactermanager.controller.repository.CharacterRepository;
import br.inatel.charactermanager.controller.repository.GameRepository;
import br.inatel.charactermanager.controller.repository.UserRepository;
import br.inatel.charactermanager.exception.InvalidPropertyException;
import br.inatel.charactermanager.model.Game;
import br.inatel.charactermanager.model.User;
import br.inatel.charactermanager.model.Character;

public class UpdateGameForm {

	private String name;
	private Long gameMasterId;
	private Long newPlayerId;
	private Long newCharacterId;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getGameMasterId() {
		return gameMasterId;
	}
	
	public void setGameMasterId(Long gameMasterId) {
		this.gameMasterId = gameMasterId;
	}
	
	public Long getNewPlayerId() {
		return newPlayerId;
	}

	public void setNewPlayerId(Long newPlayerId) {
		this.newPlayerId = newPlayerId;
	}

	public Long getNewCharacterId() {
		return newCharacterId;
	}

	public void setNewCharacterId(Long newCharacterId) {
		this.newCharacterId = newCharacterId;
	}

	public Game update(Long gameId, Long authenticatedUserId, GameRepository gameRepository, UserRepository userRepository, CharacterRepository characterRepository) throws InvalidPropertyException {
		Optional<Game> optional = gameRepository.findById(gameId);
		
		if(!optional.isPresent()) {
			throw new InvalidPropertyException("No game found with the informed id");
		}
		
		Game game = optional.get();
		if (authenticatedUserId != game.getGameMaster().getId()) {
			throw new InvalidPropertyException("Logged user is not this game's game master");
		}
		
		
		if (name != null && name != "") {
			game.setName(name);
		}
		
		if (gameMasterId != null) {
			Optional<User> newGameMaster = userRepository.findById(gameMasterId);
			if (!newGameMaster.isPresent()) {
				throw new InvalidPropertyException("Could not find a User with specified Id");
			}
			
			game.setGameMaster(newGameMaster.get());
		}
		
		if (newPlayerId != null) {
			Optional<User> newPlayer = userRepository.findById(newPlayerId);
			if (!newPlayer.isPresent()) {
				throw new InvalidPropertyException("Could not find a User with specified Id");
			}
			
			List<User> playersList = game.getPlayers();
			playersList.add(newPlayer.get());
			
			game.setPlayers(playersList);;
		}
		
		if (newCharacterId != null) {
			Optional<Character> newCharacter = characterRepository.findById(newCharacterId);
			if (!newCharacter.isPresent()) {
				throw new InvalidPropertyException("Could not find a Character with specified Id");
			}
			
			List<Character> charactersList = game.getCharacters();
			charactersList.add(newCharacter.get());
			
			game.setCharacters(charactersList);
		}
		
		return game;
	}
	
}
