package it.unifi.sam.terreni.weatherSt.dto.measure;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class UnitMeasureDto extends BaseDto{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String symbol;
	private String name;
	
	public static UnitMeasureDtoBuilder builder() {
		return new UnitMeasureDtoBuilder();
	}

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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public static class UnitMeasureDtoBuilder{
		private Long id;
		private String symbol;
		private String name;
		
		public UnitMeasureDtoBuilder id(Long id) {
			this.id = id;
			return this;
		}
		public UnitMeasureDtoBuilder symbol(String symbol) {
			this.symbol = symbol;
			return this;
		}
		public UnitMeasureDtoBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public UnitMeasureDto build() {
			UnitMeasureDto dto = new UnitMeasureDto();
			
			dto.setId(id);
			dto.setName(name);
			dto.setSymbol(symbol);
			
			return dto;
		}
	}
	
}
