package it.unifi.sam.terreni.weatherSt.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;

@Entity
@Table(name="weatherStations")
public class WeatherStation extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String longitude;
	private String latitude;
	private String description;
	private String images;
	private Integer timeZone;
	@OneToMany(fetch = FetchType.EAGER, targetEntity=Sensor.class , cascade = CascadeType.ALL )
	private Set<Sensor> sensors;

	WeatherStation() {
		super();
		sensors = new HashSet<>();
	} 

	public WeatherStation(String uuid) {
		super(uuid);
		sensors = new HashSet<>();
	}

	public static WeatherStationBuilder builder() {
		return new WeatherStationBuilder();
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
	
	
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}


	public Integer getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(Integer timeZone) {
		this.timeZone = timeZone;
	}


	public static class WeatherStationBuilder{
		private String longitude;
		private String latitude;
		private String description;
		private String images;
		private Integer timeZone;
		
		public WeatherStationBuilder longitude(String longitude) {
			this.longitude = longitude;
			return this;
		}
		public WeatherStationBuilder latitude(String latitude) {
			this.latitude = latitude;
			return this;
		}
		public WeatherStationBuilder description(String description) {
			this.description = description;
			return this;
		}
		
		public WeatherStationBuilder images(String images) {
			this.images = images;
			return this;
		}
		public WeatherStationBuilder timeZone(Integer timeZone) {
			this.timeZone = timeZone;
			return this;
		}
		
		public WeatherStation build() {
			WeatherStation weatherStation = ModelFactory.weatherStation();
			
			weatherStation.setDescription(description);
			weatherStation.setLatitude(latitude);
			weatherStation.setLongitude(longitude);
			weatherStation.setImages(images);
			weatherStation.setTimeZone(timeZone);
			
			return weatherStation;
		}
		
		
	}
}
