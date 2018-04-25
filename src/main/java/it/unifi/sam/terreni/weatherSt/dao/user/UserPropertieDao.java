package it.unifi.sam.terreni.weatherSt.dao.user;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unifi.sam.terreni.weatherSt.model.user.UserPropertie;

public class UserPropertieDao {
	@PersistenceContext
	private EntityManager entityManager;


	public void save(UserPropertie userPropertie) {
		entityManager.persist(userPropertie);
	}

	public UserPropertie findById(Long userPropertieId){
		return entityManager.find(UserPropertie.class, userPropertieId);
	}
	
}
