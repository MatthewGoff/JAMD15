package mazeEmulator;

import mazeRobot.Robot;
import mazeRobot.JAMDRobot;

import mazeGUI.MainWindow;
import mazeADT.Maze;

import mazeUtil.Direction;
import mazeUtil.Location;
import mazeUtil.LocationList;

public class Environment
{
	public static boolean DEBUG_VIEW = false;

	private MainWindow mainWindow;
	private Robot myRobot;
	private Maze simulatedMaze;
	private Direction robotDirection;
	private Location robotLocation;
	private LocationList truePath;
	private LocationList robotPath;

	public static void main(String[] args)
	{
		Environment myEnvironment = new Environment();
	}

	private Environment()
	{
		myRobot = new JAMDRobot(new Location(7,7), this);
		simulatedMaze = new Maze(16,16);
		simulatedMaze.generateDepthFirst();
		mainWindow = new MainWindow(800,800,this,simulatedMaze);
		robotDirection = Direction.NORTH;
		robotLocation = new Location(0,0);
		truePath = simulatedMaze.getPath(robotLocation, new Location(7,7));
		robotPath = new LocationList();
	}

	public void hasMovedForward()
	{
		this.robotLocation = this.robotLocation.getAdjacent(this.robotDirection);
	}

	public void hasTurnedCounterClockwise()
	{
		if (this.robotDirection == Direction.NORTH){
			this.robotDirection = Direction.WEST;
		} else if (this.robotDirection == Direction.WEST) {
			this.robotDirection = Direction.SOUTH;
		} else if (this.robotDirection == Direction.SOUTH) {
			this.robotDirection = Direction.EAST;
		} else if (this.robotDirection == Direction.EAST) {
			this.robotDirection = Direction.NORTH;
		}
	}

	public void hasTurnedClockwise()
	{
		if (this.robotDirection == Direction.NORTH){
			this.robotDirection = Direction.EAST;
		} else if (this.robotDirection == Direction.EAST) {
			this.robotDirection = Direction.SOUTH;
		} else if (this.robotDirection == Direction.SOUTH) {
			this.robotDirection = Direction.WEST;
		} else if (this.robotDirection == Direction.WEST) {
			this.robotDirection = Direction.NORTH;
		}
	}

	public void switchDebugView()
	{
		DEBUG_VIEW=!DEBUG_VIEW;
		mainWindow.updateGUI();
	}

	public void generateMaze()
	{
		simulatedMaze.generateDepthFirst();
		reset();
	}

	public void correctMaze()
	{
		simulatedMaze.correct();
		reset();
	}

	private void reset()
	{
		myRobot.clear();
		mainWindow.updateGUI();
	}

	public boolean hasWall(Location location, Direction direction)
	{
		return simulatedMaze.hasWall(location.getColumn(), location.getRow(), direction);
	}

	public LocationList getTruePath() {
		return this.simulatedMaze.getPath(robotLocation, new Location(7,7));
	}
	
	public LocationList getRobotPath() {
		return this.robotPath;
	}

	public Direction getRobotDirection() {
		return this.robotDirection;
	}

	public Location getRobotLocation() {
		return this.robotLocation;
	}
}
