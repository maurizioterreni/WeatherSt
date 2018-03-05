package it.unifi.sam.terreni.weatherSt.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.reflect.FieldUtils;

import it.unifi.sam.terreni.weatherSt.dao.measure.MeasureDao;
import it.unifi.sam.terreni.weatherSt.dao.sensor.SensorDao;
import it.unifi.sam.terreni.weatherSt.model.facotry.ModelFactory;
import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;

public class MeasureDaoTestDelegate {
	private MeasureDao measureDao;
	private SensorDao sensorDao;

	private Long measureId;
	private Long sensorId;

	public void testSave() {
		Measure measure = ModelFactory.measure();
		measureDao.save(measure);

		assertNotNull(measureDao.findById(measure.getId()));
	}

	public void testFindById() {
		assertNotNull(measureDao.findById(measureId));
	}

	public void testDelete() {
		Measure measure = measureDao.findById(measureId);

		assertNotNull(measure);

		measureDao.delete(measureDao.findById(measureId));

		assertNull(measureDao.findById(measure.getId()));
	}

	public void testMeasureBetweenDate() {

		Sensor sensor = sensorDao.findById(sensorId);

		assertNotNull(sensor);

		LocalDateTime fromDate = LocalDateTime.now().toLocalDate().atTime(LocalTime.of(0, 0, 1));
		LocalDateTime toDate = LocalDateTime.now().toLocalDate().atTime(LocalTime.of(23, 59, 59));

		List<Measure> measures = measureDao.getMeasureBetweenDate(sensor, fromDate, toDate);

		assertNotNull(measures);
		assertEquals(1,measures.size());
	}

	public void testLastMeasue() {
		Sensor sensor = sensorDao.findById(sensorId);

		assertNotNull(sensor);
		
		Measure measure = measureDao.getLastMeasue(sensor);
		
		assertNotNull(measure);
	}

	public void init(EntityManager entityManager) throws IllegalAccessException {
		measureDao = new MeasureDao();
		sensorDao = new SensorDao();

		FieldUtils.writeDeclaredField(measureDao, "entityManager", entityManager, true);
		FieldUtils.writeDeclaredField(sensorDao, "entityManager", entityManager, true);
	}

	public void insertData(EntityManager entityManager) {
		Sensor sensor = Sensor.builder()
				.sensorType(null)
				.build();

		Measure measure = Measure.buider()
				.localDateTime(LocalDateTime.now())
				.quantity(10f)
				.sensor(sensor)
				.unitMeasure(null)
				.build();



		entityManager.persist(measure);
		entityManager.persist(sensor);

		measureId = measure.getId();
		sensorId = sensor.getId();

	}
}
