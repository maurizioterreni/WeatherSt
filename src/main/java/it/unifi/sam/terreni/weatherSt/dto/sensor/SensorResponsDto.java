package it.unifi.sam.terreni.weatherSt.dto.sensor;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class SensorResponsDto extends BaseDto{
	private static final long serialVersionUID = 1L;

	private Long weatherId;
	private String description;
	private String title;
	private Long templateId;

	public SensorResponsDto() {

	}

	public static SensorResponseDtoBuilder builder() {
		return new SensorResponseDtoBuilder();
	}
	public Long getWeatherId() {
		return weatherId;
	}

	public void setWeatherId(Long weatherId) {
		this.weatherId = weatherId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

//	public

	public String getTitle() {
		return title;
	}

	public void setTitle(String titlte) {
		this.title = titlte;
	}

	public static class SensorResponseDtoBuilder{
		private Long weatherId;
		private String description;
		private String title;
		private Long templateId;

		public SensorResponseDtoBuilder weatherId(Long weatherId) {
			this.weatherId = weatherId;
			return this;
		}
		public SensorResponseDtoBuilder description(String description) {
			this.description = description;
			return this;
		}
		public SensorResponseDtoBuilder title(String title) {
			this.title = title;
			return this;
		}

		public SensorResponseDtoBuilder templateId(Long templateId) {
			this.templateId = templateId;
			return this;
		}

		public SensorResponsDto build() {
			SensorResponsDto dto = new SensorResponsDto();

			dto.setDescription(description);
			dto.setWeatherId(weatherId);
			dto.setTitle(title);
			dto.setTemplateId(templateId);

			return dto;
		}
	}
}
