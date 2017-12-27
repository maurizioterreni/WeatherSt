package it.unifi.sam.terreni.weatherSt.model.facotry;

import java.util.UUID;

import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;

public final class ModelFactory {

	private ModelFactory(){}
	
	public static Sensor temperature() {
		return new Sensor(UUID.randomUUID().toString());
	}
	
}