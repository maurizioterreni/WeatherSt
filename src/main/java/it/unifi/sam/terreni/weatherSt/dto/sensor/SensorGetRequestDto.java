package it.unifi.sam.terreni.weatherSt.dto.sensor;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class SensorGetRequestDto extends BaseDto{
	private static final long serialVersionUID = 1L;
	private Long sensorId;
	private Long sensorTypeId;
	
	public SensorGetRequestDto() {
	}
	
	public static SensorGetRequestDtoBuilder builder() {
		return new SensorGetRequestDtoBuilder();
	}
	
	
	public Long getSensorId() {
		return sensorId;
	}
	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}

	public Long getSensorTypeId() {
		return sensorTypeId;
	}

	public void setSensorTypeId(Long sensorTypeId) {
		this.sensorTypeId = sensorTypeId;
	}
	
	public static class SensorGetRequestDtoBuilder{
		private Long sensorId;
		private Long sensorTypeId;
		
		public SensorGetRequestDtoBuilder sensorId(Long sensorId) {
			this.sensorId = sensorId;
			return this;
		}
		
		public SensorGetRequestDtoBuilder sensorTypeId(Long sensorTypeId) {
			this.sensorTypeId = sensorTypeId;
			return this;
		}
		
		
		public SensorGetRequestDto build() {
			SensorGetRequestDto addDto = new SensorGetRequestDto();
			
			addDto.setSensorTypeId(sensorTypeId);
			addDto.setSensorId(sensorId);
			
			return addDto;
		}
		
	}
}
