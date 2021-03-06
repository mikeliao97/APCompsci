import java.io.File;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;



/*Name: Qin Liao
 * Period: 7
 * Reflection: This lab was actually a lot harder than I thought it would be.
 * One of my main problems that I struggled with was understanding comparable.
 *I set mergeSort and Everything to Comparable because I assumed
 * that you can use it to compare both Strings as well as Objects. However
 * I recieved an hour. Unfortunately you cannot do that (I think). My strategy
 * was decent but too long-winded. First I put the tokens into an arrayList called list
 * .I would sort the list in alphabetically. I used Collections.sort() to
 * save time and avoid the use of another method but you could just as easily use
 * bubble sort, insertion, whatever. Now with this alphabetically sorted array,
 * I dumped it into an arrayList called frequency. I ran a loop to record the number
 * of times the alphabetical and consecutive words came up. I wrote another class
 * called Word that records the frequency(int) and also the word itself(String).
 * By implementing comparable and using compareTo I compared the frequency of the
 * words using merge Sort. Lastly I printed the words out in a loop.
 * 
 */

public class CountWords {
	
	public static void main(String[] args){
		CountWords driver = new CountWords();
		System.out.println("Loading File...");
		driver.loadFile("dream.txt");
		driver.countFrequency();
		driver.sortFrequency();
		driver.printFile();
	}
	
	//Two Lists
	ArrayList<Comparable> list = new ArrayList<Comparable>();
	ArrayList<Word> frequency = new ArrayList<Word>();
	

	private void loadFile(String inFileName) {
		// Your code here
		Scanner in = null;
		try {
			in = new Scanner(new File(inFileName));
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}
		// while in has next put the files into an String object
		while (in.hasNext()) {
			String temp = in.nextLine();
			temp = cutWords(temp.toLowerCase());
			StringTokenizer t = new StringTokenizer(temp);
			while (t.hasMoreTokens()) {
				String token = t.nextToken();
					list.add(token);
			}
		}
		Collections.sort(list);
		System.out.println(list);
	}
	
	
	private String cutWords(String str){
		String temp = "";	
		String punctuation = ".;!,?";
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '"'){
				temp += " ";
			}
			else if(punctuation.contains("" + str.charAt(i))){
				temp += " ";
			}
			else if(str.charAt(i) == '-' && !(Character.isLetterOrDigit(str.charAt(i + 1)))){
				temp += " ";
			}
			else{
				temp += str.charAt(i);
			}
		}
		return temp;
	}
	
	
	private void countFrequency() {
		// At tthis point list should be sorted
		for (int i = 0; i < list.size(); i++) {
			String word = (String) list.get(i);
			int count = 1;
			while (i < (list.size() - 1) && list.get(i).equals(list.get(i + 1))) {
				i++;
				count++;
			}
			Word newWord = new Word(count, word);
			frequency.add(newWord);
		}
		System.out.print(frequency);
	}	
	
	private void printFile() {
		System.out.println();
		for (int i = 0; i < frequency.size(); i++) {
			System.out.print(frequency.get(i).getWord());
			System.out.println(" " + frequency.get(i).getFreq());
		}
		
		int totalWords = 0;
		for (int j = 0; j < frequency.size(); j++) {
			totalWords += frequency.get(j).getFreq();
		}
		
		System.out.println("Number of Unique Words: " + frequency.size());
		System.out.println("Total Number of Words: " + totalWords);

	}

	private void merge(ArrayList<Word> frequency, int first, int mid, int last) {
		ArrayList<Word> temp = new ArrayList<Word>();
		int x = first; // pointer for arraylist a
		int y = mid + 1; // pint for arraylist b
		while (x <= mid && y <= last) {
			if (((Word) frequency.get(x)).compareTo((Word)frequency.get(y)) < 0) {
				temp.add((Word) frequency.get(y));
				y++;
			} else {
				temp.add((Word)frequency.get(x));
				x++;
			}
		}
		while (y <= last) {
			temp.add((Word)frequency.get(y));
			y++;
		}
		while (x <= mid) {
			temp.add((Word)frequency.get(x));
			x++;
		}
		// use a for loop to set the list elements from the arraylsit
		for (int i = first; i <= last; i++) {
			frequency.set(i, (Word) temp.get(i - first));
		}

	}

	/**
	 * Recursive mergesort of an ArrayList of Strings
	 *
	 * @param a
	 *            reference to an ArrayList of Strings to be sorted
	 * @param first
	 *            starting index of range of values to be sorted
	 * @param last
	 *            ending index of range of values to be sorted
	 */
	public void mergeSort(ArrayList<Word> frequency, int first, int last) {
		// Your code here
		if (last - first == 0) {
			return;
		} else { // recursion, divide list into two halves
			int mid = (first + last) / 2;
			mergeSort(frequency, first, mid);
			mergeSort(frequency, mid + 1, last);
			merge(frequency, first, mid, last);
		}
	}

	public void sortFrequency() {
		// Make a single call to mergeSort to get sorting going
		// (If your mergeSort is broken, then use a quadratic sort)
		mergeSort(frequency, 0, frequency.size() - 1);
	}
}
