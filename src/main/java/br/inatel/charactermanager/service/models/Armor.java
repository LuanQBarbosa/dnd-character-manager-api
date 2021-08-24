package br.inatel.charactermanager.service.models;

public class Armor {
	
	private String index;
	private String name;
	private String armor_category;
	private boolean stealph_disadvantage;
	private Cost cost;
	private ArmorClass armor_class;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArmor_category() {
		return armor_category;
	}
	public void setArmor_category(String armor_category) {
		this.armor_category = armor_category;
	}
	public boolean isStealph_disadvantage() {
		return stealph_disadvantage;
	}
	public void setStealph_disadvantage(boolean stealph_disadvantage) {
		this.stealph_disadvantage = stealph_disadvantage;
	}
	public Cost getCost() {
		return cost;
	}
	public void setCost(Cost cost) {
		this.cost = cost;
	}
	public ArmorClass getArmor_class() {
		return armor_class;
	}
	public void setArmor_class(ArmorClass armor_class) {
		this.armor_class = armor_class;
	}

}
