package br.inatel.charactermanager.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.inatel.charactermanager.model.Character;
import br.inatel.charactermanager.service.EquipmentService;
import br.inatel.charactermanager.service.dto.Armor;
import br.inatel.charactermanager.service.dto.Item;
import br.inatel.charactermanager.service.dto.Weapon;

public class CharacterDto {
	
	private Long id;
	private String ownerName;
	private Long ownerId;
	private String gameName;
	private Long gameId;
	
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
	
	private List<Weapon> weapons = new ArrayList<>();
	private List<Armor> armors = new ArrayList<>();
	private List<Item> items = new ArrayList<>();
	
	public CharacterDto(Character character, EquipmentService equipmentService) {
		this.id = character.getId();
		this.ownerId = character.getOwner().getId();
		this.ownerName = character.getOwner().getName();
		this.gameId = character.getGame().getId();
		this.gameName = character.getGame().getName();
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
		
		this.weapons = character.getWeaponsIndex().stream().map(x -> equipmentService.getWeapon(x)).collect(Collectors.toList());
		this.armors = character.getArmorsIndex().stream().map(x -> equipmentService.getArmor(x)).collect(Collectors.toList());;
		this.items = character.getItemsIndex().stream().map(x -> equipmentService.getItem(x)).collect(Collectors.toList());;
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
	public List<Weapon> getWeapons() {
		return weapons;
	}
	public List<Armor> getArmors() {
		return armors;
	}
	public List<Item> getItems() {
		return items;
	}
	public Long getGameId() {
		return gameId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public String getGameName() {
		return gameName;
	}
	
	public static List<CharacterDto> convertList(List<Character> characters, EquipmentService equipmentService) {
		return characters.stream().map(x -> new CharacterDto(x, equipmentService)).collect(Collectors.toList());
	}

}
