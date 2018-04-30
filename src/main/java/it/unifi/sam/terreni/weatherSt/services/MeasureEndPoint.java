package it.unifi.sam.terreni.weatherSt.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import it.unifi.sam.terreni.weatherSt.dao.WeatherStationDao;
import it.unifi.sam.terreni.weatherSt.dao.measure.MeasureDao;
import it.unifi.sam.terreni.weatherSt.dao.measure.UnitMeasureKnowledgeDao;
import it.unifi.sam.terreni.weatherSt.dao.sensor.SensorDao;
import it.unifi.sam.terreni.weatherSt.dto.measure.MeasureChartDto;
import it.unifi.sam.terreni.weatherSt.dto.measure.MeasurePostRequestDto;
import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.utils.ErrorServices;



@Path("/measure")
public class MeasureEndPoint {
	
	private static Long MAX_YEAR = 631139040000L;//20 anni 31556952000 * 20
	private static Long MAX_MONTH = 63113904000L;//24 mesi 2629746000 * 24
	private static Long MAX_WEEK = 7257600000L;//12 settimane 604800000 * 12
	private static Long MAX_DAY = 864000000L;//10 giorni 86400000 * 10

	@Inject
	private SensorDao sensorDao;
	@Inject
	private MeasureDao measureDao;
	@Inject
	private WeatherStationDao weatherStationDao;
	@Inject
	private UnitMeasureKnowledgeDao unitMeasureKnowledgeDao;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response add(MeasurePostRequestDto requestDto) {
		if (requestDto == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - requestDto").build();
		
		Sensor sensor = sensorDao.findById(requestDto.getSensorId());
		
		if (sensor == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();
		
		UnitMeasureKnowledge unitMeasure = unitMeasureKnowledgeDao.findById(requestDto.getUnitMeasureId());
		if (unitMeasure == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - unitMeasure").build();

		LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(requestDto.getDateTime()), ZoneId.systemDefault());
		
		Measure measure = Measure
				.buider()
				//.localDateTime(LodcalDateTime.now())
				.localDateTime(dateTime)
				.quantity(requestDto.getQuantity())
				.unitMeasure(unitMeasure)
				.sensor(sensor)
				.build();

		
		measureDao.save(measure);
		
		return Response.status(200).entity(requestDto).build();

	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response get(@HeaderParam("sensorId") Long sensorId) {
		if (sensorId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();

		Sensor sensor = sensorDao.findById(sensorId);

		if(sensor == null)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();

		Measure measure = measureDao.getLastMeasue(sensor);

		if(measure == null)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - measure").build();

		return Response.status(200).entity(measure).build();
	}
	
	@GET
	@Path("/lastMeasure")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getLastOfWeather(@HeaderParam("weatherId") Long weatherId) {
		if (weatherId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();

		WeatherStation weatherStation = weatherStationDao.fetchById(weatherId);

		if(weatherStation == null)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - weatherStation").build();
		
		if(weatherStation.getSensors() == null || weatherStation.getSensors().size() == 0)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();

		Set<Measure> measures = new HashSet<>();
		
		for (Sensor sensor : weatherStation.getSensors()) {
			measures.add(measureDao.getLastMeasue(sensor));
		}

		return Response.status(200).entity(measures).build();
	}
	
	
	
	@GET
	@Path("/getMeasureByDate")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getMeasureByDate(@HeaderParam("sensorId") Long sensorId, @HeaderParam("fromDate") Long fromDate, @HeaderParam("toDate") Long toDate) {
		if (sensorId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();
		if (fromDate == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - fromDate").build();
		if (toDate == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - toDate").build();
		
		Sensor sensor = sensorDao.findById(sensorId);
		
		if(sensor == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensor").build();

		if(fromDate > toDate) {
			Long temp = fromDate;
			fromDate = toDate;
			toDate = temp;
		}
		
		String groupby = getGroupBy(fromDate, toDate);
		
		List<MeasureChartDto> measures = measureDao.getLotOfMeasureDtoBetweenDate(sensor, 
				Instant.ofEpochMilli(fromDate).atZone(ZoneId.systemDefault()).toLocalDateTime(), 
				Instant.ofEpochMilli(toDate).atZone(ZoneId.systemDefault()).toLocalDateTime(),
				groupby);
		
		return Response.status(200).entity(measures).build();
	}


	private String getGroupBy(Long fromDate, Long toDate) {
		Long dif = toDate - fromDate;
		if(dif > MAX_DAY)
			return " day(m.localDateTime), month(m.localDateTime), year(m.localDateTime) ";
		else if(dif > MAX_WEEK)
			return " week(m.localDateTime), month(m.localDateTime), year(m.localDateTime) ";
		else if(dif > MAX_MONTH)
			return " year(m.localDateTime), month(m.localDateTime) ";
		else if(dif > MAX_YEAR)
			return " year(m.localDateTime) ";
		else
			return " hour(m.localDateTime), day(m.localDateTime), month(m.localDateTime)  ";
		
	}

}

