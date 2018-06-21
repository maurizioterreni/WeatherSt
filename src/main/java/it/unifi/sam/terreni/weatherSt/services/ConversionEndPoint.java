package it.unifi.sam.terreni.weatherSt.services;

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

import it.unifi.sam.terreni.weatherSt.dao.ConversionFactorDao;
import it.unifi.sam.terreni.weatherSt.dao.measure.UnitMeasureKnowledgeDao;
import it.unifi.sam.terreni.weatherSt.dto.conversion.ConversionFactoryDto;
import it.unifi.sam.terreni.weatherSt.model.converter.ConversionFactor;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;
import it.unifi.sam.terreni.weatherSt.security.Authentication;
import it.unifi.sam.terreni.weatherSt.utils.ErrorServices;

@Path("/conversion")
public class ConversionEndPoint {
	@Inject
	private ConversionFactorDao conversionFactorDao;
	@Inject
	private UnitMeasureKnowledgeDao unitMeasureKnowledgeDao;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response add(@HeaderParam("token") String token, @HeaderParam("fronUnitId") Long fromUnitId, @HeaderParam("toUnitId") Long toUnitId, 
			@HeaderParam("conversionMul") Float conversionMul,@HeaderParam("conversionAdd") Float conversionAdd, @HeaderParam("conversionDiv") Float conversionDiv) {
		if (fromUnitId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - fromUnitId").build();
		if (toUnitId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - toUnitId").build();
		if (conversionMul == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - conversionMul").build();
		if (conversionAdd == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - conversionAdd").build();
		if (conversionDiv == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - conversionDiv").build();
	
		
		if(Authentication.isNotValid(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token not valid").build();
		
		
		UnitMeasureKnowledge fromUnit = unitMeasureKnowledgeDao.findById(fromUnitId);
		UnitMeasureKnowledge toUnit = unitMeasureKnowledgeDao.findById(toUnitId);
		
		ConversionFactor conversionFactor = ConversionFactor.builder()
				.conversionFactorMul(conversionMul)
				.conversionFactorAdd(conversionAdd)
				.conversionFactorDiv(conversionDiv)
				.fromUnitMeasure(fromUnit)
				.toUnitMeasure(toUnit)
				.build();
		
		conversionFactorDao.save(conversionFactor);
		
		return Response.status(200).build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response get(@HeaderParam("conversionFactorid") Long conversionFactorid) {
		if (conversionFactorid == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - conversionFactorid").build();

		ConversionFactor conversionFactor = conversionFactorDao.findById(conversionFactorid);
		
		if(conversionFactor == null)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - conversionFactor").build();
		
		

		return Response.status(200).entity(getConversionFactorDto(conversionFactor)).build();
	}
	
	
	@GET
	@Path("/getAllFromUnit")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getAllFromUnit(@HeaderParam("fromUnitId") Long fromUnitId) {
		if (fromUnitId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - fromUnitId").build();

		UnitMeasureKnowledge fromUnit = unitMeasureKnowledgeDao.findById(fromUnitId);
		
		if(fromUnit == null)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - fromUnit").build();
		
		
		
		List<ConversionFactor> listConversion = conversionFactorDao.getConversionFactorByFromUnitId(fromUnit);
		
		if(listConversion == null)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - listConversion").build();
		
		

		return Response.status(200).entity(getConversionFactorDto(listConversion)).build();
	}
	
	private List<ConversionFactoryDto> getConversionFactorDto(List<ConversionFactor> list){
		List<ConversionFactoryDto> listDto = new ArrayList<>();
		
		for (ConversionFactor obj : list) {
			listDto.add(getConversionFactorDto(obj));
		}
		
		return listDto;
	}
	private ConversionFactoryDto getConversionFactorDto(ConversionFactor conversionFactor) {
		return ConversionFactoryDto.builder()
				.conversionFactorMul(conversionFactor.getConversionFactorMul())
				.conversionFactorAdd(conversionFactor.getConversionFactorAdd())
				.conversionFactorDiv(conversionFactor.getConversionFactorDiv())
				.fromSymbol(conversionFactor.getFromUnitMeasure().getSymbol())
				.toSymbol(conversionFactor.getToUnitMeasure().getSymbol())
				.id(conversionFactor.getId())
				.build();
	}
	
}
