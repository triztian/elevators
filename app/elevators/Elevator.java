package elevators;

import java.util.concurrent.*;
import java.util.*;


import org.slf4j.Logger;

/**
 * 
 */
public final class Elevator extends Observable {

	public static final DriveSystem NO_DRIVE_SYSTEM = null;

	public static enum Direction {
		UP,
		DOWN,
		NONE;
	}

	public final String ID;

	private DriveSystem driveSystem;

	private Queue<Floor> floorQueue = new ConcurrentLinkedQueue<>();

	private Floor currentFloor = Floor.GROUND;

	private Floor targetFloor = null;

	private Logger logger = null;

	/**
	 * Create a new elevator with an AutomaticDriveSystem.
	 */
	public Elevator(final String id, final Logger logger) {
		this.ID = id;
		this.logger = logger;
		this.driveSystem = new AutomaticDriveSystem(this.new MoveTask());
	}

	/**
	 * Create a new elevator with a custom drive system.
	 */
	public Elevator(final String id, final DriveSystem driveSystem, final Logger logger) {
		this.ID = id;
		this.driveSystem = driveSystem;
		this.logger = logger;
	}

	/**
	 * 
	 */
	public DriveSystem useManualDriveSystem() {
		this.driveSystem = new ManualDriveSystem(this.new MoveTask());
		return this.driveSystem;
	}

	/**
	 * Moves the elevator to the next in the direction of the target floor.
	 * 
	 * @return true if the internal state of the elevator changed, false otherwise.
	 */
	private boolean move() {

		debug("[{}] currentFloor: {}, targetFloor: {}, direction: {}", floorQueue.size(), currentFloor, targetFloor, getDirection());

		if (targetFloor == null) {
			targetFloor = floorQueue.poll();
		}

		if (targetFloor == null) {
			return false;
		}

		if (getDirection() == Direction.NONE)
			return false;
			
		if (getDirection() == Direction.UP)
			currentFloor = currentFloor.next();

		if (getDirection() == Direction.DOWN)
			currentFloor = currentFloor.previous();

		if (targetFloor == currentFloor) {
			targetFloor = null;
		}

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
			-1, // FIXME: Provide proper implementation
			floorQueue.size()
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
		public final int queued;

		public Status(final String id, final Floor currentFloor, final Floor targetFloor, 
			final Direction direction, final boolean isMoving, final int distance, final int queued) {
			this.ID = id;
			this.currentFloor = currentFloor;
			this.targetFloor = targetFloor;
			this.direction =  direction;
			this.isMoving = isMoving;
			this.distance = distance;
			this.queued = queued;
		}

		@Override
		public String toString() {
			return String.format(
				"Status(ID: %s, queued: %d, currentFloor: %s, targetFloor: %s, direction: %s, isMoving: %b, distance: %d", 
				ID, queued, currentFloor, targetFloor, direction, isMoving, distance
			);
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