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
import it.unifi.sam.terreni.weatherSt.dto.weatherStation.WeatherStationPostRequestDto;
import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
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
	public Response add(@HeaderParam("token") String token, WeatherStationPostRequestDto weatherStationDto) {
		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		if (weatherStationDto == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - weatherStationDto").build();
		
		weatherStationDao.save(WeatherStation.builder()
				.description(weatherStationDto.getDescription())
				.latitude(weatherStationDto.getLatitude())
				.longitude(weatherStationDto.getLongitude())
				.build());
	
		return Response.status(200).entity(weatherStationDto).build();
		

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
