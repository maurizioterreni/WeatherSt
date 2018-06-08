package it.unifi.sam.terreni.weatherSt.dto.sensor;

import it.unifi.sam.terreni.weatherSt.dto.BaseDto;
import it.unifi.sam.terreni.weatherSt.dto.measure.MeasureDto;

public class SensorGetResponsDto extends BaseDto{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String description;
	private String title;
	private MeasureDto maxMeasure;
	private MeasureDto measure;
	private MeasureDto minMeasure;
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
	
	
	
//	public 
	
	public Long getUnitKnowledgeId() {
		return unitKnowledgeId;
	}

	public void setUnitKnowledgeId(Long unitKnowledgeId) {
		this.unitKnowledgeId = unitKnowledgeId;
	}

	public MeasureDto getMaxMeasure() {
		return maxMeasure;
	}

	public void setMaxMeasure(MeasureDto maxMeasure) {
		this.maxMeasure = maxMeasure;
	}

	public MeasureDto getMeasure() {
		return measure;
	}

	public void setMeasure(MeasureDto measure) {
		this.measure = measure;
	}

	public MeasureDto getMinMeasure() {
		return minMeasure;
	}

	public void setMinMeasure(MeasureDto minMeasure) {
		this.minMeasure = minMeasure;
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
		private MeasureDto maxMeasure;
		private MeasureDto measure;
		private Long unitKnowledgeId;
		private MeasureDto minMeasure;
		
		public SensorGetResponsDtoBuilder id(Long id) {
			this.id = id;
			return this;
		}
		public SensorGetResponsDtoBuilder description(String description) {
			this.description = description;
			return this;
		}
		public SensorGetResponsDtoBuilder maxMeasure(MeasureDto maxMeasure) {
			this.maxMeasure = maxMeasure;
			return this;
		}
		public SensorGetResponsDtoBuilder measure(MeasureDto measure) {
			this.measure = measure;
			return this;
		}
		public SensorGetResponsDtoBuilder minMeasure(MeasureDto minMeasure) {
			this.minMeasure = minMeasure;
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
		
		private void setMeasure(SensorGetResponsDto dto) {
			if(maxMeasure != null && minMeasure != null && measure != null) {
				dto.setMaxMeasure(maxMeasure);
				dto.setMeasure(measure);
				dto.setMinMeasure(minMeasure);
			}
		}
		
		public SensorGetResponsDto build() {
			SensorGetResponsDto dto = new SensorGetResponsDto();
			
			dto.setId(id);
			dto.setDescription(description);
			setMeasure(dto);
			dto.setTitle(title);
			dto.setUnitKnowledgeId(unitKnowledgeId);
					
			return dto;
		}
	}
}
