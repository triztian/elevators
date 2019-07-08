package actors;

import akka.actor.*;

public class ElevatorWebSocketActor extends AbstractActor {
	
	public static Props props(ActorRef out) {
		return Props.create(ElevatorWebSocketActor.class, out);
	}
	
	private final ActorRef out;
	
	public ElevatorWebSocketActor(ActorRef out) {
		this.out = out;
	}
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
		.match(String.class, message -> out.tell("I received your message: " + message, self()))
		.build();
	}
}