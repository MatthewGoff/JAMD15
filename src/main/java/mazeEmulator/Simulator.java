package mazeEmulator;

import JAMD.JAMDRobot;
import mazeADT.Maze;
import mazeUtil.Direction;
import mazeUtil.Location;
import mazeUtil.LocationList;

public class Simulator
{
	public static boolean DEBUG_VIEW = false;

	private Location robotLocation;
	private Direction robotDirection;
	private MainWindow mainWindow;
	private Robot myRobot;
	private Maze myMaze;

	public static void main(String[] args)
	{
		Simulator mySimulator = new Simulator();
		mySimulator.simulate();
	}

	private void simulate()
	{
		myRobot = new JAMDRobot(new Location(15,15));
		myMaze = new Maze(16,16);
		robotLocation = new Location(0,0);
		robotDirection = Direction.NORTH;
		myRobot.giveMaze(myMaze);

		mainWindow = new MainWindow(800,800,this,myRobot,myMaze);
	}

	protected void switchDebugView()
	{
		DEBUG_VIEW=!DEBUG_VIEW;
		mainWindow.updateGUI();
	}

	protected void generateMaze()
	{
		myMaze.generateRandom(0.7);
		myRobot.giveMaze(myMaze);
		mainWindow.updateGUI();
	}

	protected void correctMaze()
	{
		myMaze.correct();
		myRobot.giveMaze(myMaze);
		mainWindow.updateGUI();
	}
}
