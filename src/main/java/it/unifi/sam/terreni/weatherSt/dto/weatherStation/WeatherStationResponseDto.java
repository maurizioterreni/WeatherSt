package it.unifi.sam.terreni.weatherSt.dto.weatherStation;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class WeatherStationResponseDto extends BaseDto{
	private static final long serialVersionUID = 9222022175701191010L;


	private Long id;
	private String description;
	private String longitude;
	private String latitude;
	private String image;

	public WeatherStationResponseDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
