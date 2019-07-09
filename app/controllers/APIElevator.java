package controllers;

import java.util.*;

import elevators.Floor;
import elevators.Elevator;

/**
* The elevator response.
*/
public final class APIElevator {
	
	public final String id;
	public final APIFloor currentFloor;
	public final APIFloor destinationFloor;
	public final String direction;
	
	// TODO: Add other properties such as
	// capacity, etc..
	
	public APIElevator(String id, APIFloor currentFloor) {
		this.id = id;
		this.currentFloor = currentFloor;
		this.destinationFloor = null;
		this.direction = null;
	}
	
	public APIElevator(String id, APIFloor currentFloor, APIFloor destinationFloor, String direction) {
		this.id = id;
		this.currentFloor = currentFloor;
		this.destinationFloor = destinationFloor;
		this.direction = direction;
	}

	public APIElevator(Elevator.Status status) {
		this.id = status.ID;
		this.currentFloor = new APIFloor(status.currentFloor);

		if (status.isMoving) {
			this.destinationFloor = new APIFloor(status.targetFloor);
		} else {
			this.destinationFloor = null;
		}

		this.direction = status.direction.toString();
	}
	
}