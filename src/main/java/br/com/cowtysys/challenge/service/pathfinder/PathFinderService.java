package br.com.cowtysys.challenge.service.pathfinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cowtysys.challenge.dao.NodeDao;
import br.com.cowtysys.challenge.domain.DeliveryCostResponse;
import br.com.cowtysys.challenge.exception.DestinationUnreachableException;
import br.com.cowtysys.challenge.exception.InvalidParameterValueException;
import br.com.cowtysys.challenge.exception.NodeNotFoundException;
import br.com.cowtysys.challenge.repository.Edge;
import br.com.cowtysys.challenge.repository.Node;

@Service
public class PathFinderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PathFinderService.class);
	
	@Autowired private NodeDao nodeDao;
	
	public DeliveryCostResponse getShortestPath(String sourceName, String targetName, double autonomy, double gasCost) throws DestinationUnreachableException, NodeNotFoundException, InvalidParameterValueException{
		LOGGER.trace("getting shortestPath");
		
		validateAutonomyAndCost(autonomy, gasCost);
		Node source = validateNode(sourceName);
		validateNode(targetName);
		
		Node target = calculatePath(source, targetName);

		double cost = computeCost(target.getMinDistance(), autonomy, gasCost);
		List<Node> nodes = getShortestPathList(target);
		DeliveryCostResponse deliveryCostResponse = new DeliveryCostResponse(cost, nodes);

		return deliveryCostResponse;
	}

	public Node calculatePath(Node source, String targetName) throws DestinationUnreachableException{
		source.setMinDistance(0);
		PriorityQueue<Node> nodes = new PriorityQueue<Node>();
		nodes.add(source);
		Node target = null;
		
		while (!nodes.isEmpty()) {
			Node u = nodes.poll();
			for (Edge e : u.getAdjacencies()){
				Node v = e.getTarget();
				double weight = e.getWeight();
				double totalDistance = u.getMinDistance() + weight;
				if (totalDistance < v.getMinDistance()) {
					nodes.remove(v);
					v.setMinDistance(totalDistance);
					v.setPreviousNode(u);
					nodes.add(v);
					if (targetName.equalsIgnoreCase(v.getName())){
						target = v;
					}
				}
			}
		}
		
		if (target == null){
			String message = "Destination unreachable: (Source: " + source.getName() + "; target: " + targetName + ")";
			throw new DestinationUnreachableException(message);
		}
		
		return target;
	}
	
	private List<Node> getShortestPathList(Node target){
		List<Node> path = new ArrayList<Node>();
		for (Node node = target; node != null; node = node.getPreviousNode()){
			path.add(node);
		}
		Collections.reverse(path);
		
		return path;
	}

	private void validateAutonomyAndCost(double autonomy, double gasCost) throws InvalidParameterValueException {
		if (autonomy <=0){
			throw new InvalidParameterValueException("Autonomy must be greater than 0.");
		}
		if (gasCost < 0){
			throw new InvalidParameterValueException("Gas Cost cannot be negative.");
		}
	}

	private Node validateNode(String nodeName) throws NodeNotFoundException {
		Node node = getNodeDao().findByName(nodeName);
		
		if (node == null){
			throw new NodeNotFoundException("Node not found in database: " + nodeName);
		}
		
		return node;
	}
	
	public double computeCost(double distance, double autonomy, double gasCost){
		return (distance / autonomy * gasCost);
	}
	
	public NodeDao getNodeDao() {
		return nodeDao;
	}
}
