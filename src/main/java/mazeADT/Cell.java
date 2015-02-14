package mazeADT;

import mazeEmulator.Simulator;

public class Cell
{
	private Cell northCell;
	private Cell eastCell;
	private Cell southCell;
	private Cell westCell;

	private boolean northWall;
	private boolean eastWall;
	private boolean southWall;
	private boolean westWall;

	public Cell()
	{
		northWall = false;
		eastWall = false;
		southWall = false;
		westWall = false;
	}

	public void giveNeighbors(	Cell northCellParam,
								Cell eastCellParam,
								Cell southCellParam,
								Cell westCellParam)
	{
		northCell = northCellParam;
		eastCell = eastCellParam;
		southCell = southCellParam;
		westCell = westCellParam;
	}

	public void addWall(Direction direction, boolean reciprocate)
	{
		if (direction.equals(Direction.NORTH))
		{
			northWall = true;
			if (reciprocate && northCell!=null)
			{
				northCell.addWall(Direction.SOUTH,false);
			}
		}

		if (direction.equals(Direction.EAST))
		{
			eastWall = true;
			if (reciprocate && eastCell!=null)
			{
				eastCell.addWall(Direction.WEST,false);
			}
		}

		if (direction.equals(Direction.SOUTH))
		{
			southWall = true;
			if (reciprocate && southCell!=null)
			{
				southCell.addWall(Direction.NORTH,false);
			}
		}

		if (direction.equals(Direction.WEST))
		{
			westWall = true;
			if (reciprocate && westCell!=null)
			{
				westCell.addWall(Direction.EAST,false);
			}
		}
	}

	public boolean hasWall(Direction direction)
	{
		if (direction.equals(Direction.NORTH)){return northWall;}
		else if (direction.equals(Direction.EAST)){return eastWall;}
		else if (direction.equals(Direction.SOUTH)){return southWall;}
		else if (direction.equals(Direction.WEST)){return westWall;}
		else
		{
			System.out.println("Error: Recieved an invalid direction.");
			return false;
		}
	}
}