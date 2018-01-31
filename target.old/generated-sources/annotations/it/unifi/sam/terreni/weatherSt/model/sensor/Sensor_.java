package it.unifi.sam.terreni.weatherSt.model.sensor;

import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sensor.class)
public abstract class Sensor_ extends it.unifi.sam.terreni.weatherSt.model.BaseEntity_ {

	public static volatile SetAttribute<Sensor, Measure> measures;
	public static volatile SingularAttribute<Sensor, SensorType> sensorType;
	public static volatile SingularAttribute<Sensor, String> description;
	public static volatile SingularAttribute<Sensor, WeatherStation> weatherStation;

}

