package br.inatel.charactermanager.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.inatel.charactermanager.model.Game;

public class GameDto {
	
	private Long id;
	private String name;
	private String gameMaster;
	private List<String> players;
	private List<CharacterDto> characters;
	
	public GameDto(Game game) {
		this.id = game.getId();
		this.name = game.getName();
		this.gameMaster = game.getGameMaster().getName();
		this.players = game.getPlayers().stream().map(x -> x.getName()).collect(Collectors.toList());
		this.characters = game.getCharacters().stream().map(CharacterDto::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getGameMaster() {
		return gameMaster;
	}

	public List<String> getPlayers() {
		return players;
	}

	public List<CharacterDto> getCharacters() {
		return characters;
	}
	
	public List<GameDto> convert(List<Game> games) {
		return games.stream().map(GameDto::new).collect(Collectors.toList());
	}

}
