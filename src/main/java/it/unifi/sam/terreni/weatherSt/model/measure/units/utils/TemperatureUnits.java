package it.unifi.sam.terreni.weatherSt.model.measure.units.utils;

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
	
	public static TemperatureUnits getFromString(String unit) {
		if(unit.equals(CELSIUS.name)) {
			return CELSIUS;
		}else if(unit.equals(FAHRENHEIT.name)) {
			return FAHRENHEIT;
		}else if(unit.equals(KELVIN.name)) {
			return KELVIN;
		}else {
			return null;
		}
	}
}
