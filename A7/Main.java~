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
	    //Create ArrayHeap object
	    ArrayHeap<PollingData> pollHeap = new ArrayHeap<PollingData>(); //pollHeap will hold PollingData objects in maxHeap order

	    //find index filenames start at
	    int index = 0; //general index of pointer
	    int indexOfFilenames = 0; //index of filenames
	    int indexOfNFlag = -1; //index of -n
	    int indexOfRFlag = -1; //index of -r
	    
	    if (args[index].charAt(0) == '-') {
		//has flags
		//while args[index] is not filename iterate through args
		while (args[indexOfFilenames].length() < 5 || !(args[indexOfFilenames].substring(args[indexOfFilenames].length()-4).compareTo(".csv") == 0)) {
		    indexOfFilenames++;
		}
		while (index < indexOfFilenames) {
		    if (args[index].charAt(1) == 'r') {
			indexOfRFlag = index;
			index++;
			while (!(args[index].charAt(0) == '-') && index < indexOfFilenames) {
			    index++;
			}
		    }
		    else if (args[index].charAt(1) == 'n') {
		    indexOfNFlag = index;
		    index +=2;
		    }
		}
	    }
	    
	    //For each file
	    for (int i = indexOfFilenames; i < args.length; i++) {
		//call method in LookupPollingData: readFile(filename, heapToUpdate)
		LookupPollingData.readPollFile(args[i], pollHeap); //returns updated heap
		//heap is now updated with this dates info

		//print heap, formatting done in ArrayBinaryTree and ArrayHeap
	        System.out.println(pollHeap.toStringOutput());
	    }
	    	    
	    //if args has flags
	    if (indexOfFilenames > 0) {
		//if there is an r flag, execute r
		if (indexOfRFlag > -1) {
		    index = indexOfRFlag + 1;
		    String name = ""; //to hold full name
		    //while index is not a flag/more than indexOfFilenames
		    while (!(args[index].charAt(0) == '-') && index < indexOfFilenames) {
			name += args[index] + " ";
			index++;
		    }
		    name = name.substring(0, name.length()-1);
		    //create candidate to search for in heap
		    PollingData candidate = new PollingData(name, name, 0); //to search heap for
		    pollHeap.remove(candidate);
		}
		
		if (indexOfNFlag > -1) {
		    index = indexOfNFlag + 1;
		    int n = Integer.parseInt(args[index]);
		    index++;
		    ArrayList<PollingData> topN = new ArrayList<PollingData>();
		    topN = pollHeap.peekTopN(n); //ArrayList now contains topN candidates
		    //print top N candidates
		    System.out.println("\nTop " + n + " Candidates: ");
		    for (int i = 0; i < topN.size();i++) {
			System.out.println(topN.get(i));
		    }
		}
	    }
	   
	    //else there are no flags (do nothing extra)	    
	}
	catch(FileNotFoundException e) {
	    System.out.println("File not found");
	}

	catch(FullArrayException e) {
	    e.printStackTrace();
	}
    }
}
