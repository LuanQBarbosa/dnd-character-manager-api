package br.inatel.charactermanager.controller.dto;

import br.inatel.charactermanager.model.Item;

public class ItemDto {
	
	private Long id;
	private String name;
	private int cost;
	private int weight;
	
	public ItemDto(Item item) {
		this.id = item.getId();
		this.name = item.getName();
		this.cost = item.getCost();
		this.weight = item.getWeight();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

	public int getWeight() {
		return weight;
	}

}
