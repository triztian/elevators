package controllers;

import elevators.Floor;

/**
 * A JSON representation of a floor, suitable for use in API responses.
 */
public final class APIFloor {
	public final String name;
	public final Integer number;

	public APIFloor(String name, Integer number) {
		this.name = name;
		this.number = number;
	}

	public APIFloor(Floor floor) {
		this.name = floor.toString();
		this.number = floor.ordinal();
	}
}