/* Name: Julia Rieger 
* File: LookupZip.java 
* Desc: 
* 
* Posesses methods to read file, manipulate data, create
* data structures and populate them, and sort through data
* 
*/

import java.util.*;
import java.io.*;
import java.lang.Number;

class LookupZip {

    public static final int ZIP = 0;
    public static final int TOWN = 1;
    public static final int STATE = 2;
    public static final int POPULATION = 3;
    public static final int LATITUDE = 5;
    public static final int LONGITUDE = 6;
    public static final int TOWN2 = 2;
    public static final int STATE2 = 3;

    /** Parses one line of input by creating a Place or PopulatedPlace that
     * denotes the information in the given line (depending if there is a population)
     * @param line One line from the zipcodes file
     * @return A Place that contains the relevant information
     * from that line
     */
    public static Place parseLineFile1(String line) {

	//create array containing each csv
	String[] tokens = line.split(",", 5);

	//if the line has no population create a Place (this could be a Place or LocatedPlace)
	if(tokens[POPULATION].compareTo("") == 0) {

	    //assign values from tokens necessary to create an instance of Place
	    int zip = Integer.parseInt(tokens[ZIP]);
	    String town = tokens[TOWN];
	    String state = tokens[STATE];
	
	    //create and return Place object
	    Place temp = new Place(zip, town, state);
	    return temp;
	}
	//if the line does have a population create a PopulatedPlace
        else {
	    //assign values from tokens necessary to create an instance of PopulatedPlace 
	    int zip = Integer.parseInt(tokens[ZIP]);
	    String town = tokens[TOWN];
	    String state = tokens[STATE];
	    double latitude = 0.0;
	    double longitude = 0.0;
	    int population = Integer.parseInt(tokens[POPULATION]);

	    //create and return PopulatedPlace object
	    PopulatedPlace temp = new PopulatedPlace(zip, town, state, latitude, longitude, population);
	    return temp;
	}
    }

      /** Parses one line of input by creating a Place that
     * denotes the information in the given line
     * @param line One line from the zipcodes file
     * @return A Place that contains the relevant information
     * (zip code, town, state) from that line
     */
    public static Place parseLineFile2(String line) {

	 //create array containing each csv
         String[] tokens = line.split(",");

	 //if location info is not present
	 if (tokens[LATITUDE].compareTo("") == 0) {
	     int zip = Integer.parseInt(tokens[ZIP].substring(1, 6));
	     String town = tokens[TOWN2].substring(1, tokens[TOWN2].length()-1).toLowerCase();
	     town = town.substring(0,1).toUpperCase() + town.substring(1);
	     String state = tokens[STATE2].substring(1, 3);
	     
	     Place temp = new Place(zip, town, state);
	     return temp;
	 }

	 //if location info is present
	 else {
	     //create LocatedPlace object
	     int zip = Integer.parseInt(tokens[ZIP].substring(1, 6));
	     String town = tokens[TOWN2].substring(1, tokens[TOWN2].length()-1).toLowerCase();
	     town = town.substring(0,1).toUpperCase() + town.substring(1);
	     String state = tokens[STATE2].substring(1, 3);
	     double latitude = Double.parseDouble(tokens[LATITUDE]);
	     double longitude = Double.parseDouble(tokens[LONGITUDE]);

	     LocatedPlace temp = new LocatedPlace(zip, town, state, latitude, longitude);
	     return temp;
	 }
    }
	 
    /** Looks through ArrayList to see if a matching object exists in it
     * @param ArrayList<Place> places list of all places added so far
     * @param Place target object looking through list for
     * @return true if the target zip is not yet in places
     */
     public static boolean noZip(ArrayList<Place> places, Place target) {
	 for(Place i : places) { //for each Place in places
	     if (i.getZip() == target.getZip()) { //if the zip of the current Place = target
		 return false; //there is a matching zip in places
	     }
	 }

	 return true;
     }

    /** Reads a zipcodes file, parsing every line
     * @param filename1 The name of the first zipcodes file
     * @param filename2 The name of the second zipcodes file
     * @return The ArrayList of Places representing all the
     * data in the file.
     */
    public static ArrayList<Place> readZipCodes(String filename1, String filename2) throws FileNotFoundException {
	
	//Creates new Scanner object linked to filename1
	Scanner input1 = new Scanner(new File(filename1));

	//create places ArrayList to hold all places from files and to be returned
	ArrayList<Place> places = new ArrayList<Place>();
	
	//store line with column headers so they wont be in ArrayList
		String throwAwayLine = input1.nextLine();
	
	//populate places with Place/PopulatedPlace objects created from parsed file lines
	while(input1.hasNextLine() == true) {
	    //create String of next line in file for parseLineFile1 arguement
	    String line = input1.nextLine();

	    //call parseLineFile1
	    places.add(parseLineFile1(line));
	}
	
	//close Scanner1
	input1.close();

	//Creates new Scanner object lined to filename2
	Scanner input2 = new Scanner(new File(filename2));

	//store line with column headers so they wont be in ArrayList
        input2.nextLine();
	
	//fill in the gaps in places: lat/longs and some new entries
	while(input2.hasNextLine() == true) {
	    //create String of next line in file for parseLineFile2 arguement
	    String line = input2.nextLine();

	    //create int of new Place object's zip num for future use
	    int targetZip = parseLineFile2(line).getZip();
	    
	    //if places does not have a Place with this zip
	    if (noZip(places, parseLineFile2(line))) {
	        //add object
		places.add(parseLineFile2(line));
	    }
	    
	    //if found Place should be LocatedPlace
	    
	    else if (parseLineFile2(line) instanceof LocatedPlace) {
		//find LocatedPlace object in places by iterating through ArrayList
		for(Place i : places) {
		    //make LocatedPlace make first three parameters same as Place
		    //Place.get for first three attributes
		    if(i instanceof PopulatedPlace) {
			if(i.getZip() == targetZip) {
			    //found matching, replace PopulatedPlace object with completed PopulatedPlace object (add location info)
			    places.set(places.indexOf(i), new PopulatedPlace(i.getZip(), i.getCity(), i.getState(), ((LocatedPlace) parseLineFile2(line)).getLat(), ((LocatedPlace) parseLineFile2(line)).getLong(), ((PopulatedPlace) i).getPop()));
			}
		    }
		    
		    else if (i.getZip() == targetZip) {
			//found matching, replace Place object with LocatedPlace object
			places.set(places.indexOf(i), new LocatedPlace(i.getZip(), i.getCity(), i.getState(), ((LocatedPlace) parseLineFile2(line)).getLat(), ((LocatedPlace) parseLineFile2(line)).getLong()));
		    }	
		}
	    }

	    //if there is no location info available (remain Place object), do nothing
  
	}

	//close Scanner2 and return completed ArrayList
	input2.close();
	
	return places;
    }
    
    /** Find a Place with a given zip code
     * @param places The array of Place objects with every place in file
     * @param zip The zip code (as a String) to look up
     * @return A place that matches the given zip code,
     * or null if no such place exists.
     */
    public static Place lookupZip(ArrayList<Place> places, String zip) {
	//parse zip as int
	int zipcode = Integer.parseInt(zip);

	//set low, high, mid variables (first, last, mid indecies)
	int low = 0;
	int high = places.size() - 1;
	
	//binary search
	while(low <= high) {
	    int mid = low + (high - low) / 2; //mid of list for binary search
	    int thisZip = (places.get(mid)).getZip(); //store zip of current bookmarked Place

	    if(zipcode == thisZip) { //found matching object
		return places.get(mid);
	    }

	    if(zipcode > thisZip) { //zip may be on the right side
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

