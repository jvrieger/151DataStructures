public class ArrayDeque<E> implements Deque<E> {

    public static final int CAPACITY = 1000; //default array capacity
    private E[] data; //generic array used for storage of deque elements
    private int front = 1; //index of the front element of the queue in the array
    private int rear = 1; //index of the rear element of the queue in the array
    private int size = 0; //current number of elements in the queue
    
    public ArrayDeque() {
	this(CAPACITY); //constructs queue with default capacity
    }

    @SuppressWarnings({"unchecked"}) //safe cast, compiler may give warning
    public ArrayDeque(int capacity) {
	data = (E[]) new Object[capacity]; //constructs queue with given capacity
    }

   /**
    * Returns the number of elements in the deque.
    * @return number of elements in the deque
    */
    public int size() {
	return this.size;
    }

   /**
    * Tests whether the deque is empty.
    * @return true if the deque is empty, false otherwise
    */
    public boolean isEmpty() {
	return (this.size == 0);
    }

   /**
    * Returns (but does not remove) the first element of the deque.
    * @return first element of the deque (or null if empty)
    */
    public E first() {
	if (isEmpty()) {
	    return null;
	}
	return data[front];
    }

   /**
    * Returns (but does not remove) the last element of the deque.
    * @return last element of the deque (or null if empty)
    */
    public E last() {
	if (isEmpty()) {
	    return null;
	}
	return data[rear-1];
    }

   /**
    * Inserts an element at the front of the deque.
    * @param e   the new element
    */
    public void addFirst(E e) throws IllegalStateException {
	//if array is full throw exception
        if (size == data.length) {
	    throw new IllegalStateException("Queue is full");
	}
	int avail = Math.floorMod((rear-(size+1)), data.length); //use mod to find index of first available space
	data[avail] = e; //assign element in first available space
	front = Math.floorMod((front-1), data.length); //update front
	size++; //size increases by 1
    }

   /**
    * Inserts an element at the back of the deque.
    * @param e   the new element
    * @throws IllegalStateException if the array sorting the elements is full
    */
    public void addLast(E e) throws IllegalStateException {
	//if array is full throw exception
	if (size == data.length) {
	    throw new IllegalStateException("Queue is full");
	}
   
	int avail = Math.floorMod((front+size), data.length); //use mod to find index of first available space
	data[avail] = e; //assign element in first available space
	rear = (rear+1)%data.length; //update rear to next available spot
	size++; //size increases by 1
    }

   /**
    * Removes and returns the first element of the deque.
    * @return element removed (or null if empty)
    */
    public E removeFirst() {
	//if Deque is empty return null
	if (isEmpty()) {
	    return null;
	}
	//else return first element of list
	E answer = data[front];
	data[front] = null; //dereference for garbage collection
	front = (front+1)%data.length; //update front to new first element of Deque
	size--; //decrement size
	return answer;
    }
    
   /**
    * Removes and returns the last element of the deque.
    * @return element removed (or null if empty)
    */
    public E removeLast() {
	//if Deque is empty return null
	if (isEmpty()) {
	    return null;
	}
	//else return the last element of list
	E answer = data[rear-1];
	data[rear-1] = null; //dereference for garbage collection
	rear = Math.floorMod((rear-1), data.length); //update rear to new rear element of Deque
	size--; //decrement size
	return answer;
    }

   /**
    * Returns a string representation of the queue as a list of elements.
    * This method runs in O(n) time, where n is the size of the queue.
    * @return textual representation of the queue.
    */
    public String toString() {
	StringBuilder sb = new StringBuilder("(");
	int k = front;
	for (int j=0; j < size; j++) {
	    if (j > 0)
		sb.append(", ");
	    sb.append(data[k]);
	    k = (k + 1) % data.length;
	}
	sb.append(")");
	return sb.toString();
    }
}
