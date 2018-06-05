package it.unifi.sam.terreni.weatherSt.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unifi.sam.terreni.weatherSt.dao.WeatherStationDao;
import it.unifi.sam.terreni.weatherSt.dao.user.UserDao;
import it.unifi.sam.terreni.weatherSt.dto.weatherStation.WeatherStationPostRequestDto;
import it.unifi.sam.terreni.weatherSt.dto.weatherStation.WeatherStationResponseDto;
import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.model.user.User;
import it.unifi.sam.terreni.weatherSt.security.Authentication;
import it.unifi.sam.terreni.weatherSt.utils.ErrorServices;
import it.unifi.sam.terreni.weatherSt.utils.StringUtils;

@Path("/weatherstation")
public class WeatherStationEndPoint {
	@Inject
	private WeatherStationDao weatherStationDao;
	@Inject
	private UserDao userDao;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(@HeaderParam("token") String token, WeatherStationPostRequestDto weatherStationDto) {
		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		if (weatherStationDto == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - weatherStationDto").build();
		if(Authentication.isNotValid(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token not valid").build();
		
		
		Long userId = Authentication.getUserIdFormToken(token);
		
		User user = userDao.fetchById(userId);
		
		if(user == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - user").build();
		
		WeatherStation weatherStation = WeatherStation.builder()
				.description(weatherStationDto.getDescription())
				.latitude(weatherStationDto.getLatitude())
				.longitude(weatherStationDto.getLongitude())
				.images(weatherStationDto.getImage())
				.build();
		
		user.getPropertie().setWeatherStation(weatherStation);
		
		weatherStationDao.save(weatherStation);
		userDao.update(user);
		
		
	
		return Response.status(200).entity(weatherStationToDto(weatherStation)).build();
		

	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll")
	public Response getAll() {
		
		List<WeatherStation> weatherStations = weatherStationDao.getAll();
		
		if(weatherStations == null || weatherStations.size() == 0)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - measure").build();
		
		List<WeatherStationResponseDto> listWeatherStationDto = new ArrayList<>();
		
		for (WeatherStation weatherStation : weatherStations) {
			listWeatherStationDto.add(weatherStationToDto(weatherStation));
		}
		
		
		return Response.status(200).entity(listWeatherStationDto).build();
	}
	
	private WeatherStationResponseDto weatherStationToDto(WeatherStation weatherStation) {
		WeatherStationResponseDto dto = new WeatherStationResponseDto();
		
		dto.setId(weatherStation.getId());
		dto.setDescription(weatherStation.getDescription());
		dto.setLatitude(weatherStation.getLatitude());
		dto.setLongitude(weatherStation.getLongitude());
		dto.setImage(weatherStation.getImages());
		
		for (Sensor sensor : weatherStation.getSensors()) {
			dto.addSensorId(sensor.getId());
		}
		
		return dto;
	}
}
