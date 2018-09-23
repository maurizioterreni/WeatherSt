package it.unifi.sam.terreni.weatherSt.services;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unifi.sam.terreni.weatherSt.dao.measure.MeasureDao;
import it.unifi.sam.terreni.weatherSt.dao.measure.UnitMeasureKnowledgeDao;
import it.unifi.sam.terreni.weatherSt.dao.sensor.SensorDao;
import it.unifi.sam.terreni.weatherSt.dto.measure.MeasureDto;
import it.unifi.sam.terreni.weatherSt.dto.measure.MeasurePostRequestDto;
import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.utils.ErrorServices;
import it.unifi.sam.terreni.weatherSt.utils.StringUtils;



@Path("/measure")
public class MeasureEndPoint {
	@Inject
	private SensorDao sensorDao;
	@Inject
	private MeasureDao measureDao;
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

		Measure measure = Measure
				.buider()
				.localDateTime(LocalDateTime.now())
				.quantity(requestDto.getQuantity())
				.unitMeasure(unitMeasure)
				.sensor(sensor)
				.build();

		
		measureDao.save(measure);
		
		return Response.status(200).entity(requestDto).build();

	}
	

	@GET
	@Path("/sensor/{id}/last")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getBySensorId(@PathParam("id") Long id) {
		Sensor sensor = sensorDao.findById(id);

		if(sensor == null)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();

		Measure measure = measureDao.getLastMeasue(sensor);

		if(measure == null)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - measure").build();

		return Response.status(200).entity(measureToMeasureDto(measure)).build();
	}
	
	@GET
	@Path("/sensor/{id}/getValueOfYear")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getValueOfYear(@PathParam("id") Long id) {
		Sensor sensor = sensorDao.findById(id);
		
		if(sensor == null)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();
		
		
		LocalDateTime fromDate = LocalDateTime.now().with(firstDayOfYear());
		LocalDateTime toDate = LocalDateTime.now().with(lastDayOfYear());
		
		List<Measure> measures = new ArrayList<>();
		measures.add(measureDao.getMax(sensor, fromDate, toDate));
		measures.add(measureDao.getMin(sensor, fromDate, toDate));
		
		if(measures.size() != 2)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - measures").build();
		
		return Response.status(200).entity(measuresToMeasureDtos(measures)).build();
	}
	@GET
	@Path("/sensor/{id}/getValueOfAll")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getValueOfAll(@PathParam("id") Long id) {
		Sensor sensor = sensorDao.findById(id);
		
		if(sensor == null)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();
		
		
		LocalDateTime fromDate = LocalDateTime.of(1970, 01, 01, 0, 0);
		LocalDateTime toDate = LocalDateTime.now().with(lastDayOfYear());
		
		List<Measure> measures = new ArrayList<>();
		measures.add(measureDao.getMax(sensor, fromDate, toDate));
		measures.add(measureDao.getMin(sensor, fromDate, toDate));
		
		if(measures.size() != 2)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - measures").build();
		
		return Response.status(200).entity(measuresToMeasureDtos(measures)).build();
	}
	@GET
	@Path("/sensor/{id}/getValueOfMonth")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getValueOfMonth(@PathParam("id") Long id) {
		Sensor sensor = sensorDao.findById(id);
		
		if(sensor == null)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();
		
		
		LocalDateTime fromDate = LocalDateTime.now().with(firstDayOfMonth());
		LocalDateTime toDate = LocalDateTime.now().with(lastDayOfMonth());
		
		List<Measure> measures = new ArrayList<>();
		measures.add(measureDao.getMax(sensor, fromDate, toDate));
		measures.add(measureDao.getMin(sensor, fromDate, toDate));
		
		if(measures.size() != 2)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - measures").build();
		
		return Response.status(200).entity(measuresToMeasureDtos(measures)).build();
	}
	@GET
	@Path("/sensor/{id}/getValueOfDay")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getValueOfDay(@PathParam("id") Long id) {
		Sensor sensor = sensorDao.findById(id);
		
		if(sensor == null)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();
		
		
		LocalDateTime fromDate = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
		LocalDateTime toDate = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
		
		List<Measure> measures = new ArrayList<>();
		measures.add(measureDao.getMax(sensor, fromDate, toDate));
		measures.add(measureDao.getMin(sensor, fromDate, toDate));
		
		if(measures.size() != 2)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - measures").build();
		
		return Response.status(200).entity(measuresToMeasureDtos(measures)).build();
	}
	
	@GET
	@Path("/sensor/{id}/getMeasure")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getMeasure(@PathParam("id") Long id) {
		Sensor sensor = sensorDao.findById(id);
		
		if(sensor == null)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();
		
		
		LocalDateTime fromDate = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
		LocalDateTime toDate = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
		
		List<Measure> measures = measureDao.getMeasureBetweenDate(sensor, fromDate, toDate);
		
		return Response.status(200).entity(measuresToMeasureDtos(measures)).build();
	}
	
	
	private MeasureDto measureToMeasureDto(Measure measure) {
		if(measure == null)
			return null;
		
		return MeasureDto.builder()
				.dateTime(measure.getLocalDateTime().toString())
				.quantity(StringUtils.floatToString(measure.getQuantity()))
				.name(measure.getUnitMeasure().getName())
				.symbol(measure.getUnitMeasure().getSymbol())
				.build();
	}
	
	private List<MeasureDto> measuresToMeasureDtos(List<Measure> measures) {
		List<MeasureDto> measureDtos = new ArrayList<>();
		for (Measure measure : measures) {
			MeasureDto m = measureToMeasureDto(measure);
			if(m != null)
				measureDtos.add(measureToMeasureDto(measure));
		}
		return measureDtos;
	}
	
//	@GET
//	@Path("/lastMeasure")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Transactional
//	public Response getLastOfWeather(@HeaderParam("weatherId") Long weatherId) {
//		if (weatherId == null)
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();
//
//		WeatherStation weatherStation = weatherStationDao.fetchById(weatherId);
//
//		if(weatherStation == null)
//			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - weatherStation").build();
//		
//		if(weatherStation.getSensors() == null || weatherStation.getSensors().size() == 0)
//			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();
//
//		Set<Measure> measures = new HashSet<>();
//		
//		for (Sensor sensor : weatherStation.getSensors()) {
//			measures.add(measureDao.getLastMeasue(sensor));
//		}
//	
//	LocalDateTime fromDate = LocalDateTime.now().toLocalDate().atTime(LocalTime.of(0, 0, 1));
//	LocalDateTime toDate = LocalDateTime.now().toLocalDate().atTime(LocalTime.of(23, 59, 59));
//
//		return Response.status(200).entity(measures).build();
//	}
	
	
	
//	@GET
//	@Path("/getMeasureByDate")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Transactional
//	public Response getMeasureByDate(@HeaderParam("sensorId") Long sensorId, @HeaderParam("fromDate") Long fromDate, @HeaderParam("toDate") Long toDate) {
//		if (sensorId == null)
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();
//		if (fromDate == null)
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - fromDate").build();
//		if (toDate == null)
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - toDate").build();
//		
//		Sensor sensor = sensorDao.findById(sensorId);
//		
//		if(sensor == null)
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensor").build();
//
//		if(fromDate > toDate) {
//			Long temp = fromDate;
//			fromDate = toDate;
//			toDate = temp;
//		}
//		
//		String groupby = getGroupBy(fromDate, toDate);
//		
//		List<MeasureChartDto> measures = measureDao.getLotOfMeasureDtoBetweenDate(sensor, 
//				Instant.ofEpochMilli(fromDate).atZone(ZoneId.systemDefault()).toLocalDateTime(), 
//				Instant.ofEpochMilli(toDate).atZone(ZoneId.systemDefault()).toLocalDateTime(),
//				groupby);
//		
//		return Response.status(200).entity(measures).build();
//	}
//
//
//	private String getGroupBy(Long fromDate, Long toDate) {
//		Long dif = toDate - fromDate;
//		if(dif > GroupByClass.MAX_DAY)
//			return GroupByClass.DAY_GROUPBY;
//		else if(dif > GroupByClass.MAX_WEEK)
//			return GroupByClass.WEEK_GROUPBY;
//		else if(dif > GroupByClass.MAX_MONTH)
//			return GroupByClass.MONTH_GROUPBY;
//		else if(dif > GroupByClass.MAX_YEAR)
//			return GroupByClass.YEAR_GROUPBY;
//		else
//			return GroupByClass.HOUR_GROUPBY;
//		
//	}

}

