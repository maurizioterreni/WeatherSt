package it.unifi.sam.terreni.weatherSt.dto.sensor;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class SensorGetResponsDto extends BaseDto{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String description;
	private String title;
	private Long sensorTemplate;
	private Long unitKnowledgeId;

	public SensorGetResponsDto() {

	}

	public static SensorGetResponsDtoBuilder builder() {
		return new SensorGetResponsDtoBuilder();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Long getSensorTemplate() {
		return sensorTemplate;
	}

	public void setSensorTemplate(Long sensorTemplate) {
		this.sensorTemplate = sensorTemplate;
	}



//	public

	public Long getUnitKnowledgeId() {
		return unitKnowledgeId;
	}

	public void setUnitKnowledgeId(Long unitKnowledgeId) {
		this.unitKnowledgeId = unitKnowledgeId;
	}




	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}




	public static class SensorGetResponsDtoBuilder{
		private Long id;
		private String description;
		private String title;
		private Long unitKnowledgeId;
		private Long sensortemplate;

		public SensorGetResponsDtoBuilder id(Long id) {
			this.id = id;
			return this;
		}
		public SensorGetResponsDtoBuilder description(String description) {
			this.description = description;
			return this;
		}

		public SensorGetResponsDtoBuilder sensorTemplate(Long sensortemplate) {
			this.sensortemplate = sensortemplate;
			return this;
		}

		public SensorGetResponsDtoBuilder unitKnowledgeId(Long unitKnowledgeId) {
			this.unitKnowledgeId = unitKnowledgeId;
			return this;
		}
		public SensorGetResponsDtoBuilder title(String title) {
			this.title = title;
			return this;
		}

		public SensorGetResponsDto build() {
			SensorGetResponsDto dto = new SensorGetResponsDto();

			dto.setId(id);
			dto.setDescription(description);
			dto.setTitle(title);
			dto.setUnitKnowledgeId(unitKnowledgeId);
			dto.setSensorTemplate(sensortemplate);

			return dto;
		}
	}
}
