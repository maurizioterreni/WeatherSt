package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.sensor.Measure;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.PressureUnits;

@Entity
@Table(name="pressureMeasure")
public class PressureMeasure extends Measure{
	private static final long serialVersionUID = 1L;
	
	private PressureUnits unit;

	PressureMeasure() {
		super();
	}
	
	public PressureMeasure(String uuid) {
		super(uuid);
	}

	@Enumerated(EnumType.STRING)
	public PressureUnits getUnit() {
		return unit;
	}

	public void setUnit(PressureUnits unit) {
		this.unit = unit;
	}

}
