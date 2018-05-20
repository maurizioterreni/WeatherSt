package it.unifi.sam.terreni.weatherSt.services;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unifi.sam.terreni.weatherSt.dao.WeatherStationDao;
import it.unifi.sam.terreni.weatherSt.dao.measure.UnitMeasureKnowledgeDao;
import it.unifi.sam.terreni.weatherSt.dao.user.UserDao;
import it.unifi.sam.terreni.weatherSt.dto.user.UserDto;
import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;
import it.unifi.sam.terreni.weatherSt.model.user.User;
import it.unifi.sam.terreni.weatherSt.security.Authentication;
import it.unifi.sam.terreni.weatherSt.utils.ErrorServices;
import it.unifi.sam.terreni.weatherSt.utils.StringUtils;

@Path("/user")
public class UserEndPoin {
	@Inject
	private UserDao userDao;
	@Inject
	private WeatherStationDao weatherStationDao;
	@Inject
	private UnitMeasureKnowledgeDao unitMeasureKnowledgeDao;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addWeatherLike")
	@Transactional
	public Response addWeatherLike(@HeaderParam("token") String token, @HeaderParam("weatherId") Long weatherId) {
		if(weatherId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - weatherId").build();

		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		
		if(Authentication.isNotValid(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token not valid").build();
		
		
		User user = userDao.fetchById(Authentication.getUserIdFormToken(token));
		if(user == null)
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - user or password wrong").build();

		WeatherStation weatherStation = weatherStationDao.findById(weatherId);
		
		if(weatherStation == null)
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - weatherStation").build();
		
		user.getPropertie().addWeatherStationLikes(weatherStation);
		
		userDao.update(user);
		
		UserDto dto = userToDto(user);
		
		dto.setToken(token);

		return Response.status(200).entity(dto).build();
	}
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/removeWeatherLike")
	@Transactional
	public Response removeWeatherLike(@HeaderParam("token") String token, @HeaderParam("weatherId") Long weatherId) {
		if(weatherId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - weatherId").build();

		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		
		if(Authentication.isNotValid(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token not valid").build();
		
		
		User user = userDao.fetchById(Authentication.getUserIdFormToken(token));
		if(user == null)
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - user or password wrong").build();

		WeatherStation weatherStation = weatherStationDao.findById(weatherId);
		
		if(weatherStation == null)
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - weatherStation").build();
		
		user.getPropertie().removeWeatherStationLikes(weatherStation);
		
		userDao.update(user);
		
		UserDto dto = userToDto(user);
		
		dto.setToken(token);

		return Response.status(200).entity(dto).build();
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addUnitMeasure")
	@Transactional
	public Response addUnitMeasure(@HeaderParam("token") String token, @HeaderParam("unitId") Long unitId) {
		if(unitId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - weatherId").build();

		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		
		if(Authentication.isNotValid(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token not valid").build();
		
		
		User user = userDao.fetchById(Authentication.getUserIdFormToken(token));
		if(user == null)
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - user or password wrong").build();

		UnitMeasureKnowledge unitMeasureKnowledge = unitMeasureKnowledgeDao.findById(unitId);
		
		if(unitMeasureKnowledge == null)
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - unitMeasureKnowledge").build();
		
		user.getPropertie().addUnitMeasure(unitMeasureKnowledge);
		
		userDao.update(user);
		
		UserDto dto = userToDto(user);
		
		dto.setToken(token);

		return Response.status(200).entity(dto).build();
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/removeUnitMeasure")
	@Transactional
	public Response removeUnitMeasure(@HeaderParam("token") String token, @HeaderParam("unitId") Long unitId) {
		if(unitId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - weatherId").build();

		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		
		if(Authentication.isNotValid(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token not valid").build();
		
		
		User user = userDao.fetchById(Authentication.getUserIdFormToken(token));
		if(user == null)
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - user or password wrong").build();

		UnitMeasureKnowledge unitMeasureKnowledge = unitMeasureKnowledgeDao.findById(unitId);
		
		if(unitMeasureKnowledge == null)
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - unitMeasureKnowledge").build();
		
		user.getPropertie().removeUnitMeasure(unitMeasureKnowledge);
		
		userDao.update(user);
		
		UserDto dto = userToDto(user);
		
		dto.setToken(token);

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
