package mazeRobot;

import mazeADT.Maze;
import mazeUtil.LocationList;
import mazeUtil.Location;
import mazeUtil.Direction;

/**
* This class is
* NOT NECESSARY FOR FINAL IMPLEMENTATION
*/
public interface Robot
{
	public Maze getMaze();
	public LocationList getPath();
	public Location getLocation();
	public Direction getDirection();

	public void start_stop();
	public void mode_switch();
	public void reset();

	public void clear();
}