package mazeUtil;

public class LocationList
{
	private LocationListNode root;
	private LocationListNode current;

	public LocationList()
	{
		root = null;
		current = null;
	}

	public void addToStart(Location newLocation)
	{
		LocationListNode newRoot = new LocationListNode(newLocation);
		newRoot.next = this.root;
		this.root = newRoot;
	}

	public void addToEnd(Location newLocation)
	{
		if (root==null)
		{
			root = new LocationListNode(newLocation);
		}
		else
		{
			LocationListNode currentNode = root;
			while (currentNode.next!=null)
			{
				currentNode = currentNode.next;
			}

			currentNode.next = new LocationListNode(newLocation);
		}
	}

	public Location getRoot()
	{
		return root.myLocation;
	}

	public void deleteFirst()
	{
		if (root!=null)
		{
			root = root.next;
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
		/*if (current == null)
		{
			current = root;
		}*/

		return current.myLocation;
	}

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