package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.HumidityUnits;

public class HumidityMeasureTest {

	private HumidityMeasure humidityTest;
	
	@Before
	public void setUp() {
		humidityTest = ModelFactory.humidityMeasure();
	}

	@Test
	public void testBasicProperties() {
		Float value = 2f;
		Long timestamp = 2000L;
		humidityTest.setValue(value);
		humidityTest.setTimestamp(timestamp);
		humidityTest.setSensor(ModelFactory.sensor());
		humidityTest.setUnits(HumidityUnits.PERCENTAGE);
		
		assertEquals(humidityTest.getValue(), value);
		assertEquals(humidityTest.getTimestamp(), timestamp);
		assertEquals(humidityTest.getUnit(), HumidityUnits.PERCENTAGE);
		assertNotNull(humidityTest.getSensor());
	}
	
}