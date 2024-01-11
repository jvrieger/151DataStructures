import java.util.*;

public class Main {
    public static void main(String[] args) {

	System.out.println("\nTESTING ARRAYLIST\n");

	ArrayList<Integer> test = new ArrayList<Integer>();
	System.out.println(test.size());
	System.out.println(test.isEmpty());
	test.add(0, 1);
	test.add(1, 2);
	test.add(2, 3);
	test.add(3, 4);
	System.out.println(test);
	System.out.println(test.get(0));
	System.out.println(test.get(3));
	System.out.println(test.set(0, 5));
	System.out.println(test);
	System.out.println(test.remove(0));
	System.out.println(test);
	test.add(0, 1);
	System.out.println(test + "\n");

	//testing iterator
	System.out.println("\nTESTING ITERATOR\n");
	Iterator iter = test.iterator();
	System.out.println("iter id: " + iter);
	System.out.println("has next: " + iter.hasNext());
	//iter.remove(); //iterator not on anything, removes nothing/error
	System.out.println("status: " + test); //list before operations
	if (iter.hasNext()) { 
	    System.out.println("next element + iterate: " + iter.next()); //prints first item (first iteration)
	}
	iter.next(); //moves onto next item, in this case index 1 (2)
	iter.remove(); //removes that item, in this case index 1 (2)
	System.out.println("removing next element");
	System.out.println("status: " + test); //prints list after removing, 1, 3, 4
	System.out.println("new next element is " + iter.next());

	//testing MyIterator
	System.out.println("\nTESTING MYITERATOR\n");
	MyIterator myIter = test.myListIterator();
	System.out.println("iter id: " + myIter);
	System.out.println("has next: " + myIter.hasNext());
	System.out.println("status: " + test); //list before operations
	if (myIter.hasNext()) {
	    System.out.println("next element + iterate: " + myIter.next());
	}
	myIter.next();
	myIter.remove();
	System.out.println("removing next element");
	System.out.println("status: " + test); //prints list after removing 3 (1, 4)
        if (myIter.hasPrevious()) {
	    System.out.println("previous element + iterate: " + myIter.previous());
	}
	else {
	    System.out.println("No previous, new next element is " + myIter.next());
	}

	System.out.println("previous element + iterate: " + myIter.previous());
	if (myIter.hasPrevious()) {
	    System.out.println("previous element + iterate: " + myIter.previous());
	}
	else {
	    System.out.println("next element + iterate: " + myIter.next());
	}
	test.add(1, 2);
	test.add(2, 3);
	System.out.println("list reset");
	System.out.println("status: " + test);
	System.out.println("has next: " + myIter.hasNext());
	System.out.println("previous element + iterate: " + myIter.previous());
	myIter.remove();
	System.out.println("removing previous");
	System.out.println("status: " + test);
	System.out.println("next element + iterate: " + myIter.next());
	System.out.println("next element + iterate: " + myIter.next());
	myIter.remove();
	System.out.println("status: " + test);
	System.out.println("next element + iterate: " + myIter.next());
	myIter.set(2);
	System.out.println("set 4 to 2");
	System.out.println("status: " + test);
	System.out.println("previous element + iterate: " + myIter.previous());
	myIter.set(1);
	System.out.println("set first 2 to 1");
	System.out.println("status: " + test);

	//testing MyIterator indexed
	System.out.println("\nTESTING MYITERATOR WITH INDEX\n");
	MyIterator myIterI = test.myListIterator(3);
	System.out.println("iter id: " + myIterI);
	System.out.println("has next: " + myIterI.hasNext());
	System.out.println("has prev: " + myIterI.hasPrevious());
	System.out.println("status: " + test); //list before operations

	System.out.println("TESTING REMOVE METHOD");

	ArrayList<Integer> one = new ArrayList<Integer>();
	ArrayList<Integer> two = new ArrayList<Integer>();

	one.add(0, 2);
	one.add(1, 4);
	one.add(2, 6);
	one.add(3, 8);
	one.add(4, 10);
	System.out.println("First Array: " + one);
	two.add(0, 2);
	two.add(1, 4);
	System.out.println("Second Array: " + two);
	System.out.println("Using method: (2, 4, 8)");
	
    }
}
