package it.unifi.sam.terreni.weatherSt.model.sensor.units;

public enum UnitMeasure {
	PASCAL("Pascal", "Pa"),
	BAR("Bar","bar"),
	POUNDS_PER_SQUARE("Pounds per square inch","psi"),
	ATMOSPHERE("atmosphere","atm"),
	M_S("m/s","m/s"),
	KM_H("km/h","km/h"),
	MPH("mph","mph"),
	KNOT("knot","knot"),
	FT_S("ft/s","ft/s"),
	CELSIUS("Celsius", "°C"),
	FAHRENHEIT("Fahrenheit","°F"),
	KELVIN("Kelvin","K");
	
	private String name;
	private String unit;
	
	private UnitMeasure(String name, String unit) {
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
