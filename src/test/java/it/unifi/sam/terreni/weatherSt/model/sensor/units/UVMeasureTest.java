package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.UVUnits;

public class UVMeasureTest {

	private UVMeasure uvTest;

	@Before
	public void setUp() {
		uvTest = ModelFactory.uvMeasure();
	}

	@Test
	public void testBasicProperties() {
		Float value = 2f;
		Long timestamp = 2000L;
		uvTest.setValue(value);
		uvTest.setTimestamp(timestamp);
		uvTest.setSensor(ModelFactory.sensor());
		uvTest.setUnit(UVUnits.UV);

		assertEquals(uvTest.getValue(), value);
		assertEquals(uvTest.getTimestamp(), timestamp);
		assertEquals(uvTest.getUnit(), UVUnits.UV);
		assertNotNull(uvTest.getSensor());
	}

}