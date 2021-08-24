package br.inatel.charactermanager.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.inatel.charactermanager.model.Game;

public class GameDto {
	
	private Long id;
	private String name;
	private Long gameMasterId;
	private List<Long> playersIdList;
	
	public GameDto(Game game) {
		this.id = game.getId();
		this.name = game.getName();
		this.gameMasterId = game.getGameMaster().getId();
		this.playersIdList = game.getPlayers().stream().map(x -> x.getId()).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getGameMasterId() {
		return gameMasterId;
	}

	public List<Long> getPlayersIdList() {
		return playersIdList;
	}
	
	public static List<GameDto> convertList(List<Game> games) {
		return games.stream().map(GameDto::new).collect(Collectors.toList());
	}

}
