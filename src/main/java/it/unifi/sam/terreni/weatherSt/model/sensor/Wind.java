package it.unifi.sam.terreni.weatherSt.model.sensor;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;

@Entity
@Table(name="wind")
public class Wind extends BaseEntity {
	private Float speed;
	private Integer degree;
	private Timestamp timestamp;
	
	Wind(){
		super();
	}
	public Wind(String uuid) {
		super(uuid);
	}
	public Float getSpeed() {
		return speed;
	}
	public void setSpeed(Float speed) {
		this.speed = speed;
	}
	public Integer getDegree() {
		return degree;
	}
	public void setDegree(Integer degree) {
		this.degree = degree;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
