package it.unifi.sam.terreni.weatherSt.model.sensor.units.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.RainMeasure;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.RainUnits;

public class RainMeasureTestDelegate {

	private EntityManager entityManager;
	
	private String uuid;
	
	public void init(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void insertData() {
		RainMeasure rainMeasure = ModelFactory.rainMeasure();
		Sensor sensor = ModelFactory.sensor();
		
		Float value = 2f;
		Long timestamp = 2000L;
		rainMeasure.setValue(value);
		rainMeasure.setTimestamp(timestamp);
		rainMeasure.setSensor(sensor);
		rainMeasure.setUnit(RainUnits.MILLIMETRE);
		
		entityManager.persist(rainMeasure);
		entityManager.persist(sensor);
		
		uuid = rainMeasure.getUuid();

	}

	public void readTest() {
		
		RainMeasure result = entityManager
				.createQuery("from RainMeasure "
						+ "where uuid = :uuid", RainMeasure.class)
				.setParameter("uuid", uuid)
				.getSingleResult();
		
		assertNotNull(result);
		assertNotNull(result.getSensor());
		
		assertEquals(result.getTimestamp(), new Long(2000));
		assertEquals(result.getValue(), new Float(2f));
		assertEquals(result.getUnit(), RainUnits.MILLIMETRE);
		
	}
	
}

