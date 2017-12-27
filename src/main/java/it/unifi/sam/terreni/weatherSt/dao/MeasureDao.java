package it.unifi.sam.terreni.weatherSt.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unifi.sam.terreni.weatherSt.model.sensor.Measure;

public class MeasureDao {
	@PersistenceContext
	private EntityManager entityManager;
	

	public void save(Measure measure) {
		entityManager.persist(measure);
	}
	
	public Measure findById(Long measureId){
		return entityManager.find(Measure.class, measureId);
	}
	
	public void update(Measure measure) {
		Measure persisted = findById(measure.getId());
		persisted.setTimestamp(measure.getTimestamp());
		persisted.setValue(measure.getValue());
		persisted.setUnit(measure.getUnit());
		entityManager.flush();
	}
	
	public void delete(Measure measure) {
		entityManager.remove(measure);
		entityManager.flush();
	}
}
