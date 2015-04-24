package br.com.cowtysys.challenge.service.pathfinder;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

import br.com.cowtysys.challenge.dao.NodeDao;
import br.com.cowtysys.challenge.domain.DeliveryCostResponse;
import br.com.cowtysys.challenge.exception.DestinationUnreachableException;
import br.com.cowtysys.challenge.exception.InvalidParameterValueException;
import br.com.cowtysys.challenge.exception.NodeNotFoundException;
import br.com.cowtysys.challenge.repository.Edge;
import br.com.cowtysys.challenge.repository.Node;

public class PathFinderServiceTest {

	private static final String SOURCE_NAME = "A";
	private static final String TARGET_NAME = "C";
	private static final double AUTONOMY = 10;
	private static final double GAS_COST = 2;
	
	private PathFinderService pathFinderService;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		
		pathFinderService = spy(new PathFinderService());
		doReturn(getNodeDaoMock()).when(pathFinderService).getNodeDao();
	}
	
	@Test
	public void testAnnotationClass() {
		Class<? extends PathFinderService> clazz = PathFinderService.class;
		Assert.assertTrue(clazz.isAnnotationPresent(Service.class));
	}

	@Test
	public void SuccessFindingRoute() throws DestinationUnreachableException, NodeNotFoundException, InvalidParameterValueException{
		DeliveryCostResponse response = pathFinderService.getShortestPath(SOURCE_NAME, TARGET_NAME, AUTONOMY, GAS_COST);
		
		Assert.assertEquals(4, response.getCost(), 0.001);
		Assert.assertEquals(3, response.getPath().size());
		Assert.assertEquals("A", response.getPath().get(0));
		Assert.assertEquals("B", response.getPath().get(1));
		Assert.assertEquals("C", response.getPath().get(2));
	}
	
	@Test(expected=NodeNotFoundException.class)
	public void ErrorNullSource() throws DestinationUnreachableException, NodeNotFoundException, InvalidParameterValueException{
		pathFinderService.getShortestPath(null, TARGET_NAME, AUTONOMY, GAS_COST);
	}

	@Test(expected=DestinationUnreachableException.class)
	public void ErrorNullTarget() throws DestinationUnreachableException, NodeNotFoundException, InvalidParameterValueException{
		pathFinderService.getShortestPath(SOURCE_NAME, "D", AUTONOMY, GAS_COST);
	}
	
	private NodeDao getNodeDaoMock(){
		NodeDao nodeDao = mock(NodeDao.class);
		doReturn(getNode()).when(nodeDao).findByName(isA(String.class));
		
		return nodeDao;
	}
	
	private Node getNode(){
		Node nodeA = new Node(SOURCE_NAME);
		Node nodeB = new Node("B");
		Node nodeC = new Node(TARGET_NAME);
		nodeA.addAdjacency(getEdge(nodeB, 10));
		nodeB.addAdjacency(getEdge(nodeC, 10));
		nodeB.addAdjacency(getEdge(nodeA, 10));
		nodeC.addAdjacency(getEdge(nodeB, 10));
		
		return nodeA;
	}
	
	private Edge getEdge(Node node, double weight){
		Edge edge = new Edge(node, weight);
		
		return edge;
	}
}
