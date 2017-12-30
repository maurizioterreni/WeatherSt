package it.unifi.sam.terreni.weatherSt.model.facotry;

import java.util.UUID;

import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.sensor.Measure;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;

public final class ModelFactory {

	private ModelFactory(){}
	
	public static Measure measure() {
		return new Measure(UUID.randomUUID().toString());
	}

	public static Sensor sensor() {
		return new Sensor(UUID.randomUUID().toString());
	}
	
	public static WeatherStation weatherStation() {
		return new WeatherStation(UUID.randomUUID().toString());
	}
	
}