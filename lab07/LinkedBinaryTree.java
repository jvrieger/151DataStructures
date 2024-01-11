public class LinkedBinaryTree<E extends Comparable<E>> implements LabBinaryTree<E> {

    private int size;
    private Node<E> root;

    private class Node<E> {
	private E data;
	private Node<E> left;
	private Node<E> right;
	
	public Node(E data, Node<E> left, Node<E> right) {
	    this.data = data;
	    this.left = left;
	    this.right = right;
	}
	public E getData() {
	    return this.data;
	}
	public Node<E> getLeft() { 
	    return this.left;
	}
	public Node<E> getRight() {
	    return this.right;
	}
	public void setData(E data) {
	    this.data = data;
	}
	public void setLeft(Node<E> left) {
	    this.left = left;
	}
	public void setRight(Node<E> right) {
	    this.right = right;
	}
	public boolean isLeaf() {
	    return this.left == null && this.right == null;
	}
    }
    
    public int size() {
	return this.size;
    }
    
    public boolean isEmpty() {
	return this.size == 0;
    }
    
    public void insert(E element) {
	root = insertRec(root, element);
	size++;
    }

    public Node<E> insertRec(Node<E> root1, E key) {
	if (root1 == null) {
	    Node<E> rt = new Node<E>(key, null, null);
	    return rt;
	}
	else if (root1.getData().compareTo(key) == 1) {
	    root1.setLeft(insertRec(root1.getLeft(), key));
	}
	else {
	    root1.setRight(insertRec(root1.getRight(), key));
	}
	return root1;
    }
    
    public boolean contains(E element) {
	return containsRec(root, element);
    }

    public boolean containsRec(Node<E> root, E key) {
	if (root == null) {
	    return false;
	}
	else if (root.getData().compareTo(key) == 0) {
	    return true;
	}
	else if (root.getData().compareTo(key) == 1) {
	    return containsRec(root.getLeft(), key);
	}
	else {
	    return containsRec(root.getRight(), key);
	}   
    }
    
    public int height() {
	return heightRec(root);
    }

    public int heightRec(Node<E> root) {
	if (root == null) {
	    return -1;
	}
	else if (heightRec(root.getLeft()) > heightRec(root.getRight())) {
	    return heightRec(root.getLeft())+1;
	}
	else {
	    return heightRec(root.getRight())+1;
	}
    }
    
    /*
     * prints out the nodes of the binary tree in
     * in-order traversal order, left tree, root, right tree
     */
    public void printInOrder() {
	inOrderRec(root);
    }

    public void inOrderRec(Node<E> root) {
	if (root == null) {
	    return;
	}
	inOrderRec(root.getLeft());
	System.out.printf("%s, ", root.getData());
	inOrderRec(root.getRight());
    }
}
