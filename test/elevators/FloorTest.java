package elevators;


import org.slf4j.LoggerFactory;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class FloorTest {

	@Test
	public void testNext() {
		assertEquals(Floor.SIXTEENTH.next(), Floor.SIXTEENTH);
		assertEquals(Floor.GROUND.next(), Floor.FIRST);
	}

	@Test
	public void testPrevious() {
		assertEquals(Floor.GROUND.previous(), Floor.GROUND);
		assertEquals(Floor.FIRST.previous(), Floor.GROUND);
	}

	@Test
	public void testIsAbove() {
		assertTrue(Floor.SIXTEENTH.isAbove(Floor.TENTH));
		assertFalse(Floor.SIXTEENTH.isAbove(Floor.SIXTEENTH));
		assertFalse(Floor.THIRD.isAbove(Floor.SIXTEENTH));
		assertFalse(Floor.THIRD.isAbove(null));
	}

	@Test
	public void testIsBelow() {
		assertTrue(Floor.GROUND.isBelow(Floor.TENTH));
		assertFalse(Floor.SIXTEENTH.isBelow(Floor.SIXTEENTH));
		assertFalse(Floor.THIRD.isBelow(Floor.SECOND));
		assertFalse(Floor.THIRD.isBelow(null));
	}

	@Test
	public void testDistanceTo() {
		assertEquals(0, Floor.GROUND.distanceTo(Floor.GROUND));
		assertEquals(1, Floor.GROUND.distanceTo(Floor.FIRST));
		assertEquals(-16, Floor.SIXTEENTH.distanceTo(Floor.GROUND));
	}

}