public interface LabBinaryTree<E extends Comparable<E>> {
    E getRootElement();
    int size();
    boolean isEmpty();
    void insert(E element);
    boolean remove(E element);    

    String toStringBreadthFirst();
}
