package it.unifi.sam.terreni.weatherSt.model.measure.units;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureFamily;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.DirectionUnits;

@Entity
@DiscriminatorValue("direction_unit")
public class DirectionUnit extends UnitMeasureFamily {
	

	private static final long serialVersionUID = 383238746751539762L;

	private DirectionUnits units;

	DirectionUnit(){
		super();
	}
	
	public DirectionUnit(String uuid) {
		super(uuid);
	}

	public DirectionUnit(String uuid, DirectionUnits units) {
		super(uuid);
		this.units = units;
	}
	
	
	public DirectionUnits getUnits() {
		return units;
	}
	@Enumerated(EnumType.STRING)
	public void setUnits(DirectionUnits units) {
		this.units = units;
	}
	
}
