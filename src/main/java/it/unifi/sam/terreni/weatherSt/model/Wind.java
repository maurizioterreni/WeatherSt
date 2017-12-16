package it.unifi.sam.terreni.weatherSt.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="wind")
public class Wind extends BaseEntity {
	private Float speed;
	private Integer degree;
	private Long timestamp;
	
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
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
