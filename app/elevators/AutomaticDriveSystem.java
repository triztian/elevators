package elevators;

import java.util.*;
import java.util.concurrent.*;

/**
 * The DriveSystem "powers" each elevator's move
 * action ("task"). 
 */
public final class AutomaticDriveSystem implements DriveSystem {

	private Runnable currentTask;

	private ScheduledFuture<?> future;

	private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

	private final long period;

	private final TimeUnit timeUnit;

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

	public void start() {
		future = executor.scheduleAtFixedRate(currentTask, 0, 1, TimeUnit.SECONDS);
	}

	public void stop() {
		future.cancel();
	}

	public void advance() {
		this.currentTask.run();
	}

}