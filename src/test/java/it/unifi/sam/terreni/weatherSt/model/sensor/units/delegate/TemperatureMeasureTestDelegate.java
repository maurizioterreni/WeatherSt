package it.unifi.sam.terreni.weatherSt.model.sensor.units.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.TemperatureMeasure;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.TemperatureUnits;

public class TemperatureMeasureTestDelegate  {

	private EntityManager entityManager;
	
	private String uuid;
	
	public void init(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void insertData() {
		TemperatureMeasure temperatureMeasure = ModelFactory.temperatureMeasure();
		Sensor sensor = ModelFactory.sensor();
		
		Float value = 2f;
		Long timestamp = 2000L;
		temperatureMeasure.setValue(value);
		temperatureMeasure.setTimestamp(timestamp);
		temperatureMeasure.setSensor(sensor);
		temperatureMeasure.setUnit(TemperatureUnits.CELSIUS);
		
		entityManager.persist(temperatureMeasure);
		entityManager.persist(sensor);
		
		uuid = temperatureMeasure.getUuid();

	}

	public void readTest() {
		
		TemperatureMeasure result = entityManager
				.createQuery("from TemperatureMeasure "
						+ "where uuid = :uuid", TemperatureMeasure.class)
				.setParameter("uuid", uuid)
				.getSingleResult();
		
		assertNotNull(result);
		assertNotNull(result.getSensor());
		
		assertEquals(result.getTimestamp(), new Long(2000));
		assertEquals(result.getValue(), new Float(2f));
		assertEquals(result.getUnit(), TemperatureUnits.CELSIUS);
		
	}
	
}
