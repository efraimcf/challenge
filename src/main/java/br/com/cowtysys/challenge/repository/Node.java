package br.com.cowtysys.challenge.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity(name = "nodes")
public class Node extends DomainModel implements Comparable<Node> {
	
	private static final long serialVersionUID = 1880348516695244798L;

	@Column(nullable = false, unique = true)
	private String name;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "source_id")
    private List<Edge> adjacencies;
	
	@Transient
    private double minDistance = Double.POSITIVE_INFINITY;
	
	@Transient
    private Node previousNode;
	
	public Node(){}
	
	public Node(String name) {
		setName(name);
	}

	@Override
	public int compareTo(Node o) {
		return Double.compare(minDistance, o.minDistance);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Edge> getAdjacencies() {
		return adjacencies;
	}

	public void setAdjacencies(List<Edge> adjacencies) {
		this.adjacencies = adjacencies;
	}

	public double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}

	public Node getPreviousNode() {
		return previousNode;
	}

	public void setPreviousNode(Node previous) {
		this.previousNode = previous;
	}

	public void addAdjacency(Edge adjacency) {
		if (adjacencies == null){
			adjacencies = new ArrayList<Edge>();
		}
		adjacencies.add(adjacency);
		
	}

	@Override
	public String toString() {
		return "Node [name=" + name + "]";
	}

}
