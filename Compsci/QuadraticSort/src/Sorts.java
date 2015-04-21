import java.util.*;

/**
 * Description of the Class
 *
 * @author Your Name Here
 * @created Month Day, Year
 */
public class Sorts {
	private long steps;

	/**
	 * Description of Constructor
	 *
	 * @param list
	 *            Description of Parameter
	 */
	public Sorts() {
		steps = 0;
	}

	/**
	 * Description of the Method
	 *
	 * @param list
	 *            reference to an array of integers to be sorted
	 */
	public void bubbleSort(ArrayList<Comparable> list) {
		steps++; // initialize a =
		steps++; // check whether a is smaller than size
		for (int a = 0; a < list.size(); a++) {
			steps += 2; // initialize b = . Also list.size() is another step
			steps++; // check whether b is bigger than a
			for (int b = list.size() - 1; b > a; b--) {
				if (list.get(b).compareTo(list.get(b - 1)) > 0) {
					steps += 3; // If statement get two numbers, and also
								// compare
					steps += 2; // Initializes temp with list.get(b);
					steps += 4; // gets 2 numbers and sets them
					Comparable temp = list.get(b);
					list.set(b, list.get(b - 1));
					list.set(b - 1, temp);
				}
				steps++;
				// increment b
			}
			steps++; // increments a
		}
		System.out.println();
		System.out.println("Bubble Sort");
		System.out.println();
	}

	/**
	 * Description of the Method
	 *
	 * @param list
	 *            reference to an array of integers to be sorted
	 */
	public void selectionSort(ArrayList<Comparable> list) {
		steps++; // initialize a =
		steps++; // check whether a is smaller than size
		for (int a = 0; a < list.size(); a++) {
			steps += 2; // Initializes max with list.get(b);
			steps++; // initialize key
			Comparable max = list.get(a);
			int key = a;
			steps++; // initialize b =
			steps += 2; // check whether b is smaller than list.size() Also
						// list.size() is another step
			for (int b = a + 1; b < list.size(); b++) {
				if (list.get(b).compareTo(max) < 0) {
					steps += 3; // gets list.get(b), compares, check smaller
								// than 0

					steps += 3; // sets two variables into new values
					max = list.get(b);
					key = b;
				}
				steps++; // increment b
			}
			steps += 2; // Initializes temp with list.get(b);
			Comparable temp = list.get(a);

			steps += 4; // gets 2 numbers and sets them
			list.set(a, max);
			list.set(key, temp);

			steps++;
			// increment a
		}
		System.out.println();
		System.out.println("Selection Sort");
		System.out.println();
	}

	/**
	 * Description of the Method
	 *
	 * @param list
	 *            reference to an array of integers to be sorted
	 */
	public void insertionSort(ArrayList<Comparable> list) {
		// put into to ascending order
		for (int a = 1; a < list.size(); a++) {
			Comparable temp = list.get(a);
			int b = a - 1;
			while (b >= 0 && list.get(b).compareTo(temp) > 0) {
				list.set(b + 1, list.get(b));
				b--;

			}

			list.set(b + 1, temp);

		}

		// steps +=2; //intialize and check boundaries
		// for (int i = 1; i < list.size(); i++) {
		// //set comparble
		// steps+= 2;
		// Comparable temp = list.get(i);
		// //initialize j
		// steps++;
		// int j = i;
		// while (j > 0 && list.get(j - 1).compareTo(temp) < 0) {
		// steps+= 3; //while conditions
		// steps+= 2; //set j to previous
		// list.set(j, list.get(j-1));
		// j--;
		// steps++; //increment j
		// }
		// //set list to temp
		// steps+=2;
		// list.set(j, temp);
		// steps++; //increment i
		//
		// }
		//
		System.out.println();
		System.out.println("Insertion Sort");
		System.out.println();
	}

	/**
	 * Takes in entire vector, but will merge the following sections together:
	 * Left sublist from a[first]..a[mid], right sublist from a[mid+1]..a[last].
	 * Precondition: each sublist is already in ascending order
	 *
	 * @param a
	 *            reference to an array of integers to be sorted
	 * @param first
	 *            starting index of range of values to be sorted
	 * @param mid
	 *            midpoint index of range of values to be sorted
	 * @param last
	 *            last index of range of values to be sorted
	 */
	private void merge(ArrayList<Comparable> list, int first, int mid, int last) {
		steps += 3; // add one step for the initializng
		ArrayList temp = new ArrayList<Comparable>();
		int x = first; // pointer for arraylist a
		int y = mid + 1; // pint for arraylist b
		steps += 2; // add one step for checking while statements
		while (x <= mid && y <= last) {
			if (list.get(x).compareTo(list.get(y)) > 0) {
				steps += 1; // add one step for the if statement
				steps += 2; // add to list and increment
				temp.add(list.get(y));
				y++;
			} else {
				steps += 2; // add to list and increment
				temp.add(list.get(x));
				x++;
			}
		}
		steps += 1; // check condition
		while (y <= last) {
			steps += 2; // add to list and increment
			temp.add(list.get(y));
			y++;
		}
		steps += 1; // check condition
		while (x <= mid) {
			steps += 2; // add to list and increment
			temp.add(list.get(x));
			x++;
		}
		// use a for loop to set the list elements from the arraylsit
		steps += 2; // check condition
		for (int i = first; i <= last; i++) {
			steps += 2; // set stuff
			steps += 1; // increment i
			list.set(i, (Comparable) temp.get(i - first));
		}

	}

	/**
	 * Recursive mergesort of an array of integers
	 *
	 * @param a
	 *            reference to an array of integers to be sorted
	 * @param first
	 *            starting index of range of values to be sorted
	 * @param last
	 *            ending index of range of values to be sorted
	 * 
	 *            1234
	 */
	public void mergeSort(ArrayList<Comparable> list, int first, int last) {
		if (last - first == 0) {
			steps += 1; // add one step for the if statement
			return;
		} else if (last - first == 1) {
			steps += 1; // add one step for the if statement
			if (list.get(first).compareTo(list.get(last)) > 0) {
				steps += 1; // add one step for the if statement
				steps += 4; // add thre step for the switching
				Comparable temp = list.get(first);
				list.set(first, list.get(last));
				list.set(last, temp);
			}
		} else { // recursion, divide list into two halves
			steps += 1; // add one step for getting mid
			int mid = (first + last) / 2;
			mergeSort(list, first, mid);
			mergeSort(list, mid + 1, last);
			merge(list, first, mid, last);
		}
	}

	/**
	 * Accessor method to return the current value of steps
	 *
	 */
	public long getStepCount() {
		return steps;
	}

	/**
	 * Modifier method to set or reset the step count. Usually called prior to
	 * invocation of a sort method.
	 *
	 * @param stepCount
	 *            value assigned to steps
	 */
	public void setStepCount(long stepCount) {
		steps = stepCount;
	}

	/**
	 * Interchanges two elements in an ArrayList
	 *
	 * @param list
	 *            reference to an array of integers
	 * @param a
	 *            index of integer to be swapped
	 * @param b
	 *            index of integer to be swapped
	 */
	public void swap(ArrayList<Comparable> list, int a, int b) {
		// replace these lines with your code
		System.out.println();
		System.out.println("Swap");
		System.out.println();
	}
}