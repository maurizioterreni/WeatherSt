package it.unifi.sam.terreni.weatherSt.dto.user;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class LoginDto extends BaseDto{
	private static final long serialVersionUID = -7966832753869206175L;
	private String username;
	private String password;
	
	public LoginDto() {
		
	}
	
	public static LoginDtoBuilder builder() {
		return new LoginDtoBuilder();
	}

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
	
	public static class LoginDtoBuilder{
		private String username;
		private String password;
		
		public LoginDtoBuilder username(String username) {
			this.username = username;
			return this;
		}
		
		public LoginDtoBuilder password(String password) {
			this.password = password;
			return this;
		}
		
		public LoginDto build() {
			LoginDto dto = new LoginDto();
			
			dto.setPassword(password);
			dto.setUsername(username);
			
			return dto;
		}
	}
	
}
