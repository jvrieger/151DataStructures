public interface LabPriorityQueue<E extends Comparable<E>> extends LabBinaryTree<E>{
    // returns the element with the minkey, or null when PQ is empty
    E peek();
}