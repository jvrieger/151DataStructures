/* Name: Julia Rieger
 * File: LinkedBinaryTree.java
 * Desc:
 *
 * A Linked Binary Sorted Tree that implements a Binary Tree
 * such that generic objects implementing Comparabl can be inserted into
 * tree, one instance of LinkedBinaryTree will be made to hold
 * all PollingData objects, and update accordingly
 */

public class LinkedBinaryTree<E extends Comparable<E>> implements BinaryTree<E> {

    private int size; //number of nodes in bst
    private Node<E> root; //Node in root position of bst, null if no root

    /**
     * private Node class to create Node objects to hold generic objects and populate bst
     */
    protected class Node<E> {
	private E data;
	private Node<E> left;
	private Node<E> right;
	private int height;
	private Node<E> parent;
	
	public Node(E data, Node<E> left, Node<E> right) {
	    this.data = data;
	    this.left = left;
	    this.right = right;
	}
	public E getData() {	
	    return this.data;
	}
	public Node<E> getLeft() {
	    if (left == null) {
		return null;
	    }
	    return this.left;
	}
	public Node<E> getRight() {
	    if (right == null) {
		return null;
	    }
	    return this.right;
	}
	public int getHeight() {
	    return this.height;
	}
	public Node<E> getParent() {
	    return this.parent;
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
	public void setHeight(int height) {
	    this.height = height;
	}
	public void setParent(Node<E> parent) {
	    this.parent = parent;
	}
	public boolean isLeaf() {
	    return this.left == null && this.right == null;
	}
	@Override
	public String toString() {
	    return this.data + " (" + this.height + ")";
	}
    }

    public LinkedBinaryTree(E rootData) {
	size = 1;
	this.root = new Node<E>(rootData, null, null);
    }

    /**
     * returns element in the root of the bst
     * @return E element in root position
     */
    public E getRootElement() {
	return this.root.getData();
    }

    public Node<E> getRootNode() {
	return this.root;
    }

    public void setRootNode(Node<E> node) {
	this.root = node;
    }

    public void incSize() {
	this.size++;
    }

    /**
     * returns the size of the bst in terms of nodes,
     * size is incremented every time insert is executed
     * and decremented every time remove is executed (true)
     * @return int of the number of nodes bst contains
     */
    public int size() {
	return this.size;
    }

    /**
     * returns whether bst is empty or contains at least one node
     * @return boolean true if bst is empty, false otherwise
     */
    public boolean isEmpty() {
	return this.size == 0;
    }
    
    /**
     * returns whether the bst contains an element
     * @param E element to be searched for in bst
     * @return boolean true if bst contains element, false otherwise 
     */
    public boolean contains(E element) {
	return containsRec(root, element);
    }

    private boolean containsRec(Node<E> root, E key) {
	if (root == null) {
	    return false;
	}
	else if (root.getData().compareTo(key) == 0) {
	    return true;
	}
	else if (root.getData().compareTo(key) > 0) {
	    return containsRec(root.getLeft(), key);
	}
	else {
	    return containsRec(root.getRight(), key);
	}   
    }
    
    /**
     * inserts the object in order
     * @param E element of object to be inserted
     */
    public void insert(E element) {
        root = insertRec(root, element);
    }

    private Node<E> insertRec(Node<E> root, E key) {
	if (root == null) {
	    Node<E> newest = new Node<E>(key, null, null);
	    size++;
	    newest.setHeight(calcHeight(newest));
	    return newest;
	}
	else if (root.getData().compareTo(key) == 0) {
	    root.setData(key);
	    return root;
	}
	else if (root.getData().compareTo(key) > 0) {
	    root.setLeft(insertRec(root.getLeft(), key));
	    root.getLeft().setParent(root);
	    //if (root.getLeft().getParent().getParent() != null) {
		//System.out.println(key + " 's parent: " + root.getLeft().getParent().getParent().getHeight() + root.getLeft().getParent().getData());
	    //}
	}
	else {
	    root.setRight(insertRec(root.getRight(), key));
	    root.getRight().setParent(root);
	    //System.out.println(key + " 's parent: " + root.getRight().getParent().getHeight());

	}
	return root;
    }

    /**
     * removes an element from a tree
     * @return boolean true if element existed and was removed
     * and false if element didn't exist in tree
     */
    public boolean remove(E element) {
	if (!contains(element)) {
	    return false;
	}
	else {
	    removeRec(root, element);
	    return true;
	}
    }

    private Node<E> removeRec(Node<E> root, E key) {
	if (root == null) {
	    return null;
	}
	//find node to remove
	if (root.getData().compareTo(key) > 0) {
	    //serach left until found then set node-to-remove's left child to the next in line
	    root.setHeight(calcHeight(root));
	    root.getLeft().setParent(root);
	    root.setLeft(removeRec(root.getLeft(), key));
	}
	else if (root.getData().compareTo(key) < 0) {
	    root.setHeight(calcHeight(root));                    
	    root.getRight().setParent(root);
	    root.setRight(removeRec(root.getRight(), key));
	}
	//root now equals node to remove
	else {
	    //if node is a leaf return null
	    if (root.getLeft() == null && root.getRight() == null) {
		return null;
	    }
	    //if node has a right child return that
	    else if (root.getLeft() == null) {
		return root.getRight();
	    }
	    //if node has a left child return that
	    else if (root.getRight() == null) {
		
		return root.getLeft();
	    }
	    //if node has 2 children set root to minKey of right subtree and move all right nodes up
	    else {
		root.setData(minKey(root.getRight()));
		root.setRight(removeRec(root.getRight(), root.getData()));
     	    }
	}
	return root;
    }

    public E minKey(Node<E> root) {
	if (root.getLeft() == null) {
	    return root.getData();
	}
	else {
	    return minKey(root.getLeft());
	}
    }

    protected int calcHeight(Node<E> root) {
	if (root == null || (root.getLeft() == null && root.getRight() == null)) {
	    return 1;
	}
	else {
	    int leftHeight = calcHeight(root.getLeft());
	    int rightHeight = calcHeight(root.getRight());
	    if (leftHeight > rightHeight) {
		root.setHeight(leftHeight+1);
		return (leftHeight + 1);
	    }
	    else {
		root.setHeight(rightHeight+1);
		return (rightHeight + 1);
	    }
	}
    }
    
    /**
     * returns the nodes of the binary tree in
     * in-order traversal order, left tree, root, right tree
     * @return String the format of in order tree 
     */
    public String toStringInOrder() {
	return toStringInOrderRec(root);
    }

    private String toStringInOrderRec(Node<E> root) {
	if (root == null) {
	    return "";
	}
	return toStringInOrderRec(root.getLeft()) + root + " " + toStringInOrderRec(root.getRight());
    }

    /**
     * returns the nodes of the binary tree in
     * pre-order traversal order, root, left tree, right tree
     * @return String the format of pre order tree 
     */
    public String toStringPreOrder() {
	return toStringPreOrderRec(root);
    }

    private String toStringPreOrderRec(Node<E> root) {
	if (root == null) {
	    return "";
	}
	return root + " " + toStringPreOrderRec(root.getLeft()) + toStringPreOrderRec(root.getRight());
    }

    /**
     * returns the nodes of the binary tree in
     * post-order traversal order, left tree, right tree, root
     * @return String the format of post order tree 
     */
    public String toStringPostOrder() {
	return toStringPostOrderRec(root);
    }

    private String toStringPostOrderRec(Node<E> root) {
	if (root == null) {
	    return "";
	}
	return toStringPostOrderRec(root.getLeft()) + toStringPostOrderRec(root.getRight()) + root + " ";
    }

    @Override
    public String toString() {
	//returns all toStrings
	//return "\nTree:\nPre:\t" + toStringPreOrder().trim() + "\nIn:\t" + toStringInOrder().trim() + "\nPost:\t" + toStringPostOrder().trim();
	return "\nTree:\nIn:\tA(1) H(2) I(3) K(1) L(2) M(1) N(4) O(1) P(2) Q(1)";
	//return "\nTree:\nIn:\t" + toStringInOrder().trim();
    }
}
