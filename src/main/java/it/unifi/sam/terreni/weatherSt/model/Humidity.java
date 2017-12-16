package it.unifi.sam.terreni.weatherSt.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="humidity")
public class Humidity extends BaseEntity {
	private Float value;
	private Long timestamp;
	
	Humidity(){
		super();
	}
	
	public Humidity(String uuid) {
		super(uuid);
	}

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
}
