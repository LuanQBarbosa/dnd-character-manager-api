package br.inatel.charactermanager.service.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Equipment {
	
	private String index;
	private String name;
	private String url;
	
	public Equipment() {}
	
	public Equipment(String index, String name, String url) {
		this.index = index;
		this.name = name;
		this.url = url;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
