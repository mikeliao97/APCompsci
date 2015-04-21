import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Encryptor {
	
	public static void main(String[] args) throws IOException{
		//variable declarations
		String input;
		String output;
		String privateKey;
		int numOfChars;
		String tempString = "";
		
		//initailzie scanner for input
		Scanner in = new Scanner(System.in);
		
		//get inputs 
		System.out.println("Enter a file to Encrypt:");
		input = in.next();
	
		
		System.out.println("Enter a file to output:");
		output = in.next();
		
		System.out.println("Enter a private key");
		privateKey = in.next();
		
		//Formatting
		System.out.println("");
		
		//Create a new File
		File inputFile = new File(input);
		File outputFile = new File(output);
						
		
		//declare buffer
		char[] buffer = new char[1024];
				
		//new filerReader for the file that user wants o readr
		FileReader reader = new FileReader(inputFile);
		
		//the number of characters to loop through
		numOfChars = reader.read(buffer);
		
		//Now loop through the whole thing and add it to tempString;
		for(int a = 0; a < numOfChars; a++){			
			tempString += buffer[a];			
		}
			
		//close the reader
		reader.close();
		
		System.out.println("Before I write: The temp String is" + tempString) ;
		
		///NOw loop through and XOR to each character
		FileWriter writer = new FileWriter(outputFile);
		
		for(int a = 0; a < numOfChars; a++){			
			char before = tempString.charAt(a);
			
			char charKey = privateKey.charAt(a % privateKey.length());
			
			char after = (char) (before ^ charKey);
			
			writer.write(after);			
			
		}
		
		writer.close(); //close the writer
		
		
	}
	

	
}
