package it.unifi.sam.terreni.weatherSt.model.measure.units;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureFamily;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.HumidityUnits;

@Entity
@DiscriminatorValue("humidity_unit")
public class HumidityUnit extends UnitMeasureFamily {
	private static final long serialVersionUID = 1L;

	private HumidityUnits units;
	
	HumidityUnit(){
		super();
	}
	
	public HumidityUnit(String uuid) {
		super(uuid);
	}
	public HumidityUnit(String uuid, HumidityUnits units) {
		super(uuid);
		this.units = units;
	}

	public HumidityUnits getUnits() {
		return units;
	}
	@Enumerated(EnumType.STRING)
	public void setUnits(HumidityUnits units) {
		this.units = units;
	}
	
}
