package it.unifi.sam.terreni.weatherSt.dto.measure;

import java.time.LocalDateTime;

import it.unifi.sam.terreni.weatherSt.dto.BaseDTO;

public class MeasureDTO extends BaseDTO{
	private static final long serialVersionUID = 1L;
	private Long sensorId;
	private LocalDateTime localDateTime;
	private Float quantity;
	
	public MeasureDTO() {
		
	}

	public Long getSensorId() {
		return sensorId;
	}

	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
	
	
}
