package mazeRobot;

import java.util.concurrent.TimeUnit;

import mazeEmulator.Environment;
import mazeADT.Maze;
import mazeUtil.Location;
import mazeUtil.Direction;
import mazeUtil.LocationList;

public class JAMDRobot implements Robot
{
	private Environment 	myEnvironment;
	private Direction 		myDirection;
	private LocationList 	myPath;
	private Location 		myLocation;
	private Location 		myTarget;
	private Location 		endLocation;
	private Maze 			myMaze;

	private boolean running;

	public JAMDRobot(Location endLocationParam, Environment myEnvironmentParam)
	{
		myMaze = new Maze(16,16);
		myLocation = new Location(0,0);
		myDirection = Direction.NORTH;
		endLocation = endLocationParam;
		myEnvironment = myEnvironmentParam;
		running = false;
	}

	//Called setup in Arduino
	public void init()
	{
		chooseTarget();
		myPath = myMaze.getPath(myLocation,myTarget);
		running = true;
		while(running)
		{
			this.run();
			delay(100);
		}
	}

	//Called loop in Arduino
	public boolean run()
	{
		Location nextLocation;
		Direction directionToGo;
		
		if (myLocation == endLocation)
		{
			return false;
			//chooseTarget();
			//myPath = myMaze.getPath(myLocation,myTarget);
		}
		
		//in Arduino, use left IR sensor to check if there is a wall and add it
		if (hasWall(myDirection.getCounterClockwise()))
		{
			addWall(myDirection.getCounterClockwise());
		}
		
		//in Arduino, use center IR sensor to check if there is a wall and add it
		if (hasWall(myDirection))
		{
			addWall(myDirection);
		}
		
		//in Arduino, use right IR sensor to check if there is a wall and add it
		if (hasWall(myDirection.getClockwise()))
		{
			addWall(myDirection.getClockwise());
		}
		
		//once we have all the walls we can sense, find the new path
		myPath = myMaze.getPath(myLocation, endLocation);
		System.out.println(myPath);
		myPath.deleteFirst(); //gets rid of the location its already at
		nextLocation = myPath.getRoot();
		directionToGo = myLocation.getDirectionOf(nextLocation);
		
		while (myDirection != directionToGo)
		{
			System.out.println("In order to go " + directionToGo + " I'm turning clockwise!");
			myDirection = myDirection.getClockwise();
			myEnvironment.hasTurnedClockwise();
		}
		move();	
			
		
		//if we're at the desired location, go to the next location
		
		update();
		return running;
	}

	private void chooseTarget()
	{
		myTarget = endLocation;
	}

	private void update()
	{
		
		/**
		* If button 1 == HIGH, then running = false;
		* If button 2 == HIGH, then reset() and running = false;
		* If button 3 == HIGH, then switchMode();
		* If button 4 == HIGH, then clear() and running = false;
		*/
		
	}

	private void move()
	{
		myLocation = myLocation.getAdjacent(myDirection);
		System.out.println("I think I moved to " + myLocation);
		myEnvironment.hasMovedForward();
		myPath.deleteFirst();//Just so that the robot stays on the path
	}

	public void start_stop()
	{
		running = !running;
	}

	public void mode_switch()
	{

	}



	private void turnClockwise()
	{
		// Needs implementation in Arduino
		myDirection = myDirection.getClockwise();
		myEnvironment.hasTurnedClockwise();
	}

	private void turnCounterClockwise()
	{
		// Needs implementation in Arduino

		myDirection = myDirection.getCounterClockwise();
		myEnvironment.hasTurnedCounterClockwise();
	}

	private void moveForward()
	{
		// Needs implementation in Arduino

		myLocation = myLocation.getAdjacent(myDirection);
	}

	private boolean hasWall(Direction direction)
	{
		// Needs implementation in Arduino

		return myEnvironment.hasWall(myLocation, direction);
	}
	
	private void addWall(Direction direction)
	{
		myMaze.addWall(myLocation, direction);
	}

	public void reset()
	{
		myLocation = new Location(0,0);
		myDirection = Direction.NORTH;
		myMaze = new Maze(16,16);
	}
	/**
	* NOT NECESSARY FOR FINAL IMPLEMENTATION
	*@return The maze that this robot has so far constructed
	* of its environment
	*/
	public Maze getMaze()
	{
		return myMaze;
	}

	/**
	* NOT NECESSARY FOR FINAL IMPLEMENTATION
	*@return The path that this robot is curently folowing
	*/
	public LocationList getPath()
	{
		return myPath;
	}

	/**
	* NOT NECESSARY FOR FINAL IMPLEMENTATION
	* Note: Check if called
	*/
	public Location getLocation()
	{
		return myLocation;
	}

	/**
	* NOT NECESSARY FOR FINAL IMPLEMENTATION
	* Note: Check if called
	*/
	public Direction getDirection()
	{
		return myDirection;
	}

	public void setMaze(Maze newMaze)
	{
		myMaze = newMaze;
		this.reset();
	}

	private void delay(int milliseconds)
	{
		try
		{
			TimeUnit.MILLISECONDS.sleep(milliseconds);
		}
		catch (InterruptedException e)
		{
			System.out.println("Sleeping was interrupted");
		}
	}

	public void clear() {
		this.reset();
		
	}


}