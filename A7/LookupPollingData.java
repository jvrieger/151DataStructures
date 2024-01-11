/* Name: Julia Rieger
 * File: LookupPollingData.java
 * Desc:
 *
 * This class contains methods to work with the linkedBinaryTree
 * such as reading in files to populate it, update information, etc.
 */

import java.io.*;
import java.util.*;
public class LookupPollingData {

    public static final int LAST_NAME_INDEX = 0; //index of last name in line
    public static final int FULL_NAME_INDEX = 1; //index of full name in line
    public static final int POLL_RESULT_INDEX = 2; //index of poll result in line

    /**
     * parses one line from csv files to
     * seperate information and return a PollingData object
     * @param line a single line from csv
     * @return PollingData object to be added to tree
     */
    public static PollingData parseLine(String line) {
	//create array containing each csv
	String[] tokens = line.split(",");

	String lastName = tokens[LAST_NAME_INDEX]; //last name
	String fullName = tokens[FULL_NAME_INDEX]; //full name
	float pollResult = Float.parseFloat(tokens[POLL_RESULT_INDEX]); //poll result

	//return PollingData object
	return new PollingData(lastName, fullName, pollResult);
    }

   /**
    * reads in one of the Poll Result files and adds 
    * every PollingData object parsed from it to pollTree
    * or just updates its info if pollTree already contains candidate
    * @param String filename, name of file to read
    * @param LinkedBinaryTree<E> pollTree tree to add/update info
    * @return LinkedBinaryTree<E> pollTree updated tree to print in main
    */
    public static ArrayHeap<PollingData> readPollFile(String filename, ArrayHeap<PollingData> pollTree) throws FileNotFoundException {
	//create Scanner object for filename
	Scanner input = new Scanner(new File(filename));

	//call nextLine to pass header line
	input.nextLine();
	
	//call parseline on each line to add or update tree
	while(input.hasNextLine() == true) {

	    //create String of next line in file for parseLine arg
	    String line = input.nextLine();

	    //create empty PollingData object to be added to tree/updated
	    PollingData current = new PollingData();

	    //add values from line to current
	    current = parseLine(line);

	    //add candidate to tree, if already in tree insert() will update info
	    pollTree.insert(current);
	}
	//close Scanner named input
	input.close();

	//return updated tree
	return pollTree;
    }

}
