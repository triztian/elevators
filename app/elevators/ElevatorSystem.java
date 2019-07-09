package elevators;

import java.util.*;
import java.util.concurrent.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import actors.ElevatorWebSocketActor;
import util.RangeLooper;
import util.RandomLooper;

/**
 * A singleton class that represents and interfaces with the Building's 3 elevators.
 */
public final class ElevatorSystem {
	
	/**
	 * The max elevator count for the application.
	 */
	public static int ELEVATOR_COUNT = 3;

	/**
	 * Global singleton instance for the ElevatorSystem.
	 */
	private static ElevatorSystem instance = null;
	
	/**
	 * Access the elevator system singleton instance.
	 */
	public static ElevatorSystem getInstance() {
		
		if (instance == null) {
			instance = new ElevatorSystem();
		}
		
		return instance;
		
	}
	
	/**
	 * Cycle's through elevator indexes.
	 */
	private static final RandomLooper<Integer> elevator = new RandomLooper<Integer>(RangeLooper.rangeList(0, ELEVATOR_COUNT));
	// private static final RangeLooper elevator = new RangeLooper(0, ELEVATOR_COUNT);

	/**
	 * The list of elevator instances.
	 */
	private List<Elevator> elevators = new ArrayList<>(3);

	/**
	 * Call request dispatcher service.
	 */
	private ExecutorService executor = Executors.newFixedThreadPool(ELEVATOR_COUNT);


	/**
	 * A private helper class to dispatch elevator request calls.It implements
	 * Runnable so that it can be submitted to an executor service
	 */
	private final class CallDispatcher implements Runnable {

		private final Floor from;

		public CallDispatcher(final Floor from) {
			this.from = from;
		}

		public void run() {
			// `elevator` belongs to the outer class
			elevators.get((int)elevator.next()).call(from);
		}
	}

	/**
	 * A private helper class to dispatch elevator request calls. It implements
	 * Runnable so that it can be submitted to an executor service
	 */
	private final class CallWithDestinationDispatcher implements Runnable {

		private final Floor from;
		private final Floor to;

		public CallWithDestinationDispatcher(final Floor from , final Floor to) {
			this.from = from;
			this.to = to;
		}

		public void run() {
			// `elevator` belongs to the outer class
			elevators.get((int)elevator.next()).call(from, to);
		}

	}

	
	/**
	 * Create an elevator system with default values.
	 */
	private ElevatorSystem() {
		
		final Logger elevatorLogger = LoggerFactory.getLogger("elevator");

		for (int i = 0; i < ELEVATOR_COUNT; i++) {

			Elevator el = new Elevator(String.format("E%d", i), elevatorLogger);

			el.addObserver((obj, arg) -> {
				ElevatorWebSocketActor.updateStatus((Elevator.Status)arg);
			});

			elevators.add(el);

		}
		
	}
	
	/**
	 * Starts all of the elevator's DriveSystem.
	 */
	public void start() {
		for(Elevator e : elevators)
			e.start();
	}
	
	/**
	 * Call the next elevator from a given floor without a 
	 * specific destination.
	 */
	public void call(Floor from) {
		// process async
		executor.submit(this.new CallDispatcher(from));
	}

	/**
	 * Call the elevator from a floor with a specific destination floor.
	 */
	public void call(Floor from, Floor to) {
		// process async
		executor.submit(this.new CallWithDestinationDispatcher(from, to));
	}
	
	/**
	 * Provides a readonly view of the elevator's status.
	 */
	public Status getStatus() {
		
		Status status = new Status();

		for (Elevator e : elevators) {
			status.elevators.add(e.getStatus());
		}
		
		return status;
		
	}

	// ---- Static proxy methods -----
	
	public static void callElevator(Floor from) {
		instance.call(from);
	}
	
	public static void callElevator(Floor from, Floor to) {
		instance.call(from, to);
	}
	
	public static Status status() {
		return instance.getStatus();
	}
	
	/**
	* This class is a snapshot of the Elevator system.
	*/
	public static final class Status {
		public final List<Elevator.Status> elevators = new ArrayList<>();
	}

}