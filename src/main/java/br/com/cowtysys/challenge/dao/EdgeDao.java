package br.com.cowtysys.challenge.dao;

import org.springframework.stereotype.Repository;

import br.com.cowtysys.challenge.repository.Edge;

@Repository
public class EdgeDao extends HibernateGenericDao<Edge>{

	public EdgeDao(){
		super(Edge.class);
	}

	public void save(Edge edge) {
		saveBatch(edge);
	}
	
}
