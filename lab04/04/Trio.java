public class Trio implements MenuItem, Comparable<Trio> {

    private String name;
    private double price;
    
    public Trio(Sandwich sandwich, Salad salad, Drink drink) {
	this.name = sandwich.getName() + "/" + salad.getName() + "/" + drink.getName() + " Trio";
	double noSand = salad.getPrice() + drink.getPrice();
	double noSal = sandwich.getPrice() + drink.getPrice();
	double noDrink = sandwich.getPrice() + salad.getPrice();
	//throw exception
	if(sandwich.getPrice() == salad.getPrice() && sandwich.getPrice() == drink.getPrice()) {
	    throw new IllegalTrioException("Trio items have same price, please choose differently.");
	}
	//free sandwich
	if(noSand <= noSal && noSand <= noDrink) {
	    this.price = noSand;
	}
	//free salad
	if(noSal <= noSand && noSal <= noDrink) {
	    this.price = noSal;
	}
	//free drink
	if(noDrink <= noSal && noDrink <= noSand) {
	    this.price = noDrink;
	}
    }

    public String getName() {
	return this.name;
    }

    public double getPrice() {
	return this.price;
    }

    public int compareTo(Trio other) {
	if(this.price == other.price) {
	    return 0;
	}
	else if(this.price > other.price) {
	    return 1;
	}
	else {
	    return -1;
	}
    }

    public String toString() {
	return this.name + " : " + this.price;
    }
}
