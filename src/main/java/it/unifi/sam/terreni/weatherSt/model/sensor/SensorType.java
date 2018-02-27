package it.unifi.sam.terreni.weatherSt.model.sensor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;
import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasure;
import it.unifi.sam.terreni.weatherSt.model.usage.Usage;
import it.unifi.sam.terreni.weatherSt.model.usage.UsageVisitor;

@Entity
@Table(name="sensor_type")
public class SensorType  extends BaseEntity implements Usage{
	private static final long serialVersionUID = -89078019304345613L;

	private String description;
	@OneToOne()
	private UnitMeasure unitMeasure;

	SensorType() {
		super();
	}
	
	public SensorType(String uuid) {
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
	
	public UnitMeasure getUnitMeasure() {
		return unitMeasure;
	}

	public void setUnitMeasure(UnitMeasure unitMeasure) {
		this.unitMeasure = unitMeasure;
	}

	@Override
	public void accept(UsageVisitor visitor) {
		// TODO Auto-generated method stub

	}
	public static class SensorTypeBuilder{
		private String description;
		private UnitMeasure unitMeasure;
		
		public SensorTypeBuilder unitMeasure(UnitMeasure unitMeasure) {
			this.unitMeasure = unitMeasure;
			return this;
		}
		
		public SensorTypeBuilder descrition(String description) {
			this.description = description;
			return this;
		}
		
		public SensorType build() {
			SensorType sensorType = ModelFactory.sensorType();

			sensorType.description = this.description;
			sensorType.setUnitMeasure(unitMeasure);

			return sensorType;
		}
	}

}