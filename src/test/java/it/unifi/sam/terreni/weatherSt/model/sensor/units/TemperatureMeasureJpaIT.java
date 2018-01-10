package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import org.junit.Test;

import it.unifi.sam.terreni.weatherSt.model.sensor.units.delegate.TemperatureMeasureTestDelegate;
import it.unifi.sam.terreni.weatherSt.persistence.JpaIT;

public class TemperatureMeasureJpaIT  extends JpaIT {

	private TemperatureMeasureTestDelegate humidityTest;

	@Override
	public void initTest() {
		humidityTest = new TemperatureMeasureTestDelegate();
		humidityTest.init(entityManager);
		humidityTest.insertData();
	}

	@Test
	public void readTest() {
		humidityTest.readTest();
	}



}