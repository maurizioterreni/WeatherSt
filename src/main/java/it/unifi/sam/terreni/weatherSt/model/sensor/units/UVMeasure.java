package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.sensor.Measure;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.UVUnits;

@Entity
@Table(name="uv_measure")
public class UVMeasure extends Measure{
	private static final long serialVersionUID = 1L;
	
	private UVUnits unit;

	UVMeasure(){
		super();
	}
	
	public UVMeasure(String uuid) {
		super(uuid);
	}
	
	@Enumerated(EnumType.STRING)
	public UVUnits getUnit() {
		return unit;
	}

	public void setUnit(UVUnits unit) {
		this.unit = unit;
	}
	
	
	
}
