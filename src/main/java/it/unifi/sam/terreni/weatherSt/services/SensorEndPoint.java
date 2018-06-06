package it.unifi.sam.terreni.weatherSt.services;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unifi.sam.terreni.weatherSt.dao.WeatherStationDao;
import it.unifi.sam.terreni.weatherSt.dao.measure.MeasureDao;
import it.unifi.sam.terreni.weatherSt.dao.measure.UnitMeasureKnowledgeDao;
import it.unifi.sam.terreni.weatherSt.dao.sensor.SensorDao;
import it.unifi.sam.terreni.weatherSt.dao.sensor.SensorTypeKnowledgeDao;
import it.unifi.sam.terreni.weatherSt.dto.measure.CreateUnitKnowledgeDto;
import it.unifi.sam.terreni.weatherSt.dto.measure.MeasureDto;
import it.unifi.sam.terreni.weatherSt.dto.measure.UnitMeasureDto;
import it.unifi.sam.terreni.weatherSt.dto.sensor.CreateSensorKnowledgeDto;
import it.unifi.sam.terreni.weatherSt.dto.sensor.SensorGetResponsDto;
import it.unifi.sam.terreni.weatherSt.dto.sensor.SensorPostRequestDto;
import it.unifi.sam.terreni.weatherSt.dto.sensor.SensorResponsDto;
import it.unifi.sam.terreni.weatherSt.dto.sensor.SensorTypeKnowledgeDto;
import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.model.sensor.SensorTypeKnowledge;
import it.unifi.sam.terreni.weatherSt.security.Authentication;
import it.unifi.sam.terreni.weatherSt.utils.ErrorServices;
import it.unifi.sam.terreni.weatherSt.utils.StringUtils;

@Path("/sensor")
public class SensorEndPoint {
	@Inject
	private SensorDao sensorDao;
	@Inject
	private WeatherStationDao weatherStationDao;
	@Inject
	private SensorTypeKnowledgeDao sensorTypeKnowledgeDao;
	@Inject
	private MeasureDao measureDao;
	@Inject
	private UnitMeasureKnowledgeDao unitMeasureKnowledgeDao;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response add(@HeaderParam("token") String token, SensorPostRequestDto sensorDto) {
		if (sensorDto == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorDto").build();
		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		
		if(Authentication.isNotValid(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token not valid").build();
		

		WeatherStation weatherStation = weatherStationDao.findById(sensorDto.getWeatherId());

		if(weatherStation == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - weatherStation").build();

		SensorTypeKnowledge sensorType = sensorTypeKnowledgeDao.findById(sensorDto.getSensorTypeId());

		if(sensorType == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensorType").build();

		Sensor sensor = Sensor.builder()
				.sensorType(sensorType)
				.title(sensorDto.getTitle())
				.build();

		weatherStation.addSensor(sensor);

		sensorDao.save(sensor);

		return Response.status(200).entity(sensorToSensorResponsDto(weatherStation.getId(), sensor)).build();
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response get(@HeaderParam("sensorId") Long sensorId) {
		if (sensorId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();
		

		Sensor sensor = sensorDao.findById(sensorId);

		if(sensor == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();

		LocalDateTime fromDate = LocalDateTime.now().toLocalDate().atTime(LocalTime.of(0, 0, 1));
		LocalDateTime toDate = LocalDateTime.now().toLocalDate().atTime(LocalTime.of(23, 59, 59));

		Measure maxMeasure = measureDao.getMax(sensor, fromDate, toDate);
		Measure minMeasure = measureDao.getMin(sensor, fromDate, toDate);
		Measure measure = measureDao.getLastMeasue(sensor);



		return Response.status(200).entity(sensorToSensorGetResponsDto(sensor, measure, maxMeasure, minMeasure)).build();
	}
	
	
	
	@GET
	@Path("/sensorType")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response get() {
	
		List<SensorTypeKnowledgeDto> dtos = new ArrayList<>();
		
		for (SensorTypeKnowledge sensorTypeKnowledge : sensorTypeKnowledgeDao.getAllSensorType()) {
			dtos.add(sensorTypeKnowledgeToDto(sensorTypeKnowledge));
		}
		
		return Response.status(200).entity(dtos).build();
	}
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response updateSensor(@HeaderParam("token") String token, @HeaderParam("sensorId") Long sensorId, @HeaderParam("sensorKnowledgeId") Long sensorKnowledgeId) {
	
		if (sensorId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();
		if (sensorKnowledgeId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorKnowledgeId").build();
		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		
		if(Authentication.isNotValid(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token not valid").build();
		
		Sensor sensor = sensorDao.findById(sensorId);
		
		if(sensor == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensor").build();
		
		SensorTypeKnowledge typeKnowledge = sensorTypeKnowledgeDao.findById(sensorKnowledgeId);
		
		if(typeKnowledge == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - typeKnowledge").build();
		
		sensor.setSensorType(typeKnowledge);

		sensorDao.update(sensor);
		
		return Response.status(200).build();
	}
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response delete(@HeaderParam("token") String token, @HeaderParam("sensorId") Long sensorId, @HeaderParam("weatherId") Long weatherId) {
		if (sensorId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();
		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		if (weatherId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - weatherId").build();
		
		
		
		if(Authentication.isNotValid(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token not valid").build();
		

		Sensor sensor = sensorDao.fetchById(sensorId);

		if(sensor == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();

		WeatherStation weatherStation = weatherStationDao.findById(weatherId);
		
		if(!weatherStation.getSensors().contains(sensor))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.GENERIC_ERROR.getMessage() + " - weather station not contain sensor").build();
		
		
		weatherStation.getSensors().remove(sensor);
		
		for (Measure measure : sensor.getMeasures()) {
			measureDao.delete(measure);
		}
		
		sensorDao.delete(sensor);

		return Response.status(200).entity(null).build();
	}
	
	@GET
	@Path("/unitKnowledge")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getAllUnitKnowledge() {
		List<UnitMeasureKnowledge> list = unitMeasureKnowledgeDao.getAllUnitMeasureKnowledge();
		
		if(list == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.GENERIC_ERROR.getMessage() + " - list unitKnowledge").build();
		
		return Response.status(200).entity(createListUnitKnowledge(list)).build();
	}
	
	@POST
	@Path("/unitKnowledge")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response createUnitKnowledge(@HeaderParam("token") String token, CreateUnitKnowledgeDto createUnitKnowledgeDto ) {
		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		if (createUnitKnowledgeDto == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - createUnitKnowledgeDto").build();
		
		if(Authentication.isNotValid(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token not valid").build();

		unitMeasureKnowledgeDao.save(UnitMeasureKnowledge.builder()
				.name(createUnitKnowledgeDto.getName())
				.symbol(createUnitKnowledgeDto.getSymbol())
				.build());
		
		return Response.status(200).entity(null).build();
	}
	
	

	@POST
	@Path("/sensorKnowledge")
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

		List<UnitMeasureKnowledge> unitMeasureKnowledges = new ArrayList<>();
		
		for (Long unitMeasureId : createSensorKnowledgeDto.getSelectedUnitKnowledges()) {
			unitMeasureKnowledges.add(unitMeasureKnowledgeDao.findById(unitMeasureId));
		}
		
		sensorTypeKnowledgeDao.save(SensorTypeKnowledge.builder()
				.descrition(createSensorKnowledgeDto.getDescription())
				.unitMeasureKnowledges(unitMeasureKnowledges)
				.build());
		
		return Response.status(200).entity(null).build();
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
	
	private SensorTypeKnowledgeDto sensorTypeKnowledgeToDto(SensorTypeKnowledge obj) {
		return SensorTypeKnowledgeDto.builder()
				.withDescription(obj.getDescription())
				.withId(obj.getId())
				.unitMeasureDtos(createListUnitKnowledge(obj.getUnitMeasures()))
				.build();
	}

	
	private MeasureDto measureToMeasureDto(Measure measure) {
		if(measure == null)
			return null;
		
		return MeasureDto.builder()
				.dateTime(measure.getLocalDateTime().toString())
				.quantity(StringUtils.floatToString(measure.getQuantity()))
				.name(measure.getUnitMeasure().getName())
				.symbol(measure.getUnitMeasure().getSymbol())
				.build();
	}
	
	private SensorResponsDto sensorToSensorResponsDto(Long weatherId, Sensor sensor) {
		return SensorResponsDto.builder()
				.description(sensor.getSensorType().getDescription())
				.weatherId(weatherId)
				.title(sensor.getTitle())
				.build();
	}
	private SensorGetResponsDto sensorToSensorGetResponsDto(Sensor sensor, Measure measure, Measure maxMeasure, Measure minMeasure) {
		return SensorGetResponsDto.builder()
				.id(sensor.getId())
				.description(sensor.getSensorType().getDescription())
				.measure(measureToMeasureDto(measure))
				.maxMeasure(measureToMeasureDto(maxMeasure))
				.minMeasure(measureToMeasureDto(minMeasure))
				.build();
	}
	
	
}