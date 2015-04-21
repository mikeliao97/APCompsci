import java.util.ArrayList;
import java.util.Arrays;


public class InsertionSort {

	public static void main(String[] args){
		InsertionSort lab = new InsertionSort();
		ArrayList list = new ArrayList<Integer>();
		list.add(24);
		list.add(1);
		list.add(22);
		list.add(3233);
		list.add(43);
		list.add(656);
		list.add(3);
		list.add(0);
		lab.InsertionSort(list);
		System.out.print(list);
	
}

void insertionSort(ArrayList<Integer> list){
	 
	for(int a = 1; a < list.size(); a++){
		int temp = (Integer) list.get(a);
		int pos = a;
		while(a > 0 && (Integer)list.get(a - 1) > temp){
			list.set((Integer)list.get(a),(Integer)list.get(a -1));
			a--;
		
		}
		list.set((Integer)list.get(a), temp);
		
	}
	 
}
void SelectionSort(int[] list){
	 
	for(int a = 0; a < list.length; a++){
		int max = list[a];
		int key = a;
		for(int b = a + 1; b < list.length; b++){
		    if(list[b] < max){
		      max = list[b];
		      key = b;
		    }
			
		}
		int temp = list[a];
		list[a] = max;
		list[key] = temp;
		
	}
		
		
	}
	 
}



