package it.unifi.sam.terreni.weatherSt.visitor;

import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.model.usage.UsageVisitor;

public class ResolveLazyLoadUsageVisitor implements UsageVisitor {

	@Override
	public void visitSensor(Sensor sensor) {
		sensor.getDescription();
		for(Measure measure : sensor.getMeasures()) {
			measure.getValue();
			measure.accept(this);
		}
		
	}
	
	@Override
	public void visitMeasure(Measure measure) {
		return;		
	}


}

