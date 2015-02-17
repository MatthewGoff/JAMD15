package JAMD;

import mazeEmulator.Robot;
import mazeADT.Maze;

import mazeUtil.Location;
import mazeUtil.Direction;
import mazeUtil.LocationList;

public class JAMDRobot implements Robot
{
	private LocationList myPath;
	private Location myLocation;
	private Direction myDirection;
	private Location myTarget;
	private Maze myMaze;

	public JAMDRobot(Location targetParam)
	{
		myMaze = new Maze(16,16);
		myLocation = new Location(0,0);
		myDirection = Direction.NORTH;
		myTarget = targetParam;
	}

	private void move(Direction direction)
	{
		// ...

		myPath.deleteFirst();//Just so that the robot stays on the path
	}

	/**
	* NOT NECCESARY FOR FINAL IMPLEMENTATION
	*
	*@param mazeParam the maze you would like this robot to think it is in
	*/
	public void giveMaze(Maze mazeParam)
	{
		myMaze = mazeParam;
		myPath = myMaze.getPath(myLocation,myTarget);
	}

	public void start_stop()
	{

	}

	public void mode_switch()
	{

	}

	public void reset()
	{
		myLocation = new Location(0,0);
	}

	/**
	* NOT NECCESARY FOR FINAL IMPLEMENTATION
	*@return the path that this robot is curently folowing
	*/
	public LocationList getPath()
	{
		return myPath;
	}

	public Location getLocation()
	{
		return myLocation;
	}

	public Direction getDirection()
	{
		return myDirection;
	}

}