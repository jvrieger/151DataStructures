public class Drink implements MenuItem {

    private String name;
    private double price;
    
    public Drink(String name, double price) {
	this.name = name;
	this.price = price;
    }

    public String getName() {
	return this.name;
    }

    public double getPrice() {
	return this.price;
    }
}
