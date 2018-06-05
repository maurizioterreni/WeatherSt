package it.unifi.sam.terreni.weatherSt.visitor;

import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.model.usage.UsageVisitor;
import it.unifi.sam.terreni.weatherSt.model.user.UserPropertie;

public class ResolveLazyLoadUsageVisitor implements UsageVisitor {

	@Override
	public void visitSensor(Sensor sensor) {
		sensor.getSensorType();
		for(Measure measure : sensor.getMeasures()) {
			measure.getQuantity();
			measure.accept(this);
		}
		
	}
	
	@Override
	public void visitMeasure(Measure measure) {
		return;		
	}

	@Override
	public void visitUserPropertie(UserPropertie userPropertie) {
		return;
	}

	@Override
	public void visitUnitMeasureKnowledge(UnitMeasureKnowledge unitMeasureKnowledge) {
		unitMeasureKnowledge.getName();
	}

	@Override
	public void visitUserProperties(UserPropertie userPropertie) {
		userPropertie.getId();
	}	
}

