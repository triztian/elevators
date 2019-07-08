package util;

import java.util.List;
import java.util.Date;
import java.util.Arrays;
import java.util.Collections;

/**
 * 1) Implement an immutable class in Java. The class fully encapsulates an `int[]`, a `Collection` of `String`, 
 * and a `java.util.Date`. It should allow clients to specify all three when the class is constructed, 
 * and it includes accessors for each. The value returned by each accessor should not change after 
 * the object is instantiated.
 */
public final class ImmutableData {

	private final int numbers[];

	private final List<String> strings;

	private final Date date;

	/**
	 * 
	 */
	public ImmutableData(final int[] numbers, final List<String> strings, final Date date) {

		this.numbers = new int[numbers.length];
		System.arraycopy(numbers, 0, this.numbers, 0, numbers.length);

		this.strings = Arrays.asList(strings.toArray(new String[strings.size()]));
		this.date = date;

	}

	public Date getDate() {
		return (Date)date.clone();
	}

	public int[] getNumbers() {
		final int[] nums = new int[numbers.length];
		System.arraycopy(numbers, 0, nums, 0, numbers.length);
		return nums;
	}

	public List<String> getStrings() {
		return Arrays.asList(strings.toArray(new String[strings.size()]));
	}

}