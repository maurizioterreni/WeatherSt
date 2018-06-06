package it.unifi.sam.terreni.weatherSt.model.sensor;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
	@OneToMany(fetch = FetchType.EAGER, targetEntity=UnitMeasureKnowledge.class , cascade = CascadeType.ALL )
	private List<UnitMeasureKnowledge> unitMeasures;

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

	@Override
	public void accept(UsageVisitor visitor) {
		// TODO Auto-generated method stub

	}
	public static class SensorTypeBuilder{
		private String description;
		private List<UnitMeasureKnowledge> unitMeasures;

		public SensorTypeBuilder unitMeasureKnowledges(List<UnitMeasureKnowledge> unitMeasures) {
			this.unitMeasures = unitMeasures;
			return this;
		}

		public SensorTypeBuilder descrition(String description) {
			this.description = description;
			return this;
		}

		public SensorTypeKnowledge build() {
			SensorTypeKnowledge sensorType = ModelFactory.sensorType();

			sensorType.description = this.description;
			sensorType.setUnitMeasures(unitMeasures);

			return sensorType;
		}
	}

}
