package it.unifi.sam.terreni.weatherSt.dto.measure;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class MeasureDto extends BaseDto{
	private static final long serialVersionUID = 1L;
	private String quantity;
	private String name;
	private String symbol;
	private String dateTime;
	
	public MeasureDto() {
		
	}
	
	public static MeasurePostRequestDtoBuilder builder() {
		return new MeasurePostRequestDtoBuilder();
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}



	public static class MeasurePostRequestDtoBuilder{
		private String quantity;
		private String name;
		private String symbol;
		private String dateTime;
		
		public MeasurePostRequestDtoBuilder name(String name) {
			this.name = name;
			return this;
		}
		public MeasurePostRequestDtoBuilder quantity(String quantity) {
			this.quantity = quantity;
			return this;
		}
		public MeasurePostRequestDtoBuilder symbol(String symbol) {
			this.symbol = symbol;
			return this;
		}
		public MeasurePostRequestDtoBuilder dateTime(String dateTime) {
			this.dateTime = dateTime;
			return this;
		}
		public MeasureDto build() {
			MeasureDto dto = new MeasureDto();
			
			dto.setQuantity(quantity);
			dto.setName(name);
			dto.setSymbol(symbol);
			dto.setDateTime(dateTime);
			
			return dto;
		}
	}
	
}
