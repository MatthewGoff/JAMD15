package mazeRobot;

import mazeRobot.Robot;
import mazeADT.Maze;
import mazeEmulator.Environment;
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

	private void move(Direction direction)
	{
		// ...

		myPath.deleteFirst();//Just so that the robot stays on the path
	}

	public void start_stop()
	{
		running = !running;
	}

	public void mode_switch()
	{

	}

	public void reset()
	{
		myLocation = new Location(0,0);
		myDirection = Direction.NORTH;
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
	*/
	public Location getLocation()
	{
		return myLocation;
	}

	/**
	* NOT NECESSARY FOR FINAL IMPLEMENTATION
	*/
	public Direction getDirection()
	{
		return myDirection;
	}

	/**
	* NOT NECESSARY FOR FINAL IMPLEMENTATION
	*/
	public void clear()
	{
		myMaze = new Maze(16,16);
		this.reset();
	}

	private void turnClockwise()
	{
		// Needs implementation in Arduino

		myDirection = myDirection.getClockwise();
	}

	private void turnCounterClockwise()
	{
		// Needs implementation in Arduino

		myDirection = myDirection.getCounterClockwise();
	}

	private void moveForward()
	{
		// Needs implementation in Arduino

		myLocation = myLocation.getAdjacent(myDirection);
	}

	private boolean hasWall(Direction direction)
	{
		return false;
		// Needs implementation in Arduino


	}
}