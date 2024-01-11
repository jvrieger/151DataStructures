/* Name: Julia Rieger
 * File: TwoStacksQueue.java
 * 
 * Desc: Stores two ArrayStack objects as instance variables,
 * object is a Queue and is FIFO, but uses two stacks.
 *
 */
import java.util.*;

public class TwoStacksQueue<E> implements Queue<E> {

    private ArrayStack<E> s1 = new ArrayStack<E>(); //first stack with default capacity of 1000
    private ArrayStack<E> s2 = new ArrayStack<E>(); //second stack with default capacity of 1000

    public TwoStacksQueue() {
	this.s1 = new ArrayStack<E>();
	this.s2 = new ArrayStack<E>();
    }

    public TwoStacksQueue(int size) {
	this.s1 = new ArrayStack<E>(size);
	this.s2 = new ArrayStack<E>(size);
    }
    
  /**
   * Returns the number of elements in the queue.
   * @return number of elements in the queue
   */
    public int size() {
	int size = s1.size() + s2.size(); //sum of sizes of both stacks
	return size;
    }

  /**
   * Tests whether the queue is empty.
   * @return true if the queue is empty, false otherwise
   */
    public boolean isEmpty() {
	if (s1.isEmpty() && s2.isEmpty()) {
	    return true;
	}
	else {
	    return false;
	}
    }

  /**
   * Inserts an element at the rear of the queue.
   * @param e  the element to be inserted
   */
    public void enqueue(E e) {
	//if s2 has elements
	while (!s2.isEmpty()) {
	    //shift elements from s2 back to s1
	    E temp = s2.pop();
	    s1.push(temp);
	}
	//now s1 has entire queue and s2 is empty
	s1.push(e);
    }

  /**
   * Returns, but does not remove, the first element of the queue.
   * @return the first element of the queue (or null if empty)
   */
    public E first() {
	//both stacks are empty
	if (isEmpty()) {
	    return null;
	}
	//there is at least one element in either stack
	else {
	    //if s1 has elements
	    while (!s1.isEmpty()) {
		//pop and push from s1 to s2 until s1 is empty
		E temp = s1.pop();
		s2.push(temp);
	    }
		
	    //now s2 has entire queue and s1 is empty
	    //return top of s2 which is first element
	    return s2.top();
	}
    }

  /**
   * Removes and returns the first element of the queue.
   * @return element removed (or null if empty)
   */
    public E dequeue() {
	//both stacks are empty
	if (isEmpty()) {
	    return null;
	}
	//there is at least one element in either stack
	else {
	    //if s1 has elements
	    while (!s1.isEmpty()) {
		//pop and push from s1 to s2 until s1 is empty
		E temp = s1.pop();
		s2.push(temp);
	    }
	    //now s2 has whole queue and s1 is empty
	    //pop from s2 + return
	    return s2.pop();
	}
    }
   /**
    * Return a String that contains the contents of the data
    * structure in the format (element1, element2, ..., elementn).
    *
    * @return textual representation of the stack
    */
    @Override
    public String toString() {
	//both stacks are empty
	if (isEmpty()) {
	    return "The queue is empty";
	}
	//there is at least one element in either stack
	else {
	    //if s1 has elements
	    while (!s1.isEmpty()) {
		//pop and push from s1 to s2 until s1 is empty
		E temp = s1.pop();
		s2.push(temp);
	    }
	    //now s2 has whole queue and s1 is empty
	    //append all of s2 to sb (String to be returned)
	    return s2.toString();
	}
    }
}
