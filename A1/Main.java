/* Name: Julia Rieger 
* File: Main.java 
* Desc: 
* 
* The main driver program for Assignment 1. 
*
* This program reads the file specified by the first command line 
* argument, counts the number of words, spaces, and characters and 
* displays the results in the format specified in the project description. 
* 
*/


import java.io.*;
import java.util.*;
 
/* Class: Main.java 
* Desc: 
* 
* The driver program for Assignment 1.
* 
*/
public class Main {

    public static void main(String[] args) {

	try {
	    Scanner input = new Scanner(System.in);

	    System.out.print("zipcode: ");
	    String userInput = input.nextLine();

	    while(true) { //continuously prompt user for zipcode
	    
		if(userInput.compareTo("00000") == 0) { //if userInput is 00000 stop process
		    break;
		}

		//if cannot find zip print error message
		if(LookupZip.lookupZip(LookupZip.readZipCodes("uszipcodes.csv"), userInput) == null) {
		    System.out.println("No such zipcode");
		}
		else { //if can find zip print object
		System.out.println(LookupZip.lookupZip(LookupZip.readZipCodes("uszipcodes.csv"), userInput)); //prints city,state
		}
		
		System.out.println("zipcode: "); //prompt user
		userInput = input.nextLine();
	    }

	    System.out.println("Good Bye!");
	    input.close();
	}

	catch(FileNotFoundException e) {
	    System.out.print("File not found");
	}
    }
}

/* Class: Place.java 
* Desc: 
* 
* Models each zipcode to contain zip, town, and state.
* 
*/
class Place {

    private int zip;
    private String town;
    private String state;
    
    /** Creates a Place with the given zip, town name, and state
     * @param zip The 5-digit zip code
     * @param town The town name
     * @param state The state abbreviation
     */
    public Place(int zip, String town, String state) {

	this.zip = zip;
	this.town = town;
	this.state = state;
	
    }

    public int getZip() {

	return this.zip;
	
    }

    public String getTown() {

	return this.town;
	
    }

    public String getState() {

	return this.state;
	
    }

    public String toString() { //overriding the toString() method

	return this.town + ", " + this.state;
    }
}

/* Class: LookupZip.java
* Desc: 
* 
* Contains several public static methods to implement the main part of the assignment.
* 
*/

class LookupZip {

    /** Parses one line of input by creating a Place that
     * denotes the information in the given line
     * @param lineNumber The line number of this line
     * @param line One line from the zipcodes file
     * @return A Place that contains the relevant information
     * (zip code, town, state) from that line
     */
    public static Place parseLine(int lineNumber, String line) {

	 //create array containing each csv
      	 String[] tokens = line.split(",");

	 //assign values from tokens necessary to create a Place object 
	 int zip = Integer.parseInt(tokens[0]);
	 String town = tokens[1];
	 String state = tokens[2];

	 //create temp Place object to return
	 Place temp = new Place(zip, town, state);
       	 return temp;
    }
   
    /** Reads a zipcodes file, parsing every line
     * @param filename The name of the zipcodes file
     * @return The array of Places representing all the
     * data in the file.
     */
    public static Place[] readZipCodes(String filename) throws FileNotFoundException {
	
	//Creates new Scanner object linked to filename
	Scanner input = new Scanner(new File(filename));

	//parse first line and store num of zips (first int in file)
	String firstLine = input.nextLine();
     	String[] tokens = firstLine.split(",");
        int numOfZips = Integer.parseInt(tokens[0]);
	
	//create Place[] array to be returned
	Place[] zipcodes = new Place[numOfZips];
	
	//store line with column leaders so they wont be in array
	String throwAwayLine = input.nextLine();
	
	//populate zipcodes array with Place objects created from parsed file lines
	for(int i = 0; i < numOfZips-1; i++) {
	    //create String of next line in file for parseLine arguement
	    String line = input.nextLine();

	    //call parseLine
	    zipcodes[i] = parseLine(i, line);
	}

	//close Scanner
	input.close();
	
	return zipcodes;
    }
    
    /** Find a Place with a given zip code
     * @param places The array of Place objects with every place in file
     * @param zip The zip code (as a String) to look up
     * @return A place that matches the given zip code,
     * or null if no such place exists.
     */
    public static Place lookupZip(Place[] places, String zip) {
	//parse zip as int
	int zipcode = Integer.parseInt(zip);

	//set low, high, mid variables (first, last, mid indecies)
	int low = 0;
	int high = places.length - 1;
	int mid = high/2;
	
	//binary search
	while(low != high) {
	    mid = (low + (high+1))/2;
	    if(zipcode == places[mid].getZip()) { //found matching object
		    return places[mid];
	    }

	    else if(zipcode > places[mid].getZip()) { //zip may be on the right side
		low = mid + 1;
	    }

	    else { //zip may be on the left side of array
		high = mid - 1;
	    }
	}  
	//if cannot find zip in array
	return null;
    }
}

