package it.unifi.sam.terreni.weatherSt.model.measure;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;
import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.usage.Usage;
import it.unifi.sam.terreni.weatherSt.model.usage.UsageVisitor;

@Entity
@Table(name="measure")
public class Measure extends BaseEntity implements Usage{
	private static final long serialVersionUID = 1L;

	private LocalDateTime localDateTime;
	private Float quantity;
	@ManyToOne
	private UnitMeasureKnowledge unitMeasure;
	
	protected Measure(){
		super();
	}

	public Measure(String uuid) {
		super(uuid);
	}
	
	public static MeasureBuilder buider() {
		return new MeasureBuilder();
	}

	

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	
	

	public UnitMeasureKnowledge getUnitMeasure() {
		return unitMeasure;
	}

	public void setUnitMeasure(UnitMeasureKnowledge unitMeasure) {
		this.unitMeasure = unitMeasure;
	}

	@Override
	public void accept(UsageVisitor visitor) {
		visitor.visitMeasure(this);
	}
	
	public static class MeasureBuilder {
		private Float quantity;
		private LocalDateTime localDateTime;
		private UnitMeasureKnowledge unitMeasure;
		
		public MeasureBuilder quantity(Float quantity) {
			this.quantity = quantity;
			return this;
		}
		public MeasureBuilder localDateTime(LocalDateTime localDateTime) {
			this.localDateTime = localDateTime;
			return this;
		}
		
		public MeasureBuilder unitMeasure(UnitMeasureKnowledge unitMeasure) {
			this.unitMeasure = unitMeasure;
			return this;
		}
		
		public Measure build() {
			Measure measure = ModelFactory.measure();
			
			measure.localDateTime = this.localDateTime;
			measure.quantity = this.quantity;
			measure.unitMeasure = this.unitMeasure;
			
			return measure;
		}
		
	}

}
