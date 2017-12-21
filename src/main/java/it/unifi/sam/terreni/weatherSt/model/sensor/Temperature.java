package it.unifi.sam.terreni.weatherSt.model.sensor;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;

@Entity
@Table(name="temperature")
public class Temperature extends BaseEntity {
	private Float value;
	private Timestamp timestamp;
	
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

	public Timestamp getTimestamp() {
		return timestamp;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
