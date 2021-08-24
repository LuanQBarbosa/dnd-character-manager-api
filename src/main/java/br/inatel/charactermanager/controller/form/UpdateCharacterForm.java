package br.inatel.charactermanager.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.inatel.charactermanager.controller.repository.GameRepository;
import br.inatel.charactermanager.controller.repository.UserRepository;
import br.inatel.charactermanager.model.Character;
import br.inatel.charactermanager.model.Game;
import br.inatel.charactermanager.model.Job;
import br.inatel.charactermanager.model.Race;
import br.inatel.charactermanager.model.User;

public class UpdateCharacterForm {
	
	private String name;
	private String job;
	
	private int strength;
	private int intelligence;
	private int wisdom;
	private int dexterity;
	private int constitution;
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
	
//	public Character convert(UserRepository userRepository, GameRepository gameRepository) {
//		User owner = userRepository.findById(ownerId).get();
//		Game game = gameRepository.findById(gameId).get();
//		
//		Character newCharacter = new Character();
//		newCharacter.setName(name);
//		newCharacter.setRace(Race.valueOf(race.toUpperCase()));
//		newCharacter.setJob(Job.valueOf(job.toUpperCase()));
//		
//		newCharacter.setStrength(strength);
//		newCharacter.setCharisma(charisma);
//		newCharacter.setConstitution(constitution);
//		newCharacter.setIntelligence(intelligence);
//		newCharacter.setWisdom(wisdom);
//		newCharacter.setDexterity(dexterity);
//		
//		newCharacter.setOwner(owner);
//		newCharacter.setGame(game);
//		
//		return newCharacter;
//	}
}
