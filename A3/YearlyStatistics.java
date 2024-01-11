/* Name: Julia Rieger
 * File: YearlyStatistics.java
 * Desc:
 *
 * Holds information on each name to be returned,
 * including which year to search through, the name's rank
 * and the number of babies with that name, calculates Percent
 *
 */
public class YearlyStatistics {
    private String name; //the name holding this arrayList of objects
    private int year; //what year file looking in
    private int rank; //name's rank for that year
    private int number; //number of name's for that year

    public YearlyStatistics(String name, int year, int rank, int number) {
	this.name = name;
	this.year = year;
	this.rank = rank;
	this.number = number;
    }

    public String getName() {
	return this.name;
    }

    public int getYear() {
	return this.year;
    }

    public int getRank() {
	return this.rank;
    }

    public int getNumber() {
	return this.number;
    }

    public String toString() {
	
	return year + "\n" + name + ": " + rank + ", " + number + ", ";
    }
}
