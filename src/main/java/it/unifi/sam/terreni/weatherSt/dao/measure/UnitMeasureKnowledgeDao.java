package it.unifi.sam.terreni.weatherSt.dao.measure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;

public class UnitMeasureKnowledgeDao {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(UnitMeasureKnowledge unitMeasure) { 
		entityManager.persist(unitMeasure);
	}

	public UnitMeasureKnowledge findById(Long unitMeasureId){
		return entityManager.find(UnitMeasureKnowledge.class, unitMeasureId);
	}
	
	public List<UnitMeasureKnowledge> getAllUnitMeasureKnowledge(){
		return entityManager
				.createQuery("select u from UnitMeasureKnowledge u ", UnitMeasureKnowledge.class)
				.getResultList();
	}

	public void delete(UnitMeasureKnowledge unitMeasure) {
		entityManager.remove(unitMeasure);
		entityManager.flush();
	}
}
