package it.unifi.sam.terreni.weatherSt.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasure;

public class UnitMeasureDao {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(UnitMeasure unitMeasure) { 
		entityManager.persist(unitMeasure);
	}

	public UnitMeasure findById(Long unitMeasureId){
		return entityManager.find(UnitMeasure.class, unitMeasureId);
	}

	public void delete(UnitMeasure unitMeasure) {
		entityManager.remove(unitMeasure);
		entityManager.flush();
	}
}
