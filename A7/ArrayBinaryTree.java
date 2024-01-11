 /* Name: Julia Rieger
 * File: ArrayBinaryTree.java
 * Desc:
 *
 * Class to hold information to create an ArrayBinaryTree object
 * which will be used to create a heap to store PollingData
 */

public class ArrayBinaryTree<E extends Comparable<E>> implements BinaryTree<E> {

    public static final int CAPACITY=100; //base capacity of tree (ArrayFullException thrown if full)
    private int size; //keep track of how many elements are in tree
    private E[] data; //underlying data structure of binarytree (Array)

    public ArrayBinaryTree() {
	data = (E[]) new Comparable[CAPACITY];
    }

    /**
     * allows other classes to access tree array
     * @param int index of element to access
     * @return E element at i index
     */
    public E getData(int i) {
	return data[i];
    }

    /**
     * sets data at index to specified element
     * @param int i index to set element at
     * @param E element to set at index i
     */ 
     public void setData(int i, E element) {
	 data[i] = element;
     }

    /**
     * returns E element in root position of tree
     * @return E element in index 0 of array
     */
    public E getRootElement() {
	return data[0];
    }
    
    /**
     * returns number of elements in tree
     * @return int elements currently inserted
     */
    public int size() {
	return size;
    }

    /**
     * test if tree is empty
     * @return true if is empty, false if otherwise
     */
    public boolean isEmpty() {
	return size == 0;
    }

    /**
     * test if tree contains a specific element
     * @param E element to search tree for
     * @return true if element is in tree, false otherwise
     */
    public boolean contains(E element) {
	//if candidates have matching full names return true
	for (int i = 0; i < data.length; i++) {
	    if (data[i].equals(element)) {
		return true;
	    }
	}
	return false;
    }

    /**
     * insert element into tree in no certain order
     * @param E element to insert into tree
     */
    public void insert(E element) throws FullArrayException {
	if (size == CAPACITY) {
	    throw new FullArrayException("Array is full, cannot insert more candidates");
	}
	for (int i = 0; i < data.length; i++) {
	    if (data[i] == null) {
		data[i] = element;
		size++;
		break;
	    }
	}
    }

    /**
     * removes an element from the tree
     * @param E element to remove from tree if there
     * @return true if element was in tree and removed, false otherwise
     */
    public boolean remove(E element) {
	for (int i = 0; i < data.length; i++) {
	    if (data[i].equals(element)) {
		//if is last element remove without swapping
		if (i == size-1) {
		    data[size-1] = null;
		    size--;
		    return true;
		}
		else {
		    //swap with last element and remove
		    swap(i, size-1);
		    data[size-1] = null;
		    size--;
		    return true;
		}
	    }
	}
	return false;
    }

    /**
     * returns the nodes of the binary tree in
     * pre-order traversal order, root, left tree, right tree
     * @return String the format of pre order tree 
     */
    public String toStringPreOrder() {
	return toStringPreOrderRec(0);
    }

    private String toStringPreOrderRec(int parent) {
	if (data[parent] == null) {
	    return "";
	}
	return data[parent] + " " + toStringInOrderRec(left(parent)) + toStringInOrderRec(right(parent));
    }

    /**
     * returns the nodes of the binary tree in
     * in-order traversal order, left tree, root, right tree
     * @return String the format of in order tree 
     */
    public String toStringInOrder() {
	return toStringInOrderRec(0);
    }

    private String toStringInOrderRec(int parent) {
	if (data[parent] == null) {
	    return "";
	}
	return toStringInOrderRec(left(parent)) + data[parent] + " " + toStringInOrderRec(right(parent));
    }

    /**
     * returns the nodes of the binary tree in
     * post-order traversal order, left tree, right tree, root
     * @return String the format of post order tree 
     */
    public String toStringPostOrder() {
	return toStringPostOrderRec(0);
    }

    private String toStringPostOrderRec(int parent) {
	if (data[parent] == null) {
	    return "";
	}
	return toStringInOrderRec(left(parent)) + toStringInOrderRec(right(parent)) + data[parent] + " ";
    }

    @Override
    public String toString() {
	return "\nTree:\nPre:\t" + toStringPreOrder().trim() + "\nIn:\t" + toStringInOrder().trim() + "\nPost:\t" + toStringPostOrder().trim();
    }

    /**
     * returns String of tree in breadth first order
     * @return String in order from root to last element level by level
     */
    public String toStringBreadthFirst() {
	String tree = "";
	for (int i = 0; i < data.length; i++) {
	    if (data[i] != null) {
		tree += data[i] + " ";
	    }
	    else {
	        tree += "- ";
	    }
	}
	return tree;
    }

    /**
     * helper method to return parent index
     * @param int i index of child
     * @return int index of parent
     */
    protected int parent(int i) {
	return (i - 1) / 2;
    }

    /**
     * helper method to return left child
     * @param int i index of parent
     * @return int index of left child
     */
    protected int left(int i) {
	return 2 * i + 1;
    }

    /**
     * helper method to return right child
     * @param int i index of parent
     * @return int index of right child
     */
    protected int right(int i) {
	return 2 * i + 2;
    }

    /**
     * helper method to swap two elements in the tree
     * @param int i index of element to swap
     * @param int j index of element 2 to swap
     */
    protected void swap(int i, int j) {
	E temp = data[i];
        data[i] = data[j];
	data[j] = temp;
    }

    /**
     * helper method to return index of a specific element in tree
     * @param E element to search tree for
     * @return int of element's index if found, else return -1
     */
    protected int containsIdx(E element) {
	//if candidates have matching full names return index
	for (int i = 0; i < data.length; i++) {
	    if (data[i] == null) {
		return -1;
	    }

	    if (data[i].equals(element)) {
		return i;
	    }
	}
	return -1;
    }
}
