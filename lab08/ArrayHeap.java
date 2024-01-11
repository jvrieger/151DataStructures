public class ArrayHeap<E extends Comparable<E>> extends ArrayBinaryTree<E> implements LabPriorityQueue<E> {
    //inherits instance vars: size and data[]

    //inherits methods:
    //getRootElement
    //size
    //isEmpty
    //insert (must be overriden- insert in minHeap order)
    //remove (must be overriden, not in this lab though)
    //toStringBreadthOrder

    public ArrayHeap() {
	super();
    }

    @Override
    public void insert(E element) {
	super.insert(element);
	upheap(size()-1);
    }

    /*
    // Removes and returns an entry with minimal key (if any). ∗/
    @Override
    public boolean remove() {
	if (isEmpty()) {
	    return false;
	}
	swap(0,size()-1); // put minimum item at the end
	super.remove(size()-1); // and remove it from the list;
	downheap(0); // then fix new root
	return true;
    }
    */
	

    //returns the element with the minkey or null when PQ is empty
    public E peek() {
	if (isEmpty()) {
	    return null;
	}
	return data[0];
    }

    //poll not implemented

    //helper methods
    private void upheap(int i) {
	while (i > 0) { //continue until reaching root
	    int p = parent(i);
	    if (data[i].compareTo(data[p]) >= 0) { //heap property verified
		break;
	    }
	    swap(i, p);
	    i = p; //continue from parent's location
	}
    }

    private void downheap(int i) {
	//while hasLeft
	while (left(i) < size()) { //continue to bottom
	    int leftIndex = left(i);
	    int smallChildIndex = leftIndex;
	    //if hasRight
	    if (right(i) < size()) {
		int rightIndex = right(i);
		if (data[leftIndex].compareTo(data[rightIndex]) > 0) {
		    smallChildIndex = rightIndex; //right child is smaller
		}	
	    }
	    if (data[smallChildIndex].compareTo(data[i]) >= 0) {
		break; //heap property restored
	    }
	    swap(i, smallChildIndex);
	    i = smallChildIndex; //continue at child position
	}
    }
}
