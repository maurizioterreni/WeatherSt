package it.unifi.sam.terreni.weatherSt.model.sensor.units.utils;

public enum UVUnits {
	UV("Uv","");
	
	private String name;
	private String unit;
	
	private UVUnits(String name, String unit) {
		this.name = name;
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit;
	}
	
	public static UVUnits getFromString(String unit) {
		if(unit.equals(UV.name)) {
			return UV;
		}else {
			return null;
		}
	}
}
