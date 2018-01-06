package it.unifi.sam.terreni.weatherSt.model.sensor.units.utils;

public enum PressureUnits {
	PASCAL("Pascal", "Pa"),
	BAR("Bar","bar"),
	HECTOPASCAL("Hectopascal","hPa"),
	POUNDS_PER_SQUARE("Pounds per square inch","psi"),
	ATMOSPHERE("atmosphere","atm");
	
	private String name;
	private String unit;
	
	private PressureUnits(String name, String unit) {
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
