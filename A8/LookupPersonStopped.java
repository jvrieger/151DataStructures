/* Name: Julia Rieger
 * File: LookupPersonStopped.java
 * Desc:
 *
 * This class contains methods to work with the Main to
 * read in files to create methods of deduplicating csvs, stores full data set
 */

import java.io.*;
import java.util.*;

public class LookupPersonStopped {

    public static final int SEX_INDEX = 80; //index of sex in 2011.csv
    public static final int RACE_INDEX = 81;//index of race in 2011.csv
    public static final int DOB_INDEX = 82;//index of date of birth in 2011.csv
    public static final int HAIR_INDEX = 87;//index of hairColor in 2011.csv
    public static final int EYE_INDEX = 88;//index of eyeColor in 2011.csv
    public static final int CAPACITY = 1000003;//size of hashmap
    private ArrayList<PersonStopped> fullDataSet;

    public LookupPersonStopped(String filename) throws FileNotFoundException {
	this.fullDataSet = new ArrayList<PersonStopped>();
	Scanner input = new Scanner(new File(filename)); //create Scanner object for filename
	input.nextLine(); //call nextLine to pass header line

	//call parseLine on each line to add to fullDataSet
	while(input.hasNextLine()) {
	    String line = input.nextLine(); //create String of next line in file for parseLine arg
	    this.fullDataSet.add(parseLine(line)); //parse line into PersonStopped object and add to array
	}
	
	input.close(); //close Scanner named input
    }

    public ArrayList<PersonStopped> getFullDataSet() {
	return this.fullDataSet;
    }
    
    /**
     * parses one line from csv files to
     * seperate information and return a PersonStopped object
     * @param line a single line from csv
     * @return PersonStopped object to be compared
     */
    public static PersonStopped parseLine(String line) {
	//create array using regular expression to seperate each csv
	String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

	//assign relevant values in tokens[] to create a PersonStopped object
	String sex = tokens[SEX_INDEX]; //sex
	String race = tokens[RACE_INDEX]; //race
	String dob = tokens[DOB_INDEX]; //date of birth
	String hair = tokens[HAIR_INDEX]; //hair color
	String eye = tokens[EYE_INDEX]; //eye color

	//return PersonStopped object
	return new PersonStopped(sex, race, dob, hair, eye);
    }

    /**
     * uses fullDataSet instance var and deduplicates it
     * by comparing each person to every other person to count duplicates
     * @return ArrayList of unique PersonStopped objects
     */
    public ArrayList<PersonStopped> allPairsDeduplication() {
	ArrayList<PersonStopped> noPairs = new ArrayList<PersonStopped>(); //noPairs only holds unique people from fullDataSet
	noPairs.add(this.fullDataSet.get(0)); //add first element to noPairs so its size is > 0 for following loop
	boolean duplicate = false; //var to show if person will be a duplicate if added (true)
	for (int i = 0; i < this.fullDataSet.size(); i++) { //for every person in fullDataSet
	    duplicate = false;
	    for (int j = 0; j < noPairs.size(); j++) { //for every person in noPairs
		if (this.fullDataSet.get(i).compareTo(noPairs.get(j)) == 0) { //if that person is already in noPairs
		    duplicate = true;
		    break; //end this loop
		}
	    }
	    if (!duplicate) {
		noPairs.add(this.fullDataSet.get(i)); //if not in noPairs, add person to noPairs
	    }
	}
	return noPairs;
    }

    /**
     * uses fullDataSet instance var and deduplicates it
     * by inserting into hashtable using linear probing natural deduplication)
     * @return ArrayList of unique PersonStopped objects
     */
    public ArrayList<PersonStopped> hashLinearDeduplication() {
	ProbeHashMap<String, PersonStopped> hash = new ProbeHashMap<String, PersonStopped>(CAPACITY);
	ArrayList<PersonStopped> output = new ArrayList<PersonStopped>();
	for (int i = 0; i < fullDataSet.size(); i++) { //for every pereson in fullDataSet
	    if (hash.put(fullDataSet.get(i).toString(),fullDataSet.get(i)) == null) { //insert person into hash, return null if unique
		output.add(fullDataSet.get(i)); //add to unique people array if unique
	    }
	}
	//print linear probing stats
	System.out.println("average number of probes during insertions: " + hash.averageProbes());
	System.out.println("max number of probes during insertions: " + hash.maxProbes());
	System.out.println("load_factor after insertions: " + ((double) hash.size()/CAPACITY));
	return output;
    }

    /**
     * uses fullDataSet instance var and deduplicates it
     * by inserting into hashtable using double hashing (natural deduplication)
     * @return ArrayList of unique PersonStopped objects
     */
    public ArrayList<PersonStopped> hashDoubleDeduplication() {
        DoubleHashMap<String, PersonStopped> hash = new DoubleHashMap<String, PersonStopped>(CAPACITY);
	ArrayList<PersonStopped> output = new ArrayList<PersonStopped>();
	for (int i = 0; i < fullDataSet.size(); i++) { //for every pereson in fullDataSet
	    if (hash.put(fullDataSet.get(i).toString(),fullDataSet.get(i)) == null) { //insert person into hash, return null if unique
		output.add(fullDataSet.get(i)); //add to unique people array if unique
	    }
	}
	//print double hashing stats
	System.out.println("average number of probes during insertions: " + hash.averageProbes());
	System.out.println("max number of probes during insertions: " + hash.maxProbes());
	System.out.println("load_factor after insertions: " + ((double) hash.size()/CAPACITY));
	return output;
    }

    /**
     * uses fullDataSet instance var and deduplicates it
     * by sorting using Java's mergeSort and removing duplicates
     * @return ArrayList of unique PersonStopped objects
     */
    public ArrayList<PersonStopped> builtinSortDeduplication() {
	ArrayList<PersonStopped> sorted = new ArrayList<PersonStopped>(fullDataSet); //make copy of fullDataSet to sort
	//sort
	Collections.sort(sorted); //use java's collection sort (merge sort)
	//deduplicate
	for (int i = 0; i < sorted.size()-1; i++) { //for every person in sorted list
	    if (sorted.get(i).compareTo(sorted.get(i+1)) == 0) { //if current person is the same as the next
		sorted.remove(i+1); //remove duplicate element
		i--; //decrement i to deal with shifting
	    }
	}
	return sorted;
    }

    /**
     * uses fullDataSet instance var and deduplicates it
     * by sorting using quickSort implementation and removing duplicates
     * @return ArrayList of unique PersonStopped objects
     */
    public ArrayList<PersonStopped> quickSortDeduplication() {
	ArrayList<PersonStopped> sorted = new ArrayList<PersonStopped>(fullDataSet); //make copy of fullDataSet to sort
	//sort
        inPlaceQuickSort(sorted, 0, sorted.size()-1); //call helper method to sort sorted
	//deduplicate
	for (int i = 0; i < sorted.size()-1; i++) { //for every person in sorted list
	    if (sorted.get(i).compareTo(sorted.get(i+1)) == 0) { //if current person is the same as the next
		sorted.remove(i+1); //remove duplicate element
		i--; //decrement i to deal with shifting
	    }
	}
	return sorted;	
    }

    /**
     * helper method for quickSortDeduplication
     * implements quick sort algorithm to sort arraylist
     * @param ArrayList<PersonStopped> sorted, list to be sorted and returned
     * @param int startIndex, index to start sorting at
     * @param int endIndex, index to end sorting at
     * @return ArrayList<PersonStopped> sorted ArrayList
     */
    private ArrayList<PersonStopped> inPlaceQuickSort(ArrayList<PersonStopped> sorted, int startIndex, int endIndex) {
	//base case
	if (startIndex >= endIndex) { //if start >= end exit
	    return sorted;
	}
	int l = inPlacePartition(sorted, startIndex, endIndex); //get pivot index
        inPlaceQuickSort(sorted, startIndex, l); //recursive call on 0 (start) to l-1
	inPlaceQuickSort(sorted, l+1, endIndex); //recursive call on l+1 to n-1 (end)
	return sorted;
    }

    /**
     * helper method for inPlaceQuickSort
     * uses Hoare's partition scheme to partition 
     * list by L, EunionG
     * @param ArrayList<PersonStopped> list to be partitioned
     * @param int startIndex, index to start partitioning at
     * @param int endIndex, index to end partitioning at
     * @return int of partition to split list
     */
    private int inPlacePartition(ArrayList<PersonStopped> list, int startIndex, int endIndex) {
	int pivotIndex = startIndex; //pivot is first element
	PersonStopped pivot = list.get(pivotIndex); //pivot is last element

	startIndex--;
	endIndex++;
	
	while (true) {
	    //increment low and decrement high until they reach where the pivot should be
	    do {
		startIndex++;
	    } while (list.get(startIndex).compareTo(pivot) < 0);

	    do {
		endIndex--;
	    } while (list.get(endIndex).compareTo(pivot) > 0);
	    //if the 2 indicies overlap return index to be pivot
	    if (startIndex >= endIndex) {
		return endIndex;
	    }
	    //swap start and last
	    PersonStopped temp = list.get(startIndex);
	    list.set(startIndex, list.get(endIndex));
	    list.set(endIndex, temp);
	}
    }
    
}
