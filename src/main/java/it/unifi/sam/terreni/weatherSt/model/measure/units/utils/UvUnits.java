package it.unifi.sam.terreni.weatherSt.model.measure.units.utils;

public enum UvUnits {
	UV("Uv","");
	
	private String name;
	private String unit;
	
	private UvUnits(String name, String unit) {
		this.name = name;
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit;
	}
	
	public static UvUnits getFromString(String unit) {
		if(unit.equals(UV.name)) {
			return UV;
		}else {
			return null;
		}
	}
}
