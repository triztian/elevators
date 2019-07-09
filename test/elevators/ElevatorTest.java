package elevators;


import org.slf4j.LoggerFactory;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ElevatorTest {

	@Test
	public void testCallWithoutDestination() {

		final Elevator elev = new Elevator("CallWithoutDestination", Elevator.NO_DRIVE_SYSTEM, LoggerFactory.getLogger("elevator"));

		final DriveSystem driveSystem = elev.useManualDriveSystem();

		elev.call(Floor.SECOND);

		driveSystem.advance();
		driveSystem.advance();

		final Elevator.Status status = elev.getStatus();

		assertEquals(Floor.SECOND, status.currentFloor);

	}

	@Test
	public void testCallWithDestinationUP() {

		final Elevator elev = new Elevator("CallWithDestinationUP", Elevator.NO_DRIVE_SYSTEM, LoggerFactory.getLogger(Elevator.class));

		final DriveSystem driveSystem = elev.useManualDriveSystem();

		elev.call(Floor.SECOND, Floor.FIFTH);

		driveSystem.advance();
		driveSystem.advance();

		Elevator.Status status = elev.getStatus();

		assertEquals(Floor.SECOND, status.currentFloor);
		assertEquals(null, status.targetFloor);

		driveSystem.advance();
		driveSystem.advance();
		driveSystem.advance();

		status = elev.getStatus();
		assertEquals(Floor.FIFTH, status.currentFloor);

	}

	@Test
	public void testCallWithDestinationUPThenDown() {

		final Elevator elev = new Elevator("CallWithDestinationUPThenDown", Elevator.NO_DRIVE_SYSTEM, LoggerFactory.getLogger("elevator"));

		final DriveSystem driveSystem = elev.useManualDriveSystem();

		elev.call(Floor.SIXTH, Floor.SECOND);

		driveSystem.advance();
		driveSystem.advance();
		driveSystem.advance();
		driveSystem.advance();
		driveSystem.advance();
		driveSystem.advance();

		Elevator.Status status = elev.getStatus();
		assertEquals(Floor.SIXTH, status.currentFloor);

		driveSystem.advance();
		driveSystem.advance();
		driveSystem.advance();
		driveSystem.advance();

		status = elev.getStatus();
		assertEquals(Floor.SECOND, status.currentFloor);

	}

}