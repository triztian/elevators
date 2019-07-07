package elevators;

/**
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
		return ordinal() < other.ordinal();
	}

	/**
	 * Determines whether this floor is above another one.
	 */
	public boolean isAbove(Floor other) {
		return ordinal() > other.ordinal();
	}
}