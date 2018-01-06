package it.unifi.sam.terreni.weatherSt.model.sensor.units.utils;

public enum TemperatureUnits {
	CELSIUS("Celsius", "°C"),
	FAHRENHEIT("Fahrenheit","°F"),
	KELVIN("Kelvin","K");
	
	private String name;
	private String unit;
	
	private TemperatureUnits(String name, String unit) {
		this.name = name;
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit;
	}
}
