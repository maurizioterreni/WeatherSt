package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.WindSpeedUnits;

public class WindSpeedMeasureTest {

	private WindSpeedMeasure windSpeedMeasureTest;

	@Before
	public void setUp() {
		windSpeedMeasureTest = ModelFactory.windSpeedMeasure();
	}

	@Test
	public void testBasicProperties() {
		Float value = 2f;
		Long timestamp = 2000L;
		windSpeedMeasureTest.setValue(value);
		windSpeedMeasureTest.setTimestamp(timestamp);
		windSpeedMeasureTest.setSensor(ModelFactory.sensor());
		windSpeedMeasureTest.setUnit(WindSpeedUnits.FT_S);

		assertEquals(windSpeedMeasureTest.getValue(), value);
		assertEquals(windSpeedMeasureTest.getTimestamp(), timestamp);
		assertEquals(windSpeedMeasureTest.getUnit(), WindSpeedUnits.FT_S);
		assertNotNull(windSpeedMeasureTest.getSensor());
	}

}
