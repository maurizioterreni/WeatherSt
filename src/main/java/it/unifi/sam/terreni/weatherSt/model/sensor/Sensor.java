package it.unifi.sam.terreni.weatherSt.model.sensor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;

@Entity
@Table(name="sensors")
public class Sensor extends BaseEntity {
	private static final long serialVersionUID = -89078019304365613L;

	private SensorType sensorType;
	private String description;

	@OneToMany(mappedBy = "sensor", cascade = CascadeType.REMOVE)
	private Set<Measure> measures;
	
	Sensor() {
		super();
		measures = new HashSet<>();
	}
	
	public Sensor(String uuid) {
		super(uuid);
		measures = new HashSet<>();
	}

	@Enumerated(EnumType.STRING)
	public SensorType getSensorType() {
		return sensorType;
	}

	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}

	public Set<Measure> getMeasures() {
		return measures;
	}
	
	public void setMeasures(Set<Measure> measures) {
		this.measures = measures;
	}
	
	public void addMeasuer(Measure measure) {
		this.measures.add(measure);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
