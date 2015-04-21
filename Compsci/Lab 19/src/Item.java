public class Item implements Comparable<Item> {

	private int myId;
	private int myInv;

	/**
	 * Constructor for the Item object
	 *
	 * @param id
	 *            id value
	 * @param inv
	 *            inventory value
	 */
	public Item(int id, int inv) {
		myId = id;
		myInv = inv;
	}

	/**
	 * Gets the id attribute of the Item object
	 *
	 * @return The id value
	 */
	public int getId() {
		// Your code here
		return myId;
	}

	/**
	 * Gets the inv attribute of the Item object
	 *
	 * @return The inv value
	 */
	public int getInv() {
		// Your code here
		return myInv;
	}

	/**
	 * Compares this item to another item based on id number. Returns the
	 * difference between this item's id and the other item's id. A difference
	 * of zero means the items' ids are equal in value.
	 *
	 * @param other
	 *            Item object to compare to
	 * @return positive int if myId > other.myId 0 if myId == other.myId
	 *         negative int if myId < other.myId
	 */
	public int compareTo(Item other) {
		int sum = this.myId - other.myId;
		return sum;
	}

	/**
	 * Compares the Item to the specified object
	 *
	 * @param otherObject
	 *            Item object to compare to
	 * @return true if equal, false otherwise
	 */
	public boolean equals(Item other) {
		if (this.myId == other.myId) {
			return true;
		} else {
			return false;
		}
		// Your code here
	}

	/**
	 * Overrides the default toString() of Object. Returns a String
	 * representation of this object. It's up to you exactly what this looks
	 * like.
	 */
	public String toString() {
		// Your code here
		String temp = "Id" + this.myId + " Inv" + this.myInv;

		return temp;
	}
}