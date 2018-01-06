package it.unifi.sam.terreni.weatherSt.utils;

import it.unifi.sam.terreni.weatherSt.model.sensor.Measure;
import it.unifi.sam.terreni.weatherSt.model.sensor.SensorType;

public class CheckClass {
	private CheckClass() {}
	
	public static boolean checkUnit(SensorType sensorType, Measure unitMeasure) {
		return true;
	}
	
	public static boolean checkToken(String token) {
		return true;
	}
}
