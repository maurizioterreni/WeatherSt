package it.unifi.sam.terreni.weatherSt.dto.sensor;

import java.util.List;

import javax.annotation.Generated;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;
import it.unifi.sam.terreni.weatherSt.dto.measure.UnitMeasureDto;

public class SensorTypeKnowledgeDto extends BaseDto{
	private static final long serialVersionUID = 1L;
	
	private String description;
	private Long id;
	private List<UnitMeasureDto> unitMeasureDtos;

	@Generated("SparkTools")
	private SensorTypeKnowledgeDto(Builder builder) {
		this.description = builder.description;
		this.id = builder.id;
		this.unitMeasureDtos = builder.unitMeasureDtos;
	}
	
	public SensorTypeKnowledgeDto() {}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Creates builder to build {@link SensorTypeKnowledgeDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	public List<UnitMeasureDto> getUnitMeasureDtos() {
		return unitMeasureDtos;
	}

	public void setUnitMeasureDtos(List<UnitMeasureDto> unitMeasureDtos) {
		this.unitMeasureDtos = unitMeasureDtos;
	}

	/**
	 * Builder to build {@link SensorTypeKnowledgeDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String description;
		private Long id;
		private List<UnitMeasureDto> unitMeasureDtos;

		private Builder() {
		}

		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}
		public Builder unitMeasureDtos(List<UnitMeasureDto> unitMeasureDtos) {
			this.unitMeasureDtos = unitMeasureDtos;
			return this;
		}

		public SensorTypeKnowledgeDto build() {
			return new SensorTypeKnowledgeDto(this);
		}
	}
	
	
	
}
