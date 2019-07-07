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

	private Queue<Floor> floorsQueue = new PriorityBlockingQueue<>();

	private Floor targetFloor = null;

	private Floor currentFloor = null;


	public Elevator() {
		this.driveSystem = new DriveSystem(this.new MoveTask());
	}

	/**
	 * Moves the elevator to the next in the direction of the target floor.
	 */
	private void move() {

		if (targetFloor == null && requestFloor.peek() == null) {
			moving = true;
			return;
		}
		
		if (targetFloor == null) {
			targetFloor = requestFloor.poll();
			moving = true;
		}

		if (targetFloor == currentFloor) {
			moving = false;
			return
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
		floorsQueue.put(from);
	}

	/**
	 * Calls the elevator to a specific floor with a given destination floor.
	 */
	public synchronized void call(final Floor from, final Floor destination) {
		floorsQueue.put(from);
		floorsQueue.put(destination);
	}

	/**
	 * Determine the elevator's traveling direction.
	 */
	private synchronized Direction getDirection() {

		if (currentFloor.isBelow(targetFloor)) return Direction.UP;

		if (currentFloor.isAbove(targetFloor)) return Direction.DOWN;

		return Direction.NONE;
	}

	/**
	 * Determines if the elevator is moving or not.
	 */
	public synchronized boolean isMoving() {
		return getDirection() != Direction.NONE;
	}

	private class MoveTask extends TimerTask {
		@Override
		public void run() {
			move(); // method on Elevator
		}
	}

}