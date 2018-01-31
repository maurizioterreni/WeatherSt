package it.unifi.sam.terreni.weatherSt.model.measure.units;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureFamily;
import it.unifi.sam.terreni.weatherSt.model.measure.units.utils.UVUnits;

@Entity
@DiscriminatorValue("uv_unit")
public class UvUnit extends UnitMeasureFamily {
	private static final long serialVersionUID = 6560577452095710150L;

	private UVUnits units;

	UvUnit(){
		super();
	}
	
	public UvUnit(String uuid) {
		super(uuid);
	}
	public UvUnit(String uuid, UVUnits units) {
		super(uuid);
		this.units = units;
	}

	public UVUnits getUnits() {
		return units;
	}

	@Enumerated(EnumType.STRING)
	public void setUnits(UVUnits units) {
		this.units = units;
	}
	
}
