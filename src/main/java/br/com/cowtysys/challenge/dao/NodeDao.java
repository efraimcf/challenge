package br.com.cowtysys.challenge.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.cowtysys.challenge.repository.Node;

@Repository
public class NodeDao extends HibernateGenericDao<Node>{

	public NodeDao(){
		super(Node.class);
	}

	public void save(Node node) {
		if (findByName(node.getName()) == null){
			saveAndFlush(node);
		}
		node = findByName(node.getName());
	}
	
	public Node findByName(String name){
		return findOneByCriterion(Restrictions.eq("name", name));
	}
}
