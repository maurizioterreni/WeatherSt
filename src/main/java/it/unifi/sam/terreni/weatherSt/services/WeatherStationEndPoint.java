package it.unifi.sam.terreni.weatherSt.services;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unifi.sam.terreni.weatherSt.dao.WeatherStationDao;
import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.utils.CheckClass;
import it.unifi.sam.terreni.weatherSt.utils.ErrorServices;
import it.unifi.sam.terreni.weatherSt.utils.StringUtils;

@Path("/weatherstation")
public class WeatherStationEndPoint {
	@Inject
	private WeatherStationDao weatherStationDao;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response add(@HeaderParam("description") String description, @HeaderParam("latitude") String latitude, @HeaderParam("longitude") String longitude) {
		if (StringUtils.isEmpty(description))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - description").build();
		if (StringUtils.isEmpty(longitude))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - longitude").build();
		if (StringUtils.isEmpty(latitude))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - latitude").build();
		
		WeatherStation weatherStation = ModelFactory.weatherStation();
		weatherStation.setDescription(description);
		weatherStation.setLatitude(latitude);
		weatherStation.setLongitude(longitude);
		
		weatherStationDao.save(weatherStation);
	
		return Response.status(200).entity(weatherStation).build();
		

	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	@Transactional
	public Response getAll(@HeaderParam("token") String token) {
		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		
		if(!CheckClass.checkToken(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - token error").build();

		List<WeatherStation> weatherStations = weatherStationDao.getAll();
		
		if(weatherStations == null || weatherStations.size() == 0)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - measure").build();
		
		return Response.status(200).entity(weatherStations).build();
	}
}
