/* Name: Julia Rieger
 * File: PollingData.java
 * Desc:
 *
 * PollingData objects have information such as:
 * candidate's last name. candidate's full name, and poll results
 * PollingData objects can be compared with each other only
 */

import java.lang.*;

public class PollingData implements Comparable<PollingData> {

    private String lastName; //candidate's last name
    private String fullName; //candidate's full name
    private float pollResult; //candidate's poll result as a float

    public PollingData(String lastName, String fullName, float pollResult) {
	this.lastName = lastName;
	this.fullName = fullName;
	this.pollResult = pollResult;
    }

    public PollingData() {
	this.lastName = "";
	this.fullName = "";
	this.pollResult = 0;
    }

    public String getLastName() {
	return this.lastName;
    }

    public String getFullName() {
	return this.fullName;
    }

    public float getPollResult() {
	return this.pollResult;
    }

    public void setPollResult(float pollResult) {
	this.pollResult = pollResult;
    }

    /**
     * compares PollingData objects based off the
     * alphabetical order by lastname of candidate
     * @param PollingData other, other object to compare this to
     * @return int 0 if equal, 1 if this > other, -1 if this < other
     */
    public int compareTo(PollingData other) {
	if (this.lastName.compareTo(other.lastName) == 0) {
	    return 0;
	}
	else if (this.lastName.compareTo(other.lastName) > 0) {
	    return 1;
	}
	else {
	    return -1;
	}               
    }

    public String toString() {
	return this.fullName + ":" + this.pollResult;
    }
}
