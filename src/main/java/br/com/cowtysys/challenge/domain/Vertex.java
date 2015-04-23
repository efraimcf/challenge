package br.com.cowtysys.challenge.domain;

import java.util.List;


public class Vertex extends DomainModel {
	
	private static final long serialVersionUID = 1880348516695244798L;
	
	private String name;
	
	private List<Edge> neighboors;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Edge> getNeighboors() {
		return neighboors;
	}

	public void setNeighboors(List<Edge> neighboors) {
		this.neighboors = neighboors;
	}
	
	
}
