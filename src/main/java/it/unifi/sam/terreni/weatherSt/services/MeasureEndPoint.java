package it.unifi.sam.terreni.weatherSt.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
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

import it.unifi.sam.terreni.weatherSt.dao.MeasureDao;
import it.unifi.sam.terreni.weatherSt.dao.SensorDao;
import it.unifi.sam.terreni.weatherSt.dao.UnitMeasureKnowledgeDao;
import it.unifi.sam.terreni.weatherSt.dao.WeatherStationDao;
import it.unifi.sam.terreni.weatherSt.dto.measure.MeasurePostRequestDto;
import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.utils.CheckClass;
import it.unifi.sam.terreni.weatherSt.utils.ErrorServices;
import it.unifi.sam.terreni.weatherSt.utils.StringUtils;



@Path("/measure")
public class MeasureEndPoint {

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
	public Response get(@HeaderParam("token") String token, @HeaderParam("sensorId") Long sensorId) {
		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		if (sensorId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();

		if(!CheckClass.checkToken(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - token error").build();

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
	public Response getLastOfWeather(@HeaderParam("token") String token, @HeaderParam("weatherId") Long weatherId) {
		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		if (weatherId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();

		if(!CheckClass.checkToken(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - token error").build();

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

//	@GET
//	@Path("/betweenDate")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Transactional
//	public Response get(@HeaderParam("token") String token, @HeaderParam("sensorId") Long sensorId, @HeaderParam("froMatate") Date froMatate, @HeaderParam("toDate") Date toDate) {
//		if (StringUtils.isEmpty(token))
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
//		if (sensorId == null)
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();
//		if (froMatate == null)
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - froMatate").build();
//		if (toDate == null)
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - toDate").build();
//
//		if(!CheckClass.checkToken(token))
//			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - token error").build();
//
//		Sensor sensor = sensorDao.findById(sensorId);
//
//		if(sensor == null)
//			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();
//
//
//		List<Measure> measures = measureDao.getMeasureBetweenDate(sensor, froMatate, toDate);
//
//		if(measures == null || measures.size() == 0)
//			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - measure").build();
//
//		return Response.status(200).entity(measures).build();
//	}
//
//	@GET
//	@Path("/max")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Transactional
//	public Response getMax(@HeaderParam("token") String token, @HeaderParam("sensorId") Long sensorId, @HeaderParam("froMatate") Date froMatate, @HeaderParam("toDate") Date toDate) {
//		if (StringUtils.isEmpty(token))
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
//		if (sensorId == null)
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();
//		if (froMatate == null)
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - froMatate").build();
//		if (toDate == null)
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - toDate").build();
//
//		if(!CheckClass.checkToken(token))
//			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - token error").build();
//
//		Sensor sensor = sensorDao.findById(sensorId);
//
//		if(sensor == null)
//			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();
//
//		Measure measure = measureDao.getMax(sensor, froMatate, toDate);
//
//		if(measure == null)
//			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - measure").build();
//
//		return Response.status(200).entity(measure).build();
//	}
//
//	@GET
//	@Path("/min")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Transactional
//	public Response getMin(@HeaderParam("token") String token, @HeaderParam("sensorId") Long sensorId, @HeaderParam("froMatate") Date froMatate, @HeaderParam("toDate") Date toDate) {
//		if (StringUtils.isEmpty(token))
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
//		if (sensorId == null)
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();
//		if (froMatate == null)
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - froMatate").build();
//		if (toDate == null)
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - toDate").build();
//
//		if(!CheckClass.checkToken(token))
//			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - token error").build();
//
//		Sensor sensor = sensorDao.findById(sensorId);
//
//		if(sensor == null)
//			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();
//
//
//		Measure measure = measureDao.getMin(sensor, froMatate, toDate);
//
//		if(measure == null)
//			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - measure").build();
//
//		return Response.status(200).entity(measure).build();
//	}



	/*
	 public static String ALGORITHM = "AES";
	private static String AES_CBS_PADDING = "AES/CBC/PKCS5Padding";

	public static byte[] encrypt(final byte[] key, final byte[] IV, final byte[] message) throws Exception {
		return encryptDecrypt(Cipher.ENCRYPT_MODE, key, IV, message);
	}

	public static byte[] decrypt(final byte[] key, final byte[] IV, final byte[] message) throws Exception {
		return encryptDecrypt(Cipher.DECRYPT_MODE, key, IV, message);
	}

	private static byte[] encryptDecrypt(final int mode, final byte[] key, final byte[] IV, final byte[] message) throws Exception {
		final Cipher cipher = Cipher.getInstance(AES_CBS_PADDING);
		final SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
		final IvParameterSpec ivSpec = new IvParameterSpec(IV);
		cipher.init(mode, keySpec, ivSpec);
		return cipher.doFinal(message);
	}

	public static void main(String[] args) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
		keyGenerator.init(AES_128);
		//Generate Key
		SecretKey key = keyGenerator.generateKey();
		//Initialization vector
		SecretKey IV = keyGenerator.generateKey();

		String randomString = "username#" + System.currentTimeMillis();
		System.out.println("1. Message to Encrypt: " + randomString);

		byte[] cipherText = encrypt(key.getEncoded(), IV.getEncoded(), randomString.getBytes());
		System.out.println("2. Encrypted Text: " + Base64.getEncoder().encodeToString(cipherText));

		byte[] decryptedString = decrypt(key.getEncoded(), IV.getEncoded(), cipherText);
		System.out.println("3. Decrypted Message : " + new String(decryptedString));
	} 
	 */
//	LocalDate
//	LocalDateTime
}

