package it.unifi.sam.terreni.weatherSt.model.facotry;

import java.util.UUID;

import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasure;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.model.sensor.SensorType;

public final class ModelFactory {

	private ModelFactory(){}
	
	public static WeatherStation weatherStation() {
		return new WeatherStation(uuid());
	}
	
	public static Sensor sensor() {
		return new Sensor(uuid());
	}
	
	public static SensorType sensorType() {
		return new SensorType(uuid());
	}
	
	public static Measure measure() {
		return new Measure(uuid());
	}
	
	public static UnitMeasure unitMeasure() {
		return new UnitMeasure(uuid());
	}
	
	
	
	private static String uuid() {
		return UUID.randomUUID().toString();
	}
}