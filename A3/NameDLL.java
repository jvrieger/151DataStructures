/* Name: Julia Rieger
 * File: NameDLL.java
 * Desc:
 * 
 * Doubley Linked List for Name objects to create a list
 * that will alphabetically hold every name found in all
 * year csv files
 *
 */
import java.util.*;
import java.lang.*;
public class NameDLL {
    private static class Node {
	private Name data;
	private Node prev;
	private Node next;
    
	public Node(Name data, Node prev, Node next) {
	    this.data = data;
	    this.prev = prev;
	    this.next = next;
	}

	public Name getData() {return data;}
	public Node getNext() {return next;}
	public Node getPrev() {return prev;}
	public void setNext(Node n) {next = n;}
	public void setPrev(Node n) {prev = n;}
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;
    public NameDLL() {};
    public int size() {
	return size;
    }
    public boolean isEmpty() {
	return size == 0;
    }

    public Name first() {
	if (isEmpty()) {
	    return null;
	}
	else {
	    return head.getData();
	}
    }

    public Name last() {
	if (isEmpty()) {
	    return null;
	}
	else {
	    return tail.getData();
	}
    }
    
    public void addLast(Name data) {
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
    
    public void addFirst(Name data) {
	Node newest = new Node(data, null, head);
	if (isEmpty()) {
	    tail = newest;
	}
	else {
	    head.setPrev(newest);
	}
	head = newest;
	size++;
    }

    /**returns index of the data you are searching for
     * @param data is the Name object whose index you are searching for
     * @return int the index of the object or -1 if Name is not in list
     */
    public int find(Name data) {
	int count = -1;
        for (Node n=head; n!=null; n=n.getNext()) {
	    count++;
	    if (n.getData().getName().compareTo(data.getName()) == 0) { //if names are same
		return count;
	    }
	}
	return -1;
    }

    /**overloaded find method to pass String name instead of Name object
     * @param name is the name of the object you are looking for
     * @return int the index of the matching name or -1 if Name is not in list
     */
    public int find(String name) {
	int count = -1;
	for (Node n=head; n!=null; n=n.getNext()) {
	    count++;
	    if(n.getData().getName().compareTo(name) == 0) { //if names are same
		return count;
	    }
	}
	return -1;
    }

    /**Finds Node in list using an index
     * @param index number in the list to return
     * @return Node found at that index
     */
    public Node findWithIndex(int index) {
	Node n = head;
	for(int i = 0; i < index; i++) {
	    n = n.getNext();
	}
	return n;
    }

    /**Finds Name in list using an index
     * @param index number in the list to return
     * @return Name found at that index
     */
    public Name findWithIndexData(int index) {
	Node n = head;
	for(int i = 0; i < index; i++) {
	    n = n.getNext();
	}
	return n.getData();
    }
    /**Sets current object's arraylist to original's arraylist (whats in list)
     * also updates stored totals
     * @param current object with arraylist to replace
     */
    public void transferOriginalArrayList(Name current) {
	int origNodeIndex = find(current);
	current.setYearlyStats(findWithIndexData(origNodeIndex).getYearlyStats());
	current.setTotalRank(findWithIndexData(origNodeIndex).getTotalRank());
	current.setTotalNumber(findWithIndexData(origNodeIndex).getTotalNumber());
    }

    /**Replaces original Name Node with current Name Node
     * @param data Name to replace original
     */
    public void updateList(Name data) {
	Node newest = new Node(data, null, null);
        int origNodeIndex = find(data);
	
	//if head
	if (origNodeIndex == 0) {
	    //newest's prev is null
	    newest.setNext(findWithIndex(origNodeIndex).getNext()); //set newest's next to orig's next
	    //orig has no prev to setNext
	    findWithIndex(origNodeIndex).getNext().setPrev(newest); //sets orig's next's prev to newest
	}
	//if tail
	else if (findWithIndex(origNodeIndex).getNext() == null) {
	    newest.setPrev(findWithIndex(origNodeIndex).getPrev()); //set newest's prev to orig's prev
	    //newest's next is null
	    findWithIndex(origNodeIndex).getPrev().setNext(newest); //sets orig's prev's next to newest
	    //orig has no next to setPrev
	}
	//in middle of list
	else {
	    newest.setPrev(findWithIndex(origNodeIndex).getPrev()); //set newest's prev to orig's prev
	    newest.setNext(findWithIndex(origNodeIndex).getNext()); //set newest's next to orig's next
	    findWithIndex(origNodeIndex).getPrev().setNext(newest); //sets orig's prev's next to newest
	    findWithIndex(origNodeIndex).getNext().setPrev(newest); //sets orig's next's prev to newest
	}
    }

    /**Returns the total rank for a specific name
     * @param name the name which you are searching rank for
     * @return int of the total rank for that name
     */
    public int getTotalRank(String name) {
	ArrayList<Integer> rankSort = new ArrayList<Integer>();
	int index = find(name);
       	int targetRank = findWithIndex(index).getData().getTotalNumber();
	//populate rankSort with all total numbers of all names in this gender
	for (Node n = head; n!=null; n=n.getNext()) {
	    rankSort.add(n.getData().getTotalNumber());
	}
	Collections.sort(rankSort, Collections.reverseOrder());
	for (int i = 0; i < rankSort.size(); i++) {
	    if (rankSort.get(i) == targetRank) {
		return i;
	    }
	}
	    return -1;
    }

    /**Returns the total number of names for a specific year
     * @return int of the total number of names
     */
     public int getNumber() {
	int totalNumberOfNames = 0;
	for (Node n=head; n!=null; n=n.getNext()) {
	    totalNumberOfNames += n.getData().getNumber();
	}
	return totalNumberOfNames;
    }

    /**Returns the total number of names for all years
     * @return int of the total number of names
     */
    public int getTotalNumber() {
	int totalNumberOfNames = 0;
	for (Node n=head; n!=null; n=n.getNext()) {
	    totalNumberOfNames += n.getData().getTotalNumber();
	}
	return totalNumberOfNames;
    }
    
    /**
     * inserts a new Name node before a Node o
     * @param a the Name to be inserted before o
     * @param o the Node to insert Name a after
     * @return void
     */
    public void insertBefore(Name a, Node o) {
	Node newest = new Node(a, null, o);
	//isEmpty() must be false because o is in list
	//if o is head
	if (head == o) {addFirst(a);}
	else {
	    o.getPrev().setNext(newest);
	    newest.setPrev(o.getPrev());
	    o.setPrev(newest);
	    size++;
	}
    }
    
    /**
     * inserts a name in the list alphabetically (sorted)
     * by calling insertBefore(Name a, Node o)
     * @param data Name to be inserted alphabetically
     * @return void
     */
    public void insertSorted(Name data) {
	//if list is empty just insert data
	if (isEmpty()) {
	    addFirst(data);
	}

	//if data should be last make last
	else if (data.getName().compareTo(tail.getData().getName()) > 0) {
	    addLast(data);
	}

	//if data should be somewhere else in the list
	else {
	    for (Node n = head; n != null; n=n.getNext()) {
		//if data belongs before n (alphabetically less or equal)
		if (data.getName().compareTo(n.getData().getName()) <= 0) {
		    insertBefore(data, n);
		    return;
		}
		//if alphabetically greater then move onto next node
	    }
	}
    }
    
    // precondition, prev != null, next != null
    public void addBetween(Name data, Node prev, Node next) {
        Node newest = new Node(data, prev, next);
        prev.setNext(newest);
        next.setPrev(newest);
        size++;
    }

    public Name removeFirst() {
	if (isEmpty()) {return null;}
	Node target = head;
	if (head == tail) {
	    head = tail = null;
	}
	else {
	    head = head.getNext(); head.setPrev(null);
	}
	size--;
	return target.getData();
    }  
    
    public Name removeLast() {
	if (isEmpty()) {return null;}
	Node target = tail;
	if (head == tail) {
	    head = tail = null;
	}
	else {
	    tail = tail.getPrev(); tail.setNext(null);
	}
	size--;
	return target.getData();
    }

    public Name remove(Node n) {
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
	for (Node n=head; n!=null; n=n.getNext()){
	    s = s + n.getData();
	    if (n != tail) {
		s = s+", ";
	    }
	}
	return s; 
    }
}
