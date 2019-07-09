package util;

import java.util.List;
import java.util.Arrays;
import java.util.Iterator;

/**
 * An utility class that loops over a range of integers.
 */
public class Looper<T> implements Iterator<T> {

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
	 * The sequence that contains the elements that will be looped.
	 */
	private final List<T> sequence;

	/**
	 * Create a new `RangeLooper` that will loop from the `from` parameter
	 * to `to -1` (non-inclusive) and will loop to `from` if the max value
	 * is reached.
	 */
	public Looper(final List<T> sequence) {
		this.start = 0;
		this.from = start;
		this.to = sequence.size();
		this.current = 0;
		this.sequence = sequence;
	}

	/**
	 * 
	 */
	public Looper(final T... sequence) {
		this.start = 0;
		this.from = start;
		this.to = sequence.length;
		this.current = 0;
		this.sequence = Arrays.asList(sequence);
	}

	/**
	 * 
	 */
	@Override
	public T next() {

		if (!providedFirst) {
			providedFirst = true;
			return sequence.get(start);
		}

		if (current + 1 >= to) {
			current = from;
		} else {
			current++;
		}

		return sequence.get(current);

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
 