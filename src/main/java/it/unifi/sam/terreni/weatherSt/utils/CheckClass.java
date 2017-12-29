package it.unifi.sam.terreni.weatherSt.utils;

import it.unifi.sam.terreni.weatherSt.model.sensor.SensorType;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.UnitMeasure;

public class CheckClass {
	private CheckClass() {}
	
	public static boolean checkUnit(SensorType sensorType, UnitMeasure unitMeasure) {
		return true;
	}
	
	public static boolean checkToken(String token) {
		return true;
	}
}
