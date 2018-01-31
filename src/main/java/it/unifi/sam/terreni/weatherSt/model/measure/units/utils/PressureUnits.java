package it.unifi.sam.terreni.weatherSt.model.measure.units.utils;

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
	
	public static PressureUnits getFromString(String unit) {
		if(unit.equals(PASCAL.name)) {
			return PASCAL;
		}else if(unit.equals(BAR.name)) {
			return BAR;
		}else if(unit.equals(HECTOPASCAL.name)) {
			return HECTOPASCAL;
		}else if(unit.equals(POUNDS_PER_SQUARE.name)) {
			return POUNDS_PER_SQUARE;
		}else if(unit.equals(ATMOSPHERE.name)) {
			return ATMOSPHERE;
		}else {
			return null;
		}
	}
}
