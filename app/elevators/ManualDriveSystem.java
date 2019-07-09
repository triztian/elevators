package elevators;

import java.util.*;

/**
 * 
 */
public class ManualDriveSystem implements DriveSystem {

	private final Runnable task;

	public ManualDriveSystem(Runnable task) {
		this.task = task;
	}

	public void start() {
		// do nothing
	}

	public void stop() {
	// do nothii
	}

	public void advance() {
		task.run();
	}

}