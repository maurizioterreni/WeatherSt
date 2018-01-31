package it.unifi.sam.terreni.weatherSt.model.measure.units;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureFamily;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.RainUnits;

@Entity
@DiscriminatorValue("rain_unit")
public class RainUnit extends UnitMeasureFamily {
	private static final long serialVersionUID = 8577894573991411848L;

	private RainUnits units;
	
	RainUnit(){
		super();
	}
	
	public RainUnit(String uuid) {
		super(uuid);
	}
	public RainUnit(String uuid, RainUnits units) {
		super(uuid);
		this.units = units;
	}

	public RainUnits getUnits() {
		return units;
	}
	@Enumerated(EnumType.STRING)
	public void setUnits(RainUnits units) {
		this.units = units;
	}
}
