import java.util.ArrayList;

class RunMergeTemplate {

	public static void main(String[] args) {

		MergeTemplate testMerge = new MergeTemplate();

		ArrayList<Comparable> a;
		ArrayList<Comparable> b;

		System.out.println("Filling list a");
		a = testMerge.fillArray();

		System.out.println();

		System.out.println("Filling list b");
		b = testMerge.fillArray();

		ArrayList<Comparable> c = new ArrayList<Comparable>(a.size() + b.size());

		testMerge.selectionSort(a);  // You can use any sort here
		testMerge.selectionSort(b);  // You can use any sort here
		testMerge.merge(a,b,c);

		System.out.println();
		System.out.println("list a (sorted):  ");;
		testMerge.screenOutput(a);
		System.out.println();
		System.out.println();
		System.out.println("list b (sorted):  ");;
		testMerge.screenOutput(b);
		System.out.println();
		System.out.println();
		System.out.println("list c (a and b merged):  ");;
		testMerge.screenOutput(c);
		System.out.println();
	}
}