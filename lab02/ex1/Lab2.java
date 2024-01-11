import java.util.ArrayList;

public class Lab2 {
    public static void main(String[] args){

	ArrayList<Mammal> mammals = new ArrayList<Mammal>();
	mammals.add(new Dolphin());
	mammals.add(new Platypus());
	mammals.add(new Human());
	mammals.add(new CSStudent());
       
	for (int i=0; i< mammals.size(); i++){
	    System.out.print("Generally, a " + mammals.get(i).getName());
	    System.out.print(" can be found "); 
	    if(mammals.get(i).livesInWater() == false){
		System.out.print("on land, ");
	    }
	    else {
		System.out.print("in water, ");
	    }

	    System.out.print("it can ");
	    if(mammals.get(i).laysEggs() == false) {
		System.out.print("not ");
	    } 
	    System.out.print("lay eggs, and is often overheard saying '");
	    mammals.get(i).speak();
	    System.out.println("'");
	}
    }
}