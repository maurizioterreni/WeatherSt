package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.PressureUnits;

public class PressureMeasureTest {

	private PressureMeasure pressureTest;
	
	@Before
	public void setUp() {
		pressureTest = ModelFactory.pressureMeasure();
	}

	@Test
	public void testBasicProperties() {
		Float value = 2f;
		Long timestamp = 2000L;
		pressureTest.setValue(value);
		pressureTest.setTimestamp(timestamp);
		pressureTest.setSensor(ModelFactory.sensor());
		pressureTest.setUnit(PressureUnits.ATMOSPHERE);
		
		assertEquals(pressureTest.getValue(), value);
		assertEquals(pressureTest.getTimestamp(), timestamp);
		assertEquals(pressureTest.getUnit(), PressureUnits.ATMOSPHERE);
		assertNotNull(pressureTest.getSensor());
	}
	
}