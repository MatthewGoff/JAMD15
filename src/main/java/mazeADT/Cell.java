package mazeADT;

import mazeEmulator.Simulator;
import mazeUtil.Location;
import mazeUtil.Direction;
import mazeUtil.LocationList;

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

	private Location myLocation;

	public Cell(Location myLocationParam)
	{
		myLocation = myLocationParam;

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

	public Location getLocation()
	{
		return myLocation;
	}

	public LocationList getAdjacent()
	{
		LocationList adjacent= new LocationList();
		if (!this.hasWall(Direction.NORTH))
		{
			//System.out.println("We're adding the north cell to adjacent. North Cell at "+northCell.getLocation());
			adjacent.addToStart(northCell.getLocation());
		}
		if (!this.hasWall(Direction.EAST))
		{
			//System.out.println("We're adding the east cell to adjacent. East Cell at "+eastCell.getLocation());
			adjacent.addToStart(eastCell.getLocation());
		}
		if (!this.hasWall(Direction.SOUTH))
		{
			//System.out.println("We're adding the south cell to adjacent. South Cell at "+southCell.getLocation());
			adjacent.addToStart(southCell.getLocation());
		}
		if (!this.hasWall(Direction.WEST))
		{
			//System.out.println("We're adding the west cell to adjacent. West Cell at "+westCell.getLocation());
			adjacent.addToStart(westCell.getLocation());
		}

		//System.out.println("We're inside cell right now. And adjacent = "+ adjacent);
		return adjacent;
	}

	public void clearWalls()
	{
		this.removeWall(Direction.NORTH,true);
		this.removeWall(Direction.EAST,true);
		this.removeWall(Direction.SOUTH,true);
		this.removeWall(Direction.WEST,true);
	}

	public void removeWall(Direction direction, boolean reciprocate)
	{
		if (direction.equals(Direction.NORTH) && northCell!=null)
		{
			northWall = false;
			if (reciprocate && northCell!=null)
			{
				northCell.removeWall(Direction.SOUTH,false);
			}
		}
		else if (direction.equals(Direction.EAST) && eastCell!=null)
		{
			eastWall = false;
			if (reciprocate && eastCell!=null)
			{
				eastCell.removeWall(Direction.WEST,false);
			}
		}
		else if (direction.equals(Direction.SOUTH) && southCell!=null)
		{
			southWall = false;
			if (reciprocate && southCell!=null)
			{
				southCell.removeWall(Direction.NORTH,false);
			}
		}
		else if (direction.equals(Direction.WEST) && westCell!=null)
		{
			westWall = false;
			if (reciprocate && westCell!=null)
			{
				westCell.removeWall(Direction.EAST,false);
			}
		}
	}
}