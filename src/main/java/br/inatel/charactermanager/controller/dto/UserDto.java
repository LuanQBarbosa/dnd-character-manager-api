package br.inatel.charactermanager.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.inatel.charactermanager.model.User;

public class UserDto {

	private Long id;
	private String name;
	private String email;
	
	private List<Long> playingGamesIdList;
	private List<Long> ownedGamesIdList;
	private List<Long> charactersIdList;
	
	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		
		this.playingGamesIdList = user.getPlayingGames().stream().map(x -> x.getId()).collect(Collectors.toList());
		this.ownedGamesIdList = user.getOwnedGames().stream().map(x -> x.getId()).collect(Collectors.toList());
		this.charactersIdList = user.getCharacters().stream().map(x -> x.getId()).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public List<Long> getPlayingGamesIdList() {
		return playingGamesIdList;
	}

	public List<Long> getOwnedGamesIdList() {
		return ownedGamesIdList;
	}

	public List<Long> getCharactersIdList() {
		return charactersIdList;
	}
	
	public static List<UserDto> convertList(List<User> users) {
		return users.stream().map(UserDto::new).collect(Collectors.toList());
	}
	
}
