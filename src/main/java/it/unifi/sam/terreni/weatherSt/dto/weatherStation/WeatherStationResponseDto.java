package it.unifi.sam.terreni.weatherSt.dto.weatherStation;

import java.util.LinkedList;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;
import it.unifi.sam.terreni.weatherSt.dto.sensor.SensorResponsDto;

public class WeatherStationResponseDto extends BaseDto{
	private static final long serialVersionUID = 9222022175701191010L;


	private Long weatherId;
	private LinkedList<SensorResponsDto> sensors;

	public WeatherStationResponseDto() {
	}

	public Long getWeatherId() {
		return weatherId;
	}

	public void setWeatherId(Long weatherId) {
		this.weatherId = weatherId;
	}

	public LinkedList<SensorResponsDto> getSensors() {
		return sensors;
	}

	public void setSensors(LinkedList<SensorResponsDto> sensors) {
		this.sensors = sensors;
	}

}
