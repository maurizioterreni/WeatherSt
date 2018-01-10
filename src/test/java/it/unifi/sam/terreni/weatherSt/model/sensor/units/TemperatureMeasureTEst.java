package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.TemperatureUnits;

public class TemperatureMeasureTEst {

	private TemperatureMeasure temperatureTest;
	
	@Before
	public void setUp() {
		temperatureTest = ModelFactory.temperatureMeasure();
	}

	@Test
	public void testBasicProperties() {
		Float value = 2f;
		Long timestamp = 2000L;
		temperatureTest.setValue(value);
		temperatureTest.setTimestamp(timestamp);
		temperatureTest.setSensor(ModelFactory.sensor());
		temperatureTest.setUnit(TemperatureUnits.CELSIUS);
		
		assertEquals(temperatureTest.getValue(), value);
		assertEquals(temperatureTest.getTimestamp(), timestamp);
		assertEquals(temperatureTest.getUnit(), TemperatureUnits.CELSIUS);
		assertNotNull(temperatureTest.getSensor());
	}
	
}