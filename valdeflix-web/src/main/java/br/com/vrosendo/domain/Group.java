package br.com.vrosendo.domain;

import java.util.List;

public class Group {
	
	private String name;
	private List<Media> items;
	
	public Group(String name) {
		this.name = name;
	}
	
	public Group(String name, List<Media> items) {
		this.name = name;
		this.items = items;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Media> getItems() {
		return items;
	}
	public void setItems(List<Media> items) {
		this.items = items;
	}
	
	
	
}
