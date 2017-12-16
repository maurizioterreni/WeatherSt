package it.unifi.sam.terreni.weatherSt.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pressure")
public class Pressure extends BaseEntity {
	private Long value;
	private Long timestamp;
	
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

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
}
