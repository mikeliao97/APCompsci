/*Name:Qin Liao 
 * Date: Jan 12th
 * Period: 7
 * Reflection: This lab was pretty easy. I did not reallly have trouble with any
 * of the parts. Except in the beginning it was really weird because I was getting
 * this error: "heap memory overflow". Basically I was using too much memory.
 * I simplified few of my steps by removing an extra comparable arraylist and
 * restarting my computer and this ended up solving my problem. Otherwise, This 
 * lab was really easy. I looked forward to the next part. Recursive mergesort
 * and started playing around with that. I really don't know what else to reflect on
 * given that 2/4 of the section were code that was given. 1/4 was code on the final
 * and most of this was really easy. My thought process with the part that merged
 * the two arraylists was to use three while loops. Basically compare them until
 * one of the arraylists run out. And then use the next two while loops to catch
 * the remaining elements in the arraylists that are left over. This ended up working
 * so I was pretty happy.
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



import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class MergeTemplateQLiaoPeriod7 {

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

	/**
	 * Write a merge method to merge two sorted lists.
	 *
	 * Preconditions: Lists A and B are sorted in nondecreasing order. Action:
	 * Lists A and B are merged into one list, C. Postcondition: List C contains
	 * all the values from Lists A and B, in nondecreasing order.
	 */
	public void merge(ArrayList<Comparable> a, ArrayList<Comparable> b,
			ArrayList<Comparable> c) {
		int x = 0; // pointer for arraylist a
		int y = 0; // pint for arraylist b
		while (x < a.size() && y < b.size()) {
			if (a.get(x).compareTo(b.get(y)) > 0) {
				c.add(b.get(y));
				y++;
			} else {
				c.add(a.get(x));
				x++;
			}
		}
		while (x < a.size()) {
			c.add(a.get(x));
			x++;
		}

		while (y < b.size()) {
			c.add(b.get(y));
			y++;
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
