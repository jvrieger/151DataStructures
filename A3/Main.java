/* Name: Julia Rieger
 * File: Main.java
 * Desc:
 *
 * The main driver program for Assignment 03
 * 
 * This program takes command line arguements from 
 * the user and calls upon other classes to return statistics
 * for specific baby names, accessing csv files for data
 * and data from two linked lists
 *
 */
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        final int FIRST_LETTER = 0; //first letter index

	try {
	    //create two linked lists to be passed through methods in LookupName.java
	    NameDLL females = new NameDLL();
	    NameDLL males = new NameDLL();
	    
	    boolean isFemale = false; //flag for sex
	    String filename = ""; //name of file to search through
	    String name; //name to search through files for
	    
	    int validYears [] = {1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017}; //array of valid years for filenames

	    boolean isFlag = false; //flags if arguement is a flag
	    boolean isName = false; //flags if arguement is a name
	    boolean isFilename = false; //flags if arguement is a filename
	    boolean isValidFile = false; //flags if arguement is a valid filename
	    int numOfValidFiles = 0; //stores the num of valid files
	    
	    //for loop for reading in files
	    for(int i = 0; i < args.length; i++) {
		isFlag = false;
		isName = false;
		isFilename = false;
		isValidFile = false;
		
		//if element is a flag skip next element (should be name)
		if (args[i].charAt(0) == '-') {
		    i++;
		    isFlag = true;
		}

		//should not be flag or name, see if is valid filename
		else {
		    for(int j = 0; j < validYears.length; j++) {
			if (args[i].compareTo("names" + validYears[j] + ".csv") == 0) {
			    isValidFile = true;
			    numOfValidFiles++;
			}
		    }
		}

		//if valid file name (above check) and not flag or name
	        if (!isFlag && !isName && isValidFile) {
		    //set String filename to filename
		    filename = args[i];
		   
		    //call readNameFile          		    
		    LookupName.readNameFile(filename, females, true); //returns updated females list
		    LookupName.readNameFile(filename, males, false); //returns updated males list
		}

		//if input is not a valid file name exit
		else if (!isFlag && !isName && !isValidFile) {
		    System.out.println("Invalid Input");
		    System.exit(0);
		}
	    }

	    //for loop for reading names and printing info
	    for(int i = 0; i < (args.length - numOfValidFiles); i++) {
		isFlag = false;
		isName = false;
		isFilename = false;
		isValidFile = false;

		//if flag (either -f or -m)
		if (args[i].compareTo("-f") == 0 || args[i].compareTo("-m") == 0) {
		    isFlag = true;
		    //if flag is -f set flag to indicate female
		    if (args[i].compareTo("-f") == 0) {
			isFemale = true;
		       		    }
		    //if flag is -m set flag to indicate male
		    else {
			isFemale = false;
		    }
		}
		
		//if name (first char is a capital letter)
		else if (args[i].charAt(FIRST_LETTER) >= 'A' && args[i].charAt(FIRST_LETTER) <= 'Z') {
		    isName = true;
		    //set name to current arguement
		    name = args[i];
      		    //print info for each name
		    //search for right name object in list
		    //if is flagged female
		    if (isFemale) {
			printOutput(females, name);
		    }
		    //is flagged male
		    else {
			printOutput(males, name);
		    }
		}
	    }
	}
	    
	catch (FileNotFoundException e) {
	    System.out.println("File Not Found");
	    System.exit(0);
	}
    }
	
    public static void printOutput(NameDLL listName, String name) {
	
	int index = listName.find(name); //index of name looking for in list
	double totalNumberOfNames = listName.getTotalNumber(); //total number of names over all
	double numberOfNames = listName.getNumber(); //total number of names for one year
	int totalRank = listName.getTotalRank(name); //total Rank for this name
						
	System.out.println(index + "\n");
	if (index == -1) { //if name isn't in list
       	    System.out.println("Name is not in our database");
	    System.exit(0);
	}
	else { //if name is in list
	    //print yearly stats
	    ArrayList<YearlyStatistics> yearlyStats = new ArrayList<YearlyStatistics>();
	    yearlyStats = listName.findWithIndexData(index).getYearlyStats();
	    for (int j = 0; j < yearlyStats.size(); j++) {
		System.out.print(yearlyStats.get(j));
	        System.out.printf("%.6f\n\n", listName.findWithIndexData(index).getNumber()/numberOfNames);
	    }
	//print totals
	    System.out.println("Total");
	    System.out.print(name + ": " + totalRank + ", " + listName.findWithIndexData(index).getTotalNumber() + ", ");
	    System.out.printf("%.6f\n\n", listName.findWithIndexData(index).getTotalNumber()/totalNumberOfNames);
        }
    }
}
