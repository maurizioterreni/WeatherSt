package it.unifi.sam.terreni.weatherSt.services;

import java.time.LocalDateTime;
import java.time.LocalTime;

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

import it.unifi.sam.terreni.weatherSt.dao.MeasureDao;
import it.unifi.sam.terreni.weatherSt.dao.SensorDao;
import it.unifi.sam.terreni.weatherSt.dao.SensorTypeKnowledgeDao;
import it.unifi.sam.terreni.weatherSt.dao.WeatherStationDao;
import it.unifi.sam.terreni.weatherSt.dto.sensor.SensorGetResponsDto;
import it.unifi.sam.terreni.weatherSt.dto.sensor.SensorPostRequestDto;
import it.unifi.sam.terreni.weatherSt.dto.sensor.SensorResponsDto;
import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.model.sensor.SensorTypeKnowledge;
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
	public Response get(@HeaderParam("token") String token, @HeaderParam("sensorId") Long sensorId, @HeaderParam("unitMeasureId") Long unitMeasureId) {
		if (sensorId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();
		if (unitMeasureId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - unitMeasureId").build();
		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();

		Sensor sensor = sensorDao.findById(sensorId);

		if(sensor == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();

		LocalDateTime fromDate = LocalDateTime.now().toLocalDate().atTime(LocalTime.MIDNIGHT);
		LocalDateTime toDate = LocalDateTime.now().toLocalDate().atTime(LocalTime.MAX);

		Measure maxMeasure = measureDao.getMax(sensor, fromDate, toDate);
		Measure minMeasure = measureDao.getMin(sensor, fromDate, toDate);
		Measure measure = measureDao.getLastMeasue(sensor);



		return Response.status(200).entity(sensorToSensorGetResponsDto(sensor, measure, maxMeasure, minMeasure,1f)).build();
	}

	//	private SensorToAddDto sensorToSensorToAddDTO(Long weatherId,Sensor sensor) {
	//		return SensorToAddDto.builder()
	//				.sensorTypeId(sensor.getSensorType().getId())
	//				.weatherId(weatherId)
	//				.build();
	//	}
	//	
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
				.description(sensor.getSensorType().getDescription())
				.symbol(sensor.getSensorType().getUnitMeasure().getSymbol())
				.name(sensor.getSensorType().getUnitMeasure().getName())
				.measure(measure)
				.maxMeasure(maxMeasure)
				.minMeasure(minMeasure)
				.conversionFactor(conversionFactor)
				.build();
	}
}


/*
 * //Temperatura
		UnitMeasureKnowledge celsius = UnitMeasureKnowledge
				.builder()
				.symbol("°C")
				.name("degree celsius")
				.build();

		unitMeasureKnowledgeDao.save(celsius);

		UnitMeasureKnowledge fahrenheit = UnitMeasureKnowledge
				.builder()
				.symbol("°F")
				.name("fahrenheit degree")
				.build();

		unitMeasureKnowledgeDao.save(fahrenheit);

		sensorTypeKnowledgeDao.save(SensorTypeKnowledge.builder()
				.descrition("Temperature Sensor")
				.unitMeasureKnowledge(celsius)
				.build());

		sensorTypeKnowledgeDao.save(SensorTypeKnowledge.builder()
				.descrition("Temperature Sensor")
				.unitMeasureKnowledge(fahrenheit)
				.build());

		//Sensore velocità vento

		UnitMeasureKnowledge ms = UnitMeasureKnowledge
				.builder()
				.symbol("kmh")
				.name("kilometer per hour")
				.build();

		unitMeasureKnowledgeDao.save(ms);

		UnitMeasureKnowledge mis = UnitMeasureKnowledge
				.builder()
				.symbol("mph")
				.name("miles per hour")
				.build();

		unitMeasureKnowledgeDao.save(mis);

		sensorTypeKnowledgeDao.save(SensorTypeKnowledge.builder()
				.descrition("Wind speed sensor")
				.unitMeasureKnowledge(ms)
				.build());

		sensorTypeKnowledgeDao.save(SensorTypeKnowledge.builder()
				.descrition("Wind speed sensor")
				.unitMeasureKnowledge(mis)
				.build());

		//direction wind


		UnitMeasureKnowledge angular = UnitMeasureKnowledge
				.builder()
				.symbol("°")
				.name("degree")
				.build();

		unitMeasureKnowledgeDao.save(angular);

		sensorTypeKnowledgeDao.save(SensorTypeKnowledge.builder()
				.descrition("Wind direction sensor")
				.unitMeasureKnowledge(angular)
				.build());

		//rain

		UnitMeasureKnowledge millimetre = UnitMeasureKnowledge
				.builder()
				.symbol("mm")
				.name("millimetre")
				.build();

		unitMeasureKnowledgeDao.save(millimetre);

		UnitMeasureKnowledge inch = UnitMeasureKnowledge
				.builder()
				.symbol("in")
				.name("innch")
				.build();

		unitMeasureKnowledgeDao.save(inch);

		sensorTypeKnowledgeDao.save(SensorTypeKnowledge.builder()
				.descrition("rain sensor")
				.unitMeasureKnowledge(millimetre)
				.build());
		sensorTypeKnowledgeDao.save(SensorTypeKnowledge.builder()
				.descrition("rain sensor")
				.unitMeasureKnowledge(inch)
				.build());

		//Sensore Umidità

		UnitMeasureKnowledge hum = UnitMeasureKnowledge
				.builder()
				.symbol("%")
				.name("humidity")
				.build();

		unitMeasureKnowledgeDao.save(hum);

		sensorTypeKnowledgeDao.save(SensorTypeKnowledge.builder()
				.descrition("Humidity sensor")
				.unitMeasureKnowledge(hum)
				.build());

		//Sensore UV

		UnitMeasureKnowledge uv = UnitMeasureKnowledge
				.builder()
				.symbol("")
				.name("uv")
				.build();

		unitMeasureKnowledgeDao.save(uv);

		sensorTypeKnowledgeDao.save(SensorTypeKnowledge.builder()
				.descrition("Uv sensor")
				.unitMeasureKnowledge(uv)
				.build());


		//Sensore pressione

		UnitMeasureKnowledge millibar = UnitMeasureKnowledge
				.builder()
				.symbol("mb")
				.name("millibar")
				.build();

		unitMeasureKnowledgeDao.save(millibar);

		sensorTypeKnowledgeDao.save(SensorTypeKnowledge.builder()
				.descrition("Pressure sensor")
				.unitMeasureKnowledge(millibar)
				.build());
 * 
 * */
