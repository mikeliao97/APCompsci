/*Name: Qin Liao
 * Period: 7
 * Date: Feburary 3rd
 * Reflection: This lab was not that hard. I thought that it was pretty fun to see
 * that i am writing the algorithim that microsofot paint actually uses to color structure.
 * I can now see many uses for this type of recursive "finding". I did not think that
 * writing this algorithim was particularly hard. My strategy was that if the point chosen
 * by the user was one that is filled with a "@", fill it with a "-". But use a bunch of
 * if statements to check that whether the recursion can check up right left and down. If 
 * it can the if statment passes and the position is within the boundary, run the recursive
 * method again. The recursive method will stop when the position is not equal to a "@",
 * then it will continue to the next recursive method: such as checking for "@" statements
 * to the right. I thought that this lab would be easier than the maze one. The maze one
 * is sort of tricky beause you have to create new arrays for every run.
 * 
 */

import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

public class EraseObjectQLiaoPeriod7 {
	static String[][] board;

	public static void main(String[] args) {
		EraseObjectQLiaoPeriod7 driver = new EraseObjectQLiaoPeriod7();
		driver.printBoard(board);
		driver.promptUser();
		driver.printBoard(board);

	}

	public EraseObjectQLiaoPeriod7() {
		loadFile();
	}

	public void loadFile() {
		board = new String[21][21];
		fillBlocks(board);

		Scanner in;
		try {
			File f = new File("digital.txt");
			in = new Scanner(f);
			System.out.println("wet");

			// Get the first number and discard it
			String temp = in.nextLine();
			System.out.println("temp: " + temp);

			// now get the rest of the numbers
			while (in.hasNextLine()) {
				String str = in.nextLine();
				StringTokenizer t = new StringTokenizer(str);
				int x = Integer.parseInt(t.nextToken());
				int y = Integer.parseInt(t.nextToken());
				board[x][y] = "@";
			}

		} catch (Exception e) {
			System.out.println("Your problem is:   " + e.getMessage());

		}

	}

	public void promptUser() {
		boolean userWantsToContinue;
		do {
			Scanner in = new Scanner(System.in); // Scanner

			System.out.println("Enter X-coordinate");
			int row = in.nextInt();
			System.out.println("Enter y-coordinate:");
			int col = in.nextInt();

			// erase the board at that point
			eraseBoard(board, row, col);

			// print the board
			printBoard(board);

			System.out.println("Do you want to continue? Y/N");
			String promptUser = in.next();

			if (promptUser.equals("Y")) {
				userWantsToContinue = true;
			} else {
				userWantsToContinue = false;
			}

		} while (userWantsToContinue);

	}

	public void eraseBoard(String board[][], int row, int col) {
		if (row >= 1 && row < (board.length) && col >= 1
				&& col < board[0].length) {
			if (board[row][col].equals("@")) {
				board[row][col] = "-";
				if (row > 1) {
					eraseBoard(board, row - 1, col); // checks up
				}
				if (col < board.length - 1) {
					eraseBoard(board, row, col + 1); // checks right
				}
				if (row < board.length - 1) {
					eraseBoard(board, row + 1, col); // checks down
				}
				if (col > 1) {
					eraseBoard(board, row, col - 1); // checks left
				}

			}
		}
	}

	// fills blocks with dashes
	public void fillBlocks(String[][] list) {
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list.length; j++) {
				list[i][j] = "-";
			}
		}

	}

	public void printBoard(String[][] list) {
		// print out the title
		// print the first lie of spacing
		System.out.println("Image after Eraser \n");

		System.out.print("    ");
		for (int i = 1; i < board.length; i++) {
			System.out.print(i % 10);
		}
		System.out.println();
		for (int i = 1; i < board.length; i++) {
			System.out.printf("%2s", i);
			System.out.print("  ");
			for (int j = 1; j < board.length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

}
