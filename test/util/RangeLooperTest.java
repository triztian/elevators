package util;

import org.junit.Test;
import play.inject.guice.GuiceApplicationBuilder;

import static org.junit.Assert.assertEquals;

public class RangeLooperTest {
	
	@Test
	public void testRangeLooper() {
		final RangeLooper looper = new RangeLooper(0, 2);
		
		assertEquals(looper.next(), new Integer(0));
		assertEquals(looper.next(), new Integer(1));
		assertEquals(looper.next(), new Integer(0));
		assertEquals(looper.next(), new Integer(1));
	}
}