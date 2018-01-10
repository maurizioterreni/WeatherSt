package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.sensor.Measure;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.RainUnits;

@Entity
@Table(name="rainMeasure")
public class RainMeasure extends Measure{
	private static final long serialVersionUID = 1L;
	
	private RainUnits unit;

	RainMeasure(){
		super();
	}
	
	public RainMeasure(String uuid, String unit) {
		super(uuid);
		this.unit = RainUnits.getFromString(unit);
	}
	
	public RainMeasure(String uuid) {
		super(uuid);
	}

	@Enumerated(EnumType.STRING)
	public RainUnits getUnit() {
		return unit;
	}

	public void setUnit(RainUnits unit) {
		this.unit = unit;
	}
	
	
}
