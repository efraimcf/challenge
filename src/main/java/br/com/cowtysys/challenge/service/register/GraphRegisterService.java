package br.com.cowtysys.challenge.service.register;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.cowtysys.challenge.dao.EdgeDao;
import br.com.cowtysys.challenge.dao.NodeDao;
import br.com.cowtysys.challenge.exception.EdgeAlreadyRegisteredException;
import br.com.cowtysys.challenge.repository.Edge;
import br.com.cowtysys.challenge.repository.Node;

@Service
public class GraphRegisterService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GraphRegisterService.class);
	private static final String SPACE = " ";

	@Autowired private NodeDao nodeDao;
	@Autowired private EdgeDao edgeDao;
	
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW, isolation=Isolation.REPEATABLE_READ)
	public void registerGraph(String line){
		try{
			LOGGER.trace("saving line item...");
			LOGGER.info("Line Data: |" + line + "|");
			
			String[] data = line.split(Pattern.quote(SPACE));
			
			Node source = new Node(data[0]);
			Node target = new Node(data[1]);
			
			validateEdge(source.getName(), target.getName());

			Double weight = Double.valueOf(data[2]);

			getNodeDao().save(source);
			getNodeDao().save(target);

			source = getNodeDao().findByName(source.getName());
			target = getNodeDao().findByName(target.getName());
			
			Edge sourceToTarget = new Edge(target, weight);
			Edge targetToSource = new Edge(source, weight);

			getEdgeDao().save(sourceToTarget);
			getEdgeDao().save(targetToSource);
			
			source.addAdjacency(sourceToTarget);
			target.addAdjacency(targetToSource);
			
		}catch(Exception e){
			LOGGER.error("Error on save line data.", e);
		}

	}

	private void validateEdge(String sourceName, String targetName) throws EdgeAlreadyRegisteredException {
		Node node = getNodeDao().findByName(sourceName);
		if (node != null){
			for (Edge edge : node.getAdjacencies()){
				if (edge.getTarget().getName().equalsIgnoreCase(targetName)){
					String message = "Edge " + sourceName + " to " + targetName + " is already registered.";
					throw new EdgeAlreadyRegisteredException(message);
				}
			}
		}
	}
	
	public NodeDao getNodeDao() {
		return nodeDao;
	}
	
	public EdgeDao getEdgeDao() {
		return edgeDao;
	}
}
