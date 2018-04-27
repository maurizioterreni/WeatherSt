package it.unifi.sam.terreni.weatherSt.dto.sensor;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;
import javax.annotation.Generated;

public class SensorTypeKnowledgeDto extends BaseDto{
	private static final long serialVersionUID = 1L;
	
	private String description;
	private Long id;
	private String symbol;
	private String name;

	@Generated("SparkTools")
	private SensorTypeKnowledgeDto(Builder builder) {
		this.description = builder.description;
		this.id = builder.id;
		this.symbol = builder.symbol;
		this.name = builder.name;
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

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Creates builder to build {@link SensorTypeKnowledgeDto}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link SensorTypeKnowledgeDto}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String description;
		private Long id;
		private String symbol;
		private String name;

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

		public Builder withSymbol(String symbol) {
			this.symbol = symbol;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public SensorTypeKnowledgeDto build() {
			return new SensorTypeKnowledgeDto(this);
		}
	}
	
	
	
}
