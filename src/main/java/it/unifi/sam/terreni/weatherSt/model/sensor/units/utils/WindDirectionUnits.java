package it.unifi.sam.terreni.weatherSt.model.sensor.units.utils;

public enum WindDirectionUnits {
	DEGREE("Degree","°");

	private String name;
	private String unit;
	
	private WindDirectionUnits(String name, String unit) {
		this.name = name;
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit;
	}
	
	public static WindDirectionUnits getFromString(String unit) {
		if(unit.equals(DEGREE.name)) {
			return DEGREE;
		}else {
			return null;
		}
	}
}
