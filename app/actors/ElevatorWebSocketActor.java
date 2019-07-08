package actors;

import java.util.*;

import play.libs.Json;

import akka.actor.*;

import elevators.Elevator;

/**
 * 
 */
public class ElevatorWebSocketActor extends AbstractActor {

	private final ActorRef out;

	/**
	 * The id of the websocket connection associated to this actor instance.
	 */
	private final Integer connID;
	
	/**
	 * 
	 */
	public ElevatorWebSocketActor(ActorRef out) {
		this.out = out;
		this.connID = registerConnection(out);
	}
	
	@Override
	public Receive createReceive() {
		// TODO: Dtermine how to safely disable
		return receiveBuilder()
		.match(String.class, message -> out.tell("I received your message: " + message, self()))
		.build();
	}

	/**
	 * Cleanup after the websocket connection dies.
	 */
	public void postStop() throws Exception {
		unregisterConnection(connID);
	  }


	// --- Static state setup ---

	/**
	 * 
	 */
	public static Props props(ActorRef out) {
		return Props.create(ElevatorWebSocketActor.class, out);
	}

	/**
	 * "Global" static registry of connections to udpate.
	 */
	private static Map<Integer, ActorRef> connections = new HashMap<>();

	/**
	 * 
	 */
	public static synchronized Integer registerConnection(ActorRef conn) {

		Integer connID = connections.size() + 1;
		connections.put(connID, conn);
		return connID;

	}

	/**
	 * 
	 */
	public static synchronized void unregisterConnection(Integer connID) {
		connections.remove(connID);
	}

	/**
	 * 
	 */
	public static synchronized void updateStatus(Elevator.Status status) {

		for (ActorRef conn : connections.values()) {
			System.out.println("updateStatus: " + Json.toJson(status));
			conn.tell(Json.toJson(status).toString(), conn);
		}

	}
}