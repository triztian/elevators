/*
package tasks;

import javax.inject.Named;
import javax.inject.Inject;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class StartElevatorEngineTask {

	private final ActorSystem actorSystem;
	private final ExecutionContext executionContext;
  
	@Inject
	public CodeBlockTask(ActorSystem actorSystem, ExecutionContext executionContext) {
	  this.actorSystem = actorSystem;
	  this.executionContext = executionContext;
  
	  this.initialize();
	}
  
	private void initialize() {
	//   this.actorSystem
	// 	  .scheduler()
	// 	  .schedule(
	// 		  Duration.create(10, TimeUnit.SECONDS), // initialDelay
	// 		  Duration.create(1, TimeUnit.MINUTES), // interval
	// 		  () -> System.out.println("Running block of code"),
	// 		  this.executionContext);
	}
}
*/