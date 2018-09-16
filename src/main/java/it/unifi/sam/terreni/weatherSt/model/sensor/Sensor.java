package it.unifi.sam.terreni.weatherSt.model.sensor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;
import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.usage.Usage;
import it.unifi.sam.terreni.weatherSt.model.usage.UsageVisitor;

@Entity
@Table(name="sensors")
public class Sensor extends BaseEntity implements Usage{
	private static final long serialVersionUID = -89078019304365613L;

	@ManyToOne
	private SensorTypeKnowledge sensorType;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sensor" , cascade = CascadeType.REMOVE )
	private List<Measure> measures;

	private String title;

	Sensor() {
		super();
		measures = new ArrayList<>();
	}

	public Sensor(String uuid) {
		super(uuid);
		measures = new LinkedList<>();
	}

	public static SensorBuilder builder() {
		return new SensorBuilder();
	}

	@Enumerated(EnumType.STRING)
	public SensorTypeKnowledge getSensorType() {
		return sensorType;
	}

	public void setSensorType(SensorTypeKnowledge sensorType) {
		this.sensorType = sensorType;
	}
	@JsonIgnore
	public List<Measure> getMeasures() {
		return measures;
	}

	public void setMeasures(List<Measure> measures) {
		this.measures = measures;
	}

	public void addMeasuer(Measure measure) {
		this.measures.add(measure);
	}
	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void accept(UsageVisitor visitor) {
		visitor.visitSensor(this);
	}

	public static class SensorBuilder{
		private SensorTypeKnowledge sensorType;
		private String title;

		public SensorBuilder sensorType(SensorTypeKnowledge sensorType) {
			this.sensorType = sensorType;
			return this;
		}
		public SensorBuilder title(String title) {
			this.title = title;
			return this;
		}

		public Sensor build() {
			Sensor sensor = ModelFactory.sensor();

			sensor.sensorType = this.sensorType;
			sensor.title = this.title;

			return sensor;
		}
	}

}
