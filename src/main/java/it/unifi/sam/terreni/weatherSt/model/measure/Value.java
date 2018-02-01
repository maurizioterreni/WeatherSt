package it.unifi.sam.terreni.weatherSt.model.measure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;
import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.usage.Usage;
import it.unifi.sam.terreni.weatherSt.model.usage.UsageVisitor;

@Entity
@Table(name="value")
public class Value extends BaseEntity implements Usage{
	private static final long serialVersionUID = 1L;
	
	private float value;
	
	@OneToOne()
	private UnitMeasureFamily unitMeasureFamily;
	
	Value(){
		super();
	}
	
	public Value(String uuid) {
		super(uuid);
	}
	
	public static ValueBuilder builder() {
		return new ValueBuilder();
	}

	@Override
	public void accept(UsageVisitor visitor) {
	}

	public UnitMeasureFamily getUnitMeasureFamily() {
		return unitMeasureFamily;
	}

	public void setUnitMeasureFamily(UnitMeasureFamily unitMeasureFamily) {
		this.unitMeasureFamily = unitMeasureFamily;
	}


	@Column(precision=4)
	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}
	
	public static class ValueBuilder{
		private float value;
		private UnitMeasureFamily unitMeasureFamily;
		
		public ValueBuilder value(float value) {
			this.value = value;
			return this;
		}
		
		public ValueBuilder unitMeasureFamily(UnitMeasureFamily unitMeasureFamily) {
			if(unitMeasureFamily == null)
				throw new NullPointerException("unit Measure can't not be null");
			this.unitMeasureFamily = unitMeasureFamily;
			return this;
		}
		
		public Value build() {
			Value value = ModelFactory.value();
			
			value.value = this.value;
			value.unitMeasureFamily = this.unitMeasureFamily;
			
			return value;
		}
	}
}
