package it.unifi.sam.terreni.weatherSt.model.facotry;

import java.util.UUID;

import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.measure.Value;
import it.unifi.sam.terreni.weatherSt.model.measure.units.DirectionUnit;
import it.unifi.sam.terreni.weatherSt.model.measure.units.HumidityUnit;
import it.unifi.sam.terreni.weatherSt.model.measure.units.PressureUnit;
import it.unifi.sam.terreni.weatherSt.model.measure.units.RainUnit;
import it.unifi.sam.terreni.weatherSt.model.measure.units.SpeedUnit;
import it.unifi.sam.terreni.weatherSt.model.measure.units.TemperatureUnit;
import it.unifi.sam.terreni.weatherSt.model.measure.units.UvUnit;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.DirectionUnits;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.HumidityUnits;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.PressureUnits;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.RainUnits;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.SpeedUnits;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.TemperatureUnits;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.UvUnits;
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
	
	public static DirectionUnit directionUnit(DirectionUnits units) {
		return new DirectionUnit(uuid(),units);
	}
	public static HumidityUnit humidityUnit(HumidityUnits units) {
		return new HumidityUnit(uuid(),units);
	}
	public static PressureUnit pressureUnit(PressureUnits units) {
		return new PressureUnit(uuid(),units);
	}
	public static RainUnit rainUnit(RainUnits units) {
		return new RainUnit(uuid(),units);
	}
	public static SpeedUnit speedUnit(SpeedUnits units) {
		return new SpeedUnit(uuid(),units);
	}
	public static UvUnit uvUnit(UvUnits units) {
		return new UvUnit(uuid(),units);
	}
	
	private static String uuid() {
		return UUID.randomUUID().toString();
	}
}