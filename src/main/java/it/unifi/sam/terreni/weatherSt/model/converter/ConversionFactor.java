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
	
	private Float conversionFactorMul;
	private Float conversionFactorAdd;

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


	@Override
	public void accept(UsageVisitor visitor) {
		// TODO Auto-generated method stub
		
	}
	
	

	public static class ConversionFactorBuilder{
		private UnitMeasureKnowledge fromUnitMeasure;
		private UnitMeasureKnowledge toUnitMeasure;
		private Float conversionFactorMul;
		private Float conversionFactorAdd;
		
		public ConversionFactorBuilder fromUnitMeasure(UnitMeasureKnowledge fromUnitMeasure) {
			this.fromUnitMeasure = fromUnitMeasure;
			return this;
		}
		public ConversionFactorBuilder toUnitMeasure(UnitMeasureKnowledge toUnitMeasure) {
			this.toUnitMeasure = toUnitMeasure;
			return this;
		}
		public ConversionFactorBuilder conversionFactorMul(Float conversionFactorMul) {
			this.conversionFactorMul = conversionFactorMul;
			return this;
		}
		public ConversionFactorBuilder conversionFactorAdd(Float conversionFactorAdd) {
			this.conversionFactorAdd = conversionFactorAdd;
			return this;
		}
		public ConversionFactor build() {
			ConversionFactor factor = ModelFactory.conversionFactor();
			
			factor.setConversionFactorMul(conversionFactorMul);
			factor.setConversionFactorAdd(conversionFactorAdd);
			factor.setFromUnitMeasure(fromUnitMeasure);
			factor.setToUnitMeasure(toUnitMeasure);
			
			return factor;
		}
	}
}
