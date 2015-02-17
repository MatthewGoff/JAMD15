package mazeUtil;

public class Direction
{
    public static final Direction NORTH = new Direction('n');
    public static final Direction EAST = new Direction('e');
    public static final Direction SOUTH = new Direction('s');
    public static final Direction WEST = new Direction('w');

	private char myDirection;

	private Direction(char directionParam)
	{
		if (	directionParam=='n'
			||	directionParam=='e'
			||	directionParam=='s'
			||	directionParam=='w')
		{
			myDirection = directionParam;
		}
		else
		{
			System.out.println("Error: Invalid Direction");
		}
	}

	public boolean equals(Object other)
    {
        if (other == null)
        {
            return false;
        }
        else
        {
            if (other instanceof Direction)
            {
            	return this.getDirection()==((Direction)other).getDirection();
            }
            else
            {
                return false;
            }
        }
    }

    private char getDirection()
    {
    	return myDirection;
    }

    public String toString()
    {
        switch (myDirection) {
            case 'n': return "North";
            case 'e': return "East";
            case 's': return "South";
            case 'w': return "West";
            default: return "<Error: Invalid direction>";
        }
    }

    public static Direction getRandomDirection()
    {
        int decision = (int)(4*Math.random());
        if (decision==0)
        {
            return NORTH;
        }
        else if (decision==1)
        {
            return EAST;
        }
        else if (decision==2)
        {
            return SOUTH;
        }
        else if (decision==3)
        {
            return WEST;
        }
        else
        {
            System.out.println("Error, random number generator is broken");
            return null;
        }
    }

    /**
    * Get the direction counter clockwise to this one
    *
    *@return Direction the direction counter clockwise to this one
    */
    public Direction getClockwise()
    {
        if (this.equals(NORTH)){return EAST;}
        else if (this.equals(EAST)){return SOUTH;}
        else if (this.equals(SOUTH)){return WEST;}
        else if (this.equals(WEST)){return NORTH;}
        else
        {
            System.out.println("Error, Invalid direction.");
            return null;
        }
    }
}