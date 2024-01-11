
/* Name: Julia Rieger
 * File: Name.java
 * Desc:
 *
 * Name class to create Name instances to be stored
 * in linked lists, used by NameDLL.java to reference
 * Name information
 *
 */

import java.util.ArrayList; //import the ArrayList class
public class Name {

    private String name; //name of baby
    private int rank; //rank for specific year
    private int number; //rank for specific year
    private int totalRank = 0;
    private int totalNumber = 0;
    private ArrayList<YearlyStatistics> yearlyStats = new ArrayList<YearlyStatistics>(); //keep track of statistics for each year
    private ArrayList<Integer> rankSort = new ArrayList<Integer>(); //hold all number totals for every name
    
    
    public Name(String name, int rank, int number, ArrayList<YearlyStatistics> yearlyStats) {
	this.name = name;
	this.rank = rank;
	this.number = number;
	this.yearlyStats = yearlyStats;
    }

    public String getName() {
	return this.name;
    }

    public int getRank() {
	return this.rank;
    }

    public int getNumber() {
	return this.number;
    }

    //add year's stats to arraylist
    public void addToList(YearlyStatistics stats) {
	//add object to arrayList
	this.yearlyStats.add(stats);
    }

    //accessor for arraylist
    public ArrayList<YearlyStatistics> getYearlyStats() {
	return this.yearlyStats;
    }

    public void setYearlyStats(ArrayList<YearlyStatistics> yearlyStats) {
	this.yearlyStats = yearlyStats;
    }

    

    public void incrementTotalRank(int currentRank) {
	this.totalRank += currentRank;
    }

    public void incrementTotalNumber(int currentNumber) {
	this.totalNumber += currentNumber;
    }

    public int getTotalRank() {
	return this.totalRank;
    }

    public int getTotalNumber() {
	return this.totalNumber;
    }

    public void setTotalRank(int rank) {
	this.totalRank = rank;
    }

    public void setTotalNumber(int number) {
	this.totalNumber = number;
    }

    public String toString() {
	return name;
    }
}
