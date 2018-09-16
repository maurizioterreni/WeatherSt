package it.unifi.sam.terreni.weatherSt.services;

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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unifi.sam.terreni.weatherSt.dao.WeatherStationDao;
import it.unifi.sam.terreni.weatherSt.dao.measure.MeasureDao;
import it.unifi.sam.terreni.weatherSt.dao.sensor.SensorDao;
import it.unifi.sam.terreni.weatherSt.dao.sensor.SensorTypeKnowledgeDao;
import it.unifi.sam.terreni.weatherSt.dto.sensor.SensorGetResponsDto;
import it.unifi.sam.terreni.weatherSt.dto.sensor.SensorPostRequestDto;
import it.unifi.sam.terreni.weatherSt.dto.sensor.SensorResponsDto;
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
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getBySensorId(@PathParam("id") Long id) {

		Sensor sensor = sensorDao.findById(id);

		if(sensor == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();

		return Response.status(200).entity(sensorToSensorGetResponsDto(sensor)).build();
	}
	@GET
	@Path("/weatherstation/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getSensorByWeatherStation(@PathParam("id") Long id) {

		WeatherStation weatherStation = weatherStationDao.findById(id);

		if(weatherStation == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - weatherStation").build();

		List<SensorGetResponsDto> sensorDtos = new ArrayList<>();

		for (Sensor sensor : weatherStation.getSensors()) {
			sensorDtos.add(sensorToSensorGetResponsDto(sensor));
		}

		return Response.status(200).entity(sensorDtos).build();
	}


	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response updateSensor(@HeaderParam("token") String token, @PathParam("id") Long id, @HeaderParam("sensorKnowledgeId") Long sensorKnowledgeId) {

		if (sensorKnowledgeId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorKnowledgeId").build();
		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();

		if(Authentication.isNotValid(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token not valid").build();

		Sensor sensor = sensorDao.findById(id);

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
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response delete(@HeaderParam("token") String token, @PathParam("sensorId") Long id, @HeaderParam("weatherId") Long weatherId) {
		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		if (weatherId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - weatherId").build();



		if(Authentication.isNotValid(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token not valid").build();


		Sensor sensor = sensorDao.fetchById(id);

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


	private SensorResponsDto sensorToSensorResponsDto(Long weatherId, Sensor sensor) {
		return SensorResponsDto.builder()
				.description(sensor.getSensorType().getDescription())
				.templateId(sensor.getSensorType().getSensorTemplate().getId())
				.weatherId(weatherId)
				.build();
	}

	private SensorGetResponsDto sensorToSensorGetResponsDto(Sensor sensor) {
		return SensorGetResponsDto.builder()
				.id(sensor.getId())
				.description(sensor.getSensorType().getDescription())
				.sensorTemplate(sensor.getSensorType().getSensorTemplate().getId())
				.title(sensor.getTitle())
				.unitKnowledgeId(null)
				.build();
	}


}
