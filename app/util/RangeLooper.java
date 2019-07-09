package util;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * An utility class that loops over a range of integers.
 */
public final class RangeLooper extends Looper<Integer> {

	/**
	 * Creates a range looper that starts from a given lower bound up 
	 * to (non-inclusive) an upper bound.
	 */
	public RangeLooper(final int from, final int to) {
		super(RangeLooper.rangeList(from, to));
	}

	/**
	 * Create an `int[]` range from a lower bound to a non-inclusive upper bound.
	 */
	public static int[] range(final int from, final int to) {

		final int size =  Math.abs(to - from);
		final int[] seq = new int[size];

		for (int i = 0; i < size; i++)
			seq[i] = from + i;

		return seq;
	}

	/**
	 * Create an `List<Integer>` range from a lower bound to a non-inclusive upper bound.
	 */
	public static List<Integer> rangeList(final Integer from, final Integer to) {
		final int[] n = range(from, to);
		final List<Integer> nl = new ArrayList<>(n.length);
		for (int i = 0; i < n.length; i++)
			nl.add(n[i]);

		return nl;
	}

}
