package it.unifi.sam.terreni.weatherSt.persistence;

public abstract class JpaUnitTest extends JpaTest {

	private static final String PERSISTENCE_NAME = "unit";

	@Override
	protected String getPersistenceUnitName() {
		return PERSISTENCE_NAME;
	}

}
