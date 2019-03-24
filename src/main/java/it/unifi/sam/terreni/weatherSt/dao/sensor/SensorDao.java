package it.unifi.sam.terreni.weatherSt.dao.sensor;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.measure.Measure;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.visitor.ResolveLazyLoadUsageVisitor;

public class SensorDao {
	@PersistenceContext(unitName="production")
	private EntityManager entityManager;
	

	public void save(Sensor sensor) {
		entityManager.persist(sensor);
	}
	
	public Sensor findById(Long sensorId){
		return entityManager.find(Sensor.class, sensorId);
	}
	
	public void update(Sensor sensor) {
		Sensor persisted = findById(sensor.getId());
		persisted.setSensorType(sensor.getSensorType());
		persisted.setTitle(sensor.getTitle());
		entityManager.flush();
	}
	
	public void delete(Sensor sensor) {
		entityManager.remove(sensor);
		entityManager.flush();
	}
	
	public Sensor fetchById(Long id) {
		entityManager
		.createQuery("select s "
				+ "from Sensor s "
				+ "where s.id = :id", Sensor.class)
		.setParameter("id", id)
		.getResultList();

		Sensor result = entityManager.find(Sensor.class, id);

		ResolveLazyLoadUsageVisitor visitor = new ResolveLazyLoadUsageVisitor();
		for(Measure measure : result.getMeasures()) {
			measure.accept(visitor);
		}

		return result;
	}
	public List<Sensor> getByWeatherStationId(WeatherStation wt) {
		return entityManager
		.createQuery("select s "
				+ "from Sensor s "
				+ "where s in (:sensors) "
				+ "order by s.id", Sensor.class)
		.setParameter("sensors", wt.getSensors())
		.getResultList();
	}
}
