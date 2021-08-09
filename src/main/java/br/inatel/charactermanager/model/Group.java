package br.inatel.charactermanager.model;

import java.util.ArrayList;
import java.util.List;

public class Group {
	
	private Long id;
	private String name;
	private User gameMaster;
	private List<User> players = new ArrayList<>();
	private List<User> requests = new ArrayList<>();
	
	private List<Character> characters = new ArrayList<>();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getGameMaster() {
		return gameMaster;
	}

	public void setGameMaster(User gameMaster) {
		this.gameMaster = gameMaster;
	}

	public List<User> getPlayers() {
		return players;
	}

	public void setPlayers(List<User> players) {
		this.players = players;
	}

	public List<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

	public List<User> getRequests() {
		return requests;
	}

	public void setRequests(List<User> requests) {
		this.requests = requests;
	}

}
