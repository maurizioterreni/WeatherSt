package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.sensor.Measure;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.WindSpeedUnits;

@Entity
@Table(name="windSpeedMeasure")
public class WindSpeedMeasure extends Measure{
	private static final long serialVersionUID = 1L;
	
	private WindSpeedUnits unit;

	WindSpeedMeasure(){
		super();
	}
	
	public WindSpeedMeasure(String uuid) {
		super(uuid);
	}

	@Enumerated(EnumType.STRING)
	public WindSpeedUnits getUnit() {
		return unit;
	}

	public void setUnit(WindSpeedUnits unit) {
		this.unit = unit;
	}
	
	
	
}
