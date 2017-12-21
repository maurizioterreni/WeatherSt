package it.unifi.sam.terreni.weatherSt.model.facotry;

import java.util.UUID;

import it.unifi.sam.terreni.weatherSt.model.period.Day;
import it.unifi.sam.terreni.weatherSt.model.sensor.Humidity;
import it.unifi.sam.terreni.weatherSt.model.sensor.Lux;
import it.unifi.sam.terreni.weatherSt.model.sensor.Pressure;
import it.unifi.sam.terreni.weatherSt.model.sensor.Temperature;
import it.unifi.sam.terreni.weatherSt.model.sensor.UV;
import it.unifi.sam.terreni.weatherSt.model.sensor.Wind;

public final class ModelFactory {

	private ModelFactory(){}
	
	public static Temperature temperature() {
		return new Temperature(UUID.randomUUID().toString());
	}
	
	public static Humidity humidity() {
		return new Humidity(UUID.randomUUID().toString());
	}
	
	public static Lux lux() {
		return new Lux(UUID.randomUUID().toString());
	}
	
	public static Pressure pressure() {
		return new Pressure(UUID.randomUUID().toString());
	}
	
	public static UV uv() {
		return new UV(UUID.randomUUID().toString());
	}
	
	public static Wind wind() {
		return new Wind(UUID.randomUUID().toString());
	}
	
	public static Day day() {
		return new Day(UUID.randomUUID().toString());
	}
}