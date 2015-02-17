package mazeUtil;

public class LocationMap
{
	private Location[] keys;
	private Location[] values;
	private int addIndex;

	public LocationMap()
	{
		keys = new Location[256];
		values = new Location[256];
		addIndex = 0;

		for (int i=0;i<256;i++)
		{
			keys[i] = null;
			values[i] = null;
		}

	}

	public void addConnection(Location key, Location value)
	{
		if (addIndex==255)
		{
			System.out.println("Error, overflow on LocationMap. Connection not added.");
		}
		else
		{
			keys[addIndex]=key;
			values[addIndex]=value;
			addIndex++;
		}
	}

	public Location getValue(Location key)
	{
		for (int i=0;i<256;i++)
		{
			if (i==addIndex)
			{
				return null;
			}
			if (keys[i].equals(key))
			{
				return values[i];
			}
		}

		return null;//This line will never run, but it's here to make the compiler happy
	}

	public String toString()
	{
		String returnString = "LocationMap:\n\taddIndex = "+addIndex+"\n\t";

		for (int i=0;i<256;i++)
		{
			if (i==addIndex)
			{
				return returnString;
			}
			else
			{
				returnString+="["+keys[i]+"->"+values[i]+"]";
			}
			
		}

		return returnString;
	}
}