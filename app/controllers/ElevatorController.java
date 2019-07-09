package controllers;

import java.util.*;

import play.mvc.*;
import play.libs.Json;
import play.mvc.*;
import akka.actor.*;
import akka.stream.*;
import javax.inject.Inject;
import play.libs.streams.ActorFlow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elevators.Floor;
import elevators.ElevatorSystem;
import actors.ElevatorWebSocketActor;

/**
* This controller contains an action to handle HTTP requests
* to the application's home page.
*/
public class ElevatorController extends Controller {
	
	private final ActorSystem actorSystem;
	private final Materializer materializer;

	private final Logger logger = LoggerFactory.getLogger("elevator-controller");
	
	@Inject
	public ElevatorController(ActorSystem actorSystem, Materializer materializer) {
		this.actorSystem = actorSystem;
		this.materializer = materializer;
	}
	

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

		logger.debug("postCall: {}", toFloor);

		try {
			Floor floor = Floor.fromNumber(toFloor);
			ElevatorSystem.callElevator(floor);
			
			ElevatorSystem.Status status = ElevatorSystem.status();
			
			return ok(Json.toJson(status));
			
		} catch(IllegalArgumentException ex) {
			return badRequest(Json.toJson(new APIError("invalid floor", ex.getMessage())));
		}
	}
	
	/**
	 * 
	 */
	public Result postCallDestination(int fromFloor, int toFloor) {

		logger.debug("postCallDestination: {} to {}", fromFloor, toFloor);
		
		Floor from, to;
		
		try {
			from = Floor.fromNumber(fromFloor);
		} catch(IllegalArgumentException ex) {
			return badRequest(Json.toJson(new APIError("invalid call floor", ex.getMessage())));
		}
		
		try {
			to = Floor.fromNumber(toFloor);
		} catch(IllegalArgumentException ex) {
			return badRequest(Json.toJson(new APIError("invalid destination floor", ex.getMessage())));
		}
		
		ElevatorSystem.callElevator(from, to);
		
		ElevatorSystem.Status status = ElevatorSystem.status();
		
		return ok(Json.toJson(status));
		
	}
	
	/**
	 * 
	 */
	public Result getElevator(int id) {
		
		if (id < 0 || id > 2) { // FIXME: Hardcoded
			return badRequest(Json.toJson(new APIError("invalid elevator", "id must be between 0 and 2")));
		}
		
		ElevatorSystem.Status status = ElevatorSystem.status();
		
		APIElevator apiStatus = new APIElevator(status.elevators.get(id));
		
		return ok(Json.toJson(apiStatus));
		
	}
	
	/**
	* Provides a list of floors
	*/
	public Result getFloors() {
		
		List<APIFloor> floors = new ArrayList<>();
		for(Floor f : Floor.values()) {
			floors.add(new APIFloor(f.toString(), f.ordinal()));
		}
		
		return ok(Json.toJson(floors));
		
	}

	/**
	 * 
	 */
	public WebSocket websocketUpdates(final Integer id) {
		return WebSocket.Text.accept(
			request -> ActorFlow.actorRef(ElevatorWebSocketActor::props, actorSystem, materializer)
		);
	}

}
