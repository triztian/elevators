package elevators;

import java.util.*;

/**
 * A manual drive system provides a way to have fine control over the
 * engine that drives elevators.
 */
public class ManualDriveSystem implements DriveSystem {

	/**
	 * The task to be ran when advancing the DriveSystem.
	 */
	private final Runnable task;

	/**
	 * Create a new ManualDriveSystem with the given task.
	 */
	public ManualDriveSystem(Runnable task) {
		this.task = task;
	}

	/**
	 * Start has no action.
	 */
	public void start() {
		// do nothing
	}

	/**
	 * Stop has no action.
	 */
	public void stop() {
		// do nothing
	}

	/**
	 * Execute the provided task once.
	 */
	public void advance() {
		task.run();
	}

}