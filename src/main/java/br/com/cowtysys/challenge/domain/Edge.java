package br.com.cowtysys.challenge.domain;

import org.springframework.stereotype.Repository;

@Repository
public class Edge {
	
	private int weight;
	
	private Vertex origin;
	
	private Vertex destity;
	
	private boolean visited;

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Vertex getOrigin() {
		return origin;
	}

	public void setOrigin(Vertex origin) {
		this.origin = origin;
	}

	public Vertex getDestity() {
		return destity;
	}

	public void setDestity(Vertex destity) {
		this.destity = destity;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
}
