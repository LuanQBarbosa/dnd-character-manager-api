package br.inatel.charactermanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

	@RequestMapping("/groups")
	public List<GroupDto> list() {
		
	}
	
}
