package br.inatel.charactermanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.charactermanager.config.validation.ErrorFormDto;
import br.inatel.charactermanager.controller.repository.CharacterRepository;
import br.inatel.charactermanager.controller.repository.GameRepository;
import br.inatel.charactermanager.controller.repository.UserRepository;
import br.inatel.charactermanager.service.EquipmentService;
import br.inatel.charactermanager.service.models.Armor;
import br.inatel.charactermanager.service.models.Item;
import br.inatel.charactermanager.service.models.Weapon;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {
	
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CharacterRepository characterRepository;
	@Autowired
	private EquipmentService equipmentService;
	
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