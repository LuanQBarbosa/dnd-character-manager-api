package br.inatel.charactermanager.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	
}
