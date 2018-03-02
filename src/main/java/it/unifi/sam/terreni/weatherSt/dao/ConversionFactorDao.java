package it.unifi.sam.terreni.weatherSt.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unifi.sam.terreni.weatherSt.model.converter.ConversionFactor;

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
		
}