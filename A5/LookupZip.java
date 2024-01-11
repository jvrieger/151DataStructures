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

    public static final int ZIP = 0; //zipcode is order 0 in csv line
    public static final int TOWN = 1; //town is order 1 in csv line
    public static final int STATE = 2; //state is order 2 in csv line
    public static final int POPULATION = 3; //pop is order 3 in csv line
    public static final int LATITUDE = 5; //lat is order 5 in csv line
    public static final int LONGITUDE = 6; //long is order 6 in csv line
    public static final int TOWN2 = 2; //town is order 2 in csv2 line
    public static final int STATE2 = 3; //state is order 3 in csv2 line

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
	     //return Place object
	     int zip = Integer.parseInt(tokens[ZIP].substring(1, 6));
	     String town = tokens[TOWN2].substring(1, tokens[TOWN2].length()-1).toLowerCase();
	     town = town.substring(0,1).toUpperCase() + town.substring(1);
	     String state = tokens[STATE2].substring(1, 3);
	     
	     Place temp = new Place(zip, town, state);
	     return temp;
	 }

	 //if location info is present
	 else {
	     //return LocatedPlace object
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
	 
    /** Binary searches through ArrayList to see if a matching object exists in it
     * @param ArrayList<Place> places list of all places added so far
     * @param Place target object looking through list for
     * @return true if the target zip is not yet in places
     */
     public static boolean noZip(ArrayList<Place> places, Place target) {
	 //set low, high, mid variables (first, last, mid indecies)
	 int low = 0;
	 int high = places.size() - 1;
	
	 //binary search
	 while (low <= high) {
	     int mid = low + (high - low) / 2; //mid of list for binary search

	     if (places.get(mid).compareTo(target) == 0) { //found matching object
		return false;
	     }

	     if (places.get(mid).compareTo(target) == -1) { //zip may be on the right side
		 low = mid + 1;
	     }

	     else { //zip may be on the left side of array
		 high = mid - 1;
	     }
	 }  
	 //if cannot find zip in array
	 return false;
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
        input1.nextLine();
	
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

	//skip line of column headers so they wont be in ArrayList
        input2.nextLine();

	//currently array is filled with Places and PopulatedPlaces
	//fill in the gaps in places: lat/longs and some new entries
	while(input2.hasNextLine() == true) {
	    //create String of next line in file for parseLineFile2 arguement
	    String line = input2.nextLine();
	    
	    //if places does not have a Place with this zip
	    if (noZip(places, parseLineFile2(line))) {
	        //add either Place or LocatedPlace object to array, final form
		places.add(parseLineFile2(line));
	    }

	    //if found Place should be LocatedPlace, change to LocatedPlace
	    else if (parseLineFile2(line) instanceof LocatedPlace) {
		//find LocatedPlace object in places by seraching ArrayList
		//binary search
		int low = 0;
		int high = places.size()-1;
		LocatedPlace i = (LocatedPlace)parseLineFile2(line);

		while (low <= high) {
		    int mid = low + (high - low) / 2; //mid of list for binary search
		    
		    if (places.get(mid).compareTo(i) == 0) { //found matching object
			
		        if (places.get(mid) instanceof PopulatedPlace) { //this place is Populated
			    
			    //update PopulatedPlace object with location info
			    places.get(mid).setLat(i.getLat()); //set found PopulatedPlace's lat
			    places.get(mid).setLong(i.getLong()); //set found PopulatedPlace's longitude
			}
			
			else {
			    //Replace Place object with LocatedPlace object
			    places.set(places.indexOf(places.get(mid)), new LocatedPlace(i.getZip(), i.getCity(), i.getState(), (i).getLat(), (i).getLong()));
			}
		    }

		    if (places.get(mid).compareTo(i) == -1) { //zip may be on the right side
			low = mid + 1;
		    }

		    else { //zip may be on the left side of array
			high = mid - 1;
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

