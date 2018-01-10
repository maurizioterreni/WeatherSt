package it.unifi.sam.terreni.weatherSt.services;

import java.sql.Date;
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

import it.unifi.sam.terreni.weatherSt.dao.MeasureDao;
import it.unifi.sam.terreni.weatherSt.dao.SensorDao;
import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.sensor.Measure;
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

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response add(@HeaderParam("sensorId") Long sensorId, @HeaderParam("value") Float value, @HeaderParam("unit") String unit, @HeaderParam("temp") Long timstamp) {
		if (sensorId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();
		if (value == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - value").build();
		if (StringUtils.isEmpty(unit))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - valueType").build();

		Sensor sensor = sensorDao.findById(sensorId);
		if (sensor == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();


		Measure measure = ModelFactory.measure(sensor.getSensorType(),unit);
		measure.setSensor(sensor);
		//measure.setTimestamp(System.currentTimeMillis());
		measure.setTimestamp(timstamp);
		measure.setValue(value);

		sensor.addMeasuer(measure);
		measureDao.save(measure);
		return Response.status(200).entity(measure).build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getMin(@HeaderParam("token") String token, @HeaderParam("sensorId") Long sensorId) {
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
	@Path("/betweenDate")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response get(@HeaderParam("token") String token, @HeaderParam("sensorId") Long sensorId, @HeaderParam("fromDate") Date fromDate, @HeaderParam("toDate") Date toDate) {
		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		if (sensorId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();
		if (fromDate == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - fromDate").build();
		if (toDate == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - toDate").build();

		if(!CheckClass.checkToken(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - token error").build();

		Sensor sensor = sensorDao.findById(sensorId);

		if(sensor == null)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();


		List<Measure> measures = measureDao.getMeasureBetweenDate(sensor, fromDate, toDate);

		if(measures == null || measures.size() == 0)
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - measure").build();

		return Response.status(200).entity(measures).build();
	}

	@GET
	@Path("/max")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getMax(@HeaderParam("token") String token, @HeaderParam("sensorId") Long sensorId, @HeaderParam("fromDate") Date fromDate, @HeaderParam("toDate") Date toDate) {
		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		if (sensorId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();
		if (fromDate == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - fromDate").build();
		if (toDate == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - toDate").build();

		if(!CheckClass.checkToken(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - token error").build();

		Sensor sensor = sensorDao.findById(sensorId);

		if(sensor == null)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();

		Measure measure = measureDao.getMax(sensor, fromDate, toDate);

		if(measure == null)
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - measure").build();

		return Response.status(200).entity(measure).build();
	}

	@GET
	@Path("/min")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getMin(@HeaderParam("token") String token, @HeaderParam("sensorId") Long sensorId, @HeaderParam("fromDate") Date fromDate, @HeaderParam("toDate") Date toDate) {
		if (StringUtils.isEmpty(token))
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - token").build();
		if (sensorId == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - sensorId").build();
		if (fromDate == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - fromDate").build();
		if (toDate == null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ErrorServices.NULL_OBJECT.getMessage() + " - toDate").build();

		if(!CheckClass.checkToken(token))
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.UNAUTHORIZED.getMessage() + " - token error").build();

		Sensor sensor = sensorDao.findById(sensorId);

		if(sensor == null)
			return Response.status(Response.Status.NOT_FOUND).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - sensor").build();


		Measure measure = measureDao.getMin(sensor, fromDate, toDate);

		if(measure == null)
			return Response.status(Response.Status.UNAUTHORIZED).entity(ErrorServices.OBJECT_NOT_FOUND.getMessage() + " - measure").build();

		return Response.status(200).entity(measure).build();
	}



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
}

