package it.unifi.sam.terreni.weatherSt.services;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unifi.sam.terreni.weatherSt.dao.SensorDao;
import it.unifi.sam.terreni.weatherSt.dao.WeatherStationDao;
import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.model.sensor.SensorType;
import it.unifi.sam.terreni.weatherSt.utils.CheckClass;
import it.unifi.sam.terreni.weatherSt.utils.ErrorServices;
import it.unifi.sam.terreni.weatherSt.utils.StringUtils;

@Path("/sensor")
public class SensorEndPoint {
	@Inject
	private SensorDao sensorDao;
	@Inject
	private WeatherStationDao weatherStationDao;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response add(@HeaderParam("weatherId") Long weatherId, @HeaderParam("description") String description, @HeaderParam("sensorType") SensorType sensorType) {
		if (weatherId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - weatherId").build();
		if (StringUtils.isEmpty(description))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - description").build();
		if (sensorType == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorType").build();

		WeatherStation weatherStation = weatherStationDao.findById(weatherId);
		
		if(weatherStation == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - weatherStation").build();

	
		Sensor sensor = ModelFactory.sensor();
		sensor.setDescription(description);
		sensor.setSensorType(sensorType);
		sensor.setWeatherStation(weatherStation);
		weatherStation.addSensor(sensor);
		
		sensorDao.save(sensor);
		
		return Response.status(200).entity(sensor).build();
		

	}
	
	@GET
	@Path("/sensorList")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response get(@HeaderParam("token") String token, @HeaderParam("weatherStationId") Long weatherStationId) {
		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		if (weatherStationId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - weatherStationId").build();
		
		if(!CheckClass.checkToken(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - token error").build();

		WeatherStation weatherStation = weatherStationDao.fetchById(weatherStationId);
		

		if(weatherStation.getSensors() == null)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensors").build();
		
		
		return Response.status(200).entity(weatherStation.getSensors()).build();
	}
}
