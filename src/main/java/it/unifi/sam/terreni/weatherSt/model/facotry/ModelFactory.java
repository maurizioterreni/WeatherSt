package it.unifi.sam.terreni.weatherSt.model.facotry;

import static it.unifi.sam.terreni.weatherSt.model.sensor.SensorType.TEMPERATURE;
import static it.unifi.sam.terreni.weatherSt.model.sensor.SensorType.HUMIDITY;
import static it.unifi.sam.terreni.weatherSt.model.sensor.SensorType.PRESSURE;
import static it.unifi.sam.terreni.weatherSt.model.sensor.SensorType.RAIN;
import static it.unifi.sam.terreni.weatherSt.model.sensor.SensorType.UV;
import static it.unifi.sam.terreni.weatherSt.model.sensor.SensorType.WIND_DIRECTION;
import static it.unifi.sam.terreni.weatherSt.model.sensor.SensorType.WIND_SPEED;

import java.util.UUID;

import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.sensor.Measure;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.model.sensor.SensorType;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.HumidityMeasure;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.PressureMeasure;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.RainMeasure;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.TemperatureMeasure;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.UVMeasure;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.WindDirectionMeasure;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.WindSpeedMeasure;

public final class ModelFactory {

	private ModelFactory(){}
	
	public static Measure measure(SensorType sensorType,String valueType) {
		Measure measure = null;
		if(sensorType.equals(TEMPERATURE)) {
			measure = new TemperatureMeasure(UUID.randomUUID().toString(), valueType);
		}else if(sensorType.equals(HUMIDITY)) {
			measure = new HumidityMeasure(UUID.randomUUID().toString(), valueType);
		}else if (sensorType.equals(PRESSURE)) {
			measure = new PressureMeasure(UUID.randomUUID().toString(), valueType);
		}else if (sensorType.equals(RAIN)) {
			measure = new RainMeasure(UUID.randomUUID().toString(), valueType);
		}else if (sensorType.equals(UV)) {
			measure = new UVMeasure(UUID.randomUUID().toString(), valueType);
		}else if (sensorType.equals(WIND_DIRECTION)) {
			measure = new WindDirectionMeasure(UUID.randomUUID().toString());
		}else if (sensorType.equals(WIND_SPEED)) {
			measure = new WindSpeedMeasure(UUID.randomUUID().toString());
		}

		return measure;
	}
	
	public static TemperatureMeasure temperatureMeasure() {
		return new TemperatureMeasure(UUID.randomUUID().toString());
	}
	
	public static HumidityMeasure humidityMeasure() {
		return new HumidityMeasure(UUID.randomUUID().toString());
	}
	
	public static PressureMeasure pressureMeasure() {
		return new PressureMeasure(UUID.randomUUID().toString());
	}
	
	public static RainMeasure rainMeasure() {
		return new RainMeasure(UUID.randomUUID().toString());
	}
	
	public static UVMeasure uvMeasure() {
		return new UVMeasure(UUID.randomUUID().toString());
	}
	
	public static WindDirectionMeasure windDirectionMeasure() {
		return new WindDirectionMeasure(UUID.randomUUID().toString());
	}

	public static WindSpeedMeasure windSpeedMeasure() {
		return new WindSpeedMeasure(UUID.randomUUID().toString());
	}

	public static Sensor sensor() {
		return new Sensor(UUID.randomUUID().toString());
	}
	
	public static WeatherStation weatherStation() {
		return new WeatherStation(UUID.randomUUID().toString());
	}
	
}