/* Name: Qin Liao
 * Period: 7
 * Reflection: This lab was more confusing than hard. I spent quite a bit of
 * time setting up the configuration than figuring out the problems. For example
 * when I ran java -ea DeckTester in the terminal the errors would not update. 
 * Then I tried putting hte fiels into a folder in eclipse and it still wouldn't 
 * update! I was pretty frustrated. But then I asked for help. Eventually we figured
 * out that the deck class had to be inserted into the src folder. This allowed
 * the updates to the error to work. Figuring out the errors were a piece of cake
 * (comparatively), especially considering I had a working copy of the Deck
 * Class that I could use to compare and find the errors.
 * 
 * 
 */


import java.util.List;
import java.util.ArrayList;

public class Deck {

	private List<Card> cards;

	private int size;

	public Deck(String[] ranks, String[] suits, int[] values) {
		cards = new ArrayList<Card>();
		//for (int j = 0; j < ranks.length; j++) { Bug Found, Should be 1
		for (int j = 0; j < ranks.length; j++) {
			for (String suitString : suits) {
				cards.add(new Card(ranks[j], suitString, values[j]));
			}
		}		
		//Bug FOUND!!! NEED cards.size();
		size = cards.size();
		shuffle();
	}


	public boolean isEmpty() {
		return  size <= 0;
	}


	public int size() {
		return size;
	}


	public void shuffle() {
		//for (int k = size - 1; k < 0; k--) {	 Bug FOUND!!!
		for (int k = size - 1; k >= 0; k--) {						
			int howMany = k + 1;
			int start = 0;
			int randPos = (int) (Math.random() * howMany) + start;
			Card temp = cards.get(k);	
			System.out.println("I'm in shuffle");
					
			cards.set(k, cards.get(randPos));
			cards.set(randPos, temp);
		
		}
		size = cards.size();
	}

	public Card deal() {		
		//size-- Bug FOUND, must decrement size after checking isEmpty
		if (isEmpty()) {
			return null;
		}
		size--;
		Card c = cards.get(size);
		return c;
	}

	@Override
	public String toString() {
		String rtn = "size = " + size + "\nUndealt cards: \n";

		for (int k = size - 1; k >= 0; k--) {
			rtn = rtn + cards.get(k);
			if (k != 0) {
				rtn = rtn + ", ";
			}
			if ((size - k) % 2 == 0) {
				// Insert carriage returns so entire deck is visible on console.
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\nDealt cards: \n";
		for (int k = cards.size() - 1; k >= size; k--) {
			rtn = rtn + cards.get(k);
			if (k != size) {
				rtn = rtn + ", ";
			}
			if ((k - cards.size()) % 2 == 0) {
				// Insert carriage returns so entire deck is visible on console.
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\n";
		return rtn;
	}
}
