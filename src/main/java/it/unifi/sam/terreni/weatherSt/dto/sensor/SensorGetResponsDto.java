package it.unifi.sam.terreni.weatherSt.dto.sensor;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;
import it.unifi.sam.terreni.weatherSt.dto.measure.MeasureDto;

public class SensorGetResponsDto extends BaseDto{
	private static final long serialVersionUID = 1L;

	private String description;
	private String symbol;
	private String name;
	private MeasureDto maxMeasure;
	private MeasureDto measure;
	private MeasureDto minMeasue;
	private Float conversionFactor;
	
	public SensorGetResponsDto() {
		
	}

	public static SensorGetResponsDtoBuilder builder() {
		return new SensorGetResponsDtoBuilder();
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	
	
	
//	public 
	
	public Float getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(Float conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public MeasureDto getMaxMeasure() {
		return maxMeasure;
	}

	public void setMaxMeasure(MeasureDto maxMeasure) {
		this.maxMeasure = maxMeasure;
	}

	public MeasureDto getMeasure() {
		return measure;
	}

	public void setMeasure(MeasureDto measure) {
		this.measure = measure;
	}

	public MeasureDto getMinMeasue() {
		return minMeasue;
	}

	public void setMinMeasue(MeasureDto minMeasue) {
		this.minMeasue = minMeasue;
	}




	public static class SensorGetResponsDtoBuilder{
		private String description;
		private String symbol;
		private String name;
		private MeasureDto maxMeasure;
		private MeasureDto measure;
		private Float conversionFactor;
		private MeasureDto minMeasure;
		
		public SensorGetResponsDtoBuilder description(String description) {
			this.description = description;
			return this;
		}
		public SensorGetResponsDtoBuilder symbol(String symbol) {
			this.symbol = symbol;
			return this;
		}
		public SensorGetResponsDtoBuilder name(String name) {
			this.name = name;
			return this;
		}
		public SensorGetResponsDtoBuilder maxMeasure(MeasureDto maxMeasure) {
			this.maxMeasure = maxMeasure;
			return this;
		}
		public SensorGetResponsDtoBuilder measure(MeasureDto measure) {
			this.measure = measure;
			return this;
		}
		public SensorGetResponsDtoBuilder minMeasure(MeasureDto minMeasure) {
			this.minMeasure = minMeasure;
			return this;
		}
		public SensorGetResponsDtoBuilder conversionFactor(Float conversionFactor) {
			this.conversionFactor = conversionFactor;
			return this;
		}
		
		
		
		public SensorGetResponsDto build() {
			SensorGetResponsDto dto = new SensorGetResponsDto();
			
			dto.setDescription(description);
			dto.setSymbol(symbol);
			dto.setName(name);
			dto.setMaxMeasure(maxMeasure);
			dto.setMeasure(measure);
			dto.setMinMeasue(minMeasure);
			dto.setConversionFactor(conversionFactor);
					
			return dto;
		}
	}
}
