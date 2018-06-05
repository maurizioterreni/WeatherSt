package it.unifi.sam.terreni.weatherSt.model.measure;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;
import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.usage.Usage;
import it.unifi.sam.terreni.weatherSt.model.usage.UsageVisitor;

@Entity
@Table(name = "unit_measure_knowledge")
public class UnitMeasureKnowledge extends BaseEntity implements Serializable, Usage {
	private static final long serialVersionUID = 1L;

	private String symbol;
	private String name;

	protected UnitMeasureKnowledge(){
		super();
	}

	public UnitMeasureKnowledge(String uuid) {
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


	public static class UnitMeasureBuilder{
		private String symbol;
		private String name;

		public UnitMeasureBuilder symbol(String symbol) {
			this.symbol = symbol;
			return this;
		}

		public UnitMeasureBuilder name(String name) {
			this.name = name;
			return this;
		}

		public UnitMeasureKnowledge build() {
			UnitMeasureKnowledge unitMeasure = ModelFactory.unitMeasure();

			unitMeasure.setName(name);
			unitMeasure.setSymbol(symbol);

			return unitMeasure;
		}
	}


	@Override
	public void accept(UsageVisitor visitor) {
		visitor.visitUnitMeasureKnowledge(this);
	}

}
