package it.unifi.sam.terreni.weatherSt.services;

import java.time.LocalDateTime;
import java.time.LocalTime;
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

import it.unifi.sam.terreni.weatherSt.dao.WeatherStationDao;
import it.unifi.sam.terreni.weatherSt.dao.measure.MeasureDao;
import it.unifi.sam.terreni.weatherSt.dao.sensor.SensorDao;
import it.unifi.sam.terreni.weatherSt.dao.sensor.SensorTypeKnowledgeDao;
import it.unifi.sam.terreni.weatherSt.dto.measure.MeasureDto;
import it.unifi.sam.terreni.weatherSt.dto.sensor.SensorGetResponsDto;
import it.unifi.sam.terreni.weatherSt.dto.sensor.SensorPostRequestDto;
import it.unifi.sam.terreni.weatherSt.dto.sensor.SensorResponsDto;
import it.unifi.sam.terreni.weatherSt.dto.sensor.SensorTypeKnowledgeDto;
import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
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
				.build();

		weatherStation.addSensor(sensor);

		sensorDao.save(sensor);

		return Response.status(200).entity(sensorToSensorResponsDto(weatherStation.getId(), sensor)).build();
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response get(@HeaderParam("sensorId") Long sensorId, @HeaderParam("unitMeasureId") Long unitMeasureId) {
		if (sensorId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();
		if (unitMeasureId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - unitMeasureId").build();
	

		Sensor sensor = sensorDao.findById(sensorId);

		if(sensor == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();

		LocalDateTime fromDate = LocalDateTime.now().toLocalDate().atTime(LocalTime.of(0, 0, 1));
		LocalDateTime toDate = LocalDateTime.now().toLocalDate().atTime(LocalTime.of(23, 59, 59));

		Measure maxMeasure = measureDao.getMax(sensor, fromDate, toDate);
		Measure minMeasure = measureDao.getMin(sensor, fromDate, toDate);
		Measure measure = measureDao.getLastMeasue(sensor);



		return Response.status(200).entity(sensorToSensorGetResponsDto(sensor, measure, maxMeasure, minMeasure,1f)).build();
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
	
	
	private SensorTypeKnowledgeDto sensorTypeKnowledgeToDto(SensorTypeKnowledge obj) {
		return SensorTypeKnowledgeDto.builder()
				.withDescription(obj.getDescription())
				.withId(obj.getId())
				.withName(obj.getUnitMeasure().getName())
				.withSymbol(obj.getUnitMeasure().getSymbol())
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
				.symbol(sensor.getSensorType().getUnitMeasure().getSymbol())
				.name(sensor.getSensorType().getUnitMeasure().getName())
				.weatherId(weatherId)
				.build();
	}
	private SensorGetResponsDto sensorToSensorGetResponsDto(Sensor sensor, Measure measure, Measure maxMeasure, Measure minMeasure, Float conversionFactor) {
		return SensorGetResponsDto.builder()
				.id(sensor.getId())
				.description(sensor.getSensorType().getDescription())
				.symbol(sensor.getSensorType().getUnitMeasure().getSymbol())
				.name(sensor.getSensorType().getUnitMeasure().getName())
				.measure(measureToMeasureDto(measure))
				.maxMeasure(measureToMeasureDto(maxMeasure))
				.minMeasure(measureToMeasureDto(minMeasure))
				.conversionFactor(conversionFactor)
				.build();
	}
}