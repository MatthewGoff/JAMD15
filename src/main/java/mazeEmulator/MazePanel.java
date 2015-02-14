package mazeEmulator;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;

import mazeADT.Maze;
import mazeADT.Direction;

public class MazePanel extends JPanel
{
	private static final Color WALL_COLOR = Color.BLACK;
	private static final Color NO_WALL_COLOR = Color.GRAY;

	private static final Color NORTH_COLOR = Color.RED;
	private static final Color EAST_COLOR = Color.BLUE;
	private static final Color SOUTH_COLOR = Color.YELLOW;
	private static final Color WEST_COLOR = Color.GREEN;

	private int width;
	private int height;

	private int verticalMargin;
	private int horizontalMargin;
	private int cellWidth;
	private int cellHeight;

	private Robot theRobot;
	private Maze theMaze;
	private Simulator theSimulator;

	protected MazePanel(Simulator simulatorParam, Robot robotParam, Maze mazeParam)
	{
		theSimulator = simulatorParam;
		theRobot = robotParam;
		theMaze = mazeParam;

		verticalMargin = 20;
		horizontalMargin = 20;
		cellWidth = 30;
		cellHeight = 30;

		width = horizontalMargin*2 + cellWidth*theMaze.getWidth();
		height = verticalMargin*2 + cellHeight*theMaze.getHeight();
	}

	protected void paintComponent(Graphics g)
	{
		//g.drawRect(0,0,width-1,height-1);
		g.drawRect(horizontalMargin-1,verticalMargin-1,cellWidth*theMaze.getWidth()+2,cellHeight*theMaze.getHeight()+2);

		for (int column=0;column<theMaze.getWidth();column++)
		{
			for (int row=0;row<theMaze.getHeight();row++)
			{
				paintCell(column, row, g);
			}
		}
		g.setColor(WALL_COLOR);
		for (int xPillar=0;xPillar<theMaze.getWidth()+1;xPillar++)
		{
			for (int yPillar=0;yPillar<theMaze.getHeight()+1;yPillar++)
			{
				g.fillRect(horizontalMargin+(xPillar*cellWidth)-1,height-verticalMargin-(yPillar*cellHeight)-1,3,3);
			}
		}

		paintRobot(g);
	}

	private void paintCell(int column, int row, Graphics g)
	{
		if (Simulator.DEBUG_VIEW)
		{
			paintCellDebug(column, row, g);
		}
		else
		{
			paintCellRegular(column, row, g);
		}
	}

	private void paintCellDebug(int column, int row, Graphics g)
	{
		int xcoord = horizontalMargin + (column*cellWidth);
		int ycoord = height - verticalMargin - (row*cellHeight);

		if (theMaze.hasWall(column,row,Direction.NORTH))
		{
			g.setColor(NORTH_COLOR);
		}
		else
		{
			g.setColor(NO_WALL_COLOR);
		}
		g.drawLine(xcoord+1,ycoord-cellHeight+1,xcoord+cellWidth-1,ycoord-cellHeight+1);
		g.drawLine(xcoord+2,ycoord-cellHeight+2,xcoord+cellWidth-2,ycoord-cellHeight+2);

		if (theMaze.hasWall(column,row,Direction.EAST))
		{
			g.setColor(EAST_COLOR);
		}
		else
		{
			g.setColor(NO_WALL_COLOR);
		}
		g.drawLine(xcoord+cellWidth-1,ycoord-1,xcoord+cellWidth-1,ycoord-cellHeight+1);
		g.drawLine(xcoord+cellWidth-2,ycoord-2,xcoord+cellWidth-2,ycoord-cellHeight+2);

		if (theMaze.hasWall(column,row,Direction.SOUTH))
		{
			g.setColor(SOUTH_COLOR);
		}
		else
		{
			g.setColor(NO_WALL_COLOR);
		}
		g.drawLine(xcoord+1,ycoord-1,xcoord+cellWidth-1,ycoord-1);
		g.drawLine(xcoord+2,ycoord-2,xcoord+cellWidth-2,ycoord-2);

		if (theMaze.hasWall(column,row,Direction.WEST))
		{
			g.setColor(WEST_COLOR);
		}
		else
		{
			g.setColor(NO_WALL_COLOR);
		}
		g.drawLine(xcoord+1,ycoord-1,xcoord+1,ycoord-cellHeight+1);
		g.drawLine(xcoord+2,ycoord-2,xcoord+2,ycoord-cellHeight+2);

	}

	private void paintCellRegular(int column, int row, Graphics g)
	{
		int xcoord = horizontalMargin + (column*cellWidth);
		int ycoord = height - verticalMargin - (row*cellHeight);

		if (theMaze.hasWall(column,row,Direction.NORTH))
		{
			g.setColor(WALL_COLOR);	
		}
		else
		{
			g.setColor(NO_WALL_COLOR);
		}
		g.drawLine(xcoord,ycoord-cellHeight,xcoord+cellWidth,ycoord-cellHeight);
		g.drawLine(xcoord+1,ycoord-cellHeight+1,xcoord+cellWidth-1,ycoord-cellHeight+1);

		if (theMaze.hasWall(column,row,Direction.EAST))
		{
			g.setColor(WALL_COLOR);
		}
		else
		{
			g.setColor(NO_WALL_COLOR);
		}
		g.drawLine(xcoord+cellWidth,ycoord,xcoord+cellWidth,ycoord-cellHeight);
		g.drawLine(xcoord+cellWidth-1,ycoord-1,xcoord+cellWidth-1,ycoord-cellHeight+1);

		if (theMaze.hasWall(column,row,Direction.SOUTH))
		{
			g.setColor(WALL_COLOR);	
		}
		else
		{
			g.setColor(NO_WALL_COLOR);
		}
		g.drawLine(xcoord,ycoord,xcoord+cellWidth,ycoord);
		g.drawLine(xcoord+1,ycoord-1,xcoord+cellWidth-1,ycoord-1);

		if (theMaze.hasWall(column,row,Direction.WEST))
		{
			g.setColor(WALL_COLOR);
		}
		else
		{
			g.setColor(NO_WALL_COLOR);
		}
		g.drawLine(xcoord,ycoord,xcoord,ycoord-cellHeight);
		g.drawLine(xcoord+1,ycoord-1,xcoord+1,ycoord-cellHeight+1);
	}

	private void paintRobot(Graphics g)
	{
		g.setColor(Color.BLACK);

		Direction robotDirection = theSimulator.getRobotDirection();
		int[] robotLocation = theSimulator.getRobotLocation();
		int xcoord = horizontalMargin + (robotLocation[0]*cellWidth);
		int ycoord = height - verticalMargin - (robotLocation[1]*cellHeight);

		if (robotDirection.equals(Direction.NORTH))
		{
			for (int i=0; i<3; i++)
			{
				g.drawLine(xcoord+14+i,ycoord-5,xcoord+14+i,ycoord-cellHeight+5);
				g.drawLine(xcoord+15,ycoord-cellHeight+5-i,xcoord+15+10,ycoord-cellHeight+5+10-i);
				g.drawLine(xcoord+15,ycoord-cellHeight+5-i,xcoord+15-10,ycoord-cellHeight+5+10-i);
			}
		}
		else if (robotDirection.equals(Direction.EAST))
		{
			for (int i=0; i<3; i++)
			{
				g.drawLine(xcoord+5,ycoord-14-i,xcoord+cellWidth-5,ycoord-14-i);
				g.drawLine(xcoord+cellWidth-5+i,ycoord-15,xcoord+cellWidth-5-10+i,ycoord-15-10);
				g.drawLine(xcoord+cellWidth-5+i,ycoord-15,xcoord+cellWidth-5-10+i,ycoord-15+10);
			}
		}
		else if (robotDirection.equals(Direction.SOUTH))
		{
			for (int i=0; i<3; i++)
			{
				g.drawLine(xcoord+14+i,ycoord-5,xcoord+14+i,ycoord-cellHeight+5);
				g.drawLine(xcoord+15,ycoord-5+i,xcoord+15+10,ycoord-5-10+i);
				g.drawLine(xcoord+15,ycoord-5+i,xcoord+15-10,ycoord-5-10+i);
			}
		}
		else if (robotDirection.equals(Direction.WEST))
		{
			for (int i=0; i<3; i++)
			{
				g.drawLine(xcoord+5,ycoord-14-i,xcoord+cellWidth-5,ycoord-14-i);
				g.drawLine(xcoord+5-i,ycoord-14,xcoord+5+10-i,ycoord-14+10);
				g.drawLine(xcoord+5-i,ycoord-14,xcoord+5+10-i,ycoord-14-10);
			}
		}
	}

	public int getHeight()
	{
		return height;
	}

	public int getWidth()
	{
		return width;
	}
}