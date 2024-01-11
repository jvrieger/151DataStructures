public interface BinaryTree<E extends Comparable<E>> {
    E getRootElement();
    int size();
    boolean isEmpty();
    boolean contains(E element);
    void insert(E element);
    boolean remove(E element);
	
    String toStringInOrder();
    String toStringPreOrder();
    String toStringPostOrder();	
}
