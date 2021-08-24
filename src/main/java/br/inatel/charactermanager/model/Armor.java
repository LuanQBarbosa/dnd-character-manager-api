package br.inatel.charactermanager.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Armor {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String armorCategory;
	private int armorClass;
	private boolean stealthDisadvantage;
	private int cost;
	private int weight;
	
//	@ManyToMany(mappedBy = "armors")
//	private List<Character> owners;
	
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
		Armor other = (Armor) obj;
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
	
	public String getArmorCategory() {
		return armorCategory;
	}
	
	public void setArmorCategory(String armorCategory) {
		this.armorCategory = armorCategory;
	}
	
	public int getArmorClass() {
		return armorClass;
	}
	
	public void setArmorClass(int armorClass) {
		this.armorClass = armorClass;
	}
	
	public boolean isStealthDisadvantage() {
		return stealthDisadvantage;
	}
	
	public void setStealthDisadvantage(boolean stealthDisadvantage) {
		this.stealthDisadvantage = stealthDisadvantage;
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

//	public List<Character> getOwners() {
//		return owners;
//	}
//
//	public void setOwners(List<Character> owners) {
//		this.owners = owners;
//	}

}
