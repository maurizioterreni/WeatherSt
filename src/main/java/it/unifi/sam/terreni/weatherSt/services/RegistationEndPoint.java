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
import it.unifi.sam.terreni.weatherSt.dao.user.UserPropertieDao;
import it.unifi.sam.terreni.weatherSt.dto.user.RegistrationDto;
import it.unifi.sam.terreni.weatherSt.dto.user.UserDto;
import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;
import it.unifi.sam.terreni.weatherSt.model.user.User;
import it.unifi.sam.terreni.weatherSt.model.user.UserPropertie;
import it.unifi.sam.terreni.weatherSt.model.user.UserRole;
import it.unifi.sam.terreni.weatherSt.utils.ErrorServices;

@Path("/registration")
public class RegistationEndPoint {
	@Inject
	private UserDao userDao;
	@Inject
	private UserPropertieDao userPropertieDao;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response registration(RegistrationDto requestDto) {
		if(requestDto == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - requestDto").build();

		if(userDao.getUserByPassword(requestDto.getUsername(), requestDto.getPassword()) != null)
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - user must be unique").build();
		
		UserPropertie propertie = UserPropertie.builder()
				.userRole(UserRole.USER)
				.build();

		User user = User.builder()
				.email(requestDto.getEmail())
				.password(requestDto.getPassword())
				.username(requestDto.getUsername())
				.propertie(propertie)
				.build();
		
		userPropertieDao.save(propertie);
		userDao.save(user);
		return Response.status(200).entity(userToDto(user)).build();
	}
	
	private UserDto userToDto(User user) {
		UserDto dto = new UserDto();
		
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setUnitMeasure(getIdFormUnitMeasure(user.getPropertie().getUnitMeasure()));
		dto.setWeatherLikes(getIdFormWeatherLikes(user.getPropertie().getWeatherStationLikes()));
		dto.setUsername(user.getUsername());
		dto.setUserRole(user.getPropertie().getUserRole().name());
		dto.setWeatherId(user.getPropertie().getWeatherStation() != null ? user.getPropertie().getWeatherStation().getId() : null);
		
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
