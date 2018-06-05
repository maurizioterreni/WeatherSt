package it.unifi.sam.terreni.weatherSt.persistence;

public abstract class JpaIT extends JpaTest {

	private static final String PERSISTENCE_NAME = "integration";

	@Override
	protected String getPersistenceUnitName() {
		return PERSISTENCE_NAME;
	}

}