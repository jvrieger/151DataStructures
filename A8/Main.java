/* Julia Rieger
 * File: Main.java
 * Desc:
 *
 * Program to take commandline input of a csv file, then 
 * deduplicate that file, count duplications, and print attributes
 * This main will call the most efficient method of deduplicating
 */

import java.io.*;
import java.util.*;
public class Main {

    public static final String ATTRIBUTES_CHECKED = "Sex, Gender, Date of Birth, Hair Color, Eye Color"; //attributes checked for equality on dataset
    
    public static void main(String[] args) {

	try {
	    //construct a LookupPersonStopped object with the args file
	    LookupPersonStopped thisFile = new LookupPersonStopped(args[0]);

	    //get size of uniquePeople using most eficient method in LookupPersonStopped
	    int numOfUniquePeople = thisFile.quickSortDeduplication().size();
	    int numOfFullDataset = thisFile.getFullDataSet().size();
	    
	    //print length of getFullDataSet in LookupPersonStopped class
	    System.out.println("Records given: " + numOfFullDataset);
	    
	    //print which attributes checked

	    //print # duplicates found (full data - unique people)
	    System.out.println("Attributes Checked: " + ATTRIBUTES_CHECKED);
	    System.out.println("Duplicates found: " + (numOfFullDataset - numOfUniquePeople));
	}
	
	catch (FileNotFoundException e) {
	    System.out.println("File not found");
       	}
    }
}
