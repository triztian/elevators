package elevators;

/**
 * 
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