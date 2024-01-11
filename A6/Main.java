/* Name: Julia Rieger
 * File: Main.java
 * Desc:
 *
 * The driver program for Polling Data, 
 * directs user input and calls to other classes
 */
import java.lang.*;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
		
	try {
	    //Create BinaryTree object
	    LinkedBinaryTree<PollingData> pollTree = new LinkedBinaryTree<PollingData>(); //pollTree will hold PollingData objects sorted alphabetically by lastName

	    //sort args so no matter the order it will be chronological
	    Arrays.sort(args);
	    
	    //For each file
	    for (int i = 0; i < args.length; i++) {

		//call method in LookupPollingData: readFile(filename, treeToUpdate)
		LookupPollingData.readPollFile(args[i], pollTree); //returns updated BST
		//Tree is now updated with this dates info

		//print tree (formatting is done in LinkedBinaryTree.java)
	        System.out.println(pollTree);
	    }
	}
	catch(FileNotFoundException e) {
	    System.out.println("File not found");
	}
    }
}
