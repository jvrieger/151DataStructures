public class Main {

    public static void main(String[] args) {

	System.out.println("===Checking TwoStacksQueue===\n");
	try {
	    Queue<Integer> queueTest = new TwoStacksQueue<>();
	    queueTest.enqueue(5);
	    queueTest.enqueue(2);
	    queueTest.enqueue(3);
	    checkOutputFormat(queueTest.toString(), "(5, 2, 3)");
	    System.out.println("\n***End of First Check***\n");
	    queueTest.dequeue();
	    queueTest.dequeue();
	    queueTest.enqueue(0);
	    queueTest.enqueue(9);
	    queueTest.enqueue(-6);
	    queueTest.enqueue(14);
	    checkOutputFormat(queueTest.toString(), "(3, 0, 9, -6, 14)");
	    System.out.println("\n***End of Second Check***\n");
	    queueTest.dequeue();
	    queueTest.enqueue(1);
	    System.out.println(queueTest.size());
	    System.out.println(queueTest.first());
	    System.out.println(queueTest);
	    queueTest.dequeue();
	    System.out.println(queueTest.dequeue());
	    System.out.println(queueTest.first());

	    Queue<String> queueString = new TwoStacksQueue<>();
	    queueString.enqueue("Dog");
	    queueString.enqueue("Cat");
	    queueString.enqueue("Lizard");
	    System.out.println(queueString);
	    queueString.dequeue();
	    System.out.println(queueString);
	    queueString.enqueue("Julia");
	    System.out.println(queueString);

	    Queue<Integer> queueInt = new TwoStacksQueue<>(3);
	    System.out.println(queueInt);
	    queueInt.enqueue(1);
	    queueInt.enqueue(2);
	    queueInt.enqueue(3);
	    System.out.println(queueInt);
	    queueInt.dequeue();
	    queueInt.enqueue(4);
	    System.out.println(queueInt);
	}
	catch(IllegalStateException e) {System.out.println("IllegalStatekException was thrown... It should not have been thrown!");}
	catch(Exception m) {System.out.println("An exception was thrown: " + m);}

	//checking part 2 deque
	try {
	    System.out.println("\n===Checking Deque===\n");
	    Deque<String> dequeStr = new ArrayDeque<>(9);
	    dequeStr.addFirst("the");
	    dequeStr.addFirst("quick");
	    dequeStr.addLast("brown");
	    dequeStr.addLast("fox");
	    dequeStr.addFirst("jumps");
	    checkOutputFormat(dequeStr.toString(), "(jumps, quick, the, brown, fox)");
	    System.out.println("\n***End of Third Check***\n");
	    dequeStr.addLast("over");
	    dequeStr.addLast("the");
	    dequeStr.addFirst("lazy");
	    dequeStr.addLast("dog");
	    checkOutputFormat(dequeStr.toString(), "(lazy, jumps, quick, the, brown, fox, over, the, dog)");
	    System.out.println("\n***End of Fourth Check***\n");

	    Deque<Integer> dequeInt = new ArrayDeque<>(6);
	    dequeInt.addLast(4);
	    dequeInt.addFirst(3);
	    dequeInt.addFirst(2);
	    dequeInt.addLast(5);
	    dequeInt.addFirst(1);
	    System.out.println(dequeInt);
	    dequeInt.removeFirst();
	    System.out.println(dequeInt);
	    dequeInt.addLast(6);
	    System.out.println(dequeInt);
	    dequeInt.removeFirst();
	    System.out.println(dequeInt);
	    dequeInt.removeLast();
	    System.out.println(dequeInt);
	    dequeInt.addFirst(2);
	    dequeInt.addFirst(1);
	    System.out.println(dequeInt);
	    System.out.println(dequeInt.first());
	    System.out.println(dequeInt.last());
	}
	catch(IllegalStateException e) {System.out.println("IllegalStateException was thrown... It should not have been thrown!");}
	catch(Exception m) {System.out.println("An exception was thrown: " + m.toString());}
    }


    public static void checkOutputFormat(String studentStr, String correctAns) {
	System.out.println("The output should be printed as \"" + correctAns + "\"\nYour stack printed: \t\t\"" + studentStr + "\"\n");
	
	if(studentStr.equals(correctAns))
	    System.out.println("Your output format seems correct!");
	// check for commas and spaces
	else if(studentStr.indexOf(',')==-1)
	    System.out.println("Your output seems to be missing commas.\nPlease fix this error and try again.");
	else if(studentStr.indexOf(' ')==-1)
	    System.out.println("Your output seems to be missing spaces.\nPlease fix this error and try again.");
	else if(studentStr.indexOf(", ")==-1)
	    System.out.println("Your output seems to be missing spaces right after commas.\nPlease fix this error and try again.");
	// check for parenthesis
	else if(studentStr.indexOf('(')==-1)
	    System.out.println("Your output seems to be missing the left parenthesis: \"(\".\nPlease fix this error and try again.");
	else if(studentStr.indexOf(')')==-1)
	    System.out.println("Your output seems to be missing the right parenthesis: \")\".\nPlease fix this error and try again.");
	else if(studentStr.indexOf(",)")!=-1 || studentStr.indexOf(", )")!=-1)
	    System.out.println("There should not be a comma or a space right before closing the parenthesis!\nPlease fix this error and try again.");
	// all the grammatical structures in place... check the grammar
	else {
	    // check parts of the string
	    String[] parts = studentStr.split(", ");
	    String[] correctParts = correctAns.split(", ");
	    if(parts.length != correctParts.length) { 
		if(parts.length > correctParts.length)
		    System.out.println("There seems to be more commas than there should be in your output.\nPlease fix this error and try again.");
		else if(parts.length < correctParts.length)
		    System.out.println("There seems to be less commas than there should be in your output.\nPlease fix this error and try again.");
	    }
	    else {
		for(int index = 0; index < parts.length; index++) {
		    if(!parts[index].equals(correctParts[index]))
			System.out.println("Your " + index + "th element seems to be.\n\tInstead of: " + correctParts[index]+  ",\n\twe found: " + parts[index] + "\nPlease fix this error and try again.");
		}
	    }
	}
    }
}
