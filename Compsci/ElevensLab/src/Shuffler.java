import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class provides a convenient way to test shuffling methods.
 */
public class Shuffler {

	/**
	 * The number of consecutive shuffle steps to be performed in each call
	 * to each sorting procedure.
	 */
	private static final int SHUFFLE_COUNT = 1;

	/**
	 * The number of values to shuffle.
	 */
	private static final int VALUE_COUNT = 4;

	/**
	 * Tests shuffling methods.
	 * @param args is not used.
	 */
	public static void main(String[] args) {
		System.out.println("Results of " + SHUFFLE_COUNT +
								 " consecutive perfect shuffles:");
		int[] values1 = new int[VALUE_COUNT];
		for (int i = 0; i < values1.length; i++) {
			values1[i] = i;
			}
		for (int j = 1; j <= SHUFFLE_COUNT; j++) {
			perfectShuffle(values1);
			System.out.print("  " + j + ":");
			for (int k = 0; k < values1.length; k++) {
				System.out.print(" " + values1[k]);
			}
			System.out.println();
		}
		System.out.println();

		System.out.println("Results of " + SHUFFLE_COUNT +
								 " consecutive efficient selection shuffles:");
		int[] values2 = new int[VALUE_COUNT];
		for (int i = 0; i < values2.length; i++) {
			values2[i] = i;
			}
		for (int j = 1; j <= SHUFFLE_COUNT; j++) {
			selectionShuffle(values2);
			System.out.print("  " + j + ":");
			for (int k = 0; k < values2.length; k++) {
				System.out.print(" " + values2[k]);
			}
			System.out.println();
		}
		System.out.println();
	}


	/**
	 * Apply a "perfect shuffle" to the argument.
	 * The perfect shuffle algorithm splits the deck in half, then interleaves
	 * the cards in one half with the cards in the other.
	 * @param values is an array of integers simulating cards to be shuffled.
	 */
	public static void perfectShuffle(int[] values) {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 3 *** */		
		int mid = (values.length + 1)/2;
		int[] shuffle = new int[values.length];
		
		//two for loops
		for (int i = 0; i < mid; i++) {
			shuffle[i * 2] = values[i];
			System.out.println(shuffle);					
		}
		
		for (int i = mid; i < values.length; i++) {
			shuffle[(i - mid) * 2 + 1] = values[i];
		}		
		//prints out the shuffle array		
		for (int i = 0; i < shuffle.length; i++) {
			System.out.print(shuffle[i]);
			values[i] = shuffle[i]; //turn it back into array
		}
		
		
	}

	/**
	 * Apply an "efficient selection shuffle" to the argument.
	 * The selection shuffle algorithm conceptually maintains two sequences
	 * of cards: the selected cards (initially empty) and the not-yet-selected
	 * cards (initially the entire deck). It repeatedly does the following until
	 * all cards have been selected: randomly remove a card from those not yet
	 * selected and add it to the selected cards.
	 * An efficient version of this algorithm makes use of arrays to avoid
	 * searching for an as-yet-unselected card.
	 * @param cards is an array of integers simulating cards to be shuffled.
	 */
	public static void selectionShuffle(List<Card> cards) {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 3 *** */		
		ArrayList shuffled = new ArrayList<Card>(cards.size()); 
		
		Random generator = new Random();
		
		
		for (int i = cards.size() - 1; i >= 0; i--) {
			int randomInt = generator.nextInt(i + 1);
			System.out.print(randomInt);
			
			Card temp = cards.get(randomInt);
			shuffled.set(i,temp);
			
			
			cards.set(randomInt, cards.get(i));
			cards.set(i, temp);
		}
		System.out.println("*****");
		
		
		for (int i = 0; i < shuffled.size(); i++) {
			System.out.print(shuffled.get(i));
			
		}
		
				
	}
}
