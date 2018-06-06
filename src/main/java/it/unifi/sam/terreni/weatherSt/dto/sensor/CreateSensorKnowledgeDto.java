package it.unifi.sam.terreni.weatherSt.dto.sensor;

import java.util.List;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;

public class CreateSensorKnowledgeDto extends BaseDto {
	private static final long serialVersionUID = 1L;

	private String description;
	private List<Long> selectedUnitKnowledges;
	
	public CreateSensorKnowledgeDto() {}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Long> getSelectedUnitKnowledges() {
		return selectedUnitKnowledges;
	}

	public void setSelectedUnitKnowledges(List<Long> selectedUnitKnowledges) {
		this.selectedUnitKnowledges = selectedUnitKnowledges;
	}
	
	
}
