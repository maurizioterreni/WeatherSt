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
import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.usage.Usage;
import it.unifi.sam.terreni.weatherSt.model.usage.UsageVisitor;

@Entity
@Table(name="sensors")
public class Sensor extends BaseEntity implements Usage{
	private static final long serialVersionUID = -89078019304365613L;

	private SensorType sensorType;
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
	
	public static SensorBuilder builder() {
		return new SensorBuilder();
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
	
	public static class SensorBuilder{
		private WeatherStation weatherStation;
		private SensorType sensorType;
		
		

		public SensorBuilder weatherStation(WeatherStation weatherStation) {
			this.weatherStation = weatherStation;
			return this;
		}
		
		
		
		public SensorBuilder sensorType(SensorType sensorType) {
			this.sensorType = sensorType;
			return this;
		}
		
		public Sensor build() {
			Sensor sensor = ModelFactory.sensor();
			
			sensor.sensorType = this.sensorType;
			this.weatherStation.addSensor(sensor);
			sensor.weatherStation = this.weatherStation;
			
			return sensor;
		}
	}
	
}
