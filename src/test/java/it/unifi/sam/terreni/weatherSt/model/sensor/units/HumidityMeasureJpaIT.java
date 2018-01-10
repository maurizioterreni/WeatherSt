package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import org.junit.Test;

import it.unifi.sam.terreni.weatherSt.model.sensor.units.delegate.HumidityMeasureTestDelegate;
import it.unifi.sam.terreni.weatherSt.persistence.JpaIT;


public class HumidityMeasureJpaIT extends JpaIT {

	private HumidityMeasureTestDelegate humidityTest;

	@Override
	public void initTest() {
		humidityTest = new HumidityMeasureTestDelegate();
		humidityTest.init(entityManager);
		humidityTest.insertData();
	}

	@Test
	public void readTest() {
		humidityTest.readTest();
	}



}