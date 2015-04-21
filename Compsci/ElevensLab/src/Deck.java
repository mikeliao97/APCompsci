import java.util.List;
import java.util.ArrayList;
import java.util.Random;
/*
 * Name: Qin Liao
 * Period: 7
 * Time spent: 10 Minutes
 * Reflection: This is a RESUBMIT of the deck class that I implemented last week.
 * I made two mistakes that caused the resubmit. 
 * 1) I did not loop through
 * suits, when initializing the deck. This was solved by implementing another
 * for each loop that loops through each suit. 
 * 
 * 2) I did not implement the deal method correctly. I accidently removed the cards
 * instead of just decrementing the size. It was a simple change that I was
 * able to implement almost immediately.
 * 
 * 
 * 
 * 
 * 
 * 
 */

public class Deck {

	public Deck(String[] ranks, String[] suits, int[] values) {
		cards = new ArrayList<Card>();
		for (int j = 0; j < ranks.length; j++) {
			for (String suitString : suits) { //Added this Line
				cards.add(new Card(ranks[j], suitString, values[j]));
			}
		}
		size = cards.size();
		shuffle();  		//Added this line as well
	}


	public Card deal() {
		//If the deck is empty then shoot out a null card
		
		if (isEmpty() == true) {
			return null;
		}
		//otherwise decrement the size, and shoot out the card
		size = size - 1;
		return cards.get(size) 
	}

}
