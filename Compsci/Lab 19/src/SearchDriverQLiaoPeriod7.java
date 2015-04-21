/*Name:Qin Liao
 * period: 7
 * Date: jan 15th
 * Time Spent: 1 hour and 30 minutes
 * Reflection: This lab was really informative and I learned alot about
 * the relationship betweens sorting and searching. The seraching part
 * was pretty easy. The iterative search is probably the easiest thing 
 * I've ever had to code: you just run a loop to find it. The recursive
 * version was a little harder. At first I could not figure it out because
 * it kept giving me stack overflow error. After an hour of debugging I realize
 * two things. First, I have to return a value when I call the search method
 * again. Second, when I split the second half it goes from (mid +1 , last)
 * and not (mid, last). This is because say you have values 48(mid) and 49(last).
 * (48 + 49)/2 rounds down to 48. So mid is ALWAYS 48 and when it runs recursively
 * it runs to itself agin. Eventually I added one (mid +1 ) which finally solved the
 * problem. I was really proud because my computer is so slow that it can't
 * debug with eclipse. So i had to do it with hand. 
 * 
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SearchDriverQLiaoPeriod7 {

	public static void main(String[] args) {		
		Store s = new Store("file50.txt");
		s.sort();
		//s.displayStore();
		s.testSearch(); 
	
	}
}

class Item implements Comparable<Item> {

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
class Store {

	private ArrayList<Item> myStore = new ArrayList<Item>();
	int num = 0;
	

	public void testSearch() {
		int idToFind;
		int invReturn = 0;
		int index = 0;
		Scanner in = new Scanner(System.in);

		System.out.println("Testing search algorithm\n");
		do {
			System.out.println();
			System.out.print("Enter Id value to search for (-1 to quit) ---> ");
			idToFind = in.nextInt();
			//index = bsearch(new Item(idToFind, 0));
			// recursive version call
			 index = bsearch(new Item(idToFind, 0), 0, myStore.size() - 1);
			System.out.print("Id # " + idToFind);
			if (index == -1) {
				System.out.println(" No such part in stock");
			} else {
				System.out.println(" Inventory = "
						+ myStore.get(index).getInv());
			}
		} while (idToFind >= 0);
	}

	/**
	 * Searches the myStore ArrayList of Item Objects for the specified item
	 * object using a iterative binary search algorithm
	 *
	 * @param idToSearch
	 *            Item object containing id value being searched for
	 * @return index of Item if found, -1 if not found
	 */

	private int bsearch(Item idToSearch) {
		for(int a = 0; a < myStore.size(); a++){
			Item temp = (Item)myStore.get(a);
			if(temp.equals(idToSearch)){
				return a;
			}
			
		}
		return -1;
	}

	/**
	 * Searches the specified ArrayList of Item Objects for the specified id
	 * using a recursive binary search algorithm
	 *
	 * @param idToSearch
	 *            Id value being search for
	 * @param first
	 *            Starting index of search range
	 * @param last
	 *            Ending index of search range
	 * @return index of Item if found, -1 if not found
	 */

	private int bsearch(Item idToSearch, int first, int last) {
		// initialize the middle item
		int mid = (first + last) / 2;
		Item middle = (Item) myStore.get(mid);
		if (middle.equals(idToSearch)) {
			return mid;
		} else if (last - first == 0 && middle.compareTo(idToSearch) != 0) {
			return -1;
		} else {
			if (middle.compareTo(idToSearch) < 0) {
				return bsearch(idToSearch, mid + 1, last);
			} else {
				return bsearch(idToSearch, first, mid);
			}
		}
	}
		
	/**
	 * Creates a Store object from data stored in the given file name
	 *
	 * @param fName
	 *            name of the file containing id/inv pairs of data
	 */
	public Store(String fName) {
		loadFile("file50.txt");
	}

	/**
	 * Reads a file containing id/inv data pairs one pair per line.
	 *
	 * @param inFileName
	 *            name of file containing id/inv pairs of data
	 */
	private void loadFile(String inFileName) {
		// Your code here
		Scanner in = null;
		try {
			in = new Scanner(new File(inFileName));
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}
		// while in has next put the files into an item object
		while (in.hasNext()) {
			num++; // keeps track of number of objects
			String temp = in.nextLine();
			StringTokenizer t = new StringTokenizer(temp);
			int id = Integer.parseInt(t.nextToken());
			int inv = Integer.parseInt(t.nextToken());
			Item temporaryItem = new Item(id, inv);
			// add it to the arraylist
			myStore.add(temporaryItem);

		}
	}

	/**
	 * Prints the store contents in the format shown below Line # Id Inv 1 184
	 * 14 2 196 60
	 */
	public void displayStore() {
		// Your code here
		System.out.println("Line#       Id       Inv");
		for (int i = 0; i < myStore.size(); i++) {
			System.out.printf("%-5s", ("" + (i + 1)));
			System.out.printf("%9s", ((Item) myStore.get(i)).getId());
			System.out.printf("%9s", ((Item) myStore.get(i)).getInv());
			System.out.println();
		}

	}

	/**
	 * Sorts the store ArrayList using recursive mergesort
	 */
	public void sort() {
		// Make a single call to mergeSort to get sorting going
		// (If your mergeSort is broken, then use a quadratic sort)
		mergeSort(myStore, 0, num - 1);

	}

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
