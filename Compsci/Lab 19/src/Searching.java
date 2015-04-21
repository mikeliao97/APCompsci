import java.util.Scanner;

public class Searching {
	public static void main(String[] args) {
		Item bubble = new Item(12, 10);
		Item dummy = new Item(10, 10);
		System.out.print(dummy.compareTo(bubble));

		Store s = new Store("file50.txt");
		s.sort();
		s.displayStore();

	}
	public void testSearch() {
		int idToFind;
		int invReturn;
		int index;
		Scanner in = new Scanner(System.in);

		System.out.println("Testing search algorithm\n");
		do {
			System.out.println();
			System.out.print("Enter Id value to search for (-1 to quit) ---> ");
			idToFind = in.nextInt();
			// index = bsearch(new Item(idToFind, 0));
			// recursive version call
			// index = bsearch(new Item(idToFind, 0), 0, myStore.size() - 1);
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
			if((Item)myStore.get(a).equals(idToSearch)){
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
		//initialize the middle item
		int mid = (first + last)/2;
		Item middle = myStore.get(mid);
		
		if(middle.equals(idToSearch)){
			return mid;
		}
		else if(last - first == 0 && middle.compareTo(idToSearch) != 0 ){
			return -1;
		}
		else if(middle.compareTo(idToSearch) < 0){
			bsearch(idToSearch, mid, last);			
		}
		else if(middle.compareTo(idToSearch) >  0){
			bsearch(idToSearch, first, mid);			
		}
	}

}
