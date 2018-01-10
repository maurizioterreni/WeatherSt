package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import org.junit.Test;

import it.unifi.sam.terreni.weatherSt.model.sensor.units.delegate.PressureMeasureTestDelegate;
import it.unifi.sam.terreni.weatherSt.persistence.JpaIT;

public class PressureMeasureJpaIT extends JpaIT {

	private PressureMeasureTestDelegate pressureTest;

	@Override
	public void initTest() {
		pressureTest = new PressureMeasureTestDelegate();
		pressureTest.init(entityManager);
		pressureTest.insertData();
	}

	@Test
	public void readTest() {
		pressureTest.readTest();
	}

}