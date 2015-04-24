package br.com.cowtysys.challenge.service.register;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.cowtysys.challenge.dao.EdgeDao;
import br.com.cowtysys.challenge.dao.NodeDao;
import br.com.cowtysys.challenge.exception.EdgeAlreadyRegisteredException;
import br.com.cowtysys.challenge.repository.Edge;
import br.com.cowtysys.challenge.repository.Node;

public class GraphRegisterServiceTest {

	private static final String LINE = "A B 10";
	
	private GraphRegisterService graphRegisterService;
	private Node nodeA;
	private Node nodeB;
	
	@Captor
	private ArgumentCaptor<Edge> edgeABCapture;

	@Captor
	private ArgumentCaptor<Edge> edgeBACapture;
	
	@Captor
	private ArgumentCaptor<String> nameCapture;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		
		nodeA = getNodeMock("A");
		nodeB = getNodeMock("B");
		
		graphRegisterService = spy(new GraphRegisterService());		
		doReturn(getNodeDaoMock()).when(graphRegisterService).getNodeDao();
		doReturn(getEdgeDaoMock()).when(graphRegisterService).getEdgeDao();
		doReturn(nodeA).when(graphRegisterService).getNode(eq("A"));
		doReturn(nodeB).when(graphRegisterService).getNode(eq("B"));
	}
	
	@Test
	public void testAnnotationClass() {
		Class<? extends GraphRegisterService> clazz = GraphRegisterService.class;
		Assert.assertTrue(clazz.isAnnotationPresent(Service.class));
	}

	@Test
	public void testAnnotationMethod() throws Exception {
		Class<? extends GraphRegisterService> clazz = GraphRegisterService.class;
		Method method = clazz.getDeclaredMethod("registerGraph", String.class);
		Transactional annotation = method.getAnnotation(Transactional.class);
		
		Assert.assertFalse(annotation.readOnly());
		Assert.assertEquals(Propagation.REQUIRES_NEW, annotation.propagation());
		Assert.assertEquals(Isolation.REPEATABLE_READ, annotation.isolation());
	}
	
	@Test
	public void SuccessSavingLine() throws EdgeAlreadyRegisteredException{
		graphRegisterService.registerGraph(LINE);
		
		verify(nodeA).addAdjacency(edgeABCapture.capture());
		verify(nodeB).addAdjacency(edgeBACapture.capture());
		Edge edgeAB = edgeABCapture.getValue();
		Assert.assertEquals("B", edgeAB.getTarget().getName());
		Edge edgeBA = edgeBACapture.getValue();
		Assert.assertEquals("A", edgeBA.getTarget().getName());
	}
	
	@Test(expected=EdgeAlreadyRegisteredException.class)
	public void ErrorGraphAlreadyRegistered() throws EdgeAlreadyRegisteredException{
		doThrow(EdgeAlreadyRegisteredException.class).when(graphRegisterService).validateEdge(isA(String.class), isA(String.class));
		
		graphRegisterService.registerGraph(LINE);
	}

	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void ErrorInvlidLine() throws EdgeAlreadyRegisteredException{
		graphRegisterService.registerGraph("@#$@#$");
	}

	
	private NodeDao getNodeDaoMock(){
		NodeDao nodeDao = mock(NodeDao.class);
		doNothing().when(nodeDao).save(isA(Node.class));
		doReturn(nodeA).when(nodeDao).findByName(eq("A"));
		doReturn(nodeB).when(nodeDao).findByName(eq("B"));
		
		return nodeDao;
	}
	
	private EdgeDao getEdgeDaoMock(){
		EdgeDao edgeDao = mock(EdgeDao.class);
		doNothing().when(edgeDao).save(isA(Edge.class));
		
		return edgeDao;
	}
	
	private Node getNodeMock(String name){
		Node node = mock(Node.class);
		doReturn(name).when(node).getName();
		
		return node;
	}
}
