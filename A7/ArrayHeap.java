/* Name: Julia Rieger
 * File: ArrayHeap.java
 * Desc:
 *
 * Heap that extends the ArrayBinaryTree, uses maxheap
 * where the candidate with the highest pollresults will
 * be on top and from there be sorted max - min
 */
import java.util.*;
public class ArrayHeap<E extends Comparable<E>> extends ArrayBinaryTree<E> implements PriorityQueue<E> {

    //inherits methods:
    //getRootElement
    //size
    //isEmpty
    //contains
    //insert (must be overriden- insert in maxHeap order)
    //remove (must be overriden, maintain maxHeap order)
    //toStrings

    public ArrayHeap() {
	super();
    }

    /**
     * insert element into tree in maxheap order, if tree contains
     * element, update and reheap, if tree doesn't contain element,
     * add element at end and reheap
     * @param E element to insert into tree
     */
    @Override
    public void insert(E element) throws FullArrayException {
	int containsIdx = containsIdx(element);
	if (containsIdx == -1) { //if tree doesn't contain element
	    super.insert(element);
	    upheap(size()-1);
	}
	else { //if tree contains element
	    setData(containsIdx, element);
	    restoreMaxHeap(getData(containsIdx), containsIdx);
	}
    }
    
    /**
     * removes an entry of the candidate provided
     * @param E element to be removed
     * @return boolean true if element was removed, false if cannot be found
     */
    @Override
    public boolean remove(E element) {
	int containsIdx = containsIdx(element);
	if (containsIdx == -1) { //if tree doesn't contain element
	    return false;
	}
	else { //if tree contains element
	    super.remove(element);
	    restoreMaxHeap(getData(containsIdx), containsIdx);
	    return true;
	}
    }
	
    /**
     * returns the element with the minkey or null when PQ is empty
     * @return E element found (in root position bc heap is sorted)
     */
    public E peek() {
	if (isEmpty()) {
	    return null;
	}
	return getData(0);
    }

    /**
     * removes and returns the element with the minkey or null when PQ is empty
     * @return element found and removed
     */
    public E poll() {
	if (isEmpty()) {
	    return null;
	}
	E temp = getData(0);
	swap(0,size()-1); // put minimum item at the end
	super.remove(getData(size()-1)); // and remove it from the list;
	downheap(0); // then fix new root
	return temp;
    }

    /**
     * returns top n elements of the heap in order
     * @param number of elements to return
     * @return ArrayList<E> containing n elements in order
     */
    public ArrayList<E> peekTopN(int n) {
	//declare new array and make it as long as size of heap
	ArrayList<E> sortedElements = new ArrayList<E>();
	
	//make sortedElements a copy of ArrayHeap
	for (int i = 0; i < size(); i++) {
	    sortedElements.add(getData(i));
	}

	//sort array so the top N can be retrieved
	Collections.sort(sortedElements, Collections.reverseOrder());

	//create ArrayList to be returned with top N elements
	ArrayList<E> topN = new ArrayList<E>();

	//populate with top N sorted from array
	for (int i = 0; i < n; i++) {
	    topN.add(sortedElements.get(i));
	}
	return topN;
    }

    @Override
    public String toString() {
	String answer = "";
	//return heap in breadth first order level by level
	for (int i = 0; i < Math.ceil(Math.log(size())/Math.log(2)); i++) {
	    for (int j = 0; j < i; j++) {
		answer += getData(j) + " ";		  
	    }
	    answer += "\n";
	}
	return answer;
    }

    public String toStringOutput() {
	return "\nTree:\nPre:\t" + toStringPreOrder().trim() + "\nIn:\t" + toStringInOrder().trim() + "\nPost:\t" + toStringPostOrder().trim();
    }
    
    /**
     * upheaps the element at a certain index
     * @param int i index of element to be pushed higher
     */
    private void upheap(int i) {
	while (i > 0) { //continue until reaching root or break
	    int p = parent(i);
	    if (getData(i).compareTo(getData(p)) <= 0) { //heap property verified
		break;
	    }
	    swap(i, p);
	    i = p; //continue from parent's location
	}
    }
    
    /**
     * downheaps the element at a certain index
     * @param int i index of element to be pushed lower
     */
    private void downheap(int i) {
	//while hasLeft
	while (left(i) < size()) { //continue to bottom or break
	    int leftIndex = left(i);
	    int bigChildIndex = leftIndex;
	    //if hasRight
	    if (right(i) < size()) {
		int rightIndex = right(i);
		if (getData(leftIndex).compareTo(getData(rightIndex)) < 0) {
		    bigChildIndex = rightIndex; //right child is bigger
		}	
	    }
	    if (getData(bigChildIndex).compareTo(getData(i)) <= 0) {
		break; //heap property restored (child < parent)
	    }
	    swap(i, bigChildIndex);
	    i = bigChildIndex; //continue at child position
	}
    }

    /**
     * restores heap integrity (for maxHeap)
     * @param E element to verify position of
     * @param int containsIdx index to move around
     */
    private void restoreMaxHeap(E element, int containsIdx) {
	//if element is bigger than parent upheap
	if (getData(parent(containsIdx)) != null && element.compareTo(getData(parent(containsIdx))) > 0) {
	    upheap(containsIdx);
	}
	//if element is smaller than left child downheap
	if (getData(left(containsIdx)) != null && element.compareTo(getData(left(containsIdx))) < 0) {
	    downheap(containsIdx);
	}
	//if element is smaller than right child downheap
	if (getData(right(containsIdx)) != null && element.compareTo(getData(right(containsIdx))) < 0) {
	    downheap(containsIdx);
	}
	//if in correct place do nothing
    }
}
