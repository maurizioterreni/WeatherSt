package it.unifi.sam.terreni.weatherSt.dto.sensor;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class SensorPostRequestDto extends BaseDto{
	private static final long serialVersionUID = 1L;
	private Long weatherId;
	private Long sensorTypeId;
	private String title;
	
	public SensorPostRequestDto() {
	}
	
	public static SensorPostRequestDtoBuilder builder() {
		return new SensorPostRequestDtoBuilder();
	}
	
	
	public Long getWeatherId() {
		return weatherId;
	}
	public void setWeatherId(Long weatherId) {
		this.weatherId = weatherId;
	}

	public Long getSensorTypeId() {
		return sensorTypeId;
	}

	public void setSensorTypeId(Long sensorTypeId) {
		this.sensorTypeId = sensorTypeId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public static class SensorPostRequestDtoBuilder{
		private Long weatherId;
		private Long sensorTypeId;
		private String title;
		
		public SensorPostRequestDtoBuilder weatherId(Long weatherId) {
			this.weatherId = weatherId;
			return this;
		}
		
		public SensorPostRequestDtoBuilder sensorTypeId(Long sensorTypeId) {
			this.sensorTypeId = sensorTypeId;
			return this;
		}
		public SensorPostRequestDtoBuilder title(String title) {
			this.title = title;
			return this;
		}
		
		
		public SensorPostRequestDto build() {
			SensorPostRequestDto addDto = new SensorPostRequestDto();
			
			addDto.setSensorTypeId(sensorTypeId);
			addDto.setWeatherId(weatherId);
			addDto.setTitle(title);
			
			return addDto;
		}
		
	}
}
