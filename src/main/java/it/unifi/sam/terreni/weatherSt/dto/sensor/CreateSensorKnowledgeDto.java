package it.unifi.sam.terreni.weatherSt.dto.sensor;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class CreateSensorKnowledgeDto extends BaseDto {
	private static final long serialVersionUID = 1L;

	private String description;
	private Long selectedUnitKnowledge;
	
	public CreateSensorKnowledgeDto() {}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getSelectedUnitKnowledge() {
		return selectedUnitKnowledge;
	}

	public void setSelectedUnitKnowledge(Long selectedUnitKnowledge) {
		this.selectedUnitKnowledge = selectedUnitKnowledge;
	}
	
	
}
