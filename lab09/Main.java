import java.util.*;
import java.io.*;

public class Main {
    public static final int CAPACITY = 43201;
    public static void main(String[] args) {

	try {
	    
	    ProbeHashMap<String, String> hash = new ProbeHashMap<String, String>(CAPACITY); //reasonable large prime number

	    //READING DICTIONARY
	    Scanner input = new Scanner(new File("dictionary.txt"));

	    while (input.hasNextLine() == true) {
		String line = input.nextLine();
		hash.put(line, line);
	    }
	    input.close();	   

	    System.out.println("size: " + hash.size());
	    //READING SEARCH
	    Scanner input1 = new Scanner(new File("search.txt"));
	    while (input1.hasNext()) {
		String word = input1.nextLine().toLowerCase();
		//search for each line in the hashtable
		if (hash.get(word) == null) {
		//if not found assume it is mispelled: list likely suspects
		    printSuggestedCorrections(hash, word);
		}
		else {
		    //if word is found print it back out
		    System.out.println(hash.get(word));
		}
	    }
	    input1.close();
	    
	    System.out.println("average number of probes during insertions: " + hash.averageProbes());
	    System.out.println("max number of probes during insertions: " + hash.maxProbes());
	    System.out.println("load_factor after insertions: " + ((double) hash.size()/CAPACITY)); //num entries/capacity
	}

	catch(FileNotFoundException e) {
	    System.out.println("File Not Found");
	}
    }

    public static ArrayList<String> changeOneLetter(String word) {
	ArrayList<String> oneLetter = new ArrayList<String>();
	char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	for (char a : word.toCharArray()) {
	    for (char b : alphabet) {
		String combo = word.replace(a, b);
		oneLetter.add(combo);
	    }
	}
	return oneLetter;
    }

    public static ArrayList<String> adjacentLetters(String word) {
	ArrayList<String> adjLetters = new ArrayList<String>();
	for (int i = 0; i < word.length()-1; i++) {
	    String combo = word.substring(0, i) + word.charAt(i + 1) + word.charAt(i) + word.substring(i + 2);
	    adjLetters.add(combo);
	}
	return adjLetters;
    }

    public static ArrayList<String> removeOneLetter(String word) {
	ArrayList<String> removeOne = new ArrayList<String>();
	//check remove one letter rule
	//for every letter in word
	String combo = "";
	for (int i = 0; i < word.length() - 1; i++) {
	    if (i == word.length()-1) {
		combo = word.substring(0, i);
	    }
	    else {
		combo = word.substring(0, i) + word.substring(i+1);
	    }
	    removeOne.add(combo);
	}
	return removeOne;
    }

    public static void printSuggestedCorrections(ProbeHashMap<String, String> hash, String word) {
	ArrayList<String> suggestedCorrections = new ArrayList<String>();

	//check one letter
	for (String oneLetter : changeOneLetter(word)) {
	    if (hash.get(oneLetter) != null) {
		suggestedCorrections.add(oneLetter);
	    }
	}

	for (String adjLetters : adjacentLetters(word)) {
	    if (hash.get(adjLetters) != null) {
		suggestedCorrections.add(adjLetters);
	    }
	}

	for (String removeOne : removeOneLetter(word)) {
	    if (hash.get(removeOne) != null) {
		suggestedCorrections.add(removeOne);
	    }
	}

	System.out.print(word + ": ");
        for (int i = 0; i < suggestedCorrections.size(); i++) {
	    if (i == suggestedCorrections.size()-1) {
		System.out.print(suggestedCorrections.get(i) + "\n");
	    }
	    else {
		System.out.print(suggestedCorrections.get(i) + ", ");
	    }
	}		       
    }
}
