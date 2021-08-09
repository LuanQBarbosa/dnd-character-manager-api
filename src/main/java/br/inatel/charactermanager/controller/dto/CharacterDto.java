package br.inatel.charactermanager.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.inatel.charactermanager.model.Character;

public class CharacterDto {
	
	private Long id;
	private String ownerName;
	
	private String name;
	private String race;
	private String job;
	private int level;
	private int hp;
	
	private int strength;
	private int intelligence;
	private int wisdom;
	private int dexterity;
	private int constitution;
	private int charisma;
	
	private List<WeaponDto> weapons = new ArrayList<>();
	private List<ArmorDto> armors = new ArrayList<>();
	private List<ItemDto> items = new ArrayList<>();
	
	
	
	public CharacterDto(Character character) {
		this.id = character.getId();
		this.ownerName = character.getOwner().getName();
		this.name = character.getName();
		this.race = character.getRace().name();
		this.job = character.getJob().name();
		this.level = character.getLevel();
		this.hp = character.getHp();
		this.strength = character.getStrength();
		this.intelligence = character.getIntelligence();
		this.wisdom = character.getWisdom();
		this.dexterity = character.getDexterity();
		this.constitution = character.getConstitution();
		this.charisma = character.getCharisma();
		this.weapons = character.getWeapons().stream().map(WeaponDto::new).collect(Collectors.toList());
		this.armors = character.getArmors().stream().map(ArmorDto::new).collect(Collectors.toList());;
		this.items = character.getItems().stream().map(ItemDto::new).collect(Collectors.toList());;
	}
	
	public Long getId() {
		return id;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public String getName() {
		return name;
	}
	public String getRace() {
		return race;
	}
	public String getJob() {
		return job;
	}
	public int getLevel() {
		return level;
	}
	public int getHp() {
		return hp;
	}
	public int getStrength() {
		return strength;
	}
	public int getIntelligence() {
		return intelligence;
	}
	public int getWisdom() {
		return wisdom;
	}
	public int getDexterity() {
		return dexterity;
	}
	public int getConstitution() {
		return constitution;
	}
	public int getCharisma() {
		return charisma;
	}
	public List<WeaponDto> getWeapons() {
		return weapons;
	}
	public List<ArmorDto> getArmors() {
		return armors;
	}
	public List<ItemDto> getItems() {
		return items;
	}

}
