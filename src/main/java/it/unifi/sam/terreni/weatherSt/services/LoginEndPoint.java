package it.unifi.sam.terreni.weatherSt.services;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unifi.sam.terreni.weatherSt.dao.user.UserDao;
import it.unifi.sam.terreni.weatherSt.dto.user.LoginDto;
import it.unifi.sam.terreni.weatherSt.dto.user.UserDto;
import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;
import it.unifi.sam.terreni.weatherSt.model.user.User;
import it.unifi.sam.terreni.weatherSt.security.Authentication;
import it.unifi.sam.terreni.weatherSt.utils.ErrorServices;

@Path("/login")
public class LoginEndPoint {
	@Inject
	private UserDao userDao;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response login(LoginDto requestDto) {
		if(requestDto == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - requestDto").build();

		User user = userDao.getUserByPassword(requestDto.getUsername(), requestDto.getPassword());
		if(user == null)
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - user or password wrong").build();

		UserDto dto = userToDto(user);
		
		dto.setToken(Authentication.generateToken(user));

		return Response.status(200).entity(dto).build();
	}

	private UserDto userToDto(User user) {
		UserDto dto = new UserDto();
		
		dto.setEmail(user.getEmail());
		dto.setUnitMeasure(getIdFormUnitMeasure(user.getPropertie().getUnitMeasure()));
		dto.setUsername(user.getUsername());
		dto.setUserRole(user.getPropertie().getUserRole().getName());
		dto.setWeatherId(user.getPropertie().getWeatherStation() != null ? user.getPropertie().getWeatherStation().getId() : null);
		dto.setWeatherLikes(getIdFormWeatherLikes(user.getPropertie().getWeatherStationLikes()));
		
		return dto;
	}
	
	private Set<Long> getIdFormUnitMeasure(Set<UnitMeasureKnowledge> list){
		Set<Long> ids = new HashSet<>();
		if(list == null)
			return ids;
		
		for (UnitMeasureKnowledge unitMeasureKnowledge : list) {
			ids.add(unitMeasureKnowledge.getId());
		}
		
		return ids;
	}
	private Set<Long> getIdFormWeatherLikes(Set<WeatherStation> list){
		Set<Long> ids = new HashSet<>();
		if(list == null)
			return ids;
		
		for (WeatherStation wt : list) {
			ids.add(wt.getId());
		}
		
		return ids;
	}
}
