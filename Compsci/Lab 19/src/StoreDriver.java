/*
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






import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StoreDriver {

	public static void main(String[] args) {
		Item bubble = new Item(12, 10);
		Item dummy = new Item(10, 10);
		System.out.print(dummy.compareTo(bubble));
		
		
		Store s = new Store("file50.txt");
		s.sort();
		s.displayStore();
	}
}

