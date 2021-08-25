package br.inatel.charactermanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.inatel.charactermanager.service.dto.Armor;
import br.inatel.charactermanager.service.dto.Equipment;
import br.inatel.charactermanager.service.dto.EquipmentCategory;
import br.inatel.charactermanager.service.dto.Item;
import br.inatel.charactermanager.service.dto.Weapon;

@Service
public class EquipmentService {
	
	private RestTemplate restTemplate = new RestTemplate();
	private String baseURL = "https://www.dnd5eapi.co/api";
	
	@Cacheable(value = "equipmentList")
	public List<Equipment> getEquipmentList(String type) {
		EquipmentCategory equipmentCategory = restTemplate.getForObject(baseURL + "/equipment-categories/" + type, EquipmentCategory.class);
		
		List<Equipment> equipmentsList = equipmentCategory.getEquipment().stream()
				.filter(e -> e.getUrl().split("/")[2].equals("equipment"))
				.collect(Collectors.toList());
		
		return equipmentsList;
	}
	
	@Cacheable(value="weaponsList")
	public List<Equipment> getWeaponsList() {		
		List<Equipment> weaponsList = getEquipmentList("weapon");
		
		return weaponsList;
	}
	
	@Cacheable(value="armorsList")
	public List<Equipment> getArmorsList() {		
		List<Equipment> armorsList = getEquipmentList("armor");
		
		return armorsList;
	}
	
	@Cacheable(value="itemsList")
	public List<Equipment> getItemsList() {
		List<Equipment> itemsList = getEquipmentList("adventuring-gear");
		
		return itemsList;
	}
	
	public List<Weapon> searchWeaponByName(String name) {
		List<Equipment> equipmentsList = getWeaponsList();
		
		equipmentsList = equipmentsList.stream()
			.filter(e -> e.getName().toLowerCase().contains(name.toLowerCase()))
			.collect(Collectors.toList());
		
		List<Weapon> weaponsList = equipmentsList.stream()
				.map(e -> getWeapon(e.getIndex()))
				.collect(Collectors.toList());
		
		return weaponsList;
	}
	
	public List<Armor> searchArmorByName(String name) {
		List<Equipment> equipmentsList = getArmorsList();
		
		equipmentsList = equipmentsList.stream()
			.filter(e -> e.getName().toLowerCase().contains(name.toLowerCase()))
			.collect(Collectors.toList());
		
		List<Armor> armorsList = equipmentsList.stream()
				.map(e -> getArmor(e.getIndex()))
				.collect(Collectors.toList());
		
		return armorsList;
	}
	
	public List<Item> searchItemByName(String name) {
		List<Equipment> equipmentsList = getItemsList();
		
		equipmentsList = equipmentsList.stream()
			.filter(e -> e.getName().toLowerCase().contains(name.toLowerCase()))
			.collect(Collectors.toList());
		
		List<Item> itemsList = equipmentsList.stream()
				.map(e -> getItem(e.getIndex()))
				.collect(Collectors.toList());
		
		return itemsList;
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
