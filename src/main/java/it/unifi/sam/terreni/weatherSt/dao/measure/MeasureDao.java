package it.unifi.sam.terreni.weatherSt.dao.measure;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;

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
		persisted.setLocalDateTime(measure.getLocalDateTime());
		persisted.setQuantity(measure.getQuantity());
		entityManager.flush();
	}

	public void delete(Measure measure) {
		entityManager.remove(measure);
		entityManager.flush();
	}

	public List<Measure> getMeasureBetweenDate(Sensor sensor, LocalDateTime fromDate, LocalDateTime toDate){
		try {
			return entityManager
					.createQuery("from Measure m where "
							+ "m.sensor = :sensor AND (m.localDateTime >= :fromDate AND m.localDateTime < :toDate) order by m.localDateTime desc", Measure.class)
					.setParameter("sensor", sensor)
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate)
					.getResultList();
		}catch (NoResultException nre){
			return null;
		}
	}


	public Measure getMax(Sensor sensor, LocalDateTime fromDate, LocalDateTime toDate){
		try{
			return entityManager
					.createQuery("from Measure m where "
							+ "m.sensor = :sensor AND (m.localDateTime >= :fromDate AND m.localDateTime < :toDate) order by m.quantity desc", Measure.class)
					.setParameter("sensor", sensor)
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate)
					.setMaxResults(1)
					.getSingleResult();
		}catch (NoResultException nre){
			return null;
		}
	}

	public Measure getMin(Sensor sensor, LocalDateTime fromDate, LocalDateTime toDate){
		try{
			return entityManager
					.createQuery("from Measure m where "
							+ "m.sensor = :sensor AND (m.localDateTime >= :fromDate AND m.localDateTime < :toDate) order by m.quantity asc", Measure.class)
					.setParameter("sensor", sensor)
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate)
					.setMaxResults(1)
					.getSingleResult();
		}catch (NoResultException nre){
			return null;
		}

	}

	public Measure getLastMeasue(Sensor sensor) {
		try {
			return entityManager
					.createQuery("from Measure m where "
							+ "m.sensor = :sensor order by m.localDateTime desc", Measure.class)
					.setParameter("sensor", sensor)
					.setMaxResults(1)
					.getSingleResult();
		}catch (NoResultException nre){
			return null;
		}
	}
}
