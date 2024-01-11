import java.util.*;
public class Lab4 {   
    public static void main(String[] args){ 
	Sandwich burger = new Sandwich("Cheeseburger",2.75);
	Sandwich club = new Sandwich("Club Sandwich", 2.75);
	Salad spinachSalad = new Salad("Spinach Salad",1.25);
	Salad coleslaw = new Salad("Coleslaw", 1.25);
	Drink orange = new Drink("Orange Soda", 1.25);
	Drink cap = new Drink("Cappuccino", 3.50);
	// Ex1 
	System.out.println(burger.getName()+ " " + burger.getPrice());
	System.out.println(club.getName()+ " " + club.getPrice());
	System.out.println(spinachSalad.getName()+ " " +
			   spinachSalad.getPrice());
	System.out.println(coleslaw.getName()+ " " +
			   coleslaw.getPrice());
	System.out.println(orange.getName()+ " " + orange.getPrice());
	System.out.println(cap.getName()+ " " + cap.getPrice());

	//Ex2
	Trio trio1 = new Trio(burger, spinachSalad, orange);
	System.out.println(trio1.getName());
	System.out.println(trio1.getPrice());
	Trio trio2 = new Trio(club,coleslaw,cap);
	System.out.println(trio2.getName());
	System.out.println(trio2.getPrice());

	//Ex3
	try {
	    //test cases
	    Sandwich grilledCheese = new Sandwich("Grilled Cheese", 4.00);
	    Salad caesarSalad = new Salad("Caesar Salad", 2.00);
	    Drink milkshake = new Drink("Milkshake", 2.50);
	    Trio trio3 = new Trio(grilledCheese, caesarSalad, milkshake);
	    System.out.println(trio3.getName());
	    System.out.println(trio3.getPrice());

	    //Ex 4 (comparable)
	    System.out.println(trio1.compareTo(trio2));
	    System.out.println(trio2.compareTo(trio3));
	    System.out.println(trio1.compareTo(trio3));
	    Trio[] trios = new Trio[3];
	    trios[0] = trio3;
	    trios[1] = trio2;
	    trios[2] = trio1;

	    System.out.println("Unsorted Trios: ");
	    for(int i = 0; i < trios.length; i++) {
		System.out.println(trios[i]);
	    }

	    Arrays.sort(trios);
	    System.out.println("\nSorted Trios: ");
	    for(int i = 0; i < trios.length; i++) {
		System.out.println(trios[i]);
	    }
	}
	catch (IllegalTrioException e) {
	    System.out.println(e);
	    System.exit(0);
	}
    }
}
