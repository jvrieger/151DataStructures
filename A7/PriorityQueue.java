public interface PriorityQueue<E extends Comparable<E>> extends BinaryTree<E>{
    // returns the element with the minkey, or null when PQ is empty
    E peek();
    // removes and returns the element with the minkey, or null when PQ is empty
    E poll();
}