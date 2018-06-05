package it.unifi.sam.terreni.weatherSt.model.usage;

import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.model.user.UserPropertie;

public interface UsageVisitor {
	void visitSensor(Sensor sensor);
	void visitMeasure(Measure measure);
	void visitUserPropertie(UserPropertie userPropertie);
	void visitUnitMeasureKnowledge(UnitMeasureKnowledge unitMeasureKnowledge);
	void visitUserProperties(UserPropertie userPropertie);
}