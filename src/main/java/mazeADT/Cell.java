package mazeADT;

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

	/**
	* Note: Check if used.
	*/
	public Location getLocation()
	{
		return myLocation;
	}
	
	public List<Cell> getNeighbors()
	{
		List<Cell> neighbors = new LinkedList<Cell>();
		if (northCell!=null) {neighbors.add(northCell);}
		if (westCell!=null) {neighbors.add(westCell);}
		if (southCell!=null) {neighbors.add(southCell);}
		if (eastCell!=null) {neighbors.add(eastCell);}
		return neighbors;
	}
	
	public Direction getConnection(Cell neighbor)
	{
		if (this.northCell==neighbor) 
		{
			return Direction.NORTH;
		} 
		else if (this.eastCell==neighbor) 
		{
			return Direction.EAST;
		} 
		else if (this.southCell==neighbor)
		{
			return Direction.SOUTH;
		}
		else if (this.northCell==neighbor)
		{
			return Direction.WEST;
		}
		else
		{
			//System.out.println("You done fucked up!");
			return Direction.getRandomDirection();
		}
	}


	public LocationList getAdjacent()
	{
		LocationList adjacent= new LocationList();
		if (!this.hasWall(Direction.NORTH))
		{
			adjacent.addToStart(northCell.getLocation());
		}
		if (!this.hasWall(Direction.EAST))
		{
			adjacent.addToStart(eastCell.getLocation());
		}
		if (!this.hasWall(Direction.SOUTH))
		{
			adjacent.addToStart(southCell.getLocation());
		}
		if (!this.hasWall(Direction.WEST))
		{
			adjacent.addToStart(westCell.getLocation());
		}
		return adjacent;
	}

	/**
	* Removes all the walls from around this cell which are not edge walls
	*
	* NOT NECCESARY FOR FINAL IMPLEMENTATION.
	*/
	public void clearWalls()
	{
		this.removeWall(Direction.NORTH,true);
		this.removeWall(Direction.EAST,true);
		this.removeWall(Direction.SOUTH,true);
		this.removeWall(Direction.WEST,true);
	}
	
	public void addWalls()
	{
		this.clearWalls();
		this.addWall(Direction.NORTH,true);
		this.addWall(Direction.EAST,true);
		this.addWall(Direction.SOUTH,true);
		this.addWall(Direction.WEST,true);
	}

	/**
	* Removes the wall in a given direction only if it is
	* not an edge wall. Used when creating a new maze
	*
	* NOT NECCESARY FOR FINAL IMPLEMENTATION.
	*/
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