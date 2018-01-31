package it.unifi.sam.terreni.weatherSt.model.measure.units.utils;

public enum DirectionUnits {
	DEGREE("Degree","°");

	private String name;
	private String unit;
	
	private DirectionUnits(String name, String unit) {
		this.name = name;
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit;
	}
	
	public static DirectionUnits getFromString(String unit) {
		if(unit.equals(DEGREE.name)) {
			return DEGREE;
		}else {
			return null;
		}
	}
}
