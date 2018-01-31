package it.unifi.sam.terreni.weatherSt.model.measure.units;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureFamily;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.TemperatureUnits;

@Entity
@DiscriminatorValue("temperature_unit")
public class TemperatureUnit extends UnitMeasureFamily {
	private static final long serialVersionUID = -6475278948980161438L;

	private TemperatureUnits units;
	
	TemperatureUnit(){
		super();
	}
	
	public TemperatureUnit(String uuid) {
		super(uuid);
	}
	
	public TemperatureUnit(String uuid,TemperatureUnits units) {
		super(uuid);
		this.units = units;
	}
	
	public TemperatureUnit(TemperatureUnits units) {
		this.units = units;
	}

	public TemperatureUnits getUnits() {
		return units;
	}
	@Enumerated(EnumType.STRING)
	public void setUnits(TemperatureUnits units) {
		this.units = units;
	}
	
	
}
