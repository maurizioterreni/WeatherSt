package it.unifi.sam.terreni.weatherSt.dto.user;

import java.util.Set;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class UserDto extends BaseDto{
	private static final long serialVersionUID = 1L;
	private String username;
	private String email;
	private Long weatherId;
	private String token;
	private String userRole;
	private Set<Long> unitMeasure;
	private Set<Long> weatherLikes;
	
	
	public UserDto() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getWeatherId() {
		return weatherId;
	}

	public void setWeatherId(Long weatherId) {
		this.weatherId = weatherId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Set<Long> getUnitMeasure() {
		return unitMeasure;
	}

	public void setUnitMeasure(Set<Long> unitMeasure) {
		this.unitMeasure = unitMeasure;
	}

	public Set<Long> getWeatherLikes() {
		return weatherLikes;
	}

	public void setWeatherLikes(Set<Long> weatherLikes) {
		this.weatherLikes = weatherLikes;
	}
	
	
	
}
