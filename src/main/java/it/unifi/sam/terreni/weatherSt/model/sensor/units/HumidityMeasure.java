package it.unifi.sam.terreni.weatherSt.model.sensor.units;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.sensor.Measure;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.utils.HumidityUnits;

@Entity
@Table(name="humidityMeasure")
public class HumidityMeasure extends Measure{
	private static final long serialVersionUID = 1L;
	
	private HumidityUnits unit;
	
	HumidityMeasure(){
		super();
	}
	
	public HumidityMeasure(String uuid,String unit) {
		super(uuid);
		this.unit = HumidityUnits.getFromString(unit);
	}
	
	public HumidityMeasure(String uuid) {
		super(uuid);
	}

	@Enumerated(EnumType.STRING)
	public HumidityUnits getUnit() {
		return unit;
	}

	public void setUnits(HumidityUnits unit) {
		this.unit = unit;
	}
	
	
	
}
