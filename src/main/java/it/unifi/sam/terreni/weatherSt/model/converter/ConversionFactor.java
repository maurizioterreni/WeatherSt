package it.unifi.sam.terreni.weatherSt.model.converter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;
import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;
import it.unifi.sam.terreni.weatherSt.model.usage.Usage;
import it.unifi.sam.terreni.weatherSt.model.usage.UsageVisitor;

@Entity
@Table(name="conversion_factor")
public class ConversionFactor extends BaseEntity  implements Usage{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private UnitMeasureKnowledge fromUnitMeasure;
	@ManyToOne
	private UnitMeasureKnowledge toUnitMeasure;
	
	private Float conversionFactor;

	ConversionFactor(){
		super();
	}
	
	
	public ConversionFactor(String uuid) {
		super(uuid);
	}
	
	public static ConversionFactorBuilder builder() {
		return new ConversionFactorBuilder();
	}
	
	public UnitMeasureKnowledge getFromUnitMeasure() {
		return fromUnitMeasure;
	}


	public void setFromUnitMeasure(UnitMeasureKnowledge fromUnitMeasure) {
		this.fromUnitMeasure = fromUnitMeasure;
	}


	public UnitMeasureKnowledge getToUnitMeasure() {
		return toUnitMeasure;
	}


	public void setToUnitMeasure(UnitMeasureKnowledge toUnitMeasure) {
		this.toUnitMeasure = toUnitMeasure;
	}


	public Float getConversionFactor() {
		return conversionFactor;
	}


	public void setConversionFactor(Float conversionFactor) {
		this.conversionFactor = conversionFactor;
	}


	@Override
	public void accept(UsageVisitor visitor) {
		// TODO Auto-generated method stub
		
	}
	
	

	public static class ConversionFactorBuilder{
		private UnitMeasureKnowledge fromUnitMeasure;
		private UnitMeasureKnowledge toUnitMeasure;
		private Float conversionFactor;
		
		public ConversionFactorBuilder fromUnitMeasure(UnitMeasureKnowledge fromUnitMeasure) {
			this.fromUnitMeasure = fromUnitMeasure;
			return this;
		}
		public ConversionFactorBuilder toUnitMeasure(UnitMeasureKnowledge toUnitMeasure) {
			this.toUnitMeasure = toUnitMeasure;
			return this;
		}
		public ConversionFactorBuilder conversionFactor(Float conversionFactor) {
			this.conversionFactor = conversionFactor;
			return this;
		}
		public ConversionFactor build() {
			ConversionFactor factor = ModelFactory.conversionFactor();
			
			factor.setConversionFactor(conversionFactor);
			factor.setFromUnitMeasure(fromUnitMeasure);
			factor.setToUnitMeasure(toUnitMeasure);
			
			return factor;
		}
	}
}
