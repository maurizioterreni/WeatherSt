package it.unifi.sam.terreni.weatherSt.model.sensor.units.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.HumidityMeasure;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.HumidityUnits;

public class HumidityMeasureTestDelegate {

	private EntityManager entityManager;
	
	private String uuid;
	
	public void init(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void insertData() {
		HumidityMeasure humidityMeasure = ModelFactory.humidityMeasure();
		Sensor sensor = ModelFactory.sensor();
		
		Float value = 2f;
		Long timestamp = 2000L;
		humidityMeasure.setValue(value);
		humidityMeasure.setTimestamp(timestamp);
		humidityMeasure.setSensor(sensor);
		humidityMeasure.setUnits(HumidityUnits.PERCENTAGE);
		
		entityManager.persist(humidityMeasure);
		entityManager.persist(sensor);
		
		uuid = humidityMeasure.getUuid();

	}

	public void readTest() {
		
		HumidityMeasure result = entityManager
				.createQuery("from HumidityMeasure "
						+ "where uuid = :uuid", HumidityMeasure.class)
				.setParameter("uuid", uuid)
				.getSingleResult();
		
		assertNotNull(result);
		assertNotNull(result.getSensor());
		
		assertEquals(result.getTimestamp(), new Long(2000));
		assertEquals(result.getValue(), new Float(2f));
		assertEquals(result.getUnit(), HumidityUnits.PERCENTAGE);
		
	}
	
}
