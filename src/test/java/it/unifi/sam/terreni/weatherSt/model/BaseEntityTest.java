package it.unifi.sam.terreni.weatherSt.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class BaseEntityTest {

	private FakeBaseEntity e1, e2, e3;
	
	@Before
	public void setUp() {
		String uuid1 = UUID.randomUUID().toString();
		String uuid2 = UUID.randomUUID().toString();
		e1 = new FakeBaseEntity(uuid1);
		e2 = new FakeBaseEntity(uuid2);
		e3 = new FakeBaseEntity(uuid1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullUUID() {
		new FakeBaseEntity(null);
	}
	
	@Test
	public void testEquals() {
		assertEquals(e1, e1);
		assertEquals(e1, e3);
		assertEquals(e3, e3);
	}
	
	@Test
	public void testNotEquals() {
		assertNotSame(e1, e2);
		assertNotSame(e3, e2);
		
		assertNotSame(e1, null);
		assertNotSame(e1, "Ciao");
	}
	
	class FakeBaseEntity extends BaseEntity {
		private static final long serialVersionUID = 1L;

		public FakeBaseEntity(String uuid) {
			super(uuid);
		}
	}
	
}