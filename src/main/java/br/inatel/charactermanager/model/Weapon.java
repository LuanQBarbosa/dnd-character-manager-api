package br.inatel.charactermanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Weapon {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String weaponCategory;
	private String weaponRange;
	private String damageDice;
	private String damageType;
	private int cost;
	private int weight;
	
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
		Weapon other = (Weapon) obj;
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
	
	public String getWeaponCategory() {
		return weaponCategory;
	}
	
	public void setWeaponCategory(String weaponCategory) {
		this.weaponCategory = weaponCategory;
	}
	
	public String getWeaponRange() {
		return weaponRange;
	}
	
	public void setWeaponRange(String weaponRange) {
		this.weaponRange = weaponRange;
	}
	
	public String getDamageDice() {
		return damageDice;
	}
	
	public void setDamageDice(String damageDice) {
		this.damageDice = damageDice;
	}
	
	public String getDamageType() {
		return damageType;
	}
	
	public void setDamageType(String damageType) {
		this.damageType = damageType;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}

}
