/* Name: Julia Rieger 
* File: LookupName.java 
* Desc: 
* 
* Posesses methods to read file, manipulate data, create
* data structures and populate them, and sort through data
* 
*/
import java.io.*;
import java.util.*;

public class LookupName {

    public static final int RANK = 0; //index for rank
    public static final int MALE_NAME = 1; //index for male name
    public static final int MALE_NUMBER = 2; //index for male number
    public static final int FEMALE_NAME = 3; //index for female name
    public static final int FEMALE_NUMBER = 4; //index for female number

    /**
     * parses one line from csv files for A3 to
     * seperate information and make it usable
     * @param line a single line from csv
     * @param isFemale to flag the sex to return
     * @return Name object to be added to linked list
     */
    public static Name parseLine(String line, boolean isFemale) {

	//create array containing each csv
	String[] tokens = line.split(",");

	int rank = Integer.parseInt(tokens[RANK]); //rank
	String maleName = tokens[MALE_NAME]; //male name
	int maleNumber = Integer.parseInt(tokens[MALE_NUMBER]); //male number
	String femaleName = tokens[FEMALE_NAME]; //female name
	int femaleNumber = Integer.parseInt(tokens[FEMALE_NUMBER]); //female number
	ArrayList<YearlyStatistics> list = new ArrayList<YearlyStatistics>(); //holds yearly stats for each name
	//return female Name
	if (isFemale) {
	    Name female = new Name(femaleName, rank, femaleNumber, list);
	    return female;
	}
	//return male Name
	else {
	    Name male = new Name(maleName, rank, maleNumber, list);
	    return male;
	}
    }
    
    /**
     * reads in one of the year files and adds 
     * every name to either female or male DLL
     * @param filename to be read with Scanner class and parsed
     * @param names DLL to hold all names of certain sex from files
     * @param isFemale is a flag passed from main to indicate sex
     * @return NameDLL
     */
    public static NameDLL readNameFile(String filename, NameDLL names, boolean isFemale) throws FileNotFoundException {
		
	//creates new Scanner object linked to filename
	Scanner input = new Scanner(new File(filename));
	int year = Integer.parseInt(filename.substring((filename.length()-8),(filename.length()-4)));
	//populate names with Name obhects created from parsed file lines
	while(input.hasNextLine() == true) {
	    
	    //create String of next line in file for parseLine arguement
	    String line = input.nextLine();
	    
	    Name current = new Name("",0,0, null); //create empty Name object to be added to list
	    current = parseLine(line, isFemale); //add values from line above
	    
	    //create new yearlyStats object and add to arrayList in current
	    YearlyStatistics currentStats = new YearlyStatistics(current.getName(), year, current.getRank(), current.getNumber());
	    
	    //if name is not in list
	    if (names.find(current) == -1) {
		//add current stats and totals to new object
		current.addToList(currentStats);
		current.incrementTotalRank(current.getRank());
		current.incrementTotalNumber(current.getNumber());
		//insert name alphabetically
	        names.insertSorted(current);
	    }
	    //if name is already in list
	    else {
		//add orig arrayList
		names.transferOriginalArrayList(current);
		current.addToList(currentStats); //update newest info
		current.incrementTotalRank(current.getRank());
		current.incrementTotalNumber(current.getNumber());
	        //update info
		names.updateList(current);
	    }
	}

	//close Scanner
	input.close();
	
	return names;
    }
}
