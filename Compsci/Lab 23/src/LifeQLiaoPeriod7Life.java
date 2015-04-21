/*Name: Qin Liao	
 * Period: 7
 * Time Spent: 3 Hours
 * Reflection: This lab was pretty difficult. I think my logic was correct. My approach
 * was to have several different methods. One to check the number of neighbors. One to
 * plot the new set of points into another array temp[][]. And another method to put
 * the temp[][] into the main array board[][] after every generation. When I tested
 * my code for Generation. It displayed the correct output for the number of bacteria
 * on rows, columns, and total count. However, all of the bacteria's placements
 * were 5 spots to the left for some weird reason. I spent about an hour trying to debug it
 * however I could not find the bug. I thought that it was necessary to use an temporary array
 * temp[][] because there was a statements: all life and death occur simultaneously. If I only
 * used one array this would not be possible because changing one particle in the main board[][]
 * would influence future decisions, making "life and death not occur simultaneously". Overall
 * this lab was probably the hardest lab we had so far. I'm pretty happy with my efforst
 * but I still can't quite figure out why the placement is always 5 spots to the left. I'll
 * try to figure it out soon.
 * 
 * 
 * 
 * 
 */

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

class LifeQLiaoPeriod7Life {

	/*
	 * Strategy: Initialize with the fileName;. Put the file in array Read the
	 * first line for intiizliation of an array of that size. Read the other
	 * ones. and put an * in those places. Print out the array
	 */

	int numRows;
	int numColumns;
	String[][] board;
	int generations = 0;

	// Constructor that initializes a game of Life from the given data file
	public LifeQLiaoPeriod7Life(String fileName) {
		Scanner in;
		try {
			File f = new File(fileName);
			in = new Scanner(f);

			// This is to get the first line
			StringTokenizer t = new StringTokenizer(in.nextLine());
			numRows = Integer.parseInt(t.nextToken());
			System.out.println("numRows" + numRows);
			numColumns = Integer.parseInt(t.nextToken());
			System.out.println("numColumns" + numColumns);
			board = new String[numRows][numColumns];
			fillBoardWithSpaces(board);

			// now get the rest of the lines
			while (in.hasNextLine()) {
				String temp = in.nextLine();
				System.out.println("in.nextLine" + temp);

				StringTokenizer k = new StringTokenizer(temp);
				int tempRow = Integer.parseInt(k.nextToken());
				System.out.println("tempRow:" + tempRow);
				int tempCol = Integer.parseInt(k.nextToken());
				System.out.println("tempCol:" + tempCol);
				board[tempRow][tempCol] = "*";
			}

		} catch (Exception e) {
			System.out.println("The problem is : " + e.getMessage());
		}

		System.out.println(Arrays.toString(board));
	}

	// once we add the stars. Basically the spaces on board[][] that are not
	// initialized wiht a star become null
	// that is no bueno because you cannot use that to do comparisions.
	public void fillBoardWithSpaces(String[][] list) {
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list.length; j++) {
				list[i][j] = " ";
			}
		}

	}

	// Method that runs the Life simulation through the given generation
	// Generation 0 is the initial position, Generation 1 is the position after
	// one round of Life, etc...
	public void runLife(int numGenerations) {
		while (numGenerations >= 1) {
			String temp[][] = new String[numRows][numColumns];
			fillBoardWithSpaces(temp);
			// run through each point on the map.
			// check the neighbors with board
			// depending on the number of neighbors, put it in temp or not
			for (int row = 0; row < board.length; row++) {
				for (int col = 0; col < board.length; col++) {
					boolean isLiving = false;
					if (board[row][col].equals("*")) {
						isLiving = true;
					}
					int neighbors = checkNeighbors(row, col);
					plotNewPoint(neighbors, row, col, temp, isLiving);
				}
			}
			// Okay so this two for loops is to plot the points onto temp
			// now u need a funciton to transfer temp into board agan

			transferTempToBoard(temp, board);

			// numGenerations += nextGeneration();
			numGenerations--;
		}
	}

	private void transferTempToBoard(String[][] temp, String[][] board) {
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp.length; j++) {
				board[i][j] = temp[i][j];
			}
		}
	}

	// method to check the number of neighbors
	public int checkNeighbors(int row, int col) {
		int neighbors = 0;
		// just have a bunch of if statements to simplify things
		// top left corner
		int tempRow = row - 1;
		int tempCol = col - 1;
		if (tempRow >= 0 && tempRow < numRows) {
			if (tempCol >= 0 && tempCol < numColumns) {
				if (board[tempRow][tempCol].equals("*")) {
					neighbors++;
				}
			}
		}
		// top corner
		tempRow = row - 1;
		tempCol = col;
		if (tempRow >= 0 && tempRow < numRows) {
			if (tempCol >= 0 && tempCol < numColumns) {
				if (board[tempRow][tempCol].equals("*")) {
					neighbors++;
				}
			}
		}
		// top right corner
		tempRow = row - 1;
		tempCol = col + 1;
		if (tempRow >= 0 && tempRow < numRows) {
			if (tempCol >= 0 && tempCol < numColumns) {
				if (board[tempRow][tempCol].equals("*")) {
					neighbors++;
				}
			}
		}
		// left
		tempRow = row;
		tempCol = col - 1;
		if (tempRow >= 0 && tempRow < numRows) {
			if (tempCol >= 0 && tempCol < numColumns) {
				if (board[tempRow][tempCol].equals("*")) {
					neighbors++;
				}
			}
		}
		// right
		tempRow = row;
		tempCol = col + 1;
		if (tempRow >= 0 && tempRow < numRows) {
			if (tempCol >= 0 && tempCol < numColumns) {
				if (board[tempRow][tempCol].equals("*")) {
					neighbors++;
				}
			}
		}
		// bottom left
		tempRow = row + 1;
		tempCol = col - 1;
		if (tempRow >= 0 && tempRow < numRows) {
			if (tempCol >= 0 && tempCol < numColumns) {
				if (board[tempRow][tempCol].equals("*")) {
					neighbors++;
				}
			}
		}
		// bottom
		tempRow = row + 1;
		tempCol = col;
		if (tempRow >= 0 && tempRow < numRows) {
			if (tempCol >= 0 && tempCol < numColumns) {
				if (board[tempRow][tempCol].equals("*")) {
					neighbors++;
				}
			}
		}
		// bottom right
		tempRow = row + 1;
		tempCol = col + 1;
		if (tempRow >= 0 && tempRow < numRows) {
			if (tempCol >= 0 && tempCol < numColumns) {
				if (board[tempRow][tempCol].equals("*")) {
					neighbors++;
				}
			}
		}
		return neighbors;
	}

	public void plotNewPoint(int neighbors, int row, int col, String[][] temp,
			boolean isLiving) {
		if (neighbors <= 1) {
			temp[row][col] = "";
		} else if (neighbors == 2 && isLiving == true) {
			temp[row][col] = "*";
		} else if (neighbors == 3) {
			temp[row][col] = "*";
		}
	}

	// Method that returns the number of living cells in the given row
	// or returns -1 if row is out of bounds. The first row is row 0.
	public int rowCount(int row) {
		int numStars = 0;
		for (int i = 0; i < board.length; i++) {
			String temp = board[row][i];
			if (temp.equals("*")) {
				numStars++;
			}
		}
		return numStars;
	}

	// Method that returns the number of living cells in the given column
	// or returns -1 if column is out of bounds. The first column is column 0.
	public int colCount(int col) {
		int numStars = 0;
		for (int i = 0; i < board.length; i++) {
			String temp = board[i][col];
			if (temp.equals("*")) {
				numStars++;
			}
		}
		return numStars;
	}

	// Method that returns the total number of living cells on the board
	public int totalCount() {
		int numStars = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				String temp = board[i][j];
				if (temp.equals("*")) {
					numStars++;
				}
			}
		}
		return numStars;
	}

	// Prints out the Life array with row and column headers as shown in the
	// example below.
	public void printBoard() {
		// print out the title
		// print the first lie of spacing
		System.out.print("   ");
		for (int i = 0; i < board.length; i++) {
			System.out.print(i);
		}
		System.out.println();

		for (int i = 0; i < board.length; i++) {
			System.out.print(i);
			System.out.print("  ");
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}

		// Now print the number of various stuff
		System.out.println("The Number of living Cells in row 9: "
				+ rowCount(9));
		System.out.println("The Number of living Cells in col 9: "
				+ colCount(9));
		System.out.println("The Number of total Living Cells: " + totalCount());

	}

	// Advances Life forward one generation
	public void nextGeneration() {
		generations++;
	}

}