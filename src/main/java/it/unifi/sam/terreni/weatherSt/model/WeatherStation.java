package it.unifi.sam.terreni.weatherSt.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;

@Entity
@Table(name="weatherStations")
public class WeatherStation extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String longitude;
	private String latitude;
	private String description;
	@OneToMany(mappedBy = "weatherStation", cascade = CascadeType.REMOVE)
	private Set<Sensor> sensors;
	
	WeatherStation() {
		super();
		sensors = new HashSet<>();
	} 
	
	public WeatherStation(String uuid) {
		super(uuid);
		sensors = new HashSet<>();
	}
	
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@JsonIgnore
	public Set<Sensor> getSensors() {
		return sensors;
	}
	public void setSensors(Set<Sensor> sensors) {
		this.sensors = sensors;
	}
	public void addSensor(Sensor sensor) {
		this.sensors.add(sensor);
	}
}
