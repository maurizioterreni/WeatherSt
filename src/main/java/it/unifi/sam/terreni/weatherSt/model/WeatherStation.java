package it.unifi.sam.terreni.weatherSt.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;

@Entity
@Table(name="weatherStations")
public class WeatherStation extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Float longitude;
	private Float latitude;
	private String description;
	@OneToMany(mappedBy = "weatherStation", cascade = CascadeType.REMOVE)
	private Set<Sensor> sensors;
	
	
	public Float getLongitude() {
		return longitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Sensor> getSensors() {
		return sensors;
	}
	public void setSensors(Set<Sensor> sensors) {
		this.sensors = sensors;
	}
}
