package br.inatel.charactermanager.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.inatel.charactermanager.controller.repository.GameRepository;
import br.inatel.charactermanager.controller.repository.UserRepository;
import br.inatel.charactermanager.exception.InvalidPropertyException;
import br.inatel.charactermanager.model.Game;
import br.inatel.charactermanager.model.Job;
import br.inatel.charactermanager.model.Race;
import br.inatel.charactermanager.model.User;
import br.inatel.charactermanager.model.Character;

public class CharacterForm {

	@NotNull
	@NotEmpty
	private String name;
	@NotNull
	@NotEmpty
	private String race;
	@NotNull
	@NotEmpty
	private String job;
	
	@NotNull
	private int strength;
	@NotNull
	private int intelligence;
	@NotNull
	private int wisdom;
	@NotNull
	private int dexterity;
	@NotNull
	private int constitution;
	@NotNull
	private int charisma;
	
	@NotNull
	private Long gameId;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRace() {
		return race;
	}
	
	public void setRace(String race) {
		this.race = race;
	}
	
	public String getJob() {
		return job;
	}
	
	public void setJob(String job) {
		this.job = job;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	public int getIntelligence() {
		return intelligence;
	}
	
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
	
	public int getWisdom() {
		return wisdom;
	}
	
	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}
	
	public int getDexterity() {
		return dexterity;
	}
	
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}
	
	public int getConstitution() {
		return constitution;
	}
	
	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}
	
	public int getCharisma() {
		return charisma;
	}
	
	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}
	
	public Long getGameId() {
		return gameId;
	}
	
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	
	public Character convert(Long ownerId, UserRepository userRepository, GameRepository gameRepository) throws InvalidPropertyException {
		Optional<User> owner = userRepository.findById(ownerId);
		Optional<Game> game = gameRepository.findById(gameId);
		
		if (!game.isPresent()) {
			throw new InvalidPropertyException("Could not find a Game with specified Id");
		}
		
		if (!owner.isPresent()) {
			throw new InvalidPropertyException("Could not find a User with specified Id");
		}
		
		Character newCharacter = new Character();
		newCharacter.setName(name);
		newCharacter.setRace(Race.valueOf(race.toUpperCase()));
		newCharacter.setJob(Job.valueOf(job.toUpperCase()));
		
		newCharacter.setStrength(strength);
		newCharacter.setCharisma(charisma);
		newCharacter.setConstitution(constitution);
		newCharacter.setIntelligence(intelligence);
		newCharacter.setWisdom(wisdom);
		newCharacter.setDexterity(dexterity);
		
		newCharacter.setOwner(owner.get());
		newCharacter.setGame(game.get());
		
		return newCharacter;
	}
	
}
