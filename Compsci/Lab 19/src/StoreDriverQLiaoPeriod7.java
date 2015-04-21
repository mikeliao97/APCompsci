/*Name:Qin Liao
 * period: 7
 * Date: jan 15th
 * Time Spent: 1 hour and 30 minutes
 * Reflection: This lab was really informative and I learned alot. This lab took alot 
 * of debugging but I finally got it. One of the biggest problems that I faced was
 * understanding the compareTo method. First I wrote three condition cases for the 
 * comapreTo method(silly me). This created alot of problems because I did not know
 * how to debug in eclipse. Eventually after much research, I eventuallly learned 
 * how to debug using eclipse and solved the problem with compareTo. I really
 * liked this lab because You really had to use all your skills to complete it.
 * For example, I had to recall how to use File, Scanner, String Tokenizer,
 * as well as the merging methods. None of the components of the lab were
 * extremely dificult, it was just alot to write many methods. I am really
 * happy to have finished this lab because it looked pretty intimidating at first
 * . But throuhg patience with eclipse and a some reserach I finally got around
 * to completing it. My strategy for this lab was pretty non-existent, because the
 * psuedocode was already given by Mr.Ferrante. I believe this lab was to sharpen
 * the technical side of coding. And even though I did not have to think through
 * too many parts, I learned many of the technical sides of this lab.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */






import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StoreDriverQLiaoPeriod7 {

	public static void main(String[] args) {		
		Store s = new Store("file50.txt");
		s.sort();
		//s.displayStore();
		s.testSearch(); 
	
	}
}
//
//class Item implements Comparable<Item> {
//
//	private int myId;
//	private int myInv;
//
//	/**
//	 * Constructor for the Item object
//	 *
//	 * @param id
//	 *            id value
//	 * @param inv
//	 *            inventory value
//	 */
//	public Item(int id, int inv) {
//		myId = id;
//		myInv = inv;
//	}
//
//	/**
//	 * Gets the id attribute of the Item object
//	 *
//	 * @return The id value
//	 */
//	public int getId() {
//		// Your code here
//		return myId;
//	}
//
//	/**
//	 * Gets the inv attribute of the Item object
//	 *
//	 * @return The inv value
//	 */
//	public int getInv() {
//		// Your code here
//		return myInv;
//	}
//
//	/**
//	 * Compares this item to another item based on id number. Returns the
//	 * difference between this item's id and the other item's id. A difference
//	 * of zero means the items' ids are equal in value.
//	 *
//	 * @param other
//	 *            Item object to compare to
//	 * @return positive int if myId > other.myId 0 if myId == other.myId
//	 *         negative int if myId < other.myId
//	 */
//	public int compareTo(Item other) {
//		int sum = this.myId - other.myId;
//		return sum;
//	}
//
//	/**
//	 * Compares the Item to the specified object
//	 *
//	 * @param otherObject
//	 *            Item object to compare to
//	 * @return true if equal, false otherwise
//	 */
//	public boolean equals(Item other) {
//		if (this.myId == other.myId) {
//			return true;
//		} else {
//			return false;
//		}
//		// Your code here
//	}
//
//	/**
//	 * Overrides the default toString() of Object. Returns a String
//	 * representation of this object. It's up to you exactly what this looks
//	 * like.
//	 */
//	public String toString() {
//		// Your code here
//		String temp = "Id" + this.myId + " Inv" + this.myInv;
//
//		return temp;
//	}
//}
//class Store {
//
//	
//	
//	/**
//	 * Creates a Store object from data stored in the given file name
//	 *
//	 * @param fName
//	 *            name of the file containing id/inv pairs of data
//	 */
//	public Store(String fName) {
//		loadFile("file50.txt");
//	}
//
//	/**
//	 * Reads a file containing id/inv data pairs one pair per line.
//	 *
//	 * @param inFileName
//	 *            name of file containing id/inv pairs of data
//	 */
//	private void loadFile(String inFileName) {
//		// Your code here
//		Scanner in = null;
//		try {
//			in = new Scanner(new File(inFileName));
//		} catch (Exception e) {
//			System.out.println("Error " + e.getMessage());
//		}
//		// while in has next put the files into an item object
//		while (in.hasNext()) {
//			num++; // keeps track of number of objects
//			String temp = in.nextLine();
//			StringTokenizer t = new StringTokenizer(temp);
//			int id = Integer.parseInt(t.nextToken());
//			int inv = Integer.parseInt(t.nextToken());
//			Item temporaryItem = new Item(id, inv);
//			// add it to the arraylist
//			myStore.add(temporaryItem);
//
//		}
//	}
//
//	/**
//	 * Prints the store contents in the format shown below Line # Id Inv 1 184
//	 * 14 2 196 60
//	 */
//	public void displayStore() {
//		// Your code here
//		System.out.println("Line#       Id       Inv");
//		for (int i = 0; i < myStore.size(); i++) {
//			System.out.printf("%-5s", ("" + (i + 1)));
//			System.out.printf("%9s", ((Item) myStore.get(i)).getId());
//			System.out.printf("%9s", ((Item) myStore.get(i)).getInv());
//			System.out.println();
//		}
//
//	}
//
//	/**
//	 * Sorts the store ArrayList using recursive mergesort
//	 */
//	public void sort() {
//		// Make a single call to mergeSort to get sorting going
//		// (If your mergeSort is broken, then use a quadratic sort)
//		mergeSort(myStore, 0, num - 1);
//
//	}

	private void merge(ArrayList list, int first, int mid, int last) {
		ArrayList<Item> temp = new ArrayList<Item>();
		int x = first; // pointer for arraylist a
		int y = mid + 1; // pint for arraylist b
		while (x <= mid && y <= last) {
			if (((Item) list.get(x)).compareTo((Item) list.get(y)) > 0) {
				temp.add((Item) list.get(y));
				y++;
			} else {
				temp.add((Item) list.get(x));
				x++;
			}
		}
		while (y <= last) {
			temp.add((Item) list.get(y));
			y++;
		}
		while (x <= mid) {
			temp.add((Item) list.get(x));
			x++;
		}
		// use a for loop to set the list elements from the arraylsit
		for (int i = first; i <= last; i++) {
			list.set(i, (Comparable) temp.get(i - first));
		}

	}

	/**
	 * Recursive mergesort of an ArrayList of Items
	 *
	 * @param a
	 *            reference to an ArrayList of Items to be sorted
	 * @param first
	 *            starting index of range of values to be sorted
	 * @param last
	 *            ending index of range of values to be sorted
	 */
	public void mergeSort(ArrayList<Item> list, int first, int last) {
		// Your code here
		if (last - first == 0) {
			return;
		} else { // recursion, divide list into two halves
			int mid = (first + last) / 2;
			mergeSort(list, first, mid);
			mergeSort(list, mid + 1, last);
			merge(list, first, mid, last);
		}
	}
}
