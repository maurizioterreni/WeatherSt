package it.unifi.sam.terreni.weatherSt.model.measure.units;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureFamily;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.SpeedUnits;

@Entity
@DiscriminatorValue("speed_unit")
public class SpeedUnit extends UnitMeasureFamily{
	private static final long serialVersionUID = 1L;

	private SpeedUnits units;
	
	SpeedUnit(){
		super();
	}
	
	public SpeedUnit(String uuid) {
		super(uuid);
	}
	public SpeedUnit(String uuid, SpeedUnits units) {
		super(uuid);
		this.units = units;
	}

	public SpeedUnits getUnits() {
		return units;
	}
	@Enumerated(EnumType.STRING)
	public void setUnits(SpeedUnits units) {
		this.units = units;
	}
	
}
