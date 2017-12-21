package it.unifi.sam.terreni.weatherSt.model.sensor;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;

@Entity
@Table(name="uv")
public class UV extends BaseEntity {
	private Integer value;
	private Timestamp timestamp;
	
	UV(){
		super();
	}
	
	public UV(String uuid) {
		super(uuid);
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
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
