package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.sensor.Measure;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.TemperatureUnits;

@Entity
@Table(name="temperatureMeasure")
public class TemperatureMeasure extends Measure{
	private static final long serialVersionUID = 1L;
	
	private TemperatureUnits unit;

	TemperatureMeasure() {
		super();
	}
	public TemperatureMeasure(String uuid, String unit){
		super(uuid);
		this.unit = TemperatureUnits.getFromString(unit);
	}
	
	public TemperatureMeasure(String uuid){
		super(uuid);
	}
	
	@Enumerated(EnumType.STRING)
	public TemperatureUnits getUnit() {
		return unit;
	}
	public void setUnit(TemperatureUnits unit) {
		this.unit = unit;
	}	
}
