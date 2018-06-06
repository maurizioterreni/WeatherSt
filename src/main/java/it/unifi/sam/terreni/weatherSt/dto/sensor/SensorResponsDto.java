package it.unifi.sam.terreni.weatherSt.dto.sensor;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class SensorResponsDto extends BaseDto{
	private static final long serialVersionUID = 1L;

	private Long weatherId;
	private String description;
	
	public SensorResponsDto() {
		
	}

	public static SensorResponseDtoBuilder builder() {
		return new SensorResponseDtoBuilder();
	}
	public Long getWeatherId() {
		return weatherId;
	}

	public void setWeatherId(Long weatherId) {
		this.weatherId = weatherId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public 
	
	public static class SensorResponseDtoBuilder{
		private Long weatherId;
		private String description;
		
		public SensorResponseDtoBuilder weatherId(Long weatherId) {
			this.weatherId = weatherId;
			return this;
		}
		public SensorResponseDtoBuilder description(String description) {
			this.description = description;
			return this;
		}
		
		public SensorResponsDto build() {
			SensorResponsDto dto = new SensorResponsDto();
			
			dto.setDescription(description);
			dto.setWeatherId(weatherId);
			
			return dto;
		}
	}
}
