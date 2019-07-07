package tasks;

import com.google.inject.AbstractModule;
// import app.elevators.ElevatorSystem;

import elevators.ElevatorSystem;

public class ElevatorModule extends AbstractModule {
	
	@Override
	protected void configure() {
		ElevatorSystem.getInstance().start();
	}
	
}