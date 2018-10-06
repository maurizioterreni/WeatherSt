package it.unifi.sam.terreni.weatherSt.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unifi.sam.terreni.weatherSt.dao.measure.UnitMeasureKnowledgeDao;
import it.unifi.sam.terreni.weatherSt.dao.sensor.SensorTypeKnowledgeDao;
import it.unifi.sam.terreni.weatherSt.dto.measure.UnitMeasureDto;
import it.unifi.sam.terreni.weatherSt.dto.sensor.CreateSensorKnowledgeDto;
import it.unifi.sam.terreni.weatherSt.dto.sensor.SensorTypeKnowledgeDto;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;
import it.unifi.sam.terreni.weatherSt.model.sensor.SensorTypeKnowledge;
import it.unifi.sam.terreni.weatherSt.security.Authentication;
import it.unifi.sam.terreni.weatherSt.utils.ErrorServices;
import it.unifi.sam.terreni.weatherSt.utils.StringUtils;

@Path("/sensorTypeKnowledge")
public class SensorTypeKnowledgeEndPoint {
	@Inject
	private SensorTypeKnowledgeDao sensorTypeKnowledgeDao;
	@Inject
	private UnitMeasureKnowledgeDao unitMeasureKnowledgeDao;

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response sensorKnowledge(@HeaderParam("token") String token, CreateSensorKnowledgeDto createSensorKnowledgeDto) {
		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		if (createSensorKnowledgeDto == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - createSensorKnowledgeDto").build();
		
		if(Authentication.isNotValid(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token not valid").build();

		List<UnitMeasureKnowledge> measureKnowledges = new ArrayList<>();
		
		for (Long id : createSensorKnowledgeDto.getSelectedUnitKnowledges()) {
			measureKnowledges.add(unitMeasureKnowledgeDao.findById(id));
		}
		
		if (measureKnowledges.isEmpty())
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - unitMeasureKnowledge").build();
		
		sensorTypeKnowledgeDao.save(SensorTypeKnowledge.builder()
				.descrition(createSensorKnowledgeDto.getDescription())
				.unitMeasureKnowledges(measureKnowledges)
				.build());
		
		return Response.status(200).entity(null).build();
	}
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getAllSensorKnowledge() {
	
		List<SensorTypeKnowledgeDto> dtos = new ArrayList<>();
		
		for (SensorTypeKnowledge sensorTypeKnowledge : sensorTypeKnowledgeDao.getAllSensorType()) {
			dtos.add(sensorTypeKnowledgeToDto(sensorTypeKnowledge));
		}
		
		return Response.status(200).entity(dtos).build();
	}

	private SensorTypeKnowledgeDto sensorTypeKnowledgeToDto(SensorTypeKnowledge obj) {
		return SensorTypeKnowledgeDto.builder()
				.withDescription(obj.getDescription())
				.withId(obj.getId())
				.withUnitMeasureDtos(createListUnitKnowledge(obj.getUnitMeasures()))
				.build();
	}
	
	private List<UnitMeasureDto> createListUnitKnowledge(List<UnitMeasureKnowledge> list){
		List<UnitMeasureDto> obj = new ArrayList<>();
		for (UnitMeasureKnowledge unitMeasureKnowledge : list) {
			obj.add(UnitMeasureDto.builder()
					.id(unitMeasureKnowledge.getId())
					.name(unitMeasureKnowledge.getName())
					.symbol(unitMeasureKnowledge.getSymbol())
					.build());
		}
		
		return obj;
	}

}
