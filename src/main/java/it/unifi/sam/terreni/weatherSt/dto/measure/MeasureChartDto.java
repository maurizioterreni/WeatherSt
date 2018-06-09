package it.unifi.sam.terreni.weatherSt.dto.measure;

import javax.annotation.Generated;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class MeasureChartDto extends BaseDto{
	private static final long serialVersionUID = 1L;
	private String quantity;
	private String dateTime;
	private Long unitId;

	@Generated("SparkTools")
	private MeasureChartDto(Builder builder) {
		this.quantity = builder.quantity;
		this.dateTime = builder.dateTime;
		this.unitId = builder.unitId;
	}
	
	public MeasureChartDto() {
		
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
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
		private String quantity;
		private String dateTime;
		private Long unitId;

		private Builder() {
		}

		public Builder withQuantity(String quantity) {
			this.quantity = quantity;
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
