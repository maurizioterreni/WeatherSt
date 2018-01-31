package it.unifi.sam.terreni.weatherSt.model.measure.units;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureFamily;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.PressureUnits;

@Entity
@DiscriminatorValue("pressure_unit")
public class PressureUnit extends UnitMeasureFamily {
	private static final long serialVersionUID = -8211754155656047069L;

	private PressureUnits units;
	
	PressureUnit(){
		super();
	}
	
	public PressureUnit(String uuid) {
		super(uuid);
	}
	public PressureUnit(String uuid, PressureUnits units) {
		super(uuid);
		this.units = units;
	}

	public PressureUnits getUnits() {
		return units;
	}
	@Enumerated(EnumType.STRING)
	public void setUnits(PressureUnits units) {
		this.units = units;
	}
	
	
}
