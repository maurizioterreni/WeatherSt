package it.unifi.sam.terreni.weatherSt.model.facotry;

import java.util.UUID;

import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.converter.ConversionFactor;
import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.model.sensor.SensorTemplate;
import it.unifi.sam.terreni.weatherSt.model.sensor.SensorTypeKnowledge;
import it.unifi.sam.terreni.weatherSt.model.user.User;
import it.unifi.sam.terreni.weatherSt.model.user.UserPropertie;

public final class ModelFactory {

	private ModelFactory(){}
	
	public static WeatherStation weatherStation() {
		return new WeatherStation(uuid());
	}
	
	public static Sensor sensor() {
		return new Sensor(uuid());
	}
	
	public static SensorTypeKnowledge sensorType() {
		return new SensorTypeKnowledge(uuid());
	}
	
	public static Measure measure() {
		return new Measure(uuid());
	}
	
	public static UnitMeasureKnowledge unitMeasure() {
		return new UnitMeasureKnowledge(uuid());
	}
	
	public static ConversionFactor conversionFactor() {
		return new ConversionFactor(uuid());
	}
	public static User user() {
		return new User(uuid());
	}
	public static UserPropertie userPropertie() {
		return new UserPropertie(uuid());
	}
	public static SensorTemplate sensorTemplate() {
		return new SensorTemplate(uuid());
	}
	
	
	private static String uuid() {
		return UUID.randomUUID().toString();
	}
}