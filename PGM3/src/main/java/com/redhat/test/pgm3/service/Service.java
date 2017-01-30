package com.redhat.test.pgm3.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

@Stateless
public abstract class Service<T> {

	protected Class<T> type = getType();
	
	@PersistenceContext(unitName = "primary")
	protected EntityManager em;

	public void save(T entidade) {
		em.persist(entidade);
	}

	public void remove(T entidade) {
		em.remove(entidade);
	}

	@SuppressWarnings("unchecked")
	public List<T> all() {
		CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(type));
		return (List<T>) em.createQuery(cq).getResultList();
	}

	public T findById(long id) {
		return em.find(type, id);
	}

	public T update(T entidade) {
		return em.merge(entidade);
	}

	@SuppressWarnings({ "unchecked" })
	private Class<T> getType() {
		
		Class<?> clazz = this.getClass();
		
		while (!clazz.getSuperclass().equals(Service.class)) {
			clazz = clazz.getSuperclass();
		}
		
		ParameterizedType genericType = (ParameterizedType) clazz.getGenericSuperclass();
		return (Class<T>) genericType.getActualTypeArguments()[0];
	}
}
