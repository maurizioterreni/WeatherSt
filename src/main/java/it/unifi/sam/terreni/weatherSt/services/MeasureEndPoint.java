package it.unifi.sam.terreni.weatherSt.services;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unifi.sam.terreni.weatherSt.dao.MeasureDao;
import it.unifi.sam.terreni.weatherSt.dao.SensorDao;
import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.sensor.Measure;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.model.sensor.units.UnitMeasure;
import it.unifi.sam.terreni.weatherSt.utils.StringUtils;



@Path("/measure")
public class MeasureEndPoint {
	
	@Inject
	private SensorDao sensorDao;
	@Inject
	private MeasureDao measureDao;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response add(@HeaderParam("token") String token, @HeaderParam("sensorId") Long sensorId,@HeaderParam("value") Float value, 
			@HeaderParam("units") UnitMeasure unitMeasure) {
		if (StringUtils.isEmpty(token) || sensorId == null) {
			return Response.status(500).build();
		}
		
		Sensor sensor = sensorDao.findById(sensorId);
		if (sensor == null) {
			return Response.status(404).build();
		}
		
		Measure measure = ModelFactory.measure();
		measure.setSensor(sensor);
		measure.setTimestamp(System.currentTimeMillis());
		measure.setUnit(unitMeasure);
		measure.setValue(value);

		sensor.addMeasuer(measure);
		
		measureDao.save(measure);
		
		
		return Response.status(200).entity(measure).build();
	}
}

