package it.unifi.sam.terreni.weatherSt.dao.sensor;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unifi.sam.terreni.weatherSt.model.sensor.SensorTemplate;

public class SensorTemplateDao {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(SensorTemplate sensorTemplate) { 
		entityManager.persist(sensorTemplate);
	}

	public SensorTemplate findById(Long sensorTemplateId){
		return entityManager.find(SensorTemplate.class, sensorTemplateId);
	}
	
	public List<SensorTemplate> getAllSensorTemplate(){
		return entityManager.createQuery("from SensorTemplate s ", SensorTemplate.class)
				.getResultList();
	}

	public void delete(SensorTemplate sensorTemplate) {
		entityManager.remove(sensorTemplate);
		entityManager.flush();
	}
}
