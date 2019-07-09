package elevators;


import org.slf4j.LoggerFactory;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ElevatorTest {

	@Test
	public void testCallWithoutDestination() {

		final Elevator elev = new Elevator("CallWithoutDestination", Elevator.NO_DRIVE_SYSTEM, LoggerFactory.getLogger("elevator"));

		final DriveSystem driveSystem = elev.useManualDriveSystem();

		elev.call(Floor.SECOND);

		driveSystem.advance();
		driveSystem.advance();

		final Elevator.Status status = elev.getStatus();

		assertEquals(status.currentFloor, Floor.SECOND);

	}

	@Test
	public void testCallWithDestinationUP() {

		final Elevator elev = new Elevator("CallWithDestinationUP", Elevator.NO_DRIVE_SYSTEM, LoggerFactory.getLogger("elevator"));

		final DriveSystem driveSystem = elev.useManualDriveSystem();

		elev.call(Floor.SECOND, Floor.FIFTH);

		driveSystem.advance();
		driveSystem.advance();

		Elevator.Status status = elev.getStatus();

		assertEquals(status.currentFloor, Floor.SECOND);
		assertEquals(status.targetFloor, null);

		driveSystem.advance();
		driveSystem.advance();
		driveSystem.advance();

		status = elev.getStatus();
		assertEquals(status.currentFloor, Floor.FIFTH);

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
		assertEquals(status.currentFloor, Floor.SIXTH);

		driveSystem.advance();
		driveSystem.advance();
		driveSystem.advance();
		driveSystem.advance();

		status = elev.getStatus();
		assertEquals(status.currentFloor, Floor.SECOND);

	}

}