package elevators;

import java.util.concurrent.*;
import java.util.*;

/**
 * 
 */
public final class Elevator {

	public static enum Direction {
		UP,
		DOWN,
		NONE;
	}

	private DriveSystem driveSystem;

	private Queue<Floor> floorQueue = new PriorityBlockingQueue<>();

	private Floor currentFloor = Floor.GROUND;

	private Floor targetFloor = null;

	/**
	 * 
	 */
	public Elevator() {
		this.driveSystem = new DriveSystem(this.new MoveTask());
	}


	/**
	 * Moves the elevator to the next in the direction of the target floor.
	 */
	private void move() {

		if (targetFloor == null) {
			targetFloor = floorQueue.poll();
		}

		if (targetFloor == null) {
			return;
		}

		if (targetFloor == currentFloor) {
			return;
		}
			
		if (getDirection() == Direction.UP)
			currentFloor = currentFloor.next();

		if (getDirection() == Direction.DOWN)
			currentFloor = currentFloor.previous();

	}

	/**
	 * Calls the elevator to a specific floor without a destination.
	 */
	public synchronized void call(final Floor from) {
		floorQueue.offer(from);
	}

	/**
	 * Calls the elevator to a specific floor with a given destination floor.
	 */
	public synchronized void call(final Floor from, final Floor destination) {
		floorQueue.offer(from);
		floorQueue.offer(destination);
	}

	/**
	 * Determine the elevator's traveling direction.
	 */
	public synchronized Direction getDirection() {

		if (currentFloor.isBelow(targetFloor)) return Direction.UP;

		if (currentFloor.isAbove(targetFloor)) return Direction.DOWN;

		return Direction.NONE;
	}

	/**
	 * Obtain the current flor.
	 */
	public synchronized Floor getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * OObtain the destination floor; if any.
	 */
	public synchronized Floor getDestinationFloor() {
		return targetFloor;
	}

	/**
	 * Determines if the elevator is moving or not.
	 */
	public synchronized boolean isMoving() {
		return getDirection() != Direction.NONE;
	}

	private class MoveTask implements Runnable {
		@Override
		public void run() {
			move(); // method on Elevator
		}
	}

	public synchronized void start() {
		driveSystem.start();
	}

}