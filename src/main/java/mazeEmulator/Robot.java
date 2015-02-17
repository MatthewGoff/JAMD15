package mazeEmulator;

import mazeADT.Maze;
import mazeUtil.LocationList;
import mazeUtil.Location;
import mazeUtil.Direction;

public interface Robot
{
	public void giveMaze(Maze mazeParam);
	public LocationList getPath();
	public Location getLocation();
	public Direction getDirection();
}