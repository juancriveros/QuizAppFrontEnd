package fr.epita.quiz.services.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

/***
 * Generic data access object class
 * Class that handle the generic operations of all the tables in the database
 * @author juanc
 *
 * @param <T>
 * @param <ID>
 */
public abstract class GenericDAO<T,ID> {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional(value = TxType.REQUIRED)
	public void create(T newEntity) {
		em.persist(newEntity);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void update(T updatedEntity) {
		em.merge(updatedEntity);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void delete(T deletedEntity) {
		em.remove(em.contains(deletedEntity) ? deletedEntity : em.merge(deletedEntity));
	}
	
	public abstract String getQuery();
	public abstract void setParameters(Map<String, Object> parameters, T criteria);
	
	public List<T> search(T criteria){
		Query searchQuery = em.createQuery(getQuery());
		Map<String, Object> parameters = new LinkedHashMap<>();
		setParameters(parameters, criteria);
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			searchQuery.setParameter(entry.getKey(), entry.getValue());
		}
		return searchQuery.getResultList();
	};
	
	public abstract Class<T> getEntityClass();
	
	public T getById(ID id) {
		return (T) em.find(getEntityClass(), id);
	}
	
		
	
}