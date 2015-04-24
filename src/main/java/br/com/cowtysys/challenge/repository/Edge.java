package br.com.cowtysys.challenge.repository;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity(name = "edges")
public class Edge extends DomainModel{
	
	private static final long serialVersionUID = -3548966741868380870L;
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Node target;

	@Column(nullable = false)
    private double weight;

	public Edge(){}
	
	public Edge(Node target, Double weight) {
		setTarget(target);
		setWeight(weight);
	}

	public Node getTarget() {
		return target;
	}

	public void setTarget(Node target) {
		this.target = target;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
