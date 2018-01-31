package it.unifi.sam.terreni.weatherSt.model.usage;

import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;

public interface UsageVisitor {
	void visitSensor(Sensor sensor);
	void visitMeasure(Measure measure);
}