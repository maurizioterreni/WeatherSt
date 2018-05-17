package it.unifi.sam.terreni.weatherSt.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import it.unifi.sam.terreni.weatherSt.model.converter.ConversionFactor;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;

public class ConversionFactorDao {
		@PersistenceContext
		private EntityManager entityManager;
		

		public void save(ConversionFactor conversionFactor) {
			entityManager.persist(conversionFactor);
		}
		
		public ConversionFactor findById(Long conversionFactorId){
			return entityManager.find(ConversionFactor.class, conversionFactorId);
		}
		
		public void delete(ConversionFactor ConversionFactor) {
			entityManager.remove(ConversionFactor);
			entityManager.flush();
		}
		
		public List<ConversionFactor> getConversionFactorByFromUnitId(UnitMeasureKnowledge fromUnit){
			try {
				return entityManager
						.createQuery("from ConversionFactor c where "
								+ "c.fromUnitMeasure = :fromUnit", ConversionFactor.class)
						.setParameter("fromUnit", fromUnit)
						.getResultList();
			}catch (NoResultException nre){
				return new ArrayList<>();
			}
		}
		
}