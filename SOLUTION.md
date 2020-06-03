# Summary

- This coding challenge is designed for a Senior Fullstack Engineer

- **Part 1** asks you to simulate an elevator and connect a backend Java API to a React frontend
- **Part 2** asks you short questions about immutability

## Solution

Basically the solution consists of the following.

 * You can find a video here: https://github.com/triztian/elevators/blob/master/Solution/Elevator%20Frontend.mov?raw=true

### Environment

 * macOS 10.14
 * Java Play 2.7 Framework (https://www.playframework.com/)
 * React 16.8.6 using Hooks (https://reactjs.org/docs/getting-started.html)
 * openjdk version "1.8.0_212"

### Description

#### The backend consists of the following:

  * An `ElevatorController` that has 5 routes and that provides API JSON 
    responses.

      * Getting a list of floors 
      * Posting a call to a floor
      * Posting a call to a floor with a destination
     * Getting the status of an elevator

  * A series of `controllers.APInnnnn` classes that determine the response 
   JSON structure.
   
    * https://github.com/triztian/elevators/tree/master/app/controllers

  * A `ElevatorWebSocketActor` to handle websockets (pushes elevator updates)

  * An `elevators` package that contains all of the simulation logic. Elevators  
   are observable and are observed for "movement", whenever an elevator  
   moves (travels to another floor) a WebSocket message (JSON) is sent to any  
   connected clients.
   
 * A `Floor` enum with utility functions.

 * There are 2 drive systems; one automatic and a manual one, the automatic
   owns the worker threads that process the queued floors. The manual one is
   used mostly for tests.

    * **Automatic:** https://github.com/triztian/elevators/blob/master/app/elevators/AutomaticDriveSystem.java#L10
    * **Manual:** https://github.com/triztian/elevators/blob/master/app/elevators/ManualDriveSystem.java#L9

 * Static assets are served under `/app`

#### The Frontend consists of the following:

 * The React Source is here: https://github.com/triztian/elevators/tree/master/public/javascripts

 * An `App` component that renders everything.

 * A `Floor` component that holds the floor name and elevator controls 
   (`FloorSelector` and `CallButton` components).

 * 3 `Elevator` components that render the elevator at a given position and that
   initiates the websocket connection and will update the elevator when notified
   by the server.

 * An `ElevatorAPI` class and singleton instance hat provides access to 
   the `ElevatorController` endpoints. The API is promise based. The API also 
   provides the WebSocket endpoint. 

     * https://github.com/elevators/blob/master/public/javascripts/src/Api.js#L4
     
 * A `css` directory with styels for each component.

# Part 1 - elevator simulation

## Java Backend API

- **There are 3 elevators in a 16-floor building**  
  This is defined here: https://github.com/triztian/elevators/blob/master/app/elevators/ElevatorSystem.java#L19

- **Implement a Java simulator system that schedules the elevators randomly**  
  For this I'm using a simple randomly cycle counter which will schedule the request 
  to a random elevator.

   * https://github.com/triztian/elevators/blob/master/app/elevators/ElevatorSystem.java#L42
   * https://github.com/triztian/elevators/blob/master/app/util/RandomLooper.java#L11

- **Start scheduling when simulator's `start()` method is invoked**  
  Part of the `ElevatorSystem` class:

   * https://github.com/triztian/elevators/blob/master/app/elevators/ElevatorSystem.java#L73

- **Every elevator starts on the ground floor**  
  Defined as a static constant on the `Elevator` class:

   * https://github.com/triztian/elevators/blob/master/app/elevators/Elevator.java#L34

- **Each elevator travels at velocity of 1 floor/sec and has its own Drive System**  
  The `AutomaticDriveSystem` class defines a default 1 floor/sec speed:

   * https://github.com/triztian/elevators/blob/master/app/elevators/AutomaticDriveSystem.java#L41


- **System accepts async input from Destination-Specific Call Buttons on each floor**  
  The controller accepts requests asynchronously, however the Elevator (which is the shared resource) accepts 
  a `call` (with or without destination floor) is protected by 
  `synchronized` modifiers, the queue is non-blocking so it can be emptied
  asynchronously by worker threads (DriveSystem):

    * https://github.com/triztian/elevators/blob/master/app/elevators/ElevatorSystem.java#L112
	* https://github.com/triztian/elevators/blob/master/app/elevators/Elevator.java#L112

- **Create a scheduler that enqueues each Call Request to any random elevator**  
  For this I'm using a simple randomly cycle counter (`RandomLooper`) which will  schedule the request 
  the a random elevator.

   * https://github.com/triztian/elevators/blob/master/app/elevators/ElevatorSystem.java#L42
   * https://github.com/triztian/elevators/blob/master/app/util/RandomLooper.java#L11


## React/Javascript Frontend

https://github.com/triztian/elevators/tree/master/public/javascripts

- **Display elevator positions**  
  The Elevators are shown as they move from floor to floor and the have a legend
  that displays the textual position.
  
  ![Elevator](https://github.com/triztian/elevators/blob/master/Solution/Elevator.png)
  
- **Display each floor**  
  Each floor is labeled on the left side in all caps.
  
  ![Multiple Floors](https://github.com/triztian/elevators/blob/master/Solution/Multiple%20Floors.png)

- **Display a clickable Destination-Specific Call Button on each floor**  
  The HTML selector dropdown labeled "Destination" addresses this.
  
  ![Control Panel](https://github.com/triztian/elevators/blob/master/Solution/ControlPanel.png)

- **Allow user to click Destination-Specific Call Button to request an elevator 
  On a particular floor; this request should be sent to the Java backend API**  
  The HTML selector dropdown labeled "Destination" addresses this, the user
  can select a destination floor and then call the elevator and it will 
  queue the request on the backend.
  
   ![Control Panel](https://github.com/triztian/elevators/blob/master/Solution/ControlPanel.png)
  

## Assumptions

- Every elevator serves every floor of the building; there are exactly 16 floors

- Passengers instantly enter and exit

- Each elevator has infinite capacity

- Drive System accelerates each elevator instantaneously and with a constant velocity

- The system runs indefinitely (no graceful shutdown)

- Elevators themselves don't have any buttons. The call button panel that is 
  typically found in an elevator is instead found on each floor. Passengers 
  specify their Destination Floor with a Destination-Specific Call Button 
  located on each floor prior to entering the elevator. Signs above each elevator 
  directs passengers to the appropriate elevator.

## Terminology

- **Call Request:** The pressing of a call button on a request floor.
- **Request Floor:** The floor number from which a call button is pressed.
- **Destination Floor:** The floor number requested by a passenger on another floor.
- **Destination-Specific Call Button:** A button on a request floor that signals the intent of a passenger on her request floor to travel to a destination floor.
- **Drive System:** An engine that moves each elevator from one floor to another at a constant velocity of 1 floor per second.

# Part 2 - immutability

Completely unrelated to the elevator problem in Part 1, create a separate file 
with your answers:

  1. **Implement an immutable class in Java. The class fully encapsulates an
   `int[]`, a `Collection` of `String`, and a `java.util.Date`. It should allow 
   clients to specify all three when the class is constructed, and it includes 
   accessors for each. The value returned by each accessor should not change 
   after the object is instantiated**  

     * **Implementation:** https://github.com/triztian/elevators/blob/master/app/util/ImmutableData.java#L14
     * **Tests:** https://github.com/triztian/elevators/blob/master/test/util/ImmutableDataTest.java#L14
 
  2. **Implement an immutable class in JavaScript. The class fully encapsulates 
     an array of integers, an arbitrary data object, and a `Date`. It should 
     allow clients to specify all three when the class is constructed. Once 
     instantiated, the value of each property should not change**

      * **Implementation:** https://github.com/triztian/elevators/blob/master/public/javascripts/src/util/ImmutableData.js#L8
      * **Tests:** https://github.com/triztian/elevators/blob/master/public/javascripts/src/test/ImmutableData.test.js#L3
      
