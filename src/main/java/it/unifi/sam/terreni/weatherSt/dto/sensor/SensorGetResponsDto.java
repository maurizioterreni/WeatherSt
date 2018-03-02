package it.unifi.sam.terreni.weatherSt.dto.sensor;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;
import it.unifi.sam.terreni.weatherSt.model.measure.Measure;

public class SensorGetResponsDto extends BaseDto{
	private static final long serialVersionUID = 1L;

	private String description;
	private String symbol;
	private String name;
	private Measure maxMeasure;
	private Measure measure;
	private Measure minMeasue;
	
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
	
	public Measure getMaxMeasure() {
		return maxMeasure;
	}

	public void setMaxMeasure(Measure maxMeasure) {
		this.maxMeasure = maxMeasure;
	}

	public Measure getMeasure() {
		return measure;
	}

	public void setMeasure(Measure measure) {
		this.measure = measure;
	}

	public Measure getMinMeasue() {
		return minMeasue;
	}

	public void setMinMeasue(Measure minMeasue) {
		this.minMeasue = minMeasue;
	}




	public static class SensorGetResponsDtoBuilder{
		private String description;
		private String symbol;
		private String name;
		private Measure maxMeasure;
		private Measure measure;
		private Measure minMeasure;
		
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
		public SensorGetResponsDtoBuilder maxMeasure(Measure maxMeasure) {
			this.maxMeasure = maxMeasure;
			return this;
		}
		public SensorGetResponsDtoBuilder measure(Measure measure) {
			this.measure = measure;
			return this;
		}
		public SensorGetResponsDtoBuilder minMeasure(Measure minMeasure) {
			this.minMeasure = minMeasure;
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
					
			return dto;
		}
	}
}
