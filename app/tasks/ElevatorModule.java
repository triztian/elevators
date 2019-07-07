package tasks;

import com.google.inject.AbstractModule;

public class ElevatorModule extends AbstractModule {
	
	private ElevatorSystem elevatorSystem = new ElevatorSystem();
	
	@Override
	protected void configure() {
		elevatorSystem.start();
	}
	
}