package br.inatel.charactermanager.service.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weapon {
	
	private String index;
	private String name;
	private String weapon_category;
	private String weapon_range;
	private String weight;
	private Cost cost;
	private Damage damage;
	
	public Weapon(String index, String name, String weapon_category, String weapon_range, String weight, Cost cost, Damage damage) {
		this.index = index;
		this.name = name;
		this.setWeapon_category(weapon_category);
		this.setWeapon_range(weapon_range);
		this.setWeight(weight);
		this.setCost(cost);
		this.setDamage(damage);
	}
	
	public Weapon() {}

	public String getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeapon_category() {
		return weapon_category;
	}

	public void setWeapon_category(String weapon_category) {
		this.weapon_category = weapon_category;
	}

	public String getWeapon_range() {
		return weapon_range;
	}

	public void setWeapon_range(String weapon_range) {
		this.weapon_range = weapon_range;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public Damage getDamage() {
		return damage;
	}

	public void setDamage(Damage damage) {
		this.damage = damage;
	}

}
