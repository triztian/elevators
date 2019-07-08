package util;

import java.util.Date;
import java.util.List;
import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImmutableDataTest {
	
	@Test
	public void testImmutability() {
		
		final int[] srcNums = new int[]{1, 2, 3};
		final Date srcDate = new Date();
		final int srcHours = srcDate.getHours();
		final List<String> srcStrings = Arrays.asList("a", "b", "c");
		
		final ImmutableData data = new ImmutableData(srcNums, srcStrings, srcDate);
		
		final int[] resNums = data.getNumbers();
		
		// modify a number entry
		srcNums[2] = 99;
		assertEquals(resNums[2], 3);
		
		// modify a string entry
		final List<String> resStrings = data.getStrings();
		resStrings.set(0, "zzz");
		assertEquals(srcStrings.get(0), "a");
		assertEquals(data.getStrings().get(0), "a");
		
		// modify the date
		final Date resDate = data.getDate();
		resDate.setHours(10);
		assertEquals(srcDate.getHours(), srcHours);
		
	}
}