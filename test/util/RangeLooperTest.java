package util;

import org.junit.Test;
import play.inject.guice.GuiceApplicationBuilder;

import static org.junit.Assert.assertEquals;

public class RangeLooperTest {
	
	@Test
	public void testRangeLooper() {
		final RangeLooper looper = new RangeLooper(0, 2);
		
		assertEquals(new Integer(0), looper.next());
		assertEquals(new Integer(1), looper.next());
		assertEquals(new Integer(0), looper.next());
		assertEquals(new Integer(1), looper.next());

	}

}