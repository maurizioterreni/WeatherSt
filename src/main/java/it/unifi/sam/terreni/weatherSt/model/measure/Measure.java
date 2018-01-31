package it.unifi.sam.terreni.weatherSt.model.measure;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;
import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.model.usage.Usage;
import it.unifi.sam.terreni.weatherSt.model.usage.UsageVisitor;

@Entity
@Table(name="measure")
public class Measure extends BaseEntity implements Usage{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "sensor")
	private Sensor sensor;
	@OneToOne()
	private Value value;
	private Long timestamp;
	
	protected Measure(){
		super();
	}

	public Measure(String uuid) {
		super(uuid);
	}
	
	public static MeasureBuilder buider() {
		return new MeasureBuilder();
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	@JsonIgnore
	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	@Override
	public void accept(UsageVisitor visitor) {
		visitor.visitMeasure(this);
	}

	public static class MeasureBuilder {
		private Sensor sensor;
		private Value value;
		private Long timestamp;
		
		public MeasureBuilder sensor(Sensor sensor) {
			this.sensor = sensor;
			return this;
		}
		public MeasureBuilder value(Value value) {
			this.value = value;
			return this;
		}
		public MeasureBuilder timestamp(Long timestamp) {
			this.timestamp = timestamp;
			return this;
		}
		
		public Measure build() {
			Measure measure = ModelFactory.measure();
			
			this.sensor.addMeasuer(measure);
			measure.sensor = this.sensor;
			measure.timestamp = this.timestamp;
			measure.value = this.value;
			
			return measure;
		}
		
	}

}
