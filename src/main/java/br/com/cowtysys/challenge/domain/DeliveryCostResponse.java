package br.com.cowtysys.challenge.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.cowtysys.challenge.repository.Node;

public class DeliveryCostResponse {

	private double cost;
	
	private List<String> path;

	public DeliveryCostResponse(){}
	
	public DeliveryCostResponse(double cost, List<Node> nodes){
		setCost(cost);
		setPathFromNodes(nodes);
	}
	
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public List<String> getPath() {
		return path;
	}

	public void setPath(List<String> path) {
		this.path = path;
	}
	
	public void setPathFromNodes(List<Node> nodes) {
		List<String> path = new ArrayList<String>();
		for(Node node : nodes){
			path.add(node.getName());
		}
		this.path = path;
	}

}
