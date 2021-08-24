package br.inatel.charactermanager.controller.form;

import java.util.List;

import br.inatel.charactermanager.controller.repository.CharacterRepository;
import br.inatel.charactermanager.controller.repository.GameRepository;
import br.inatel.charactermanager.controller.repository.UserRepository;
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

	public Game update(Long gameId, GameRepository gameRepository, UserRepository userRepository, CharacterRepository characterRepository) {
		Game game = gameRepository.findById(gameId).get();
		
		if (name != null || name != "") {
			game.setName(name);
		}
		
		if (gameMasterId != null) {
			User newGameMaster = userRepository.findById(gameMasterId).get();
			game.setGameMaster(newGameMaster);
		}
		
		if (newPlayerId != null) {
			User newPlayer = userRepository.findById(newPlayerId).get();
			List<User> playersList = game.getPlayers();
			playersList.add(newPlayer);
			
			game.setPlayers(playersList);;
		}
		
		if (newCharacterId != null) {
			Character newCharacter = characterRepository.findById(newCharacterId).get();
			List<Character> charactersList = game.getCharacters();
			charactersList.add(newCharacter);
			
			game.setCharacters(charactersList);
		}
		
		return game;
	}
	
}
