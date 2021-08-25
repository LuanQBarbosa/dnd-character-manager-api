package br.inatel.charactermanager.controller.form;

import java.util.List;
import java.util.Optional;

import br.inatel.charactermanager.controller.repository.CharacterRepository;
import br.inatel.charactermanager.exception.InvalidPropertyException;
import br.inatel.charactermanager.model.Character;
import br.inatel.charactermanager.service.EquipmentService;
import br.inatel.charactermanager.service.dto.Equipment;

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
	
	public Character update(Long characterId, CharacterRepository characterRepository, EquipmentService equipmentService) throws InvalidPropertyException {
		Optional<Character> optional = characterRepository.findById(characterId);
		if (!optional.isPresent()) {
			throw new InvalidPropertyException("Could not find a Character with specified Id");
		}
		
		Character character = optional.get();
		if (weaponIndex != null && weaponIndex != "") {
			List<Equipment> weapons = equipmentService.getWeaponsList();
			boolean contains = weapons.stream().anyMatch(x -> x.getIndex().equals(weaponIndex));
			if (!contains) {
				throw new InvalidPropertyException("Invalid weapon index");
			}
			
			List<String> weaponsIndex = character.getWeaponsIndex();
			weaponsIndex.add(weaponIndex);
			character.setWeaponsIndex(weaponsIndex);
		}
		
		if (armorIndex != null && armorIndex != "") {
			List<Equipment> armors = equipmentService.getArmorsList();
			boolean contains = armors.stream().anyMatch(x -> x.getIndex().equals(armorIndex));
			if (!contains) {
				throw new InvalidPropertyException("Invalid armor index");
			}
			
			List<String> armorsIndex = character.getArmorsIndex();
			armorsIndex.add(armorIndex);
			character.setArmorsIndex(armorsIndex);
		}
		
		if (itemIndex != null && itemIndex != "") {
			List<Equipment> items = equipmentService.getItemsList();
			boolean contains = items.stream().anyMatch(x -> x.getIndex().equals(itemIndex));
			if (!contains) {
				throw new InvalidPropertyException("Invalid item index");
			}
			
			List<String> itemsIndex = character.getItemsIndex();
			itemsIndex.add(itemIndex);
			character.setItemsIndex(itemsIndex);
		}
		
		return character;
	}

}
