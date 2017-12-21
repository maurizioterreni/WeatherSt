package it.unifi.sam.terreni.weatherSt.model.sensor;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;

@Entity
@Table(name="pressure")
public class Pressure extends BaseEntity {
	private Long value;
	private Timestamp timestamp;
	
	Pressure(){
		super();
	}
	
	public Pressure(String uuid) {
		super(uuid);
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
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
