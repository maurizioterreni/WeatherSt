package it.unifi.sam.terreni.weatherSt.model.measure.units.utils;

public enum SpeedUnits {
	M_S("m/s","m/s"),
	KM_H("km/h","km/h"),
	MPH("mph","mph"),
	KNOT("knot","knot"),
	FT_S("ft/s","ft/s");
	
	private String name;
	private String unit;
	
	private SpeedUnits(String name, String unit) {
		this.name = name;
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit;
	}
	
	public static SpeedUnits getFromString(String unit) {
		if(unit.equals(M_S.name)) {
			return M_S;
		}if(unit.equals(KM_H.name)) {
			return KM_H;
		}if(unit.equals(MPH.name)) {
			return MPH;
		}if(unit.equals(KNOT.name)) {
			return KNOT;
		}if(unit.equals(FT_S.name)) {
			return FT_S;
		}else {
			return null;
		}
	}
}
