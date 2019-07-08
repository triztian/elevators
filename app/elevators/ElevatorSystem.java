package elevators;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public final class ElevatorSystem {
	
  private static ElevatorSystem instance = null;

  public static ElevatorSystem getInstance() {

	if (instance == null) {
		instance = new ElevatorSystem();
	}

	return instance;

  }

  private List<Elevator> elevators = new ArrayList<>(3);

  private int currentElevator = 0;

  private ElevatorSystem() {

	final Logger elevatorLogger = LoggerFactory.getLogger("elevator");

	elevators.add(new Elevator("E0", elevatorLogger));
	elevators.add(new Elevator("E1", elevatorLogger));
	elevators.add(new Elevator("E2", elevatorLogger));

  }

  public void start() {
	for(Elevator e : elevators)
		e.start();
  }

  public void call(Floor from) {
	elevators.get(currentElevator).call(from);
	currentElevator++;
  }

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

  public void call(Floor from, Floor to) {
	elevators.get(currentElevator).call(from, to);
	currentElevator++;
  }

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