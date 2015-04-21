import java.util.*;

/*
 * Name: Qin Liao	
 * Period: 7
 * Date:January 8
 * ReflectioN:This lab was not too bad,It took about an hour to finish the whole thing.
 * I think the selection sort is probably the easiest one out of all of them.
 * The buble sort was medium. And the insertion sort was the hardest and 
 * I had to watch a youtube a video to completely understand the process.
 * I also really like the idea that these sorts are sort of interchangeable
 * for example, bubble sort's 2nd for loop can be used to find the largest number
 * in order to implement it into selection sort. I would guess that the insertion
 * sort is probably the easiest one. For bubble sort and and selection sort you 
 * have to implement two for loops that run completely. Insertion sort's while
 * loop has a chance to stop, so thus I think it is more efficient. I'm not completely
 * sure though. In the process, I learned alot about sorting. In fact, I watched
 * a youtube video and familiarized myself with even more types of sorting.
 * 
 */

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Lab17_0_QinLiao {

	public static void main(String[] args) {

		Lab17_0_QinLiao lab = new Lab17_0_QinLiao();

		ArrayList<Comparable> list = lab.initializeList();
		ArrayList<Comparable> copy = lab.duplicate(list);

		System.out.println("Before Merge Sort:");
		System.out.println(list);

		lab.mergeSort(list, 0, list.size()-1); // runs your Bubble Sort code
		Collections.sort(copy); // runs built-in sorting code
		Collections.reverse(copy);

		System.out.println("After Merge Sort:");
		System.out.println(list);
		System.out.println(copy.toString().equals(list.toString()) ? "CORRECT"
				: "NOT SORTED PROPERLY");

		System.out.println("Before Bubble Sort:");
		System.out.println(list);

		lab.bubbleSort(list); // runs your Bubble Sort code
		Collections.sort(copy); // runs built-in sorting code
		Collections.reverse(copy);

		System.out.println("After Bubble Sort:");
		System.out.println(list);
		System.out.println(copy.toString().equals(list.toString()) ? "CORRECT"
				: "NOT SORTED PROPERLY");

		list = lab.initializeList();
		copy = lab.duplicate(list);
		System.out.println("\nBefore Selection Sort:");
		System.out.println(list);

		lab.selectionSort(list); // runs your Selection Sort code
		Collections.sort(copy); // runs built-in sorting code

		System.out.println("After Selection Sort:");
		System.out.println(list);
		System.out.println(copy.toString().equals(list.toString()) ? "CORRECT"
				: "NOT SORTED PROPERLY");

		list = lab.initializeList();
		copy = lab.duplicate(list);
		System.out.println("\nBefore Insertion Sort:");
		System.out.println(list);

		lab.insertionSort(list); // runs your Insertion Sort code
		Collections.sort(copy); // runs built-in sorting code
		Collections.reverse(copy);

		System.out.println("After Insertion Sort:");
		System.out.println(list);
		System.out.println(copy.toString().equals(list.toString()) ? "CORRECT"
				: "NOT SORTED PROPERLY");
	}

	// sort it in descending order
	//
	void mergeSort(ArrayList<Comparable> list, int first, int last) {
		if (last - first == 0) {
		} else { // recursion, divide list into two halves
			int mid = (first + last) / 2;
			mergeSort(list, first, mid);
			mergeSort(list, mid + 1, last);
			merge(list, first, mid, last);
		}
	}

	/*
	 * will merge the two sorted sublists within A into one continuous sublist
	 * from A[first] .. A[last]. The left list ranges from A[first]..A[mid]. The
	 * right list ranges from A[mid+1]..A[last].
	 */
	void merge(ArrayList<Comparable> list, int first, int mid, int last) {
		ArrayList temp = new ArrayList<Comparable>();
		int x = first; // pointer for arraylist a
		int y = mid + 1; // pint for arraylist b
		while (x < mid + 1 && y <= last) {
			if (list.get(x).compareTo(list.get(y)) < 0) {
				temp.add(list.get(y));
				y++;
			} else {
				temp.add(list.get(x));
				x++;
			}
		}
		while (y <= last) {
			temp.add(list.get(y));
			y++;
		}
		while (x < mid + 1) {
			temp.add(list.get(x));
			x++;
		}
		// use a for loop to set the list elements from the arraylsit
		for (int i = first; i <= last; i++) {
			list.set(i, (Comparable) temp.get(i - first));
		}

	}

	
	/*
	 * Write code for a Bubble Sort algorithm that starts at the right side of
	 * of ArrayList of Comparable objects and "bubbles" the largest item to the
	 * left of the list. The result should be an ArrayList arranged in
	 * descending  order.
	 */
	void bubbleSort(ArrayList<Comparable> list) {
		for (int i = list.size() - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if(list.get(j).compareTo(list.get(j +1)) < 0){
					Comparable temp = list.get(j);
					list.set(j, list.get(j+1));
					list.set(j+1, temp);
				}
			}
		}
		
	}

	/*
	 * Write code for a Selection Sort algorithm that starts at the left side of
	 * an ArrayList of Comparable objects and searches through the list for the
	 * largest item and then swaps it with the last item in the list. The
	 * "last item" then becomes the item to its left. The result should be an
	 * ArrayList arranged in ascending order.
	 */
	void selectionSort(ArrayList<Comparable> list) {
		for (int a = 0; a < list.size(); a++) {
			Comparable max = list.get(a);
			int key = a;
			for (int b = a + 1; b < list.size(); b++) {
				if (list.get(b).compareTo(max) < 0) {
					max = list.get(b);
					key = b;
				}
			}
			Comparable temp = list.get(a);
			list.set(a, max);
			list.set(key, temp);

		}

	}

	/*
	 * Write code for an Insertion Sort algorithm that starts at the left side
	 * of an ArrayList of Comparable objects and inserts the first item (in
	 * position 1) into it's correct place within the first two items...then
	 * inserts the third item into its correct place on the left, then the
	 * fourth item into its correct place on the left, etc, until the last item
	 * is inserted into the list. Insert items so the result is an ArrayList
	 * arranged in ascending order1
	 */
	void insertionSort(ArrayList<Comparable> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			int j = i + 1;
			Comparable temp = list.get(j);
			while(j > 0 && temp.compareTo(list.get(j - 1)) > 0){
				list.set(j, list.get(j-1));
				j--;
			}
			list.set(j, temp);
			
			System.out.println(list);
		}
		
	}

	ArrayList<Comparable> initializeList() {

		String[] words = { "apple", "orange", "banana", "pear", "peach",
				"plum", "grape", "cherry", "apricot", "strawberry" };

		ArrayList<Comparable> temp = new ArrayList<Comparable>();
		ArrayList<Comparable> list = new ArrayList<Comparable>();

		for (int i = 0; i < words.length; i++)
			temp.add(words[i]);

		list.clear(); // clear the list before adding to it

		while (temp.size() > 0) {
			list.add(temp.remove((int) (Math.random() * temp.size())));
		}

		return list;
	}

	ArrayList<Comparable> duplicate(ArrayList<Comparable> list) {

		ArrayList<Comparable> listCopy = new ArrayList<Comparable>();

		Iterator<Comparable> iter = list.iterator();

		while (iter.hasNext()) {
			listCopy.add(iter.next());
		}

		return listCopy;
	}

}