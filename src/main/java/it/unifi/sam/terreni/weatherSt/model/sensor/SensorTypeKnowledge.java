package it.unifi.sam.terreni.weatherSt.model.sensor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
	@ManyToOne
	private SensorTemplate sensorTemplate;
	
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

	
	
	public SensorTemplate getSensorTemplate() {
		return sensorTemplate;
	}

	public void setSensorTemplate(SensorTemplate sensorTemplate) {
		this.sensorTemplate = sensorTemplate;
	}

	@Override
	public void accept(UsageVisitor visitor) {
		// TODO Auto-generated method stub

	}
	public static class SensorTypeBuilder{
		private SensorTemplate sensorTemplate;
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
		public SensorTypeBuilder sensorTemplate(SensorTemplate sensorTemplate) {
			this.sensorTemplate = sensorTemplate;
			return this;
		}
		
		public SensorTypeKnowledge build() {
			SensorTypeKnowledge sensorType = ModelFactory.sensorType();

			sensorType.setDescription(description);
			sensorType.setUnitMeasure(unitMeasure);
			sensorType.setSensorTemplate(sensorTemplate);

			return sensorType;
		}
	}

}