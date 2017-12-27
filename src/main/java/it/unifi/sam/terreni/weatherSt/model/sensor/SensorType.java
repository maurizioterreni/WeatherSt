package it.unifi.sam.terreni.weatherSt.model.sensor;


public enum SensorType {
	TEMPERATURE("Temperature"),
	HUMIDITY("Humidity"),
	PRESSURE("Pressure"),
	UV("Uv"),
	WIND_SPEED("Wind Speed"),
	WIND_DIRECTION("Wind Direction"),
	RAIN("Rain"),
	VISIBILITY("Visibility");
	
	private String type;
	
	private SensorType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
}
