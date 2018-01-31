package it.unifi.sam.terreni.weatherSt.model.measure;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Value.class)
public abstract class Value_ extends it.unifi.sam.terreni.weatherSt.model.BaseEntity_ {

	public static volatile SingularAttribute<Value, Measure> measure;
	public static volatile SingularAttribute<Value, Float> value;
	public static volatile SingularAttribute<Value, UnitMeasureFamily> unitMeasureFamily;

}

