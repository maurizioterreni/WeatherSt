package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.sensor.Measure;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.WindDirectionUnits;

@Entity
@Table(name = "windDirectionMeasure")
public class WindDirectionMeasure extends Measure{
	private static final long serialVersionUID = 1L;

	private WindDirectionUnits unit;
	
	WindDirectionMeasure() {
		super();
	}
	
	public WindDirectionMeasure(String uuid) {
		super(uuid);
	}
	
	public WindDirectionMeasure(String uuid, String unit) {
		super(uuid);
		this.unit = WindDirectionUnits.getFromString(unit);
	}

	@Enumerated(EnumType.STRING)
	public WindDirectionUnits getUnit() {
		return unit;
	}

	public void setUnit(WindDirectionUnits unit) {
		this.unit = unit;
	}
	
	
	
}
