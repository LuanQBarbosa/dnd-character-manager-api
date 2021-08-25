package br.inatel.charactermanager.service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EquipmentCategory {
	
	private String index;
	private String name;
	private List<Equipment> equipment;
	
	public EquipmentCategory(String index, String name, List<Equipment> equipment) {
		this.index = index;
		this.name = name;
		this.equipment = equipment;
	}
	
	public EquipmentCategory() {}

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

	public List<Equipment> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}

}
