import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MergeTemplate {

	/**
	 * Sorts any ArrayList of Comparable objects using Selection Sort.
	 *
	 * @param list
	 *            reference to an array of integers to be sorted
	 */
	public void selectionSort(ArrayList<Comparable> list) {
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

	//sort it in descending order
		void mergeSort(ArrayList <Comparable> list, int first, int last) {
			if (last - first == 0){
			} else if (last - first == 1){
				if(list.get(first).compareTo(list.get(last)) > 0 ){ //if the first value is bigger than the second 
				  Comparable temp = list.get(first);
				  list.set(first, list.get(last));
				  list.set(last, temp);
				}
			}else{ // recursion, divide list into two halves
				int mid = (first + last) /2; 
				mergeSort(list, first, mid);
				mergeSort(list, mid + 1, last);
				merge(list, first, mid, last);
			} 
		}
		
		/* will merge the two sorted sublists within A into
		one continuous sublist from A[first] .. A[last].
		The left list ranges from A[first]..A[mid]. The
		right list ranges from A[mid+1]..A[last].
		*/
		void merge (ArrayList<Comparable> list, int first, int mid, int last){
			ArrayList temp = new ArrayList<Comparable>();
			int x = 0; //pointer for arraylist a 
			int y = mid + 1; // pint for arraylist b
			while(x < mid + 1 && y < last){
				 if(list.get(x).compareTo(list.get(y)) > 0){
					temp.add(list.get(y));
					y++;
				}
				else{
					temp.add(list.get(x));
					x++;
				}
			}
			
			while (y < last) {
				temp.add(list.get(y));
				y++;
			}
			while (x < mid + 1) {
				temp.add(list.get(x));
				x++;		
			}
			//use a for loop to set the list elements from the arraylsit
			for (int i = first; i < last; i++) {
				list.set(i, (Comparable) temp.get(first + i));
			}
			
			
		}

	/**
	 * Write a method to - Ask the user how many numbers to generate - Ask the
	 * user to enter the largest integer to generate - Initialize an ArrayList
	 * of random Integers from 1 to largestInt - Return the ArrayList
	 *
	 * @return an ArrayList of size specified by the user filled with random
	 *         numbers
	 */
	public ArrayList<Comparable> fillArray() {
		Scanner console = new Scanner(System.in);
		ArrayList myArray = new ArrayList<Comparable>();

		System.out.print("How many numbers do you wish to generate? ");
		int numInts = console.nextInt();
		System.out.print("Largest integer to generate? ");
		int largestInt = console.nextInt();

		Random randGen = new Random();
		myArray = new ArrayList<Comparable>();

		for (int loop = 0; loop < numInts; loop++) {
			Integer x = new Integer(randGen.nextInt(largestInt) + 1);
			myArray.add(x);
		}
		return myArray;
	}

	/**
	 * Write a method to print out the contents of the ArrayList in tabular
	 * form, 20 columns. You can use the \t escape character or use printf to
	 * format using fields.
	 */
	public void screenOutput(ArrayList<Comparable> myArray) {
//		for (int loop = 0; loop < myArray.size(); loop++) {
//			if (loop % 20 == 0) {
//				System.out.println();
//			}
//			System.out.print(myArray.get(loop) + "\t");
//		}
//		System.out.println();
		
		for (int loop = 0; loop < myArray.size(); loop++) {
			if (loop % 20 == 0) {
				System.out.println();
			}
			System.out.printf("%-5s", myArray.get(loop));
		}
		System.out.println();
		
	}
}
