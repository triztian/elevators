package controllers;

import java.util.*;

/**
 * The elevator response
 */
public final class APIElevatorStatus {

	public final List<Elevator> elevators = new ArrayList<>();

	public static final class Elevator {

		public final String id;
		public final String currentFloor;
		public final String nextFloor;
		public final String direction;

		// TODO: Add other properties such as
		// capacity, etc..

		public Elevator(String id, String currentFloor) {
			this.id = id;
			this.currentFloor = currentFloor;
			this.nextFloor = null;
			this.direction = null;
		}

		public Elevator(String id, String currentFloor, String nextFloor, String direction) {
			this.id = id;
			this.currentFloor = currentFloor;
			this.nextFloor = nextFloor;
			this.direction = direction;
		}
	}
}