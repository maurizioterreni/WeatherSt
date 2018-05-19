package it.unifi.sam.terreni.weatherSt.dao.user;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import it.unifi.sam.terreni.weatherSt.model.measure.UnitMeasureKnowledge;
import it.unifi.sam.terreni.weatherSt.model.user.User;
import it.unifi.sam.terreni.weatherSt.visitor.ResolveLazyLoadUsageVisitor;

public class UserDao {
	@PersistenceContext
	private EntityManager entityManager;


	public void save(User user) {
		entityManager.persist(user);
	}

	public User findById(Long userId){
		return entityManager.find(User.class, userId);
	}
	public User fetchById(Long userId){
		entityManager
		.createQuery("select u "
				+ "from User u "
				+ "where u.id = :id", User.class)
		.setParameter("id", userId)
		.getResultList();

		User result = entityManager.find(User.class, userId);

		ResolveLazyLoadUsageVisitor visitor = new ResolveLazyLoadUsageVisitor();
		result.getPropertie().accept(visitor);

		return result;
	}


	public void delete(User user) {
		entityManager.remove(user);
		entityManager.flush();
	}
	public void update(User user) {
		User persisted = findById(user.getId());
		persisted.setPropertie(user.getPropertie());
		persisted.setEmail(user.getEmail());
		persisted.setPassword(user.getPassword());
		persisted.setUsername(user.getUsername());
		entityManager.flush();
	}

	public void updatePassword(Long userId, String password) {
		User user = findById(userId);
		user.setPassword(password);
		entityManager.flush();

	} 

	public User getUserByPassword(String username, String password) {
		try {
			return entityManager
					.createQuery("select u "
							+ "from User u "
							+ "where u.username = :username AND u.password = :password", User.class)
					.setParameter("username", username)
					.setParameter("password", password)
					.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
	
	
	public User getUserByUsername(String username) {
		try {
			User result = entityManager
					.createQuery("select u "
							+ "from User u "
							+ "where u.username = :username", User.class)
					.setParameter("username", username)
					.getSingleResult();
			

			ResolveLazyLoadUsageVisitor visitor = new ResolveLazyLoadUsageVisitor();
			result.getPropertie().accept(visitor);
			
			for (UnitMeasureKnowledge unitMeasureKnowledge : result.getPropertie().getUnitMeasure()) {
				unitMeasureKnowledge.accept(visitor);
			}
			
			return result;
			
		}catch(NoResultException e) {
			return null;
		}
	}

}
