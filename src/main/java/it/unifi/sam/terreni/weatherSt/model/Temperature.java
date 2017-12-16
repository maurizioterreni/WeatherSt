package it.unifi.sam.terreni.weatherSt.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="temperature")
public class Temperature extends BaseEntity {
	private Float value;
	private Long timestamp;
	
	Temperature() {
		super();
	}
	
	public Temperature(String uuid) {
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
