package it.unifi.sam.terreni.weatherSt.model.sensor;

import javax.persistence.Entity;
import javax.persistence.Table;

import it.unifi.sam.terreni.weatherSt.model.BaseEntity;
import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.usage.Usage;
import it.unifi.sam.terreni.weatherSt.model.usage.UsageVisitor;

@Entity
@Table(name="sensor_template")
public class SensorTemplate extends BaseEntity implements Usage{
	private static final long serialVersionUID = 1L;
	
	private String description;

	SensorTemplate() {
		super();
	}
	
	public SensorTemplate(String uuid) {
		super(uuid);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void accept(UsageVisitor visitor) {
		// TODO Auto-generated method stub
		
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String description;

		private Builder() {
		}

		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}

		public SensorTemplate build() {
			SensorTemplate obj = ModelFactory.sensorTemplate();
			
			obj.description = this.description;
			
			return obj;
		}
	}

	
}
