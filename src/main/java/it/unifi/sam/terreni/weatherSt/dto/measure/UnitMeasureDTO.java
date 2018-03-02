package it.unifi.sam.terreni.weatherSt.dto.measure;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class UnitMeasureDTO extends BaseDto{
	private static final long serialVersionUID = 1L;
	private String symbol;
	private String name;
	private Float convertionFactor;

	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getConvertionFactor() {
		return convertionFactor;
	}
	public void setConvertionFactor(Float convertionFactor) {
		this.convertionFactor = convertionFactor;
	}
	
}
