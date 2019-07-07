package controllers;

import play.mvc.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class ElevatorController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
	}

	public Result postCall(int toFloor) {
		return ok("postCall");
	}

	public Result postCallDestination(int fromFloor, int toFloor) {
		return ok("postCallDestination");
	}

	public Result getElevator(int id) {
		return ok("getElevator");
	}

}