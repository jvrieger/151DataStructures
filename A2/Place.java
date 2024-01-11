/* Name: Julia Rieger
* File: Place.java
* Desc: 
* 
* The class for zipcodes without population or location 
*
* Will assign data for Place objects and accessors
*/
public class Place {

    private int zipcode;
    private String city;
    private String state;

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
    
}
