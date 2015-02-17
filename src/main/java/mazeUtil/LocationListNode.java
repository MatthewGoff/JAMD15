package mazeUtil;

public class LocationListNode
{
	protected Location myLocation;
	protected LocationListNode next;

	protected LocationListNode(Location myLocationParam)
	{
		myLocation = myLocationParam;
		next = null;
	}
}