package br.inatel.charactermanager.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Damage {
	
	private String damage_dice;

	public String getDamage_dice() {
		return damage_dice;
	}

	public void setDamage_dice(String damage_dice) {
		this.damage_dice = damage_dice;
	}

}
