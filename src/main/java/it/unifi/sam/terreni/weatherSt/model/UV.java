package it.unifi.sam.terreni.weatherSt.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="uv")
public class UV extends BaseEntity {
	private Integer value;
	private Long timestamp;
	
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
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
