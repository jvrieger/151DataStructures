import java.util.Iterator;

public interface MyIterator<E> extends Iterator<E> {
    /**
     * Returns {@code true} if the iteration has more previous elements.
     * (In other words, returns {@code true} if {@link #previous} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more previous elements
     */
    boolean hasPrevious();

    /**
     * Returns the previous element in the iteration.
     *
     * @return the previous element in the iteration
     * @throws NoSuchElementException if the iteration has no more previous elements
     */
    E previous();

    /**
     * Replaces the last element returned by {@link #next} or {@link #previous}
     * with the specified element. This method can be called only if 
     * {@link #remove} has not been called after the last call to {@link #next} 
     * {@link #previous}.  This method can be called only once per call to 
     * or {@link #next} or {@link #previous}. The behavior of an iterator
     * is unspecified if the underlying collection is modified while the
     * iteration is in progress in any way other than by calling this
     * method.
     *
     * @throws IllegalStateException if the {@code previous} or {@code next} 
     *         method has not yet been called, or the {@code remove} method 
     *         has already been called after the last call to the 
     *         {@code previous} or {@code next} method
     */
    void set(E e);
}
