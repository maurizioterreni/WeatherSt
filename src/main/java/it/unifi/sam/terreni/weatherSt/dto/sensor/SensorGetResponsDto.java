package it.unifi.sam.terreni.weatherSt.dto.sensor;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class SensorGetResponsDto extends BaseDto{
	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer sensorTemplate;
	private String description;
	private String symbol;
	private String name;
	private Long templateId;
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

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	//	public 

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Long getUnitKnowledgeId() {
		return unitKnowledgeId;
	}

	public void setUnitKnowledgeId(Long unitKnowledgeId) {
		this.unitKnowledgeId = unitKnowledgeId;
	}

	public Integer getSensorTemplate() {
		return sensorTemplate;
	}

	public void setSensorTemplate(Integer sensorTemplate) {
		this.sensorTemplate = sensorTemplate;
	}






	public static class SensorGetResponsDtoBuilder{
		private Long id;
		private Integer sensorTemplate;
		private String description;
		private String symbol;
		private String name;
		private Long unitKnowledgeId;
		private Long templateId;

		public SensorGetResponsDtoBuilder id(Long id) {
			this.id = id;
			return this;
		}
		public SensorGetResponsDtoBuilder description(String description) {
			this.description = description;
			return this;
		}
		public SensorGetResponsDtoBuilder symbol(String symbol) {
			this.symbol = symbol;
			return this;
		}
		public SensorGetResponsDtoBuilder name(String name) {
			this.name = name;
			return this;
		}
		public SensorGetResponsDtoBuilder templateId(Long templateId) {
			this.templateId = templateId;
			return this;
		}
		public SensorGetResponsDtoBuilder unitKnowledgeId(Long unitKnowledgeId) {
			this.unitKnowledgeId = unitKnowledgeId;
			return this;
		}
		public SensorGetResponsDtoBuilder sensorTemplate(Integer sensorTemplate) {
			this.sensorTemplate = sensorTemplate;
			return this;
		}



		public SensorGetResponsDto build() {
			SensorGetResponsDto dto = new SensorGetResponsDto();

			dto.setId(id);
			dto.setDescription(description);
			dto.setSymbol(symbol);
			dto.setName(name);
			dto.setTemplateId(templateId);
			dto.setUnitKnowledgeId(unitKnowledgeId);
			dto.setSensorTemplate(sensorTemplate);

			return dto;
		}
	}
}
