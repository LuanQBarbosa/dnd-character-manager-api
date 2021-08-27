package br.inatel.charactermanager.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.inatel.charactermanager.controller.repository.CharacterRepository;
import br.inatel.charactermanager.exception.InvalidPropertyException;
import br.inatel.charactermanager.model.Character;
import br.inatel.charactermanager.model.Job;
import br.inatel.charactermanager.model.Race;

public class UpdateCharacterForm {
	
	@NotNull
	@NotEmpty
	private String name;
	@NotNull
	private int level;
	@NotNull
	private int hp;
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}
	
	public Character update(Long characterId, Long authenticatedUserId, CharacterRepository characterRepository) throws InvalidPropertyException {
		Optional<Character> optional = characterRepository.findById(characterId);
		if (!optional.isPresent()) {
			throw new InvalidPropertyException("Could not find a Character with specified Id");
		}
		
		Character character = optional.get();
		if (authenticatedUserId != character.getOwner().getId()) {
			throw new InvalidPropertyException("Logged user is not this character's owner");
		}
		
		character.setName(name);
		character.setLevel(level);
		character.setHp(hp);
		character.setRace(Race.valueOf(race.toUpperCase()));
		character.setJob(Job.valueOf(job.toUpperCase()));
		
		character.setCharisma(charisma);
		character.setDexterity(dexterity);
		character.setIntelligence(intelligence);
		character.setStrength(strength);
		character.setWisdom(wisdom);
		character.setConstitution(constitution);
		
		return character;
	}
}
