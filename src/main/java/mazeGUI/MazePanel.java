package mazeGUI;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;

import mazeEmulator.Environment;
import mazeADT.Maze;
import mazeUtil.Direction;
import mazeUtil.Location;
import mazeUtil.LocationList;

public class MazePanel extends JPanel
{
	private static final Color WALL_COLOR = Color.BLACK;
	private static final Color NO_WALL_COLOR = Color.GRAY;
	private static final boolean DRAW_ABSENT_WALLS = false;

	private static final Color NORTH_COLOR = Color.RED;
	private static final Color EAST_COLOR = Color.BLUE;
	private static final Color SOUTH_COLOR = Color.YELLOW;
	private static final Color WEST_COLOR = Color.GREEN;

	private static final int CELL_WIDTH = 30;
	private static final int CELL_HEIGHT = 30;

	private int width;
	private int height;

	private int verticalMargin;
	private int horizontalMargin;

	private Environment theEnvironment;
	private Maze theMaze;

	protected MazePanel(Environment environmentParam, Maze mazeParam)
	{
		theEnvironment = environmentParam;
		theMaze = mazeParam;

		verticalMargin = 20;
		horizontalMargin = 20;

		width = horizontalMargin*2 + CELL_WIDTH*theMaze.getWidth();
		height = verticalMargin*2 + CELL_HEIGHT*theMaze.getHeight();
	}

	protected void paintComponent(Graphics g)
	{
		paintMaze(g);
		paintRobotsPath(g);
		paintTruePath(g);
		paintRobot(g);
	}

	private void paintMaze(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawRect(horizontalMargin-1,verticalMargin-1,CELL_WIDTH*theMaze.getWidth()+2,CELL_HEIGHT*theMaze.getHeight()+2);

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
				g.fillRect(horizontalMargin+(xPillar*CELL_WIDTH)-1,height-verticalMargin-(yPillar*CELL_HEIGHT)-1,3,3);
			}
		}
	}

	private void paintRobotsPath(Graphics g)
	{
		LocationList drawPath = theEnvironment.getRobotPath();

		if (drawPath!=null)
		{
			drawPath.init_iter();
			while (!drawPath.atEnd())
			{
				Location location1 = drawPath.getCurrent();
				drawPath.next();
				Location location2 = drawPath.getCurrent();
				paintPathLine(location1, location2, g);
			}
			Location lastLocation = drawPath.getCurrent();
			int xcoord = horizontalMargin + (lastLocation.getColumn()*CELL_WIDTH);
			int ycoord = height - verticalMargin - (lastLocation.getRow()*CELL_HEIGHT);
			xcoord = xcoord + 15 - 10;
			ycoord = ycoord - 15 - 10;

			g.fillOval(xcoord,ycoord,20,20);
		}
	}
	
	private void paintTruePath(Graphics g)
	{
		LocationList drawPath = theEnvironment.getTruePath();

		if (drawPath!=null)
		{
			drawPath.init_iter();
			while (!drawPath.atEnd())
			{
				Location location1 = drawPath.getCurrent();
				drawPath.next();
				Location location2 = drawPath.getCurrent();
				paintPathLine(location1, location2, g);
			}
			Location lastLocation = drawPath.getCurrent();
			int xcoord = horizontalMargin + (lastLocation.getColumn()*CELL_WIDTH);
			int ycoord = height - verticalMargin - (lastLocation.getRow()*CELL_HEIGHT);
			xcoord = xcoord + 15 - 10;
			ycoord = ycoord - 15 - 10;

			g.fillOval(xcoord,ycoord,20,20);
		}
	}

	private void paintPathLine(Location location1, Location location2, Graphics g)
	{
		int xcoord1 = horizontalMargin + (location1.getColumn()*CELL_WIDTH);
		int ycoord1 = height - verticalMargin - (location1.getRow()*CELL_HEIGHT);
		int xcoord2 = horizontalMargin + (location2.getColumn()*CELL_WIDTH);
		int ycoord2 = height - verticalMargin - (location2.getRow()*CELL_HEIGHT);

		xcoord1 = xcoord1 + 15;
		ycoord1 = ycoord1 - 15;
		xcoord2 = xcoord2 + 15;
		ycoord2 = ycoord2 - 15;

		g.setColor(Color.CYAN);
		g.drawLine(xcoord1,ycoord1,xcoord2,ycoord2);
		g.drawLine(xcoord1+1,ycoord1+1,xcoord2+1,ycoord2+1);
		g.drawLine(xcoord1-1,ycoord1-1,xcoord2-1,ycoord2-1);
	}

	private void paintCell(int column, int row, Graphics g)
	{
		if (Environment.DEBUG_VIEW)
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
		int xcoord = horizontalMargin + (column*CELL_WIDTH);
		int ycoord = height - verticalMargin - (row*CELL_HEIGHT);

		if (theMaze.hasWall(column,row,Direction.NORTH))
		{
			g.setColor(NORTH_COLOR);
		}
		else
		{
			g.setColor(NO_WALL_COLOR);
		}
		g.drawLine(xcoord+1,ycoord-CELL_HEIGHT+1,xcoord+CELL_WIDTH-1,ycoord-CELL_HEIGHT+1);
		g.drawLine(xcoord+2,ycoord-CELL_HEIGHT+2,xcoord+CELL_WIDTH-2,ycoord-CELL_HEIGHT+2);

		if (theMaze.hasWall(column,row,Direction.EAST))
		{
			g.setColor(EAST_COLOR);
		}
		else
		{
			g.setColor(NO_WALL_COLOR);
		}
		g.drawLine(xcoord+CELL_WIDTH-1,ycoord-1,xcoord+CELL_WIDTH-1,ycoord-CELL_HEIGHT+1);
		g.drawLine(xcoord+CELL_WIDTH-2,ycoord-2,xcoord+CELL_WIDTH-2,ycoord-CELL_HEIGHT+2);

		if (theMaze.hasWall(column,row,Direction.SOUTH))
		{
			g.setColor(SOUTH_COLOR);
		}
		else
		{
			g.setColor(NO_WALL_COLOR);
		}
		g.drawLine(xcoord+1,ycoord-1,xcoord+CELL_WIDTH-1,ycoord-1);
		g.drawLine(xcoord+2,ycoord-2,xcoord+CELL_WIDTH-2,ycoord-2);

		if (theMaze.hasWall(column,row,Direction.WEST))
		{
			g.setColor(WEST_COLOR);
		}
		else
		{
			g.setColor(NO_WALL_COLOR);
		}
		g.drawLine(xcoord+1,ycoord-1,xcoord+1,ycoord-CELL_HEIGHT+1);
		g.drawLine(xcoord+2,ycoord-2,xcoord+2,ycoord-CELL_HEIGHT+2);

	}

	private void paintCellRegular(int column, int row, Graphics g)
	{
		int xcoord = horizontalMargin + (column*CELL_WIDTH);
		int ycoord = height - verticalMargin - (row*CELL_HEIGHT);

		if (theMaze.hasWall(column,row,Direction.NORTH))
		{
			g.setColor(WALL_COLOR);
			g.drawLine(xcoord,ycoord-CELL_HEIGHT,xcoord+CELL_WIDTH,ycoord-CELL_HEIGHT);
			g.drawLine(xcoord+1,ycoord-CELL_HEIGHT+1,xcoord+CELL_WIDTH-1,ycoord-CELL_HEIGHT+1);
		}
		else
		{
			if (DRAW_ABSENT_WALLS)
			{
				g.setColor(NO_WALL_COLOR);
				g.drawLine(xcoord,ycoord-CELL_HEIGHT,xcoord+CELL_WIDTH,ycoord-CELL_HEIGHT);
				g.drawLine(xcoord+1,ycoord-CELL_HEIGHT+1,xcoord+CELL_WIDTH-1,ycoord-CELL_HEIGHT+1);
			}
			
		}

		if (theMaze.hasWall(column,row,Direction.EAST))
		{
			g.setColor(WALL_COLOR);
			g.drawLine(xcoord+CELL_WIDTH,ycoord,xcoord+CELL_WIDTH,ycoord-CELL_HEIGHT);
			g.drawLine(xcoord+CELL_WIDTH-1,ycoord-1,xcoord+CELL_WIDTH-1,ycoord-CELL_HEIGHT+1);
		}
		else
		{
			if (DRAW_ABSENT_WALLS)
			{
				g.setColor(NO_WALL_COLOR);
				g.drawLine(xcoord+CELL_WIDTH,ycoord,xcoord+CELL_WIDTH,ycoord-CELL_HEIGHT);
				g.drawLine(xcoord+CELL_WIDTH-1,ycoord-1,xcoord+CELL_WIDTH-1,ycoord-CELL_HEIGHT+1);
			}
			
		}

		if (theMaze.hasWall(column,row,Direction.SOUTH))
		{
			g.setColor(WALL_COLOR);
			g.drawLine(xcoord,ycoord,xcoord+CELL_WIDTH,ycoord);
			g.drawLine(xcoord+1,ycoord-1,xcoord+CELL_WIDTH-1,ycoord-1);
		}
		else
		{
			if (DRAW_ABSENT_WALLS)
			{
				g.setColor(NO_WALL_COLOR);
				g.drawLine(xcoord,ycoord,xcoord+CELL_WIDTH,ycoord);
				g.drawLine(xcoord+1,ycoord-1,xcoord+CELL_WIDTH-1,ycoord-1);
			}
		}

		if (theMaze.hasWall(column,row,Direction.WEST))
		{
			g.setColor(WALL_COLOR);
			g.drawLine(xcoord,ycoord,xcoord,ycoord-CELL_HEIGHT);
			g.drawLine(xcoord+1,ycoord-1,xcoord+1,ycoord-CELL_HEIGHT+1);
		}
		else
		{
			if (DRAW_ABSENT_WALLS)
			{
				g.setColor(NO_WALL_COLOR);
				g.drawLine(xcoord,ycoord,xcoord,ycoord-CELL_HEIGHT);
				g.drawLine(xcoord+1,ycoord-1,xcoord+1,ycoord-CELL_HEIGHT+1);
			}
		}
		
	}

	private void paintRobot(Graphics g)
	{
		g.setColor(Color.BLACK);

		Direction robotDirection = theEnvironment.getRobotDirection();
		Location robotLocation = theEnvironment.getRobotLocation();
		int xcoord = horizontalMargin + (robotLocation.getColumn()*CELL_WIDTH);
		int ycoord = height - verticalMargin - (robotLocation.getRow()*CELL_HEIGHT);

		if (robotDirection.equals(Direction.NORTH))
		{
			for (int i=0; i<3; i++)
			{
				g.drawLine(xcoord+14+i,ycoord-5,xcoord+14+i,ycoord-CELL_HEIGHT+5);
				g.drawLine(xcoord+15,ycoord-CELL_HEIGHT+5-i,xcoord+15+10,ycoord-CELL_HEIGHT+5+10-i);
				g.drawLine(xcoord+15,ycoord-CELL_HEIGHT+5-i,xcoord+15-10,ycoord-CELL_HEIGHT+5+10-i);
			}
		}
		else if (robotDirection.equals(Direction.EAST))
		{
			for (int i=0; i<3; i++)
			{
				g.drawLine(xcoord+5,ycoord-14-i,xcoord+CELL_WIDTH-5,ycoord-14-i);
				g.drawLine(xcoord+CELL_WIDTH-5+i,ycoord-15,xcoord+CELL_WIDTH-5-10+i,ycoord-15-10);
				g.drawLine(xcoord+CELL_WIDTH-5+i,ycoord-15,xcoord+CELL_WIDTH-5-10+i,ycoord-15+10);
			}
		}
		else if (robotDirection.equals(Direction.SOUTH))
		{
			for (int i=0; i<3; i++)
			{
				g.drawLine(xcoord+14+i,ycoord-5,xcoord+14+i,ycoord-CELL_HEIGHT+5);
				g.drawLine(xcoord+15,ycoord-5+i,xcoord+15+10,ycoord-5-10+i);
				g.drawLine(xcoord+15,ycoord-5+i,xcoord+15-10,ycoord-5-10+i);
			}
		}
		else if (robotDirection.equals(Direction.WEST))
		{
			for (int i=0; i<3; i++)
			{
				g.drawLine(xcoord+5,ycoord-14-i,xcoord+CELL_WIDTH-5,ycoord-14-i);
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