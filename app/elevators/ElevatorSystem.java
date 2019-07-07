package elevators;

import java.util.*;

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

	elevators.add(new Elevator());
	elevators.add(new Elevator());
	elevators.add(new Elevator());

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
	return null;
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

  }

}