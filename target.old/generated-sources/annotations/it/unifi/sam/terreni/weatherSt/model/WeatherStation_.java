package it.unifi.sam.terreni.weatherSt.model;

import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WeatherStation.class)
public abstract class WeatherStation_ extends it.unifi.sam.terreni.weatherSt.model.BaseEntity_ {

	public static volatile SetAttribute<WeatherStation, Sensor> sensors;
	public static volatile SingularAttribute<WeatherStation, String> latitude;
	public static volatile SingularAttribute<WeatherStation, String> description;
	public static volatile SingularAttribute<WeatherStation, String> longitude;

}

