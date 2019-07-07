package elevators;

import java.lang.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * The DriveSystem "powers" each elevator's move
 * action ("task"). 
 */
public final class DriveSystem {

	private Runnable currentTask;

	private ScheduledFuture<?> future;

	private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

	public DriveSystem(Runnable task) {
		this.currentTask = task;
	}

	public void start() {
		future = executor.scheduleAtFixedRate(currentTask, 0, 1, TimeUnit.SECONDS);
	}

}