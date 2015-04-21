import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Store {

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