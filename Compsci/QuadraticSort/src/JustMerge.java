/*Name: Qin Liao
 * Period: 7
 * Date: Jan 15th
 * Reflection: Wow I am very surprised by how much more efficient
 * this sort is then the other sorts. In the smallest case it
 * was twice as efficient and the larger cases turned out 5 times more
 * efficient. Overall, the lab was not too hard given that we already
 * had the pseudo code. However, I spent about an hour and a half
 * . The reason being is that I had a small bug that I couldn't find
 * turns out. I initialized x = 0 instead of x = first. SO FRUSTRATING.
 * This one bug made my code work half the time(I don't know how).
 * but I think another reason was that the eclipse debugger
 * is much more complicated than the blueJ one and I'm still getting
 * used to using eclipse. Nonetheless, I really like this sorting
 * and because I debugged the steps by hand, in the end, I really
 * understood the concept. I think that is pretty important.
 */

private void merge(ArrayList<Comparable> list, int first, int mid, int last) {
		steps+= 3; //add one step for the initializng
		ArrayList temp = new ArrayList<Comparable>();
		int x = first; // pointer for arraylist a
		int y = mid + 1; // pint for arraylist b
		steps+= 2; //add one step for checking while statements
		while (x <= mid && y <= last) {
			if (list.get(x).compareTo(list.get(y)) > 0) {
				steps+= 1; //add one step for the if statement
				steps+= 2; //add to list and increment
				temp.add(list.get(y));
				y++;
			} else {
				steps+= 2; //add to list and increment
				temp.add(list.get(x));
				x++;
			}
		}
		steps+= 1; //check condition
		while (y <= last) {
			steps+= 2; //add to list and increment
			temp.add(list.get(y));
			y++;
		}
		steps+= 1; //check condition
		while (x <= mid) {
			steps+= 2; //add to list and increment
			temp.add(list.get(x));
			x++;
		}
		// use a for loop to set the list elements from the arraylsit
		steps+= 2; //check condition
		for (int i = first; i <= last; i++) {
			steps+= 2; //set stuff
			steps+= 1; //increment i
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
			steps+= 1; //add one step for the if statement
			return;
		} 
		else if(last - first == 1 ){
			steps+= 1; //add one step for the if statement
			if(list.get(first).compareTo(list.get(last)) > 0){
				steps+= 1; //add one step for the if statement
				steps+= 4; //add thre step for the switching
				Comparable temp = list.get(first);
				list.set(first, list.get(last));
			 	list.set(last, temp);
			}
		}
		else { // recursion, divide list into two halves
			steps+= 1; //add one step for getting mid
			int mid = (first + last) / 2;
			mergeSort(list, first, mid);
			mergeSort(list, mid + 1, last);
			merge(list, first, mid, last);
		}
	}
