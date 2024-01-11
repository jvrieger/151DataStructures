/* Name: Julia Rieger
* File: Place.java
* Desc: 
* 
* The class for zipcodes without population or location 
*
* Will assign data for Place objects and accessors
*/
import java.lang.*;
public class Place implements Comparable<Place> {

    private int zipcode; //zipcode of Place
    private String city; //zipcode is in this city
    private String state; //zipcode is in this state

    public Place(int zipcode, String city, String state) {
	this.zipcode = zipcode;
	this.city = city;
	this.state = state;
    }

    public int getZip() {
	return this.zipcode;
    }

    public String getCity() {
	return this.city;
    }

    public String getState() {
	return this.state;
    }

    public String toString() {
	return this.city + ", " + this.state;
    }

    public void setLat(double lat) {}
    public void setLong(double lon) {}
    
    @Override
    public int compareTo(Place targetZip) {
	int target = targetZip.getZip();
	int thisZip = this.getZip();
	if (target == thisZip) {
	    return 0; //0 if zip matches target
	}

	else if (target > thisZip) {
	    return -1; //-1 if zip is less than target
	}

	else {
	    return 1; //1 if zip is more than target
	}
    }
}
