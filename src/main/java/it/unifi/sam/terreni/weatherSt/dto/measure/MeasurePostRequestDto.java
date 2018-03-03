package it.unifi.sam.terreni.weatherSt.dto.measure;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class MeasurePostRequestDto extends BaseDto{
	private static final long serialVersionUID = 1L;
	private Long sensorId;
	private Float quantity;
	private Long unitMeasureId;
	private Long dateTime;
	
	public MeasurePostRequestDto() {
		
	}
	
	public static MeasurePostRequestDtoBuilder builder() {
		return new MeasurePostRequestDtoBuilder();
	}

	public Long getSensorId() {
		return sensorId;
	}

	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Long getUnitMeasureId() {
		return unitMeasureId;
	}

	public void setUnitMeasureId(Long unitMeasureId) {
		this.unitMeasureId = unitMeasureId;
	}
	
	
	public Long getDateTime() {
		return dateTime;
	}

	public void setDateTime(Long dateTime) {
		this.dateTime = dateTime;
	}


	public static class MeasurePostRequestDtoBuilder{
		private Long sensorId;
		private Float quantity;
		private Long unitMeasureId;
		
		public MeasurePostRequestDtoBuilder sensorId(Long sensorId) {
			this.sensorId = sensorId;
			return this;
		}
		public MeasurePostRequestDtoBuilder quantity(Float quantity) {
			this.quantity = quantity;
			return this;
		}
		public MeasurePostRequestDtoBuilder unitMeasureId(Long unitMeasureId) {
			this.unitMeasureId = unitMeasureId;
			return this;
		}
		public MeasurePostRequestDto build() {
			MeasurePostRequestDto dto = new MeasurePostRequestDto();
			
			dto.setQuantity(quantity);
			dto.setSensorId(sensorId);
			dto.setUnitMeasureId(unitMeasureId);
			
			return dto;
		}
	}
	
}
