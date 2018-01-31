package it.unifi.sam.terreni.weatherSt.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unifi.sam.terreni.weatherSt.model.measure.Value;

public class ValueDao {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(Value value) { 
		entityManager.persist(value);
	}

	public Value findById(Long valueId){
		return entityManager.find(Value.class, valueId);
	}

	public void update(Value value) {
		Value persisted = findById(value.getId());
		persisted.setValue(value.getValue());
		persisted.setUnitMeasureFamily(value.getUnitMeasureFamily());
		entityManager.flush();
	}

	public void delete(Value value) {
		entityManager.remove(value);
		entityManager.flush();
	}

}
