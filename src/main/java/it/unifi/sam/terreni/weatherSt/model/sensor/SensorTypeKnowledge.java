package it.unifi.sam.terreni.weatherSt.model.sensor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;
import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;
import it.unifi.sam.terreni.weatherSt.model.usage.Usage;
import it.unifi.sam.terreni.weatherSt.model.usage.UsageVisitor;

@Entity
@Table(name="sensor_type_knowledge")
public class SensorTypeKnowledge  extends BaseEntity implements Usage{
	private static final long serialVersionUID = -89078019304345613L;

	private String description;
	@OneToOne()
	private UnitMeasureKnowledge unitMeasure;

	SensorTypeKnowledge() {
		super();
	}
	
	public SensorTypeKnowledge(String uuid) {
		super(uuid);
	}
	
	public static SensorTypeBuilder builder() {
		return new SensorTypeBuilder();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public UnitMeasureKnowledge getUnitMeasure() {
		return unitMeasure;
	}

	public void setUnitMeasure(UnitMeasureKnowledge unitMeasure) {
		this.unitMeasure = unitMeasure;
	}

	@Override
	public void accept(UsageVisitor visitor) {
		// TODO Auto-generated method stub

	}
	public static class SensorTypeBuilder{
		private String description;
		private UnitMeasureKnowledge unitMeasure;
		
		public SensorTypeBuilder unitMeasureKnowledge(UnitMeasureKnowledge unitMeasure) {
			this.unitMeasure = unitMeasure;
			return this;
		}
		
		public SensorTypeBuilder descrition(String description) {
			this.description = description;
			return this;
		}
		
		public SensorTypeKnowledge build() {
			SensorTypeKnowledge sensorType = ModelFactory.sensorType();

			sensorType.description = this.description;
			sensorType.setUnitMeasure(unitMeasure);

			return sensorType;
		}
	}

}