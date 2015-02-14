package mazeEmulator;

import JAMD.JAMDRobot;
import mazeADT.Maze;
import mazeADT.Direction;

public class Simulator
{
	public static boolean DEBUG_VIEW = false;

	private int[] robotLocation;
	private Direction robotDirection;
	private MainWindow mainWindow;

	public static void main(String[] args)
	{
		Simulator mySimulator = new Simulator();
		mySimulator.simulate();
	}

	private void simulate()
	{
		Robot myRobot = new JAMDRobot();
		Maze myMaze = new Maze(16,16);
		robotLocation = new int[2];
		robotLocation[0]=0;
		robotLocation[1]=0;
		robotDirection = Direction.NORTH;

		mainWindow = new MainWindow(800,800,this,myRobot,myMaze);
	}

	protected int[] getRobotLocation()
	{
		return robotLocation;
	}

	protected Direction getRobotDirection()
	{
		return robotDirection;
	}

	protected void switchDebugView()
	{
		DEBUG_VIEW=!DEBUG_VIEW;
		mainWindow.updateGUI();
	}
}
