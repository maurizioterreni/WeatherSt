package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.WindDirectionUnits;

public class WindDirectionMeasureTest {

	private WindDirectionMeasure windDirectionMeasureTest;

	@Before
	public void setUp() {
		windDirectionMeasureTest = ModelFactory.windDirectionMeasure();
	}

	@Test
	public void testBasicProperties() {
		Float value = 2f;
		Long timestamp = 2000L;
		windDirectionMeasureTest.setValue(value);
		windDirectionMeasureTest.setTimestamp(timestamp);
		windDirectionMeasureTest.setSensor(ModelFactory.sensor());
		windDirectionMeasureTest.setUnit(WindDirectionUnits.DEGREE);

		assertEquals(windDirectionMeasureTest.getValue(), value);
		assertEquals(windDirectionMeasureTest.getTimestamp(), timestamp);
		assertEquals(windDirectionMeasureTest.getUnit(), WindDirectionUnits.DEGREE);
		assertNotNull(windDirectionMeasureTest.getSensor());
	}

}