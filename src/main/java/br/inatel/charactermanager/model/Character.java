package br.inatel.charactermanager.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Character {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String name;
	private int level = 1;
	private int hp = 100;
	
	@Enumerated(EnumType.STRING)
	private Race race;
	@Enumerated(EnumType.STRING)
	private Job job;
	
	private int strength;
	private int intelligence;
	private int wisdom;
	private int dexterity;
	private int constitution;
	private int charisma;
	
	@ManyToOne
	private User owner;
	@ManyToOne
	private Game game;

	@ElementCollection
	private List<String> weaponsIndex = new ArrayList<>();
	@ElementCollection
	private List<String> armorsIndex = new ArrayList<>();
	@ElementCollection
	private List<String> itemsIndex = new ArrayList<>();
	
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
		Character other = (Character) obj;
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

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public List<String> getWeaponsIndex() {
		return weaponsIndex;
	}

	public void setWeaponsIndex(List<String> weaponsIndex) {
		this.weaponsIndex = weaponsIndex;
	}

	public List<String> getArmorsIndex() {
		return armorsIndex;
	}

	public void setArmorsIndex(List<String> armorsIndex) {
		this.armorsIndex = armorsIndex;
	}

	public List<String> getItemsIndex() {
		return itemsIndex;
	}

	public void setItemsIndex(List<String> itemsIndex) {
		this.itemsIndex = itemsIndex;
	}
	
}
