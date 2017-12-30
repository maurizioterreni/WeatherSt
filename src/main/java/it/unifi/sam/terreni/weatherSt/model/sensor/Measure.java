package it.unifi.sam.terreni.weatherSt.model.sensor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.UnitMeasure;
import it.unifi.sam.terreni.weatherSt.model.usage.Usage;
import it.unifi.sam.terreni.weatherSt.model.usage.UsageVisitor;

@Entity
@Table(name="measures")
public class Measure extends BaseEntity implements Usage{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "sensor")
	private Sensor sensor;
	private Float value;
	private Long timestamp;
	private UnitMeasure unit;

	Measure(){
		super();
	}

	public Measure(String uuid) {
		super(uuid);
	}

	@Column(precision=4)
	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Enumerated(EnumType.STRING)
	public UnitMeasure getUnit() {
		return unit;
	}

	public void setUnit(UnitMeasure unit) {
		this.unit = unit;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	@Override
	public void accept(UsageVisitor visitor) {
		visitor.visitMeasure(this);
	}


}
