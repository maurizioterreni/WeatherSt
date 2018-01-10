package it.unifi.sam.terreni.weatherSt.model.sensor.units.utils;

public enum HumidityUnits {
	PERCENTAGE("Percentage","%");
	
	private String name;
	private String unit;
	
	private HumidityUnits(String name, String unit) {
		this.name = name;
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit;
	}
	
	public static HumidityUnits getFromString(String unit) {
		if(unit.equals(PERCENTAGE.name)) {
			return PERCENTAGE;
		}else {
			return null;
		}
	}
}
