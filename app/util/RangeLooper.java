package util;

import java.util.Iterator;

/**
 * An utility class that loops over a range of integers.
 */
public final class RangeLooper implements Iterator<Integer> {

	/**
	 * The initial value that will be provided by the first call to `next`.
	 */
	private final int start;

	/**
	 * The lower value for this loop.
	 */
	private final int from;

	/**
	 * The max (non-inclusive) value for this loop.
	 */
	private final int to;

	/**
	 * The current value.
	 */
	private int current = 0;

	/**
	 * Marks whether the `start` value has been provided by the `next` method
	 */
	private boolean providedFirst = false;

	/**
	 * Create a new `RangeLooper` that will loop from the `from` parameter
	 * to `to -1` (non-inclusive) and will loop to `from` if the max value
	 * is reached.
	 */
	public RangeLooper(int from, int to) {
		this.start = 0;
		this.from = from;
		this.to = to;
		this.current = 0;
	}

	@Override
	public Integer next() {

		if (!providedFirst) {
			providedFirst = true;
			return start;
		}

		if (current + 1 >= to) {
			current = from;
		} else {
			current++;
		}

		return current;

	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}