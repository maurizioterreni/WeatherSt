package it.unifi.sam.terreni.weatherSt.dto.sensor;

import it.unifi.sam.terreni.weatherSt.dto.BaseDTO;

public class SensorToAddDto extends BaseDTO{
	private static final long serialVersionUID = 1L;
	private Long weatherId;
	private Long sensorTypeId;
	
	public SensorToAddDto() {
	}
	
	public static SensorToAddDtoBuilder builder() {
		return new SensorToAddDtoBuilder();
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
	
	public static class SensorToAddDtoBuilder{
		private Long weatherId;
		private Long sensorTypeId;
		
		public SensorToAddDtoBuilder weatherId(Long weatherId) {
			this.weatherId = weatherId;
			return this;
		}
		
		public SensorToAddDtoBuilder sensorTypeId(Long sensorTypeId) {
			this.sensorTypeId = sensorTypeId;
			return this;
		}
		
		
		public SensorToAddDto build() {
			SensorToAddDto addDto = new SensorToAddDto();
			
			addDto.setSensorTypeId(sensorTypeId);
			addDto.setWeatherId(weatherId);
			
			return addDto;
		}
		
	}
}
