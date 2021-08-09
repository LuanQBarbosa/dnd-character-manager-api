package br.inatel.charactermanager.controller.dto;

import br.inatel.charactermanager.model.Weapon;

public class WeaponDto {
	
	private Long id;
	private String name;
	private String weaponCategory;
	private String weaponRange;
	private String damageDice;
	private String damageType;
	private int cost;
	private int weight;
	
	public WeaponDto(Weapon weapon) {
		this.id = weapon.getId();
		this.name = weapon.getName();
		this.weaponCategory = weapon.getWeaponCategory();
		this.weaponRange = weapon.getWeaponRange();
		this.damageDice = weapon.getDamageDice();
		this.damageType = weapon.getDamageType();
		this.cost = weapon.getCost();
		this.weight = weapon.getWeight();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getWeaponCategory() {
		return weaponCategory;
	}

	public String getWeaponRange() {
		return weaponRange;
	}

	public String getDamageDice() {
		return damageDice;
	}

	public String getDamageType() {
		return damageType;
	}

	public int getCost() {
		return cost;
	}

	public int getWeight() {
		return weight;
	}

}
