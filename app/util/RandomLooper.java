package util;

import java.util.Iterator;
import java.util.Random;
import java.util.List;
import java.util.Arrays;

/**
 * A looping iterator tha cycle through an items list randomly.
 */
public final class RandomLooper<T> implements Iterator<T> {

	/**
	 * The randomizer.
	 */
	private final Random rnd = new Random();

	/**
	 * The source of elements.
	 */
	private final List<T> items;

	/**
	 * Create a looper that will cycle randomly between the elements of the given
	 * items.
	 */
	public RandomLooper(T... items) {
		this.items = Arrays.asList(items);
	}

	/**
	 * Create a looper that will cycle randomly between the elements of the given
	 * items.
	 */
	public RandomLooper(List<T> items) {
		this.items = items;
	}

	/**
	 * Returns the next random element. If the collection contains non-immutable
	 * objects then they _will_ be modifyable if mutated.
	 */
	@Override
	public T next() {
		return items.get(rnd.nextInt(items.size()));
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