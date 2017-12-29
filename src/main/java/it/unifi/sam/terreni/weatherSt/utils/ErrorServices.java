package it.unifi.sam.terreni.weatherSt.utils;

public enum ErrorServices {
	NULL_OBJECT("Object cannot be null "),
	OBJECT_NOT_FOUND("Object not found "),
	OBJECTS_NOT_COMPATIBLE("Objects not compatible "),
	UNAUTHORIZED("Unauthorized ");
	
	private String message;
	
	private ErrorServices(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
