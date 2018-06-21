package it.unifi.sam.terreni.weatherSt.dto.conversion;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class ConversionFactoryDto extends BaseDto{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String fromSymbol;
	private String toSymbol;
	private Float conversionFactorMul;
	private Float conversionFactorAdd;
	private Float conversionFactorDiv;
	
	public static ConversionFactoryDtoBuilder builder() {
		return new ConversionFactoryDtoBuilder();
	}
	
	private ConversionFactoryDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromSymbol() {
		return fromSymbol;
	}

	public void setFromSymbol(String fromSymbol) {
		this.fromSymbol = fromSymbol;
	}

	public String getToSymbol() {
		return toSymbol;
	}

	public void setToSymbol(String toSymbol) {
		this.toSymbol = toSymbol;
	}

	public Float getConversionFactorMul() {
		return conversionFactorMul;
	}

	public void setConversionFactorMul(Float conversionFactorMul) {
		this.conversionFactorMul = conversionFactorMul;
	}
	
	
	
	public Float getConversionFactorAdd() {
		return conversionFactorAdd;
	}

	public void setConversionFactorAdd(Float conversionFactorAdd) {
		this.conversionFactorAdd = conversionFactorAdd;
	}



	public Float getConversionFactorDiv() {
		return conversionFactorDiv;
	}

	public void setConversionFactorDiv(Float conversionFactorDiv) {
		this.conversionFactorDiv = conversionFactorDiv;
	}



	public static class ConversionFactoryDtoBuilder{
		private Long id;
		private String fromSymbol;
		private String toSymbol;
		private Float conversionFactorMul;
		private Float conversionFactorAdd;
		private Float conversionFactorDiv;
		
		
		public ConversionFactoryDtoBuilder id(Long id) {
			this.id = id;
			return this;
		}
		public ConversionFactoryDtoBuilder fromSymbol(String fromSymbol) {
			this.fromSymbol = fromSymbol;
			return this;
		}
		public ConversionFactoryDtoBuilder toSymbol(String toSymbol) {
			this.toSymbol = toSymbol;
			return this;
		}
		public ConversionFactoryDtoBuilder conversionFactorMul(Float conversionFactorMul) {
			this.conversionFactorMul = conversionFactorMul;
			return this;
		}
		public ConversionFactoryDtoBuilder conversionFactorAdd(Float conversionFactorAdd) {
			this.conversionFactorAdd = conversionFactorAdd;
			return this;
		}
		public ConversionFactoryDtoBuilder conversionFactorDiv(Float conversionFactorDiv) {
			this.conversionFactorDiv = conversionFactorDiv;
			return this;
		}
		
		public ConversionFactoryDto build(){
			ConversionFactoryDto dto = new ConversionFactoryDto();
			
			dto.setConversionFactorMul(conversionFactorMul);
			dto.setConversionFactorAdd(conversionFactorAdd);
			dto.setConversionFactorDiv(conversionFactorDiv);
			dto.setFromSymbol(fromSymbol);
			dto.setId(id);
			dto.setToSymbol(toSymbol);
			
			return dto;
		}
	}
	
}
