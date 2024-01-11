/* Name: Julia Rieger
* File: LocatedPlace.java 
* Desc: 
* 
* Subclass of Place, for zipcodes with lat/long but no population
*
* Creates a LocatedPlace object with inherited and new data
* from Place class, with accessors 
*/
public class LocatedPlace extends Place {

    private double latitude; //zipcode of LocatedPlace is in this latitude
    private double longitude; //zipcode of LocatedPlace is in this longitude

    public LocatedPlace(int zipcode, String city, String state, double latitude, double longitude) {
	super(zipcode, city, state);
	this.latitude = latitude;
	this.longitude = longitude;
    }

    public double getLat() {
	return this.latitude;
    }

    public double getLong() {
	return this.longitude;
    }

    public void setLat(double lat) {
	this.latitude = lat;
    }

    public void setLong(double lon) {
	this.longitude = lon;
    }

    @Override
    public String toString() {
	return super.getCity() + ", " + super.getState() + " " + this.latitude + " " + this.longitude;
    }
}
