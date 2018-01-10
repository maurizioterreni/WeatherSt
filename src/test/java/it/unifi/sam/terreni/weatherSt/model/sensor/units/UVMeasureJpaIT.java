package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import org.junit.Test;

import it.unifi.sam.terreni.weatherSt.model.sensor.units.delegate.UVMeasureTestDelegate;
import it.unifi.sam.terreni.weatherSt.persistence.JpaIT;

public class UVMeasureJpaIT  extends JpaIT {

	private UVMeasureTestDelegate uvTest;

	@Override
	public void initTest() {
		uvTest = new UVMeasureTestDelegate();
		uvTest.init(entityManager);
		uvTest.insertData();
	}

	@Test
	public void readTest() {
		uvTest.readTest();
	}



}