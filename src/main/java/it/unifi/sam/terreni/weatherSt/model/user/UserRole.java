package it.unifi.sam.terreni.weatherSt.model.user;

public enum UserRole {
	ADMINISTRATOR("administrator"),
	USER("user"),
	WEATHERSTATION("weather station");
	
	private String name;
	
	private UserRole(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static UserRole userRoleFromStr(String name) {
		if(name.equals("administrator"))
			return ADMINISTRATOR;
		else if(name.equals("user"))
			return USER;
		else if(name.equals("weather station"))
			return WEATHERSTATION;
		else 
			return null;
	}
}
