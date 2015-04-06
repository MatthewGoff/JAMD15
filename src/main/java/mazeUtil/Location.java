package mazeUtil;

public class Location
{
	private int column;
	private int row;

	public Location(int columnParam, int rowParam)
	{
		column = columnParam;
		row = rowParam;
	}

	public boolean equals(Object other)
	{
		if (other == null)
		{
			return false;
		}
		else
		{
			if (other instanceof Location)
			{
				Location that = (Location) other;
				return (this.column==that.column && this.row==that.row);
			}
			else
			{
				return false;
			}
		}
	}

	public int getColumn()
	{
		return column;
	}

	public int getRow()
	{
		return row;
	}

	/**
	* NOT NECESSARY FOR FINAL IMPLEMENTATION
	*/
	public String toString()
	{
		return "("+column+","+row+")";
	}

	public Location getAdjacent(Direction direction)
	{
		if (direction.equals(Direction.NORTH))
		{
			return (new Location(this.getColumn(),this.getRow()+1));
		}
		else if (direction.equals(Direction.EAST))
		{
			return (new Location(this.getColumn()+1,this.getRow()));
		}
		else if (direction.equals(Direction.SOUTH))
		{
			return (new Location(this.getColumn(),this.getRow()-1));
		}
		else if (direction.equals(Direction.WEST))
		{
			return (new Location(this.getColumn()-1,this.getRow()));
		}
		else
		{
			System.out.println("Error. direction was not one of the 4 constants");
			return null;
		}
	}
	
	public boolean isValid()
	{
		if ((this.column<0||this.column>15)||(this.row<0||this.row>15))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public Direction getDirectionOf(Location other)
	{
		if (other.column = this.column - 1 && other.row = this.row)
		{
			return Direction.WEST;
		}
		else if (other.column = this.column + 1 && other.row = this.row)
		{
			return Direction.EAST;
		}
		else if (other.column = this.column && other.row = this.row - 1)
		{
			return Direction.SOUTH;
		}
		else if (other.column = this.column && other.row = this.row + 1)
		{
			return Direction.NORTH;
		}
		else
		{
			System.out.println("Tried to compare direction of non-adjacent locations");
			return null;
		}
	}


}