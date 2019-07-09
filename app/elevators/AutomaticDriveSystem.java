package elevators;

import java.util.*;
import java.util.concurrent.*;

/**
 * The DriveSystem "powers" each elevator's move
 * action ("task"). 
 */
public final class AutomaticDriveSystem implements DriveSystem {

	/**
	 * The task to run.
	 */
	private Runnable task;

	/**
	 * The interval value.
	 */
	private final long period;

	/**
	 * The time units corresponding to the interval.
	 */
	private final TimeUnit timeUnit;

	/**
	 * The schedule of tasks the the system is running.
	 */
	private ScheduledFuture<?> schedule;

	/**
	 * The service that executes the tasks in background threads.
	 */
	private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

	/**
	 * Create an automatic drive system with a 1 Second interval.
	 */
	public AutomaticDriveSystem(Runnable task) {
		this(task, 1, TimeUnit.SECONDS);
	}

	/**
	 * Create an automatic drive system with a custom interval.
	 */
	public AutomaticDriveSystem(Runnable task, long period, TimeUnit timeUnit) {
		this.task = task;
		this.period = period;
		this.timeUnit = timeUnit;
	}

	/**
	 * Start the task schedule with 0 delay.
	 */
	public void start() {
		schedule = executor.scheduleAtFixedRate(task, 0, period, timeUnit);
	}

	/**
	 * Cancels the task schedule.
	 */
	public void stop() {
		schedule.cancel(true);
	}

	/**
	 * Schedule the configured task once; immediately.
	 */
	public void advance() {
		executor.schedule(task, 0, timeUnit);
	}

}