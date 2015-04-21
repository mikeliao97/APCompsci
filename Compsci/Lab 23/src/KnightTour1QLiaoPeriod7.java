import java.util.ArrayList;
import java.util.Random;

/* Name: Qin Liao
 * Period: 7
 * Time Spent: 1 hour
 * Reflection: This lab was not that hard I did it in about 1 hour.
 * My strategy for this lab was to first find the moves that
 * work. ( There are only 8 moves). For each of the 8 moves,
 * I have a different move scenario on the board. I keep track
 * of teh move scenarios with variables arrays liek horizong[] 
 * and vertical[]. For example,  move 1, horizontal[1] = 1, and 
 * vertical[1] = 2. Thus if we have move 1, the piece
 * moves 1 to the right and 2 to the top. I check the boundaries
 * and make sure the moves dont cross the board. I also make
 * sure that the moves don't end up in the same place.
 * In the end, I move the pieces. Finally print it out. 
 * The part that I had the most trouble with was debugging it
 * . At one point I forgot to have the condition that checks
 * to make sure that each move is differnt from the other moves.
 * This ended up as an infinite loop. I did not even know however
 * because eclipse is sort of confusing and doesn't prompt an
 * error on my computer. But after I got passed that point,
 * the lab became really easy
 * 
 *
 * 
 * 
 */

public class KnightTour1QLiaoPeriod7 {
	public static void main(String[] args) {
		KnightTourRun driver = new KnightTourRun();
		driver.playBoard();
		driver.printBoard();

	}
}

class KnightTourRun {
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
		
		ArrayList possMoves;

		do {
			possMoves = new ArrayList<Integer>();
			for (int i = 1; i <= 8; i++) {
				if (row + horizontal[i] >= 1 && row + horizontal[i] <= 8) {
					if (col + vertical[i] >= 1 && col + vertical[i] <= 8) {
						if (board[row + horizontal[i]][col + vertical[i]] == 0) {
							possMoves.add(i);
						}
					}

				}

			}

			if (possMoves.size() > 0) {
				// Now that you have the poosssible moves, play them randomly
				int randomInt = rand.nextInt(possMoves.size()); // this gets a
																// random
				int randomMove = (Integer) possMoves.get(randomInt);

				// Now actaully move the piece
				row += horizontal[randomMove];
				col += vertical[randomMove];
				moveCounter++;
				board[row][col] = moveCounter;

			}
		} while (possMoves.size() > 0);

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
