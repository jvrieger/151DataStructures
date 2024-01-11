public class ArrayBinaryTree<E extends Comparable<E>> implements LabBinaryTree<E> {

    public static final int CAPACITY=100;
    private int size;
    protected E[] data;

    public ArrayBinaryTree() {
	data = (E[]) new Comparable[CAPACITY];
    }

    public int size() {
	return size;
    }

    public boolean isEmpty() {
	return size == 0;
    }

    public void insert(E element) {
	for (int i = 0; i < data.length; i++) {
	    if (data[i] == null) {
		data[i] = element;
		size++;
		break;
	    }
	}
    }

    public E getRootElement() {
	return data[0];
    }

    public boolean remove(E element) {
	for (int i = 0; i < data.length; i++) {
	    if (data[i].compareTo(element) == 0) {
		//traverse through tree backwards
		for (int j = data.length-1; j > -1; j--) {
		    //first not null spot swap w remove element
		    if (data[j] != null) {
			swap(i, j);
			data[j] = null;
			size--;
			return true;
		    }
		}
	    }
	}
	return false;
    }

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

    protected int parent(int i) {
	return (i - 1) / 2;
    }

    protected int left(int i) {
	return 2 * i + 1;
    }

    protected int right(int i) {
	return 2 * i + 2;
    }
    
    protected void swap(int i, int j) {
	E temp = data[i];
        data[i] = data[j];
	data[j] = temp;
    }

    protected int containsIdx(E element) {
	for (int i = 0; i < data.length; i++) {
	    if (data[i].compareTo(element) == 0) {
		return i;
	    }
	}
	return -1;
    }
      
    
    
}