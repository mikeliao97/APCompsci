/*Name: Qin Liao
 * Date: March 29th 
 * Period: 7
 * Time spent: 1 hour and 30 minutes
 * Reflection: This lab was actually more difficult than I thought it would be.
 * It was tough and very educational. I am starting to understand the concept of
 * separating classes, so each of them work separately. This lab was especially
 * educational beccaue it made me relearn several concepts such as concrete classes/
 * abstract classes, getter/setter methods, and separate class functions. For example
 * at first, i had alot of trouble because I did not know how each of the methods
 * such as cardIndexes or CardAt() fit into the grand scheme of things. 
 * This was especially confusing because i instead used Cards.get(i) which was naive
 * because i did not realize that my elevensboard did not know about the cards
 * on the board. IT had to use CardAt which was provided by the parent class
 * that has access to the instance variables. Ultimatley I think I learned alot from 
 * this lab.
 * 
 */

import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoard extends Board {

	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 9;

	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};

	/**
	 * Flag used to control debugging print statements.
	 */
	private static final boolean I_AM_DEBUGGING = true;


	/**
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	 public ElevensBoard() {
	 	super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
	 }

	/**
	 * Determines if the selected cards form a valid group for removal.
	 * In Elevens, the legal groups are (1) a pair of non-face cards
	 * whose values add to 11, and (2) a group of three cards consisting of
	 * a jack, a queen, and a king in some order.
	 * @param selectedCards the list of the indices of the selected cards.
	 * @return true if the selected cards form a valid group for removal;
	 *         false otherwise.
	 */
	@Override
	public boolean isLegal(List<Integer> selectedCards) {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
		if(selectedCards.size() == 2){
			if(containsPairSum11(selectedCards) == true){
				return true;
			}
			else{
				return false;
			}
		}
		else if(selectedCards.size() == 3){
			if(containsJQK(selectedCards) == true){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}

	/**
	 * Determine if there are any legal plays left on the board.
	 * In Elevens, there is a legal play if the board contains
	 * (1) a pair of non-face cards whose values add to 11, or (2) a group
	 * of three cards consisting of a jack, a queen, and a king in some order.
	 * @return true if there is a legal play left on the board;
	 *         false otherwise.
	 */
	@Override
	public boolean anotherPlayIsPossible() {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
		//This is to check the cards on display
		List<Integer> cardsCurrentlyOnBoard = cardIndexes();
		if(containsPairSum11(cardsCurrentlyOnBoard) == true
			|| containsJQK(cardsCurrentlyOnBoard) == true){
				return true;
			}
		return false;
		
	}

	/**
	 * Check for an 11-pair in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find an 11-pair.
	 * @return true if the board entries in selectedCards
	 *              contain an 11-pair; false otherwise.
	 */
	private boolean containsPairSum11(List<Integer> selectedCards) {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
		//use two for loops to go through the list
		for (int i = 0; i < selectedCards.size(); i++) {
			int currentCardValue = cardAt(selectedCards.get(i)).pointValue();
			//have a second loop that goes through the cards not equal to i
			for (int j = 0; j < selectedCards.size(); j++) {
				int comparedCardValue = cardAt(selectedCards.get(j)).pointValue();
				if(j != i){ //make sure its not the same card repeating
					if(currentCardValue + comparedCardValue == 11){
						return true;
					}
				}
			}
			
			
		}
		return false;				
	}

	/**
	 * Check for a JQK in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a JQK group.
	 * @return true if the board entries in selectedCards
	 *              include a jack, a queen, and a king; false otherwise.
	 */
	private boolean containsJQK(List<Integer> selectedCards) {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
		boolean hasKing = false;
		boolean hasQueen = false;
		boolean hasJack = false;
		
		for (int i = 0; i < selectedCards.size(); i++) {
			if(cardAt(selectedCards.get(i)).rank() == "jack"){
				hasJack = true;
			}
			if(cardAt(selectedCards.get(i)).rank() == "king"){
				hasQueen = true;
			}
			if(cardAt(selectedCards.get(i)).rank() == "queen"){
				hasKing = true;
			}
		}
		
		if(hasKing == true & hasQueen == true && hasJack == true){
			return true;
		}
		else{
			return false;
		}
		
	}
}
