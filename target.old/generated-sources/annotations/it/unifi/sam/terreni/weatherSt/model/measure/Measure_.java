package it.unifi.sam.terreni.weatherSt.model.measure;

import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Measure.class)
public abstract class Measure_ extends it.unifi.sam.terreni.weatherSt.model.BaseEntity_ {

	public static volatile SingularAttribute<Measure, Sensor> sensor;
	public static volatile SingularAttribute<Measure, Value> value;
	public static volatile SingularAttribute<Measure, Long> timestamp;

}

