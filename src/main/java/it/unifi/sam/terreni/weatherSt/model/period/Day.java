package it.unifi.sam.terreni.weatherSt.model.period;

import java.sql.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;

public class Day extends BaseEntity {
	private Date date;
	
	Day(){
		super();
	}
	
	public Day(String uuid) {
		super(uuid);
	}

	public Date getDate() {
		return date;
	}

	@Temporal(TemporalType.DATE)
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
