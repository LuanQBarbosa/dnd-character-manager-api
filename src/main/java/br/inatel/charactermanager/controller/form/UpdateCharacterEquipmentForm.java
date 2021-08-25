package br.inatel.charactermanager.controller.form;

import java.util.List;

import br.inatel.charactermanager.controller.repository.CharacterRepository;
import br.inatel.charactermanager.model.Character;

public class UpdateCharacterEquipmentForm {
	
	private String weaponIndex;
	private String armorIndex;
	private String itemIndex;
	
	public String getWeaponIndex() {
		return weaponIndex;
	}
	
	public void setWeaponIndex(String weaponIndex) {
		this.weaponIndex = weaponIndex;
	}
	
	public String getArmorIndex() {
		return armorIndex;
	}
	
	public void setArmorIndex(String armorIndex) {
		this.armorIndex = armorIndex;
	}
	
	public String getItemIndex() {
		return itemIndex;
	}
	
	public void setItemIndex(String itemIndex) {
		this.itemIndex = itemIndex;
	}
	
	public Character update(Long characterId, CharacterRepository characterRepository) {
		Character character = characterRepository.findById(characterId).get();
		
		if (weaponIndex != null || weaponIndex != "") {
			List<String> weaponsIndex = character.getWeaponsIndex();
			weaponsIndex.add(weaponIndex);
			character.setWeaponsIndex(weaponsIndex);
		}
		
		if (armorIndex != null || armorIndex != "") {
			List<String> armorsIndex = character.getArmorsIndex();
			armorsIndex.add(armorIndex);
			character.setArmorsIndex(armorsIndex);
		}
		
		if (itemIndex != null || itemIndex != "") {
			List<String> itemsIndex = character.getItemsIndex();
			itemsIndex.add(itemIndex);
			character.setItemsIndex(itemsIndex);
		}
		
		return character;
	}

}
