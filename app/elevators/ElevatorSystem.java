package elevators;

import java.util.*;

public final class ElevatorSystem {
	
  private List<Elevator> elevators = new ArrayList<>(3);

  public ElevatorSystem() {

	elevators.add(new Elevator());
	elevators.add(new Elevator());
	elevators.add(new Elevator());

  }

  public void start() {
	for(Elevator e : elevators)
		e.start();
  }
}