
/**
 * This is a class that tests the Card class.
 */
public class CardTester {

	/**
	 * The main method in this class checks the Card operations for consistency.
	 *	@param args is not used.
	 */
	public static void main(String[] args) {
		Card redKing = new Card("King", "Heart", 13);
		Card blackKing = new Card("King", "Spade", 13);
		Card red10 = new Card("10", "Heart", 10);
		
		System.out.println(redKing.toString());
		System.out.println(blackKing.toString());
		System.out.println(red10.toString());
		
	}
}
