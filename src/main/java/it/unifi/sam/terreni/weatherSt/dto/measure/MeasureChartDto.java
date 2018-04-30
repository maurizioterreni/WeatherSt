package it.unifi.sam.terreni.weatherSt.dto.measure;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;
import javax.annotation.Generated;

public class MeasureChartDto extends BaseDto{
	private static final long serialVersionUID = 1L;
	private String minQuantity;
	private String maxQuantity;
	private String dateTime;

	@Generated("SparkTools")
	private MeasureChartDto(Builder builder) {
		this.minQuantity = builder.minQuantity;
		this.maxQuantity = builder.maxQuantity;
		this.dateTime = builder.dateTime;
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

	public static final class Builder {
		private String minQuantity;
		private String maxQuantity;
		private String dateTime;

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

		public MeasureChartDto build() {
			return new MeasureChartDto(this);
		}
	}
	
	
	
}
