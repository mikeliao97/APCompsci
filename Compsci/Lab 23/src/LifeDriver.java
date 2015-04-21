
public class LifeDriver {
	public static void main(String[] args){
		
		LifeQLiaoPeriod7Life driver = new LifeQLiaoPeriod7Life("life100.txt");
		driver.printBoard();
		driver.runLife(1);
		driver.printBoard();
			
	}
}
