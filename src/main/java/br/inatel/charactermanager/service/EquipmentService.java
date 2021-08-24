package br.inatel.charactermanager.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.inatel.charactermanager.service.models.Armor;
import br.inatel.charactermanager.service.models.EquipmentCategory;
import br.inatel.charactermanager.service.models.Item;
import br.inatel.charactermanager.service.models.Weapon;

@Service
public class EquipmentService {
	
	private RestTemplate restTemplate = new RestTemplate();
	private String baseURL = "https://www.dnd5eapi.co/api";
	
	public List<EquipmentCategory> getWeaponsList() {
		EquipmentCategory weaponsList = restTemplate.getForObject(baseURL + "/equipment-categories/weapon", EquipmentCategory.class);
		
		return Arrays.asList(weaponsList);
	}
	
	public List<EquipmentCategory> getArmorsList() {
		EquipmentCategory armorsList = restTemplate.getForObject(baseURL + "/equipment-categories/armor", EquipmentCategory.class);
		
		return Arrays.asList(armorsList);
	}
	
	public List<EquipmentCategory> getItemsList() {
		EquipmentCategory itemsList = restTemplate.getForObject(baseURL + "/equipment-categories/adventuring-gear", EquipmentCategory.class);
		
		return Arrays.asList(itemsList);
	}
	
	public Weapon getWeapon(String index) {
		Weapon weapon = restTemplate.getForObject(baseURL + "/equipment/" + index, Weapon.class);
		
		return weapon;
	}
	
	public Armor getArmor(String index) {
		Armor armor = restTemplate.getForObject(baseURL + "/equipment/" + index, Armor.class);
		
		return armor;
	}
	
	public Item getItem(String index) {
		Item item = restTemplate.getForObject(baseURL + "/equipment/" + index, Item.class);
		
		return item;
	}

}
