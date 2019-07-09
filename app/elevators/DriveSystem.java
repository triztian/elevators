package elevators;

/**
 * A drive system controls movement of an elevetor. It may be started, 
 * stopped or manually triggered.
 */
public interface DriveSystem {

	/**
	 * Starts the DriveSystem and sets the state in a way in which it requires no further action.
	 */
	public void start();

	/**
	 * Stops the drive system.
	 */
	public void stop();

	/**
	 * Advance the drive system, "clock" it.
	 */
	public void advance();

}