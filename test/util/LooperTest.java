package util;

import org.junit.Test;
import play.inject.guice.GuiceApplicationBuilder;

import static org.junit.Assert.assertEquals;

public class LooperTest {
	
	@Test
	public void testLooper() {

		final String[] seq = new String[]{"a", "b", "c"};
		final Looper looper = new Looper(seq);
		
		assertEquals("a", looper.next());
		assertEquals("b", looper.next());
		assertEquals("c", looper.next());
		assertEquals("a", looper.next());
		assertEquals("b", looper.next());
		assertEquals("c", looper.next());
	}

}