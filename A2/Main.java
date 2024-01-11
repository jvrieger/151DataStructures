/* Name: Julia Rieger
* File: Main.java 
* Desc: 
* 
* The main driver program for Assignment 2. 
*
* Continuously prompts user for zipcode entry
* and uses classes Place, LocatedPlace, PopulatedPlace
* to return as much information as possible (from dataset) 
*/

import java.io.*;
import java.util.*;

public class Main {

    public static boolean isInteger(String input) {

	try { //try to make input into int
	    Integer.parseInt(input);
	    return true;
	}
	catch(Exception e) {
	    return false;
	}
    }

    public static void main(String[] args) {


	try {

	    //read file once
	    ArrayList<Place> places = LookupZip.readZipCodes("uszipcodes.csv", "ziplocs.csv");

	    Scanner input = new Scanner(System.in);

	    System.out.print("zipcode: ");
	    String userInput = input.next();
	    
	    while(true) { //continuously prompt user for zipcode
	    
		if(userInput.compareTo("00000") == 0) { //if userInput is 00000 stop process
		    break;
		}

		if(!isInteger(userInput)) {
		    System.out.println("No such zipcode");
		}
		
		//if cannot find zip print error message
		else if(LookupZip.lookupZip(places, userInput) == null) {
		    System.out.println("No such zipcode");
		}

		else { //if can find zip print object
		System.out.println(LookupZip.lookupZip(places, userInput)); //prints city,state
		}
		
		System.out.print("zipcode: "); //prompt user
		userInput = input.next();
	    }

	    System.out.println("Good Bye!");
	    input.close();
	}

	catch(FileNotFoundException e) {
	    System.out.print("File not found");
	}
    }
}
