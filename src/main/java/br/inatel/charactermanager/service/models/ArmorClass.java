package br.inatel.charactermanager.service.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArmorClass {
	
	private String base;
	private boolean dex_bonus;
	
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public boolean isDex_bonus() {
		return dex_bonus;
	}
	public void setDex_bonus(boolean dex_bonus) {
		this.dex_bonus = dex_bonus;
	}

}
