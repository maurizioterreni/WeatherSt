package it.unifi.sam.terreni.weatherSt.utils;

import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.sensor.SensorTypeKnowledge;

public class CheckClass {
	private CheckClass() {}
	
	public static boolean checkUnit(SensorTypeKnowledge sensorType, Measure unitMeasure) {
		return true;
	}
	
	public static boolean checkToken(String token) {
		return true;
	}
}
