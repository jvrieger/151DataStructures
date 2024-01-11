/* Name: Julia Rieger                                                                                      
* File: PopulatedPlace.java                                                                                 
* Desc:                                                                                                     
*                                                                                                           
* Subclass of LocatedPlace, for zipcodes with lat/long and  population                                     
*                                                                                                           
* Creates a PopulatedPlace object with inherited and new data                                               
* from Place and LocatedPlace class, with accessors                                                         
*/
public class PopulatedPlace extends LocatedPlace {

    private int population;

    public PopulatedPlace(int zipcode, String city, String state, double latitude, double longitude, int population) {
        super(zipcode, city, state, latitude, longitude);
	this.population = population;
    }

    public int getPop() {
        return this.population;
    }

    @Override
    public String toString() {
        return super.getCity() + ", " + super.getState() + " " + super.getLat() + " " + super.getLong() + " " + this.population;
    }
}
