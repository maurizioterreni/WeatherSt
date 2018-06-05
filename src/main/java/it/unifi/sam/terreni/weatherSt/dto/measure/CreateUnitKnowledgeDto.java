package it.unifi.sam.terreni.weatherSt.dto.measure;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class CreateUnitKnowledgeDto extends BaseDto {
	private static final long serialVersionUID = 1L;

	private String symbol;
	private String name;
	
	public CreateUnitKnowledgeDto() {}

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
	
	
}
