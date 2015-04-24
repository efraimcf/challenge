package br.com.cowtysys.challenge.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.cowtysys.challenge.domain.DeliveryCostResponse;
import br.com.cowtysys.challenge.exception.DestinationUnreachableException;
import br.com.cowtysys.challenge.exception.InvalidParameterValueException;
import br.com.cowtysys.challenge.exception.NodeNotFoundException;
import br.com.cowtysys.challenge.service.file.GraphFileParserService;
import br.com.cowtysys.challenge.service.pathfinder.PathFinderService;

@Component
public class ChallengeFacade {

	@Autowired private GraphFileParserService graphFileParserService;
	@Autowired private PathFinderService pathFinderService; 
	
	@Async
	public void parseAndSaveFileData(MultipartFile file){
		graphFileParserService.parseAndSaveData(file);
	}
	
	public DeliveryCostResponse getShortestPath(String source, 
			String target, 
			double autonomy, 
			double gasCost) throws DestinationUnreachableException, NodeNotFoundException, InvalidParameterValueException {
		return pathFinderService.getShortestPath(source, target, autonomy, gasCost);
	}
}
