package elevators;

import java.util.concurrent.*;
import java.util.*;


import org.slf4j.Logger;

/**
 * 
 */
public final class Elevator extends Observable {

	public static enum Direction {
		UP,
		DOWN,
		NONE;
	}

	public final String ID;

	private DriveSystem driveSystem;

	private Queue<Floor> floorQueue = new PriorityBlockingQueue<>();

	private Floor currentFloor = Floor.GROUND;

	private Floor targetFloor = null;

	private Logger logger = null;

	/**
	 * Create a new elevator.
	 */
	public Elevator(final String id, Logger logger) {
		this.ID = id;
		this.driveSystem = new DriveSystem(this.new MoveTask());
		this.logger = logger;
	}

	/**
	 * Moves the elevator to the next in the direction of the target floor.
	 * 
	 * @return true if the internal state of the elevator changed, false otherwise.
	 */
	private boolean move() {

		debug("currentFloor: {}, targetFloor: {}, direction: {}", currentFloor, targetFloor, getDirection());

		if (targetFloor == null) {
			targetFloor = floorQueue.poll();
		}

		if (targetFloor == null) {
			return false;
		}

		if (targetFloor == currentFloor) {
			currentFloor = targetFloor;
			targetFloor = null;
			return false;
		}
			
		if (getDirection() == Direction.UP)
			currentFloor = currentFloor.next();

		if (getDirection() == Direction.DOWN)
			currentFloor = currentFloor.previous();

		return true;
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

	/**
	 * Provides the a Readonly view of the elevator's status at a given point. Useful to 
	 * avoid passing the actual elevator instance around.
	 */
	public synchronized Status getStatus() {
		return new Status(
			ID,
			currentFloor, 
			targetFloor, 
			getDirection(),
			isMoving(), 
			-1 // FIXME: Provide proper implementation
		);
	}

	public synchronized void start() {
		driveSystem.start();
	}

	/**
	 * This class is passed to the DriveSystem on every "tick"
	 * it will call the `run` method which will trigger the update of the elevator's internal 
	 * state.
	 */
	private class MoveTask implements Runnable {
		@Override
		public void run() {
			if (move()) {
				setChanged();
				notifyObservers(getStatus());
			}
		}
	}

	/**
	 * A helper class that provides a readonly view to the elevator's status
	 */
	public static class Status {
		public final String ID; 
		public final Floor currentFloor;
		public final Floor targetFloor;
		public final Direction direction;
		public final boolean isMoving;
		public final int distance;

		public Status(final String id, final Floor currentFloor, final Floor targetFloor, final Direction direction, final boolean isMoving, final int distance) {
			this.ID = id;
			this.currentFloor = currentFloor;
			this.targetFloor = targetFloor;
			this.direction =  direction;
			this.isMoving = isMoving;
			this.distance = distance;
		}
	}

	// --- logging helpers --

	private void info(final String format, Object... args) {

		if (logger == null) return;

		logger.info(ID + " " + format, args);

	}

	private void debug(final String format, Object... args) {

		if (logger == null) return;

		logger.debug(ID + " " + format, args);

	}

}