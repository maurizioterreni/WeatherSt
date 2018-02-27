package it.unifi.sam.terreni.weatherSt.model.measure;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private LocalDateTime localDateTime;
	private Float quantity;
	
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
		private Float quantity;
		private LocalDateTime localDateTime;
		
		public MeasureBuilder sensor(Sensor sensor) {
			this.sensor = sensor;
			return this;
		}
		public MeasureBuilder quantity(Float quantity) {
			this.quantity = quantity;
			return this;
		}
		public MeasureBuilder localDateTime(LocalDateTime localDateTime) {
			this.localDateTime = localDateTime;
			return this;
		}
		
		public Measure build() {
			Measure measure = ModelFactory.measure();
			
			this.sensor.addMeasuer(measure);
			measure.sensor = this.sensor;
			measure.localDateTime = this.localDateTime;
			measure.quantity = this.quantity;
			
			return measure;
		}
		
	}

}
