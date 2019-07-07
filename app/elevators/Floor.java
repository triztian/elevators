package elevator;

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

		return Floor.valueOf(this.ordinal() + 1);
	}

	/**
	 * Return the previous floor. If the value represents
	 * ground floor; the ground floor is returned.
	 */
	public Floor previous() {
		if (this == GROUND) return GROUND;

		return Floor.valueOf(this.ordinal() -1);
	}

	/**
	 * 
	 */
	public boolean isBelow(Floor other) {
		return this.ordinal() < other.ordinal()
	}

	/**
	 * 
	 */
	public boolean isAbove(Floor other) {
		return this.ordinal() > other.ordinal()
	}
}