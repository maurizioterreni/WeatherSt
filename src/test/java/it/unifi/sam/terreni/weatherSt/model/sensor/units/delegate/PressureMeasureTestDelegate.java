package it.unifi.sam.terreni.weatherSt.model.sensor.units.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.PressureMeasure;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.PressureUnits;

public class PressureMeasureTestDelegate {

	private EntityManager entityManager;
	
	private String uuid;
	
	public void init(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void insertData() {
		PressureMeasure pressureMeasure = ModelFactory.pressureMeasure();
		Sensor sensor = ModelFactory.sensor();
		
		Float value = 2f;
		Long timestamp = 2000L;
		pressureMeasure.setValue(value);
		pressureMeasure.setTimestamp(timestamp);
		pressureMeasure.setSensor(sensor);
		pressureMeasure.setUnit(PressureUnits.ATMOSPHERE);
		
		entityManager.persist(pressureMeasure);
		entityManager.persist(sensor);
		
		uuid = pressureMeasure.getUuid();

	}

	public void readTest() {
		
		PressureMeasure result = entityManager
				.createQuery("from PressureMeasure "
						+ "where uuid = :uuid", PressureMeasure.class)
				.setParameter("uuid", uuid)
				.getSingleResult();
		
		assertNotNull(result);
		assertNotNull(result.getSensor());
		
		assertEquals(result.getTimestamp(), new Long(2000));
		assertEquals(result.getValue(), new Float(2f));
		assertEquals(result.getUnit(), PressureUnits.ATMOSPHERE);
		
	}
	
}
