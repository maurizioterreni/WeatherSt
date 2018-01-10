package it.unifi.sam.terreni.weatherSt.model.sensor.units.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.UVMeasure;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.UVUnits;

public class UVMeasureTestDelegate {

	private EntityManager entityManager;
	
	private String uuid;
	
	public void init(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void insertData() {
		UVMeasure uvMeasure = ModelFactory.uvMeasure();
		Sensor sensor = ModelFactory.sensor();
		
		Float value = 2f;
		Long timestamp = 2000L;
		uvMeasure.setValue(value);
		uvMeasure.setTimestamp(timestamp);
		uvMeasure.setSensor(sensor);
		uvMeasure.setUnit(UVUnits.UV);
		
		entityManager.persist(uvMeasure);
		entityManager.persist(sensor);
		
		uuid = uvMeasure.getUuid();

	}

	public void readTest() {
		
		UVMeasure result = entityManager
				.createQuery("from UVMeasure "
						+ "where uuid = :uuid", UVMeasure.class)
				.setParameter("uuid", uuid)
				.getSingleResult();
		
		assertNotNull(result);
		assertNotNull(result.getSensor());
		
		assertEquals(result.getTimestamp(), new Long(2000));
		assertEquals(result.getValue(), new Float(2f));
		assertEquals(result.getUnit(), UVUnits.UV);
		
	}
	
}
