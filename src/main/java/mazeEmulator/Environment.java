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
	private JAMDRobot myRobot;
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
		//simulatedMaze.generateDepthFirst();
		mainWindow = new MainWindow(800,1500,this,simulatedMaze,myRobot);
		robotDirection = Direction.NORTH;
		robotLocation = new Location(0,0);
		robotPath = new LocationList();
		robotPath.addToEnd(robotLocation);
		truePath = simulatedMaze.getPath(robotLocation, new Location(7,7));
	}

	public void hasMovedForward()
	{
		if (!hasWall(robotLocation,robotDirection))
		{
			robotPath.addToStart(robotLocation);
			robotLocation = robotLocation.getAdjacent(robotDirection);
			//System.out.println("It moved to " + robotLocation);
		}
		mainWindow.updateGUI();
	}

	public void hasTurnedCounterClockwise()
	{
		robotDirection = robotDirection.getCounterClockwise();
		mainWindow.updateGUI();
		//System.out.println("It turned left");
	}

	public void hasTurnedClockwise()
	{
		robotDirection = robotDirection.getClockwise();
		mainWindow.updateGUI();
		//System.out.println("It turned right");
	}

	public void switchDebugView()
	{
		DEBUG_VIEW=!DEBUG_VIEW;
		mainWindow.updateGUI();
	}

	public void generateMaze()
	{
		simulatedMaze.generateDepthFirst();
		this.reset();
	}
	
	private void reset()
	{
		myRobot.clear();
		robotDirection = Direction.NORTH;
		robotLocation = new Location(0,0);
		robotPath = new LocationList();
		robotPath.addToEnd(robotLocation);
		truePath = simulatedMaze.getPath(robotLocation, new Location(7,7));
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
	
	public void runRobot() {
		myRobot.init();
	}
}
