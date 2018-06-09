package it.unifi.sam.terreni.weatherSt.dao.measure;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.unifi.sam.terreni.weatherSt.dto.measure.MeasureChartDto;
import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.utils.GroupByClass;
import it.unifi.sam.terreni.weatherSt.utils.StringUtils;

public class MeasureDao {
	@PersistenceContext(unitName="production")
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
							+ "m.sensor = :sensor AND (m.localDateTime >= :fromDate AND m.localDateTime < :toDate) order by m.localDateTime asc", Measure.class)
					.setParameter("sensor", sensor)
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate)
					.getResultList();
		}catch (NoResultException nre){
			return new ArrayList<>();
		}
	}

	//select avg(quantity) from measure where sensor_id = 1
	//group by month(localDateTime) order by localDateTime asc
	public List<MeasureChartDto> getLotOfMeasureDtoBetweenDate(Sensor sensor, LocalDateTime fromDate, LocalDateTime toDate, String groupby){
		try {
			TypedQuery<Object[]> query = entityManager.createQuery(
					"SELECT m.localDateTime, avg(quantity), m.unitMeasure  FROM Measure m where "
					+ " m.sensor = :sensor AND (m.localDateTime >= :fromDate AND m.localDateTime < :toDate) "
					+ " group by " + groupby
					+ " order by m.localDateTime asc", Object[].class)
					.setParameter("sensor", sensor)
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate);
			String pattern = "";
			if(groupby.equals(GroupByClass.HOUR_GROUPBY))
				pattern = "HH:mm";
			else if(groupby.equals(GroupByClass.WEEK_GROUPBY))
				pattern = "yy, 'week' w";
			else if(groupby.equals(GroupByClass.DAY_GROUPBY))
				pattern = "dd/MM";
			else if(groupby.equals(GroupByClass.MONTH_GROUPBY))
				pattern = "MM-yy";
			else if(groupby.equals(GroupByClass.YEAR_GROUPBY))
				pattern = "yy";
			else
				pattern = "yy-MM-dd";
			
			List<MeasureChartDto> results = new ArrayList<>();
			for (Object[] result : query.getResultList()) {
				results.add(MeasureChartDto.builder()
						.withDateTime(StringUtils.locatDateTimeToString((LocalDateTime) result[0], pattern))
						.withQuantity(StringUtils.doubleToString((Double) result[1]))
						.withUnitId(((UnitMeasureKnowledge) result[2]).getId())
						.build());
			}
			
			return results;
		}catch (NoResultException nre){
			return new ArrayList<>();
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
