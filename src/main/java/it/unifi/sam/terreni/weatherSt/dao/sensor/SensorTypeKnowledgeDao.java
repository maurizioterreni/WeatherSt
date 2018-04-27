package it.unifi.sam.terreni.weatherSt.dao.sensor;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unifi.sam.terreni.weatherSt.model.sensor.SensorTypeKnowledge;

public class SensorTypeKnowledgeDao {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(SensorTypeKnowledge sensorTypeKnowledge) { 
		entityManager.persist(sensorTypeKnowledge);
	}

	public SensorTypeKnowledge findById(Long sensorTypeKnowledgeId){
		return entityManager.find(SensorTypeKnowledge.class, sensorTypeKnowledgeId);
	}
	
	public List<SensorTypeKnowledge> getAllSensorType(){
		return entityManager.createQuery("from SensorTypeKnowledge s ", SensorTypeKnowledge.class)
				.getResultList();
	}

	public void delete(SensorTypeKnowledge sensorTypeKnowledge) {
		entityManager.remove(sensorTypeKnowledge);
		entityManager.flush();
	}
}
