package it.unifi.sam.terreni.weatherSt.model.sensor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;
import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.usage.Usage;
import it.unifi.sam.terreni.weatherSt.model.usage.UsageVisitor;

@Entity
@Table(name="sensors")
public class Sensor extends BaseEntity implements Usage{
	private static final long serialVersionUID = -89078019304365613L;

	private SensorType sensorType;
	private String description;
	@ManyToOne
	@JoinColumn(name = "weatherStation")
	private WeatherStation weatherStation;

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
	@JsonIgnore
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
	@JsonIgnore
	public WeatherStation getWeatherStation() {
		return weatherStation;
	}

	public void setWeatherStation(WeatherStation weatherStation) {
		this.weatherStation = weatherStation;
	}

	@Override
	public void accept(UsageVisitor visitor) {
		visitor.visitSensor(this);
	}
	
	
	
}
