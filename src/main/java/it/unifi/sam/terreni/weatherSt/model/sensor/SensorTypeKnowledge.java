package it.unifi.sam.terreni.weatherSt.model.sensor;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	@ManyToMany(fetch = FetchType.LAZY, targetEntity=UnitMeasureKnowledge.class , cascade = CascadeType.ALL )
	private List<UnitMeasureKnowledge> unitMeasures;
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

	public List<UnitMeasureKnowledge> getUnitMeasures() {
		return unitMeasures;
	}

	public void setUnitMeasures(List<UnitMeasureKnowledge> unitMeasures) {
		this.unitMeasures = unitMeasures;
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
		private String description;
		private List<UnitMeasureKnowledge> unitMeasures;
		private SensorTemplate sensorTemplate;

		public SensorTypeBuilder unitMeasureKnowledges(List<UnitMeasureKnowledge> unitMeasures) {
			this.unitMeasures = unitMeasures;
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
			sensorType.setUnitMeasures(unitMeasures);
			sensorType.setSensorTemplate(sensorTemplate);

			return sensorType;
		}
	}

}
