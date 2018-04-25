package it.unifi.sam.terreni.weatherSt.dto.user;

import java.util.Set;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;

public class UserDto extends BaseDto{
	private static final long serialVersionUID = 1L;
	private String username;
	private String email;
	private Long weatherId;
	private String token;
	private String userRole;
	private Set<UnitMeasureKnowledge> unitMeasure;
	
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

	public Set<UnitMeasureKnowledge> getUnitMeasure() {
		return unitMeasure;
	}

	public void setUnitMeasure(Set<UnitMeasureKnowledge> unitMeasure) {
		this.unitMeasure = unitMeasure;
	}
	
	
	
}
