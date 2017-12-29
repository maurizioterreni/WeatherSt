package it.unifi.sam.terreni.weatherSt.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unifi.sam.terreni.weatherSt.model.sensor.Measure;

public class MeasureDao {
	@PersistenceContext
	private EntityManager entityManager;


	public void save(Measure measure) {
		entityManager.persist(measure);
	}

	public Measure findById(Long measureId){
		return entityManager.find(Measure.class, measureId);
	}

	public void update(Measure measure) {
		Measure persisted = findById(measure.getId());
		persisted.setTimestamp(measure.getTimestamp());
		persisted.setValue(measure.getValue());
		persisted.setUnit(measure.getUnit());
		entityManager.flush();
	}

	public void delete(Measure measure) {
		entityManager.remove(measure);
		entityManager.flush();
	}

	public List<Measure> getMeasureBetweenDate(Long sensorId, Date fromDate, Date toDate){
		return entityManager
				.createQuery("from Measure m where "
						+ "m.sensor_id = :sensor AND m.timestamp BETWEEN :fromDate AND :toDate ", Measure.class)
				.setParameter("sensor", sensorId)
				.setParameter("fromDate", fromDate.getTime())
				.setParameter("toDate", toDate.getTime())
				.getResultList();
	}


	public Measure getMax(Long sensorId, Date fromDate, Date toDate){
		return entityManager
				.createQuery("from Measure m where "
						+ "m.sensor_id = :sensor AND m.timestamp BETWEEN :fromDate AND :toDate order by m.value desc", Measure.class)
				.setParameter("sensor", sensorId)
				.setParameter("fromDate", fromDate.getTime())
				.setParameter("toDate", toDate.getTime())
				.setMaxResults(1)
				.getSingleResult();
	}

	public Measure getMin(Long sensorId, Date fromDate, Date toDate){
		return entityManager
				.createQuery("from Measure m where "
						+ "m.sensor_id = :sensor AND m.timestamp BETWEEN :fromDate AND :toDate order by m.value asc", Measure.class)
				.setParameter("sensor", sensorId)
				.setParameter("fromDate", fromDate.getTime())
				.setParameter("toDate", toDate.getTime())
				.setMaxResults(1)
				.getSingleResult();
	}

	public Measure getLastMeasue(Long sensorId) {
		return entityManager
				.createQuery("from Measure m where "
						+ "m.sensor_id = :sensor order by m.timestamp desc", Measure.class)
				.setParameter("sensor", sensorId)
				.setMaxResults(1)
				.getSingleResult();
	}
}
