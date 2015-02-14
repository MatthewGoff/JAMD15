package mazeADT;

import mazeEmulator.Simulator;

public class Maze
{
	private int height;
	private int width;

	private Cell[][] cells;

	public Maze(int heightParam, int widthParam)
	{
		height = heightParam;
		width = widthParam;

		cells = new Cell[width][height];		

		for (int column=0;column<width;column++)
		{
			for (int row=0;row<height;row++)
			{
				cells[column][row] = new Cell();
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
		cells[0][1].addWall(Direction.NORTH,true);
		cells[1][2].addWall(Direction.SOUTH,true);
		cells[1][0].addWall(Direction.NORTH,true);
	}

	public int getHeight()
	{
		return cells[0].length;
	}

	public int getWidth()
	{
		return cells.length;
	}

	public boolean hasWall(int column, int row, Direction direction)
	{
		if (column>=width || column<0 || row>=height || row<0)
		{
			System.out.println("Error: Invalid Maze index");
			return false;
		}

		return cells[column][row].hasWall(direction);
	}
}