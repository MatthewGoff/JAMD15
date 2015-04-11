package mazeADT;

import java.util.List;
import java.util.Random;
import java.util.Stack;

import mazeUtil.Location;
import mazeUtil.Direction;
import mazeUtil.LocationMap;
import mazeUtil.LocationList;

/**
* An ADT which holds a two dimensional array of cells with walls in between them.
* Is can generate random maze arrangements and can find a path between any two cells.
*
*/
public class Maze
{
	private int height;
	private int width;

	private Cell[][] cells;

	/**
	* Creates an empty maze with cells of argument height and width.
	* Has walls along the edges and to the east of the startinc cell.
	*
	*@param heightParam the height of this maze
	*@param widthParam the width of this maze
	*/
	public Maze(int heightParam, int widthParam)
	{
		height = heightParam;
		width = widthParam;

		cells = new Cell[width][height];
		Location cellLocation;		

		for (int column=0;column<width;column++)
		{
			for (int row=0;row<height;row++)
			{
				cellLocation = new Location(column,row);
				cells[column][row] = new Cell(cellLocation);
			}
		}

		Cell northNeighbor;
		Cell eastNeighbor;
		Cell southNeighbor;
		Cell westNeighbor;

		for (int column=0;column<width;column++)
		{
			for (int row=0;row<height;row++)
			{
				if (row<height-1){
					northNeighbor = cells[column][row+1];
				}else{
					northNeighbor = null;
					cells[column][row].addWall(Direction.NORTH,false);
				}

				if (column<width-1){
					eastNeighbor = cells[column+1][row];
				}else{
					eastNeighbor = null;
					cells[column][row].addWall(Direction.EAST,false);
				}

				if (row>0){
					southNeighbor = cells[column][row-1];
				}else{
					southNeighbor = null;
					cells[column][row].addWall(Direction.SOUTH,false);
				}

				if (column>0){
					westNeighbor = cells[column-1][row];
				}else{
					westNeighbor = null;
					cells[column][row].addWall(Direction.WEST,false);
				}

				cells[column][row].giveNeighbors(northNeighbor,
												eastNeighbor,
												southNeighbor,
												westNeighbor);
			}
		}

		cells[0][0].addWall(Direction.EAST,true);
	}

	/**
	* Gets the height of this maze
	*
	* NOT NECESSARY FOR FINAL IMPLEMENTATION
	*
	*@return int the height of this maze
	*/
	public int getHeight()
	{
		return cells[0].length;
	}

	/**
	* Gets the width of this maze
	*
	* NOT NECESSARY FOR FINAL IMPLEMENTATION
	*
	*@return int the width of this maze
	*/
	public int getWidth()
	{
		return cells.length;
	}

	/**
	* Finds a path between two locations. If there is no possible path, returns null
	*
	*@param origin the first location in the desired path
	*@param destination the last cell in the desired path
	*@return LocationList a list of locations starting with origin and ending with destination
	*/
	public LocationList getPath(Location origin, Location destination)
	{
		LocationMap connections = new LocationMap();
		LocationList adjacent;
		LocationList visited = new LocationList();
		visited.addToEnd(origin);

		visited.init_iter();
		while (!(visited.getCurrent().equals(destination)))
		{
			adjacent = getCell(visited.getCurrent()).getAdjacent();
			while (!adjacent.isEmpty())
			{
				if (!visited.contains(adjacent.getFirst()))
				{
					connections.addConnection(adjacent.getFirst(),visited.getCurrent());
					visited.addToEnd(adjacent.getFirst());
				}
				adjacent.deleteFirst();
			}

			if (visited.atEnd())
			{
				return null;
			}
			else
			{
				visited.next();
			}
		}

		LocationList returnList = new LocationList();		
		returnList.addToStart(destination);

		Location currentLocation = destination;

		while(!currentLocation.equals(origin))
		{
			currentLocation = connections.getValue(currentLocation);
			returnList.addToStart(currentLocation);
		}

		return returnList;
	}

	/**
	* returns whether there is a wall in the argument direction at the argument location
	*
	* NOT NECESSARY FOR FINAL IMPLEMENTATION
	* Note: Check if used.
	*
	*@param column the column of the cell you are curious about
	*@param row the row of the cell you are curious about
	*@param direction the direction in which you want to know if there is a wall
	*@return boolean whether there is a wall in the argument direction at the cell at the argument row and column
	*/
	public boolean hasWall(int column, int row, Direction direction)
	{
		if (column>=width || column<0 || row>=height || row<0)
		{
			System.out.println("Error: Invalid Maze index");
			return false;
		}

		return cells[column][row].hasWall(direction);
	}

	/**
	* Returns the cell in this maze at the argument location
	* Note: Used in getPath
	*
	*@param cellLocation the location of the cell you want
	*@param Cell the cell at the argument location
	*/
	private Cell getCell(Location cellLocation)
	{
		return cells[cellLocation.getColumn()][cellLocation.getRow()];
	}

	/**
	* Clears all of the internal walls in this maze and 
	* randomly repopulates with new walls with the density of the argument.
	* This method does not guarantee anything about the solvability of the maze
	*
	* NOT NECESSARY FOR FINAL IMPLEMENTATION
	*
	*@param wallDensity the density of walls to randomize this maze with. Must be between 0 and 1.
	*/
	public void generateRandom(double wallDensity)
	{
		if (wallDensity<0.0 || wallDensity>1.0)
		{
			wallDensity = 0.5;
		}
		
		for (int column=0;column<width;column++)
		{
			for (int row=0;row<height;row++)
			{
				cells[column][row].clearWalls();
			}
		}

		for (int column=0;column<width;column++)
		{
			for (int row=0;row<height;row++)
			{
				if (Math.random()<wallDensity)
				{
					cells[column][row].addWall(Direction.NORTH,true);
				}
				if (Math.random()<wallDensity)
				{
					cells[column][row].addWall(Direction.EAST,true);
				}
			}
		}

		cells[0][0].addWall(Direction.EAST,true);
	}
	
	/**
	 * Generates a depth-first style maze by initializing a maze fully walled, then uses a
	 * randomized depth first search to eliminate walls between cells. Honestly for this part
	 * I just looked up maze generation on Google and found: 
	 * 			http://en.wikipedia.org/wiki/Maze_generation_algorithm#Depth-first_search
	 * I modified this basic algorithm to ensure there were multiple solutions to the maze,
	 * to put a 2x2 "end zone" in the center of the maze, and to ensure wall-following alone
	 * isn't enough to solve the maze. 
	 * 
	 * This method is more robust than the random method, as it ensures the maze is solvable
	 * and complex. 
	 * 
	 * NOT NECCESARY FOR FINAL IMPLEMENTATION
	 */
	public void generateDepthFirst()
	{
		Stack<Cell> unvisited = new Stack<Cell>();
		Cell currentCell;
		Random randomizer = new Random();
		
		for (int column=0;column<width;column++)
		{
			for (int row=0;row<height;row++)
			{
				cells[column][row].addWalls();
				unvisited.add(cells[column][row]);
			}
		}
		currentCell = unvisited.pop();
		this.randomDepthFirst(currentCell, unvisited, randomizer);
		this.clearCenter();
		cells[0][0].clearWalls();
		cells[0][0].addWall(Direction.EAST, true);
		this.correct();
	}
	
	private boolean randomDepthFirst(Cell currentCell, Stack<Cell> unvisited, Random randomizer)
	{
		List<Cell> neighbors = currentCell.getNeighbors();
		neighbors.retainAll(unvisited);
		while (!neighbors.isEmpty())
		{
			Cell newCell = neighbors.get(randomizer.nextInt(neighbors.size()));
			Direction neighborDirection = newCell.getConnection(currentCell);
			if (neighborDirection!=null)
			{
				newCell.removeWall(neighborDirection, true);
			}
			unvisited.remove(newCell);
			//System.out.println("Current Cell: " + currentCell);
			this.randomDepthFirst(newCell,unvisited,randomizer);
			neighbors.retainAll(unvisited);
		}
		return false;
		
	}
	
	/*
	 * Clears a 2x2 center square in the maze
	 */
	public void clearCenter()
	{
		cells[7][7].removeWall(Direction.NORTH, true);
		cells[7][7].removeWall(Direction.EAST, true);
		cells[8][8].removeWall(Direction.SOUTH, true);
		cells[8][8].removeWall(Direction.WEST, true);
	}

	/**
	* Ensures that there is a path from every cell in this maze to every other cell.
	* If this is not the case, it will strategically remove walls until the condition is met
	*
	* NOT NECESSARY FOR FINAL IMPLEMENTATION
	*/
	public void correct()
	{
		for (int column=0;column<width;column++)
		{
			for (int row=0;row<height-1;row++)
			{
				ensurePath(cells[column][row].getLocation(),Direction.NORTH);
			}
		}

		int row = 8;
		for (int column=0;column<width-1;column++)
		{
			ensurePath(cells[column][row].getLocation(),Direction.EAST);
		} 
	}

	/**
	* Ensures that there is a path between the argument cell and the cell next to it in the argument direction.
	* Randomly removes walls from the neighboring cell until this is true.
	*
	* NOT NECESSARY FOR FINAL IMPLEMENTATION
	*
	*@param origin a cell in the maze
	*@param direction the direction of an adjacent cell
	*/
	
	
	private void ensurePath(Location origin, Direction direction)
	{
		Direction randomDirection = Direction.getRandomDirection();
		while (getPath(origin,origin.getAdjacent(direction))==null)
		{
			getCell(origin).removeWall(randomDirection, true);
			randomDirection = randomDirection.getClockwise();
		}
	}

	public void addWall(Location myLocation, Direction direction)
	{
		cells[myLocation.getColumn()][myLocation.getRow()].addWall(direction, true);
	}
}