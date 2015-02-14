package mazeADT;

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
}