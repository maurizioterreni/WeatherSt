package it.unifi.sam.terreni.weatherSt.dto.weatherStation;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class WeatherStationPostRequestDto extends BaseDto{
	private static final long serialVersionUID = 1L;

	
	private String longitude;
	private String latitude;
	private String description;

	public WeatherStationPostRequestDto() {}

	public static WeatherStationPostRequestDtoBuilder builder() {
		return new WeatherStationPostRequestDtoBuilder();
	}

	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public static class WeatherStationPostRequestDtoBuilder{
		private String longitude;
		private String latitude;
		private String description;
		
		public WeatherStationPostRequestDtoBuilder longitude(String longitude) {
			this.longitude = longitude;
			return this;
		}
		public WeatherStationPostRequestDtoBuilder latitude(String latitude) {
			this.latitude = latitude;
			return this;
		}
		public WeatherStationPostRequestDtoBuilder description(String description) {
			this.longitude = description;
			return this;
		}
		
		public WeatherStationPostRequestDto build() {
			WeatherStationPostRequestDto weatherStation = new WeatherStationPostRequestDto();
			
			weatherStation.setDescription(description);
			weatherStation.setLatitude(latitude);
			weatherStation.setLongitude(longitude);
			
			return weatherStation;
		}
		
		
	}
}
