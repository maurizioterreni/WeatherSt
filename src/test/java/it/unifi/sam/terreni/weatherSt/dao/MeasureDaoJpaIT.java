package it.unifi.sam.terreni.weatherSt.dao;

import org.junit.Test;

import it.unifi.sam.terreni.weatherSt.exception.TestInitializationException;
import it.unifi.sam.terreni.weatherSt.persistence.JpaIT;

public class MeasureDaoJpaIT extends JpaIT {

	private MeasureDaoTestDelegate measureDaoTest;
	
	@Override
	protected void initTest() throws TestInitializationException {
		measureDaoTest = new MeasureDaoTestDelegate();
		try {
			measureDaoTest.init(entityManager);
		} catch (Exception e) {
			throw new TestInitializationException(e);
		}
		
		measureDaoTest.insertData(entityManager);
	}
	
	@Test
	public void testSave() {
		measureDaoTest.testSave();
	}
	@Test
	public void testFindById() {
		measureDaoTest.testFindById();
	}
	@Test
	public void testDelete() {
		measureDaoTest.testDelete();
	}
	
	@Test
	public void testLastMeasue() {
		measureDaoTest.testLastMeasue();
	}

	@Test
	public void testMeasureBetweenDate() {
		measureDaoTest.testMeasureBetweenDate();
	}
}
