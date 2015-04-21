/*Name: Qin Liao
 * Period; 7
 * Time Spent: 1 hour and 30 minutes
 * Reflection: My code works but I do not know why it does not
 * get 64. Mostly, it goes up to around 50. However, I tested
 * extensively and it should work. My strategy was to import
 * the "access.txt" into an array called location. I have another function called
 * decrement(board, row) that , when passed, row and col,
 * it will decrement location[][] and all the moves that can
 * be accessed from row and col. With this strategy. I added
 * all the lowest possible values of location in each run into
 * an arraylist. Then i used the random class to pick a random number
 * then i picked that number then ran the corresponding move.
 * In the end, I think my program works better than I can solve the puzzle
 * However, it can not get 64 consistently.
 * 
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class KnightsTour2QLiaoPeriod7 {

	public static void main(String[] args) {
		KnightTour driver = new KnightTour();

		driver.loadLocation();
		
		driver.playBoard();
		driver.printBoard();
	}
}

class KnightTour {
	int[][] board = new int[9][9];
	int[][] location = new int[9][9];
	int[] horizontal = new int[] { 0, 1, 2, 2, 1, -1, -2, -2, -1 };
	int[] vertical = new int[] { 0, -2, -1, 1, 2, 2, 1, -1, -2 };

	void loadLocation() {
		try {
			File f = new File("access.txt");
			Scanner in = new Scanner(f);

			for (int i = 1; i <= board.length - 1; i++) {
				for (int j = 1; j <= board.length - 1; j++) {
					int temp = in.nextInt();
					location[i][j] = temp;
				}
			}
		} catch (Exception e) {
			System.out.println("e.getMessage()");

		}
	}

	void playBoard() {
		// Each time find the possible moves
		int moveCounter = 1;
		int row = 1;
		int col = 1;
		board[row][col] = moveCounter; // this is the first move
		Random rand = new Random();
		int possibleMoves = 0;

		do {
			possibleMoves = 0;
			// for loop to find the list of possible moves
			ArrayList possMoves = new ArrayList<Integer>();
			for (int i = 1; i <= 8; i++) {
				if (row + horizontal[i] >= 1 && row + horizontal[i] <= 8) {
					if (col + vertical[i] >= 1 && col + vertical[i] <= 8) {
						if (board[row + horizontal[i]][col + vertical[i]] == 0) {
							possibleMoves++; // add the number of possible moves
							possMoves.add(i); // possMoves is adding the move
												// Number
						}
					}

				}

			}

			if (possibleMoves > 0) {
				ArrayList lowestMoves = new ArrayList<Integer>();
				// Now that you have the moves.
				// Compare each move with the access point
				// Find the maximum access point
				int min = 100;
				for (int i = 0; i < possMoves.size(); i++) {
					int a = (Integer) possMoves.get(i); // this gets the Move#
					int verticalMove = col + vertical[a];
					int horizontalMove = row + horizontal[a];

					int temp = location[horizontalMove][verticalMove];
					if (temp <= min) {
						lowestMoves.add(a);
						min = temp;
					}
				}

				// Now actaully move the piece but you have to pick a random
				// a random number from the ArrayLIst(lowestMoves)
				int randomLowestMove = (Integer) lowestMoves.get(rand
						.nextInt(lowestMoves.size()));

				row += horizontal[randomLowestMove];
				col += vertical[randomLowestMove];
				moveCounter++;
				board[row][col] = moveCounter;
				// Now decrement the spaces that could be reached by board
				decrementBoard(row, col);
			}
		} while (possibleMoves > 0);

	}

	// this method decrements the availibility of spaces
	private void decrementBoard(int row, int col) {
		for (int i = 1; i <= 8; i++) {
			if (row + horizontal[i] >= 0 && row + horizontal[i] <= 8) {
				if (col + vertical[i] >= 0 && col + vertical[i] <= 8) {
					location[row + horizontal[i]][col + vertical[i]] -= 1;
				}
			}

		}
	}

	public void printBoard() {
		for (int i = 1; i <= 8; i++) {
			System.out.printf("%5d", i);
		}
		System.out.println();

		for (int i = 1; i <= board.length - 1; i++) {
			System.out.print(i + " "); // print the vertical header
			for (int j = 1; j <= board.length - 1; j++) {
				if (j == 1) {
					System.out.printf("%3d", board[i][j]);
				} else {
					System.out.printf("%5d", (Integer) board[i][j]);
				}

			}
			System.out.println();
		}

	}

	public void printLocation() {

		for (int i = 1; i <= board.length - 1; i++) {
			for (int j = 1; j <= board.length - 1; j++) {
				if (j == 1) {
					System.out.printf("%3d", location[i][j]);
				} else {
					System.out.printf("%5d", (Integer) location[i][j]);
				}

			}
			System.out.println();
		}
	}

}

