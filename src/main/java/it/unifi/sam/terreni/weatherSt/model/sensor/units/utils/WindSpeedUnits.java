package it.unifi.sam.terreni.weatherSt.model.sensor.units.utils;

public enum WindSpeedUnits {
	M_S("m/s","m/s"),
	KM_H("km/h","km/h"),
	MPH("mph","mph"),
	KNOT("knot","knot"),
	FT_S("ft/s","ft/s");
	
	private String name;
	private String unit;
	
	private WindSpeedUnits(String name, String unit) {
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
