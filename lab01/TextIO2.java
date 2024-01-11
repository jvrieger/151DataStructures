import java.io.*;
import java.util.*;

public class TextIO2 {
    public static void main(String[] args) throws FileNotFoundException{
	String inFileName = "LiamNeeson.txt";
	Scanner input;
	String line;
	
	// Create a new Scanner for the input file
	input = new Scanner(new File(inFileName));
	
	while (input.hasNextLine()) { // test if there is a line to read
	    // read the next line
	    line = input.nextLine();
	    
	    // output it to Console
	    System.out.println(line);
	}

	// Close the input stream
	input.close();
    }
}


