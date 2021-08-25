package br.inatel.charactermanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.charactermanager.config.validation.ErrorFormDto;
import br.inatel.charactermanager.service.EquipmentService;
import br.inatel.charactermanager.service.dto.Armor;
import br.inatel.charactermanager.service.dto.Item;
import br.inatel.charactermanager.service.dto.Weapon;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {
	
	@Autowired
	private EquipmentService equipmentService;
	
	@GetMapping("/weapon/{weaponId}")
	public ResponseEntity<?> getWeaponById(@PathVariable String weaponId) {
		Weapon weapon;
		try {
			weapon = equipmentService.getWeapon(weaponId);			
		} catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorFormDto("weaponId", "Invalid weapon id"));
		}
		return ResponseEntity.ok().body(weapon);
	}
	
	@GetMapping("/armor/{armorId}")
	public ResponseEntity<?> getArmorById(@PathVariable String armorId) {
		Armor armor;
		try {
			armor = equipmentService.getArmor(armorId);			
		} catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorFormDto("armorId", "Invalid armor id"));
		}
		return ResponseEntity.ok().body(armor);
	}
	
	@GetMapping("/item/{itemId}")
	public ResponseEntity<?> getItemById(@PathVariable String itemId) {
		Item item;
		try {
			item = equipmentService.getItem(itemId);			
		} catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorFormDto("itemId", "Invalid item id"));
		}
		return ResponseEntity.ok().body(item);
	}
	
	@GetMapping("/weapon/search")
	public ResponseEntity<?> searchWeaponByName(@RequestParam(required = true, name = "name") String name) {
		List<Weapon> weaponsList = equipmentService.searchWeaponByName(name);
		
		if (weaponsList.size() > 0) {
			return ResponseEntity.ok().body(weaponsList);
		}
		
		return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(new ErrorFormDto("", "Weapon with given name not found"));
	}
	
	@GetMapping("/armor/search")
	public ResponseEntity<?> searchArmorByName(@RequestParam(required = true, name = "name") String name) {
		List<Armor> armorsList = equipmentService.searchArmorByName(name);
		
		if (armorsList.size() > 0) {
			return ResponseEntity.ok().body(armorsList);
		}
		
		return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(new ErrorFormDto("", "Armor with given name not found"));
	}
	
	@GetMapping("/item/search")
	public ResponseEntity<?> searchItemByName(@RequestParam(required = true, name = "name") String name) {
		List<Item> itemsList = equipmentService.searchItemByName(name);
		
		if (itemsList.size() > 0) {
			return ResponseEntity.ok().body(itemsList);
		}
		
		return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(new ErrorFormDto("", "Item with given name not found"));
	}

}
