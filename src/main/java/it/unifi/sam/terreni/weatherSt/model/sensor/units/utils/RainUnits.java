package it.unifi.sam.terreni.weatherSt.model.sensor.units.utils;

public enum RainUnits {
	MILLIMETRE("Millimetre","mm");
	
	private String name;
	private String unit;
	
	private RainUnits(String name, String unit) {
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
