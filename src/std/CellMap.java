package std;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CellMap {

	public static int XSIZE = 50, YSIZE = 50;
	private boolean[][] map;

	/**
	 * Creates an empty cell map
	 * 
	 */
	public CellMap() {

		createNewRep();

	}

	private void createNewRep() {
		this.map = new boolean[XSIZE][YSIZE];
	}

	/**
	 * Returns the status of the cell at (x,y) or false if it is an invalid cell
	 * 
	 * @param x X position
	 * @param y Y position
	 * @return The status of the cell
	 */
	public boolean cellAt(int x, int y) {
		if (isValid(x, y)) {
			return this.map[x][y];
		}
		return false;
	}

	/**
	 * Sets the cell of the cell at (x,y) to {@code newStatus}
	 * 
	 * @param x         X position
	 * @param y         Y position
	 * @param newStatus The new status of the cell
	 */
	public void setCell(int x, int y, boolean newStatus) {
		if (isValid(x, y)) {
			this.map[x][y] = newStatus;
		}
	}

	public void clear() {
		this.createNewRep();
	}

	public void processAll() {
		CellMap temp = this.copy();
		for (int x = 0; x < XSIZE; x++) {
			for (int y = 0; y < YSIZE; y++) {
				processCell(x, y, this, temp);
			}
		}

		this.map = temp.map;
	}

	/**
	 * Randomizes the elements in {@code this};
	 */
	public void randomize() {
		Random r = new Random();
		for (int x = 0; x < XSIZE; x++) {
			for (int y = 0; y < YSIZE; y++) {
				this.map[x][y] = r.nextBoolean();
			}
		}

	}

	/**
	 * Builds a gilder shape. This pattern "lives" in a 3x3 area and as such
	 * requires {@code XSIZE >=3 && YSIZE >=3} to work properly.
	 */
	public void buildGlider() {
		buildGliderAt(0,0);
	}

	/**
	 * Builds a gilder shape. This pattern "lives" in a 3x3 area and as such
	 * requires {@code x+3 < XSIZE && y+3 < YSIZE} to work properly.
	 * 
	 * @param x X coordinate to build the glider at.
	 * @param y Y coordinate to build the glider at
	 */
	public void buildGliderAt(int x, int y) {
		this.setCell(x + 1, y, true);
		this.setCell(x + 2, y + 1, true);
		this.setCell(x + 2, y + 2, true);
		this.setCell(x + 1, y + 2, true);
		this.setCell(x, y + 2, true);
	}

	/**
	 * This method requires {@code x+15 < XSIZE && y+15 < YSIZE} to work properly.
	 * 
	 * @param x
	 * @param y
	 */
	public void buildPulsarAt(int x, int y) {
		this.setCell(x + 2, y, true);
		this.setCell(x + 3, y, true);
		this.setCell(x + 4, y, true);
		this.setCell(x + 8, y, true);
		this.setCell(x + 9, y, true);
		this.setCell(x + 10, y, true);

		this.setCell(x, y + 2, true);
		this.setCell(x + 5, y + 2, true);
		this.setCell(x + 7, y + 2, true);
		this.setCell(x + 12, y + 2, true);

		this.setCell(x, y + 3, true);
		this.setCell(x + 5, y + 3, true);
		this.setCell(x + 7, y + 3, true);
		this.setCell(x + 12, y + 3, true);

		this.setCell(x, y + 4, true);
		this.setCell(x + 5, y + 4, true);
		this.setCell(x + 7, y + 4, true);
		this.setCell(x + 12, y + 4, true);

		this.setCell(x + 2, y + 5, true);
		this.setCell(x + 3, y + 5, true);
		this.setCell(x + 4, y + 5, true);
		this.setCell(x + 8, y + 5, true);
		this.setCell(x + 9, y + 5, true);
		this.setCell(x + 10, y + 5, true);

		this.setCell(x + 2, y + 7, true);
		this.setCell(x + 3, y + 7, true);
		this.setCell(x + 4, y + 7, true);
		this.setCell(x + 8, y + 7, true);
		this.setCell(x + 9, y + 7, true);
		this.setCell(x + 10, y + 7, true);

		this.setCell(x, y + 8, true);
		this.setCell(x + 5, y + 8, true);
		this.setCell(x + 7, y + 8, true);
		this.setCell(x + 12, y + 8, true);

		this.setCell(x, y + 9, true);
		this.setCell(x + 5, y + 9, true);
		this.setCell(x + 7, y + 9, true);
		this.setCell(x + 12, y + 9, true);

		this.setCell(x, y + 10, true);
		this.setCell(x + 5, y + 10, true);
		this.setCell(x + 7, y + 10, true);
		this.setCell(x + 12, y + 10, true);

		this.setCell(x + 2, y + 12, true);
		this.setCell(x + 3, y + 12, true);
		this.setCell(x + 4, y + 12, true);
		this.setCell(x + 8, y + 12, true);
		this.setCell(x + 9, y + 12, true);
		this.setCell(x + 10, y + 12, true);

	}

	/**
	 * Returns a copy of {@code this}.
	 * 
	 * @return a copy of {@code this}
	 */
	public CellMap copy() {
		CellMap newMap = new CellMap();

		for (int x = 0; x < XSIZE; x++) {
			for (int y = 0; y < YSIZE; y++) {
				newMap.map[x][y] = this.map[x][y];
			}
		}

		return newMap;
	}

	/**
	 * Checks whether or not the specified cell is within bounds
	 * 
	 * @param x X position
	 * @param y Y position
	 * @return The existence of the cell
	 */
	private boolean isValid(int x, int y) {
		if (x >= 0 && x < XSIZE) {
			if (y >= 0 && y < YSIZE) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Processes a single cell in {@code this}.
	 * 
	 * @param x
	 * @param y
	 * @param mainMap
	 * @param tempMap
	 */
	private static void processCell(int x, int y, CellMap mainMap, CellMap tempMap) {
		int numNeighbors = 0;

		for (int tmpX = x - 1; tmpX <= x + 1; tmpX++) {
			for (int tmpY = y - 1; tmpY <= y + 1; tmpY++) {
				if ((tmpX != x || tmpY != y) && mainMap.cellAt(tmpX, tmpY)) {
					numNeighbors++;
				}
			}
		}
		/*
		 * Any live cell with two or three live neighbors survives. Because live cells
		 * stay alive, we don't have to do anything.
		 */

		// Any dead cell with three live neighbors becomes a live cell.
		if (numNeighbors == 3 && !mainMap.cellAt(x, y)) {
			tempMap.setCell(x, y, true);
		}

		/*
		 * All other live cells die in the next generation. Similarly, all other dead
		 * cells stay dead. This means that any live cell with a number of neighbors
		 * that's not 2 or 3 dies.
		 */
		else if (mainMap.cellAt(x, y) && (numNeighbors != 2 && numNeighbors != 3)) {
			tempMap.setCell(x, y, false);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CellMap)) {
			return false;
		}
		CellMap local = (CellMap) obj;

		for (int x = 0; x < XSIZE; x++) {
			for (int y = 0; y < YSIZE; y++) {
				if (this.map[x][y] != local.map[x][y])
					return false;
			}
		}

		return true;
	}

	
}
