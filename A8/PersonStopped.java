/* Name: Julia Rieger
 * File: PersonStopped.java
 * Desc:
 *
 * PersonStopped objects represent events where a person
 * was the victim of a stop and frisk by the NYPD, and information
 * is gathered from a set of specific attributes from a list of 112,
 * in which the attributes chosen show equality in people (unique person)
 */
public class PersonStopped implements Comparable<PersonStopped> {

    private String sex; //sex of person stopped
    private String race; //race of person stopped
    private String dob; //date of birth of person stopped
    private String hair; //hair color of person stopped
    private String eye; //eye color of person stopped

    public PersonStopped(String sex, String race, String dob, String hair, String eye) {
	this.sex = sex;
	this.race = race;
	this.dob = dob;
	this.hair = hair;
	this.eye = eye;
    }
    
    /**
     * compares PersonStopped objects based off the
     * attributes taken from NYPD data
     * @param PersonStopped other, other person to compare this to
     * @return int 0 if they are the same person, otherwise, not same person
     */
    @Override
    public int compareTo(PersonStopped other) {
	if (this.sex.compareTo(other.sex)==0 && this.race.compareTo(other.race)==0 && this.dob.compareTo(other.dob)==0 && this.hair.compareTo(other.hair)==0 && this.eye.compareTo(other.eye)==0) {
	    return 0;
	}
	else if (this.toString().compareTo(other.toString()) > 0) {
	    return 1;
	}
	else {
	    return -1;
	}
    }

    public String toString() {
	return sex + race + dob + hair + eye;
    }
}
