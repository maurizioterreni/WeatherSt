package it.unifi.sam.terreni.weatherSt.dto.user;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class RegistrationDto extends BaseDto{
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String email;
	private Long weatherId;
	
	
	public RegistrationDto() {}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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

	
	
	
}
