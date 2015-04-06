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
		robotPath = simulatedMaze.getPath(robotLocation, new Location(7,7));
	}

	public void hasMovedForward()
	{

	}

	public void hasTurnedCounterClockwise()
	{

	}

	public void hasTurnedClockwise()
	{

	}

	public void switchDebugView()
	{
		DEBUG_VIEW=!DEBUG_VIEW;
		mainWindow.updateGUI();
	}

	public void generateMaze()
	{
		simulatedMaze.generateDepthFirst();;
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
