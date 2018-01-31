package it.unifi.sam.terreni.weatherSt.model.facotry;

import java.util.UUID;

import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.measure.Value;
import it.unifi.sam.terreni.weatherSt.model.measure.units.TemperatureUnit;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.TemperatureUnits;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;

public final class ModelFactory {

	private ModelFactory(){}
	
	public static WeatherStation weatherStation() {
		return new WeatherStation(uuid());
	}
	
	public static Sensor sensor() {
		return new Sensor(uuid());
	}
	
	public static Measure measure() {
		return new Measure(uuid());
	}
	
	public static Value value() {
		return new Value(uuid());
	}
	
	public static TemperatureUnit temperatureUnit(TemperatureUnits units) {
		return new TemperatureUnit(uuid(),units);
	}
	
	private static String uuid() {
		return UUID.randomUUID().toString();
	}
}