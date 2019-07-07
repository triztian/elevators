package elevators;

import java.lang.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * The DriveSystem "powers" each elevator's move
 * action ("task"). 
 */
public class DriveSystem extends Timer {

	public static boolean IS_DAEMON = true;

	private TimerTask currentTask;

	public DriveSystem() {
		super(IS_DAEMON); 
	}

	public DriveSystem(TimerTask task) {
		super(IS_DAEMON); 
		this.currentTask = task;
	}

	/**
	 * Sets the system's task, if a previous task exists it is cancelled 
	 * and set to `null`
	 */
	public void setTask(TimerTask task) {
		if (currentTask != null) {
			currentTask.cancel();
		}

		currentTask = task;

		start();
	}

	public void start() {
		scheduleAtFixedRate(currentTask, 0, TimeUnit.SECONDS.toMillis(1)); // every second
	}

}