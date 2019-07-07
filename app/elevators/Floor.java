package elevators;

/**
 * Represents the number of available floors for this SPECIFIC building.
 * Should the floors be more than say 20 then this class should be converted into a 
 * list of special floors and their numbers and the rest should be handled by `int`.
 */
public enum Floor {
	GROUND,
	FIRST,
	SECOND,
	THIRD,
	FOURTH,
	FIFTH,
	SIXTH,
	SEVENTH,
	EIGHTH,
	NINETH,
	TENTH,
	ELEVENTH,
	TWELVETH,
	THIRTEENTH,
	FOURTEENTH,
	FIFTEENTH,
	SIXTEENTH;

	/**
	 * Obtain the Floor from a given number.
	 * 
	 * @throws IllegalArgumentException if the number is less than 0 or greater than 16.
	 */
	public static Floor fromNumber(int number) {
		if (number < GROUND.ordinal()) 
			throw new IllegalArgumentException("number must be greater than 0");

		if (number > SIXTEENTH.ordinal()) 
			throw new IllegalArgumentException("number must be less or equal to 16");


		return Floor.values()[number];
	}

	/**
	 * Return the next floor. If the value represents the l
	 * last floor; the last floor is returned.
	 */
	public Floor next() {
		if (this == SIXTEENTH) 
			return SIXTEENTH;

		return Floor.values()[ordinal()+1];
	}

	/**
	 * Return the previous floor. If the value represents
	 * ground floor; the ground floor is returned.
	 */
	public Floor previous() {
		if (this == GROUND) return GROUND;

		return Floor.values()[ordinal()-1];
	}

	/**
	 * Determines whether this floor is below another one.
	 */
	public boolean isBelow(Floor other) {
		if (other == null) return false;
		return ordinal() < other.ordinal();
	}

	/**
	 * Determines whether this floor is above another one.
	 */
	public boolean isAbove(Floor other) {
		if (other == null) return false;
		return ordinal() > other.ordinal();
	}
}