package br.inatel.charactermanager.controller.dto;

import br.inatel.charactermanager.model.Armor;

public class ArmorDto {
	
	private Long id;
	private String name;
	private String armorCategory;
	private int armorClass;
	private boolean stealthDisadvantage;
	private int cost;
	private int weight;
	
	public ArmorDto(Armor armor) {
		this.id = armor.getId();
		this.name = armor.getName();
		this.armorCategory = armor.getArmorCategory();
		this.armorClass = armor.getArmorClass();
		this.stealthDisadvantage = armor.isStealthDisadvantage();
		this.cost = armor.getCost();
		this.weight = armor.getWeight();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getArmorCategory() {
		return armorCategory;
	}

	public int getArmorClass() {
		return armorClass;
	}

	public boolean isStealthDisadvantage() {
		return stealthDisadvantage;
	}

	public int getCost() {
		return cost;
	}

	public int getWeight() {
		return weight;
	}

}
