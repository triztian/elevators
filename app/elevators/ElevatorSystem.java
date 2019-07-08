package elevators;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import actors.ElevatorWebSocketActor;
import util.RangeLooper;

/**
 * 
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
	private static final RangeLooper elevator = new RangeLooper(0, ELEVATOR_COUNT);

	/**
	 * The list of elevator instances.
	 */
	private List<Elevator> elevators = new ArrayList<>(3);
	
	/**
	 * 
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
		elevators.get(elevator.next()).call(from);
	}

	/**
	 * Call the elevator from a floor with a specific destination floor.
	 */
	public void call(Floor from, Floor to) {
		elevators.get(elevator.next()).call(from, to);
	}
	
	/**
	 * Provides a readonly view of the elevator's status.
	 */
	public Status getStatus() {
		
		Status status = new Status();

		for (Elevator e : elevators) {
			Map<String, Object> el = new HashMap<>();
			el.put("currentFloor", e.getCurrentFloor());
			
			if (e.isMoving()) {
				el.put("destinationFloor", e.getDestinationFloor());
				el.put("direction", e.getDirection());
			}
			
			status.elevators.add(el);
		}
		
		return status;
		
	}

	// ---- Static synchronized proxy methods -----
	
	public static synchronized void callFrom(Floor from) {
		instance.call(from);
	}
	
	public static synchronized void callFromTo(Floor from, Floor to) {
		instance.call(from, to);
	}
	
	public static synchronized Status status() {
		return instance.getStatus();
	}
	
	/**
	* This class is a snapshot of the Elevator system.
	*/
	public static final class Status {
		public final List<Map<String, Object>> elevators = new ArrayList<>();
	}

}