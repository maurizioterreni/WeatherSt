package it.unifi.sam.terreni.weatherSt.model.sensor;

import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

	@OneToMany( targetEntity=Measure.class , cascade = CascadeType.REMOVE )
	private LinkedList<Measure> measures;
	
	Sensor() {
		super();
		measures = new LinkedList<>();
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
	public LinkedList<Measure> getMeasures() {
		return measures;
	}
	
	public void setMeasures(LinkedList<Measure> measures) {
		this.measures = measures;
	}
	
	public void addMeasuer(Measure measure) {
		this.measures.add(measure);
	}

	@Override
	public void accept(UsageVisitor visitor) {
		visitor.visitSensor(this);
	}
	
	public static class SensorBuilder{
		private SensorTypeKnowledge sensorType;

		public SensorBuilder sensorType(SensorTypeKnowledge sensorType) {
			this.sensorType = sensorType;
			return this;
		}
		
		public Sensor build() {
			Sensor sensor = ModelFactory.sensor();
			
			sensor.sensorType = this.sensorType;
			
			return sensor;
		}
	}
	
}
