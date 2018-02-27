package it.unifi.sam.terreni.weatherSt.model.measure;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;
import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.util.InternationalSystemUnit;

@Entity
@Table(name = "unit")
public class UnitMeasure extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String symbol;
	private String name;
	private Float convertionFactor;
	private InternationalSystemUnit internationalSystemUnit;
	
	protected UnitMeasure(){
		super();
	}
	
	public UnitMeasure(String uuid) {
		super(uuid);
	}
	
	public static UnitMeasureBuilder builder() {
		return new UnitMeasureBuilder();
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

	public Float getConvertionFactor() {
		return convertionFactor;
	}

	public void setConvertionFactor(Float convertionFactor) {
		this.convertionFactor = convertionFactor;
	}

	public InternationalSystemUnit getInternationalSystemUnit() {
		return internationalSystemUnit;
	}

	public void setInternationalSystemUnit(InternationalSystemUnit internationalSystemUnit) {
		this.internationalSystemUnit = internationalSystemUnit;
	}
	
	public static class UnitMeasureBuilder{
		private String symbol;
		private String name;
		private Float convertionFactor;
		private InternationalSystemUnit internationalSystemUnit;
		
		public UnitMeasureBuilder symbol(String symbol) {
			this.symbol = symbol;
			return this;
		}
		
		public UnitMeasureBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public UnitMeasureBuilder convertionFactor(Float convertionFactor) {
			this.convertionFactor = convertionFactor;
			return this;
		}
		
		public UnitMeasureBuilder internationalSystemUnit(InternationalSystemUnit internationalSystemUnit) {
			this.internationalSystemUnit = internationalSystemUnit;
			return this;
		}
		
		public UnitMeasure build() {
			UnitMeasure unitMeasure = ModelFactory.unitMeasure();
			
			unitMeasure.setConvertionFactor(convertionFactor);
			unitMeasure.setInternationalSystemUnit(internationalSystemUnit);
			unitMeasure.setName(name);
			unitMeasure.setSymbol(symbol);
			
			return unitMeasure;
		}
	}
	
}
