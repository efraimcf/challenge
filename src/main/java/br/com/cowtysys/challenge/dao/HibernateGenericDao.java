package br.com.cowtysys.challenge.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.converters.IntegerConverter;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import br.com.cowtysys.challenge.domain.DomainModel;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class HibernateGenericDao<T extends DomainModel> extends HibernateDaoSupport {

	protected Class<T> persistentClass;  
    
	public HibernateGenericDao(Class<T> persistentClass) {  
		this.persistentClass = (Class<T>) persistentClass; 
     }   
	
	public HibernateGenericDao(){
		
	}
	
	@Autowired
	public void init(SessionFactory factory) {
	    setSessionFactory(factory);
	}
	
	public List<T> findAll() {
        return getCriteria().list();  
    }  
	
	public T findForUpdateById(Long id){
		return (T) findForUpdateByCriterion(Restrictions.eq("id", id));
	}

	public T findById(Long id){
		 return findOneByCriterion(Restrictions.eq("id", id));
	}
	
	public List<T> findByCriterion(List<Criterion> criterions){
    	return getCriteria(criterions).list();
    }
	
	public List<T> findByCriterionAndOrderByAsc(List<Criterion> criterions, String propertyName){
		Criteria criteria = getCriteria(criterions);
		criteria.addOrder(Order.asc(propertyName));
		return criteria.list();
	}
	
	public List<T> findByCriterionAndOrderByDesc(List<Criterion> criterions, String propertyName){
		Criteria criteria = getCriteria(criterions);
		criteria.addOrder(Order.desc(propertyName));
		return criteria.list();
	}

	public T findOneByCriterionWithJoin(List<Criterion> criterions, Criterion criterionJoin, String joinTable){
		Criteria criteria = getCriteria(criterions);
		criteria.createAlias(joinTable, joinTable);
		criteria.add(criterionJoin);
		return (T) criteria.uniqueResult();
	}
	
	public List<T> findByCriterionWithJoin(List<Criterion> criterions, Criterion criterionJoin, String joinTable){
		Criteria criteria = getCriteria(criterions);
		criteria.createAlias(joinTable, joinTable);
		criteria.add(criterionJoin);
		return criteria.list();
	}
	
	public List<T> findByCriterionWithJoinAndOrderByDesc(List<Criterion> criterions, Criterion criterionJoin, String joinTable, String propertyNameToOrderBy){
		Criteria criteria = getCriteria(criterions);
		criteria.createAlias(joinTable, joinTable);
		criteria.add(criterionJoin);
		criteria.addOrder(Order.desc(propertyNameToOrderBy));
		return criteria.list();
	}
	
	public List<T> findByCriterion(Criterion criterion) {
    	return getCriteria().add(criterion).list();
    }
    
	public T findOneByCriterion(List<Criterion> criterions){
    	return (T) getCriteria(criterions).uniqueResult();
    }

	public T findOneByCriterion(Criterion criterion) {
		return (T) getCriteria().add(criterion).uniqueResult();
	}
	
	public T findForUpdateByCriterion(Criterion criterion) {
		Criteria criteria = getCriteria();
		criteria.add(criterion);
		criteria.setLockMode(LockMode.PESSIMISTIC_WRITE);
		return (T) criteria.uniqueResult();
	}
	
	public List<T> findForUpdateByCriterionList(Criterion criterion, int maxResults) {
		Criteria criteria = getCriteria();
		criteria.add(criterion);
		criteria.setMaxResults(maxResults);
		criteria.setLockMode(LockMode.PESSIMISTIC_WRITE);
		return (List<T>) criteria.list();
	}
	
	public List<T> findForUpdateByCriterionList(Criterion criterion) {
		Criteria criteria = getCriteria();
		criteria.add(criterion);
		criteria.setLockMode(LockMode.PESSIMISTIC_WRITE);
		return (List<T>) criteria.list();
	}
	
	public boolean hasEntityByCriterion(List<Criterion> criterions){
    	return countByCriterion(Projections.rowCount(), criterions) > 0;
    }

	public boolean hasEntityByCriterion(Criterion criterion) {
		return countByCriterion(Projections.rowCount(), criterion) > 0;
	}
	
	public int countByCriterion(Projection projection, Criterion criterion) {
		Object uniqueResult = getCriteria(projection, criterion).uniqueResult();
		return uniqueResult == null ? 0 :  new IntegerConverter().convert(Integer.class, uniqueResult);
	}
	
	public int countByCriterion(Projection projection, List<Criterion> criterions){
    	Object uniqueResult = getCriteria(projection, criterions).uniqueResult();
    	return uniqueResult == null ? 0 : new IntegerConverter().convert(Integer.class, uniqueResult);
    }
	
	public int countByCriterion(List<Criterion> criterions){
    	Object uniqueResult = getCriteria(Projections.rowCount(), criterions).uniqueResult();
    	return uniqueResult == null ? 0 : new IntegerConverter().convert(Integer.class, uniqueResult);
    }
	
	public int countByCriterion(Criterion criterion) {
		Object uniqueResult = getCriteria(Projections.rowCount(), criterion).uniqueResult();
		return uniqueResult == null ? 0 : new IntegerConverter().convert(Integer.class, uniqueResult);
	}

	public Long saveAndFlush(T entity) {  
		Session currentSession = currentSession();
		long id = (Long) currentSession.save(entity); 
		currentSession.flush();
        return id;  
    }
	
	public Long saveBatch(T entity) {  
		return (Long) currentSession().save(entity); 
    }
	
	public void update(T entity){
		currentSession().update(entity);
	}
	
	public void updateAndFlush(T entity){
		Session currentSession = currentSession();
		currentSession.update(entity);
		currentSession.flush();
	}
	
	public void flush() {  
		currentSession().flush();
    }
	
    public Criteria getCriteria(Projection projection, List<Criterion> criterions) {
    	Criteria criteria = getCriteria(criterions);
    	criteria.setProjection(projection);
		return criteria;
    }
    
    public Criteria getCriteria(Projection projection, Criterion criterion) {
    	Criteria criteria = getCriteria();
    	criteria.setProjection(projection);
    	criteria.add(criterion);
		return criteria;
    }
    
    protected Criteria getCriteria() {
    	return currentSession().createCriteria(persistentClass);
    }
    
	private Criteria getCriteria(List<Criterion> criterions) {
		Criteria criteria = getCriteria();
    	for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}
		return criteria;
	}

}
