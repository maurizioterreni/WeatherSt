package it.unifi.sam.terreni.weatherSt.dto.measure;

import javax.annotation.Generated;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class MeasureChartDto extends BaseDto{
	private static final long serialVersionUID = 1L;
	private String minQuantity;
	private String maxQuantity;
	private String dateTime;
	private Long unitId;

	@Generated("SparkTools")
	private MeasureChartDto(Builder builder) {
		this.minQuantity = builder.minQuantity;
		this.maxQuantity = builder.maxQuantity;
		this.dateTime = builder.dateTime;
		this.unitId = builder.unitId;
	}
	
	public MeasureChartDto() {
		
	}

	public String getMinQuantity() {
		return minQuantity;
	}

	public void setMinQuantity(String minQuantity) {
		this.minQuantity = minQuantity;
	}

	public String getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(String maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public static Builder builder() {
		return new Builder();
	}
	
	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}



	public static final class Builder {
		private String minQuantity;
		private String maxQuantity;
		private String dateTime;
		private Long unitId;

		private Builder() {
		}

		public Builder withMinQuantity(String minQuantity) {
			this.minQuantity = minQuantity;
			return this;
		}

		public Builder withMaxQuantity(String maxQuantity) {
			this.maxQuantity = maxQuantity;
			return this;
		}

		public Builder withDateTime(String dateTime) {
			this.dateTime = dateTime;
			return this;
		}
		
		public Builder withUnitId(Long unitId) {
			this.unitId = unitId;
			return this;
		}

		public MeasureChartDto build() {
			return new MeasureChartDto(this);
		}
	}
	
	
	
}
