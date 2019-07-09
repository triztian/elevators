package util;

import org.junit.Test;
import play.inject.guice.GuiceApplicationBuilder;

import static org.junit.Assert.assertEquals;

public class RandomLooperTest {
	
	@Test
	public void testRandomLooper() {

		final RandomLooper looper = new RandomLooper(0, 2);
		
		// We have to "eye" check these
		// System.out.println(looper.next());
		// System.out.println(looper.next());
		// System.out.println(looper.next());
		// System.out.println(looper.next());

	}
}