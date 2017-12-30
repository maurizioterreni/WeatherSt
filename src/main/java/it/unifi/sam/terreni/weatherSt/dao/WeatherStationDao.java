package it.unifi.sam.terreni.weatherSt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unifi.sam.terreni.weatherSt.model.WeatherStation;
import it.unifi.sam.terreni.weatherSt.model.sensor.Sensor;
import it.unifi.sam.terreni.weatherSt.visitor.ResolveLazyLoadUsageVisitor;

public class WeatherStationDao {
	@PersistenceContext
	private EntityManager entityManager;


	public void save(WeatherStation weatherStation) {
		entityManager.persist(weatherStation);
	}

	public WeatherStation findById(Long weatherStationId){
		return entityManager.find(WeatherStation.class, weatherStationId);
	}

	public void update(WeatherStation weatherStation) {
		WeatherStation persisted = findById(weatherStation.getId());
		persisted.setDescription(weatherStation.getDescription());
		persisted.setLatitude(weatherStation.getLatitude());
		persisted.setLongitude(weatherStation.getLongitude());
		entityManager.flush();
	}

	public void delete(WeatherStation weatherStation) {
		entityManager.remove(weatherStation);
		entityManager.flush();
	}

	public List<WeatherStation> getAll(){
		return entityManager.createQuery("from WeatherStation w ", WeatherStation.class)
				.getResultList();
	}
	
	public WeatherStation fetchById(Long id) {
		entityManager
		.createQuery("select w "
				+ "from WeatherStation w "
				+ "where w.id = :id", WeatherStation.class)
		.setParameter("id", id)
		.getResultList();

		WeatherStation result = entityManager.find(WeatherStation.class, id);

		ResolveLazyLoadUsageVisitor visitor = new ResolveLazyLoadUsageVisitor();
		for(Sensor sensor : result.getSensors()) {
			sensor.accept(visitor);
		}

		return result;
	}
}
