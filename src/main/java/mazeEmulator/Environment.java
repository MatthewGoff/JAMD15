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

	public static void main(String[] args)
	{
		Envioronment myEnvironment = new Environment();
	}

	private Environment()
	{
		myRobot = new JAMDRobot(new Location(7,7), this);
		simulatedMaze = new Maze(16,16);
		mainWindow = new MainWindow(800,800,this,simulatedMaze);
	}

	public void switchDebugView()
	{
		DEBUG_VIEW=!DEBUG_VIEW;
		mainWindow.updateGUI();
	}

	public void generateMaze()
	{
		simulatedMaze.generateRandom(0.7);
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

	public void hasWall(Direction direction)
	{

	}
}
