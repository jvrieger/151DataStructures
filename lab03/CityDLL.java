import java.lang.*;
public class CityDLL {

    private static class Node {
	private City data;
	private Node prev;
	private Node next;

	public Node(City data, Node prev, Node next) {
	    this.data = data;
	    this.prev = prev;
	    this.next = next;
	}

	public City getData() {return this.data;}
	public Node getNext() {return this.next;}
	public Node getPrev() {return this.prev;}
	public void setNext(Node n) {this.next = n;}
	public void setPrev(Node n) {this.prev = n;}
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;
    public CityDLL() {};
    
    public int size() {
	return size;
    }
    
    public boolean isEmpty() {
	return this.size == 0;
    }

    public City first() {
	if(isEmpty()) {
	    return null;
	}
	else {
	    return head.getData();
	}
    }

    public City last() {
	if(isEmpty()) {
	    return null;
	}
	else {
	    return tail.getData();
	}
    }

    public void addLast(City data) {
	Node newest = new Node(data, tail, null);
	if (isEmpty()) {
	    head = newest;
	}
	else {
	    tail.setNext(newest);
	}
	tail = newest;
	size++;
    }

    public void addFirst(City data) {
	Node newest = new Node(data, null, head);
	if(isEmpty()) {
	    tail = newest;
	}
	else {
	    head.setPrev(newest);
	}
	head = newest;
	size++;
    }

    //precondition, prev != null, next != null
    public void addBetween(City data, Node prev, Node next) {
	Node newest = new Node(data, prev, next);
	prev.setNext(newest);
	next.setPrev(newest);
	size++;
    }

    //inserts a City c just before some Node n
    public void insertBefore(City c, Node n) {
	Node newest = new Node(c, null, n);
	//isEmpty() must be length 1+ bc n is in List
	//if n is head
	if (head == n) {addFirst(c);}
       
	else {
	    n.getPrev().setNext(newest);
	    newest.setPrev(n.getPrev());
	    n.setPrev(newest);
	    size++;
	}
    }

    //inserts a City c into the list in alphabetically sorted order
    public void insertSorted(City c) {
	char nameCLetter = c.getName().charAt(0);
	char firstLetterC = Character.toUpperCase(nameCLetter); //set to first letter of city

	//if list is empty
	if (isEmpty()) {
	    addLast(c);
	    return;
	}

        else {
	    for (Node n = head; n!= null; n=n.getNext()) {
		char nameNLetter = n.getData().getName().charAt(0);
		char firstLetterN = Character.toUpperCase(nameNLetter); //set to first letter of city
		//if first letter is less
		if (firstLetterC < firstLetterN) {
		    insertBefore(c, n);
		    return;
		}
		//if first letter is same (tests every letter)
		else if (firstLetterC == firstLetterN) {
		    int nameLength;
		    if (c.getName().length() < n.getData().getName().length()) {
			nameLength = c.getName().length();
		    }
		    else {
			nameLength = n.getData().getName().length();
		    }
		    //abc acd should return
		    //abc abc can return
		    //abc aab should move on
		    for(int i = 1; i < nameLength; i++) {
			char currentNameC = c.getName().charAt(i);
			char currentNameN = n.getData().getName().charAt(i);
			char currentLetterC = Character.toUpperCase(currentNameC);
			char currentLetterN = Character.toUpperCase(currentNameN);
			if (currentLetterC < currentLetterN) {
			    insertBefore(c, n);
			    return;
			}
			if (currentLetterC > currentLetterN) {
			    break;
			}
		    }
		}
		//if first letter is greater do nothing, will exit for loop iterating through list
	    }
	    //if alphabetically greater than every node
	    addLast(c);
	}
    }

    public City removeFirst() {
	if(isEmpty()) {return null;}
	Node target = head;
	if (head == tail) {
	    head = tail = null;
	}
	else {
	    head = head.getNext();
	    head.setPrev(null);
	}
	size--;
	return target.getData();
    }

    public City removeLast() {
	if(isEmpty()) {return null;}
	Node target = tail;
	if(head == tail) {
	    head = tail = null;
	}
	else {
	    tail = tail.getPrev();
	    tail.setNext(null);
	}
	size--;
	return target.getData();
    }

    public City remove(Node n) {
	if (head == n) {return removeFirst();}
	else if (tail == n) {return removeLast();}
	else {
	    n.getPrev().setNext(n.getNext());
	    n.getNext().setPrev(n.getPrev());
	    size--;
	    return n.getData();
	}
    }
    
    public String toString() {
	String s = new String();
	for (Node n = head; n!= null; n=n.getNext()) {
	    s = s + n.getData();
	    if (n != tail) {
		s = s + ",\n\n";
	    }
	}
	return s;
    }	
}
