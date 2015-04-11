package mazeUtil;

public class LocationList
{
	private LocationListNode root;
	private LocationListNode current;
	public int length;

	public LocationList()
	{
		root = null;
		current = null;
		length = 0;
	}

	public void addToStart(Location newLocation)
	{
		LocationListNode newRoot = new LocationListNode(newLocation);
		newRoot.next = this.root;
		this.root = newRoot;
		this.length++;
	}

	public void addToEnd(Location newLocation)
	{
		if (root==null)
		{
			root = new LocationListNode(newLocation);
			this.length++;
		}
		else
		{
			LocationListNode currentNode = root;
			while (currentNode.next!=null)
			{
				currentNode = currentNode.next;
			}

			currentNode.next = new LocationListNode(newLocation);
			this.length++;
		}
	}
	
	/**
	* Renamed from getRoot.
	*/
	public Location getFirst()
	{
		if (this.isEmpty())
		{
			return null;
		}
		else
		{
			return root.myLocation;
		}
	}

	public void deleteFirst()
	{
		if (root!=null)
		{
			root = root.next;
			this.length--;
		}
	}

	public boolean isEmpty()
	{
		return (root==null);
	}

	public boolean contains(Location location)
	{
		LocationListNode currentNode = root;
		while (currentNode!=null)
		{
			if (currentNode.myLocation.equals(location))
			{
				return true;
			}
			else
			{
				currentNode = currentNode.next;
			}
		}

		return false;
	}

	public void init_iter()
	{
		current = root;
	}

	public void next()
	{
		current = current.next;
	}

	public boolean atEnd()
	{
		return (current.next==null);
	}

	public Location getCurrent()
	{
		return current.myLocation;
	}

	/**
	*
	*
	* NOT NECCESARY FOR FINAL IMPLEMENTATION.
	*/
	public String toString()
	{
		String returnString = "";

		LocationListNode currentNode = root;

		while (currentNode!=null)
		{
			returnString+=currentNode.myLocation.toString();
			currentNode = currentNode.next;
		}

		return returnString;
	}

}