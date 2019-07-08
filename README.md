# Summary

- This coding challenge is designed for a Senior Fullstack Engineer

- **Part 1** asks you to simulate an elevator and connect a backend Java API to a React frontend
- **Part 2** asks you short questions about immutability

# Part 1 - elevator simulation

## Java Backend API

- There are 3 elevators in a 16-floor building
- Implement a Java simulator system that schedules the elevators randomly
- Start scheduling when simulator's **start()** method is invoked
- Every elevator starts on the ground floor
- Each elevator travels at velocity of 1 floor/sec and has its own Drive System
- System accepts async input from Destination-Specific Call Buttons on each floor
- Create a scheduler that enqueues each Call Request to any random elevator

## React/Javascript Frontend

- Display elevator positions
- Display each floor
- Display a clickable Destination-Specific Call Button on each floor
- Allow user to click Destination-Specific Call Button to request an elevator on a particular floor; 
  this request should be sent to the Java backend API

## Assumptions

- Every elevator serves every floor of the building; there are exactly 16 floors
- Passengers instantly enter and exit
- Each elevator has infinite capacity
- Drive System accelerates each elevator instantaneously and with a constant velocity
- The system runs indefinitely (no graceful shutdown)
- Elevators themselves don't have any buttons. The call button panel that is typically found in an elevator is instead found on each floor. Passengers specify their Destination Floor with a Destination-Specific Call Button located on each floor prior to entering the elevator. Signs above each elevator directs passengers to the appropriate elevator.

## Terminology
- **Call Request:** The pressing of a call button on a request floor.
- **Destination Floor:** The floor number requested by a passenger on another floor.
- **Destination-Specific Call Button:** A button on a request floor that signals the intent of a passenger on her request floor to travel to a destination floor.
- **Drive System:** An engine that moves each elevator from one floor to another at a constant velocity of 1 floor per second.
- **Request Floor:** The floor number from which a call button is pressed.

# Part 2 - immutability
Completely unrelated to the elevator problem in Part 1, create a separate file with your answers:

1) Implement an immutable class in Java. The class fully encapsulates an `int[]`, a `Collection` of `String`, and a `java.util.Date`. It should allow clients to specify all three when the class is constructed, and it includes accessors for each. The value returned by each accessor should not change after the object is instantiated.
 
2) Implement an immutable class in JavaScript. The class fully encapsulates an array of integers, an arbitrary data object, and a `Date`. It should allow clients to specify all three when the class is constructed. Once instantiated, the value of each property should not change.
