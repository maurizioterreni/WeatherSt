package it.unifi.sam.terreni.weatherSt.model.util;

public enum InternationalSystemUnit {
	FREQUENCY("Hz","hertz"),
	POWER("W","watt"),
	PRESSURE("Pa", "pascal"),
	ENERGY("J","joule"),
	STRENGTH("N","newton"),
	VOLTAGE("V","volt"), 
	RESISTANCE("ohm","ohm"), 
	CONDUCTANCE("S","simens"),
	CAPACITY("F","farad"),
	MAGNETIC_FLUX("T","tesla"),
	INDUCTANCE("H","henry"),
	TEMPERATURE("C","celsius"),
	FLAT_CORNER("rad","radiant"),
	SOLID_ANGLE("sr","steradian"),
	LUMINOUS_FLUX("lm","lumen"),
	ILLUMINANCE("lx","lux"),
	AREA("m2","square meter"),
	VOLUME("m3","cubic meter"),
	SPEED("m/s","meter per second"),
	ACCELERATION("",""),
	ANGULAR_ACCELERATION("",""),
	DENSITY("kg/m3","kilogram per cubic meter");
	
	
	private String symbol;
	private String unitName;
	
	private InternationalSystemUnit(String symbol, String unitName) {
		this.symbol = symbol;
		this.unitName = unitName;
	}
	

	public String getSymbol() {
		return symbol;
	}

	public String getUnitName() {
		return unitName;
	}

}