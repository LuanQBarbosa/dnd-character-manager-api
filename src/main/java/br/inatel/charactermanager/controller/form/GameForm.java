package br.inatel.charactermanager.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.inatel.charactermanager.controller.repository.UserRepository;
import br.inatel.charactermanager.exception.InvalidPropertyException;
import br.inatel.charactermanager.model.Game;
import br.inatel.charactermanager.model.User;

public class GameForm {
	
	@NotNull
	@NotEmpty
	private String name;
	@NotNull
	private Long gameMasterId;
	
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
	
	public Game convert(UserRepository userRepository) throws InvalidPropertyException {
		Optional<User> gameMaster = userRepository.findById(gameMasterId);
		
		if (!gameMaster.isPresent()) {
			throw new InvalidPropertyException("Could not find a User with specified Id");
		}
		
		Game newGame = new Game();
		newGame.setGameMaster(gameMaster.get());
		newGame.setName(name);
		
		return newGame;
	}
	
}
