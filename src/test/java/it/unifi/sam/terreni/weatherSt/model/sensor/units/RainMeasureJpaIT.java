package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import org.junit.Test;

import it.unifi.sam.terreni.weatherSt.model.sensor.units.delegate.RainMeasureTestDelegate;
import it.unifi.sam.terreni.weatherSt.persistence.JpaIT;

public class RainMeasureJpaIT extends JpaIT {

	private RainMeasureTestDelegate rainTest;

	@Override
	public void initTest() {
		rainTest = new RainMeasureTestDelegate();
		rainTest.init(entityManager);
		rainTest.insertData();
	}

	@Test
	public void readTest() {
		rainTest.readTest();
	}

}