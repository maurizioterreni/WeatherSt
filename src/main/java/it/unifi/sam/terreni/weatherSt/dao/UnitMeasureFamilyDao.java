package it.unifi.sam.terreni.weatherSt.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureFamily;

public class UnitMeasureFamilyDao {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(UnitMeasureFamily unitMeasureFamily) { 
		entityManager.persist(unitMeasureFamily);
	}

	public UnitMeasureFamily findById(Long unitMeasureFamilyId){
		return entityManager.find(UnitMeasureFamily.class, unitMeasureFamilyId);
	}

	public void delete(UnitMeasureFamily unitMeasureFamily) {
		entityManager.remove(unitMeasureFamily);
		entityManager.flush();
	}
}
