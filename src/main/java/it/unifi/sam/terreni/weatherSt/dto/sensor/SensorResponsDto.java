package it.unifi.sam.terreni.weatherSt.dto.sensor;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class SensorResponsDto extends BaseDto{
	private static final long serialVersionUID = 1L;

	private Long weatherId;
	private String description;
	private String symbol;
	private String name;
	
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

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
//	public 
	
	public static class SensorResponseDtoBuilder{
		private Long weatherId;
		private String description;
		private String symbol;
		private String name;
		
		public SensorResponseDtoBuilder weatherId(Long weatherId) {
			this.weatherId = weatherId;
			return this;
		}
		public SensorResponseDtoBuilder description(String description) {
			this.description = description;
			return this;
		}
		public SensorResponseDtoBuilder symbol(String symbol) {
			this.symbol = symbol;
			return this;
		}
		public SensorResponseDtoBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public SensorResponsDto build() {
			SensorResponsDto dto = new SensorResponsDto();
			
			dto.setDescription(description);
			dto.setSymbol(symbol);
			dto.setName(name);
			dto.setWeatherId(weatherId);
			
			return dto;
		}
	}
}
