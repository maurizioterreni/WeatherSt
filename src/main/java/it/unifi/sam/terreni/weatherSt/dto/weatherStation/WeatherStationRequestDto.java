package it.unifi.sam.terreni.weatherSt.dto.weatherStation;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class WeatherStationRequestDto extends BaseDto{
	private static final long serialVersionUID = 1L;

	private Long weatherId;
	
	public WeatherStationRequestDto() {
	}

	public Long getWeatherId() {
		return weatherId;
	}

	public void setWeatherId(Long weatherId) {
		this.weatherId = weatherId;
	}
	
}
