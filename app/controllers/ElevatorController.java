package controllers;

import java.util.*;

import play.mvc.*;
import play.libs.Json;

import elevators.Floor;
import elevators.ElevatorSystem;

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
		try {
			Floor floor = Floor.fromNumber(toFloor);
			ElevatorSystem.callFrom(floor);

			ElevatorSystem.Status status = ElevatorSystem.status();

			return ok(Json.toJson(convertElevatorStatusToAPIResponse(status)));

		} catch(IllegalArgumentException ex) {
			return badRequest(Json.toJson(new APIError("invalid floor", ex.getMessage())));
		}
	}

	public Result postCallDestination(int fromFloor, int toFloor) {

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

		ElevatorSystem.callFromTo(from, to);

		ElevatorSystem.Status status = ElevatorSystem.status();

		return ok(Json.toJson(convertElevatorStatusToAPIResponse(status)));

	}

	public Result getElevator(int id) {

		ElevatorSystem.Status status = ElevatorSystem.status();

		APIElevatorStatus apiStatus = convertElevatorStatusToAPIResponse(status);

		return ok(Json.toJson(apiStatus.elevators.get(id)));

	}

	public static APIElevatorStatus convertElevatorStatusToAPIResponse(ElevatorSystem.Status status) {
		APIElevatorStatus apiStatus = new APIElevatorStatus();

		int elevatorID = 0;
		for (Map<String, Object> elevator : status.elevators) {
			if (elevator.containsKey("direction")) {
				apiStatus.elevators.add(new APIElevatorStatus.Elevator(
					"E" + String.valueOf(elevatorID), 
					elevator.get("currentFloor").toString(),
					elevator.get("destinationFloor").toString(),
					elevator.get("direction").toString()
				));
			} else {
				apiStatus.elevators.add(new APIElevatorStatus.Elevator(
					"E" + String.valueOf(elevatorID), 
					elevator.get("currentFloor").toString()
				));
			}
			elevatorID++;
		}

		return apiStatus;
	}

}
