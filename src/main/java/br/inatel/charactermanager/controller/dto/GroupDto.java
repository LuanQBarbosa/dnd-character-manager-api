package br.inatel.charactermanager.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.inatel.charactermanager.model.Group;

public class GroupDto {
	
	private Long id;
	private String name;
	private String gameMaster;
	private List<String> players;
	private List<CharacterDto> characters;
	
	public GroupDto(Group group) {
		this.id = group.getId();
		this.name = group.getName();
		this.gameMaster = group.getGameMaster().getName();
		this.players = group.getPlayers().stream().map(x -> x.getName()).collect(Collectors.toList());
		this.characters = group.getCharacters().stream().map(CharacterDto::new).collect(Collectors.toList());
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
	
	public List<GroupDto> convert(List<Group> groups) {
		return groups.stream().map(GroupDto::new).collect(Collectors.toList());
	}

}
