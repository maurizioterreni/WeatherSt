package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.RainUnits;

public class RainMeasureTest {

	private RainMeasure rainTest;
	
	@Before
	public void setUp() {
		rainTest = ModelFactory.rainMeasure();
	}

	@Test
	public void testBasicProperties() {
		Float value = 2f;
		Long timestamp = 2000L;
		rainTest.setValue(value);
		rainTest.setTimestamp(timestamp);
		rainTest.setSensor(ModelFactory.sensor());
		rainTest.setUnit(RainUnits.MILLIMETRE);
		
		assertEquals(rainTest.getValue(), value);
		assertEquals(rainTest.getTimestamp(), timestamp);
		assertEquals(rainTest.getUnit(), RainUnits.MILLIMETRE);
		assertNotNull(rainTest.getSensor());
	}
	
}