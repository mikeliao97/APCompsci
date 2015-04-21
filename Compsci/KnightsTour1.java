import java.util.ArrayList;
import java.util.Random;

public class KnightTour {
	int[][] board = new int[9][9];

	void playBoard() {
		// Each time find the possible moves

		int moveCounter = 1;
		int row = 1;
		int col = 1;
		board[row][col] = moveCounter; // this is the first move
		Random rand = new Random();

		int[] horizontal = new int[] { 0, 1, 2, 2, 1, -1, -2, -2, -1 };
		int[] vertical = new int[] { 0, -2, -1, 1, 2, 2, 1, -1, -2 };

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
							possMoves.add(i);
						}
					}

				}

			}

			if (possibleMoves > 0) {
				// Now that you have the poosssible moves, play them randomly
				int randomMove = rand.nextInt(possibleMoves); // this gets a
																// random
				randomMove = (Integer) possMoves.get(randomMove);

				// Now actaully move the piece
				row += horizontal[randomMove];
				col += vertical[randomMove];
				moveCounter++;
				board[row][col] = moveCounter;

			}
		} while (possibleMoves > 0);

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

}
