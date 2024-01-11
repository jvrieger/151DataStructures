public class Main {
    public static void main(String[] args) {
	ArrayBinaryTree<Integer> intTree = new ArrayBinaryTree<Integer>();
	System.out.println("TREE");
	for (int i = 1; i < 21; i++) {
	    intTree.insert(i);
	}

	System.out.println("size: " + intTree.size());
	System.out.println(intTree.toStringBreadthFirst());

	System.out.println("root: " + intTree.getRootElement());

	System.out.println("removing 5: " + intTree.remove(5));

	System.out.println("size: " + intTree.size());
	System.out.println(intTree.toStringBreadthFirst());

	ArrayHeap<Integer> intHeap = new ArrayHeap<Integer>();
	System.out.println("HEAP");
	for (int i = 9; i > -1; i--) {
	    intHeap.insert(i);
	}

	System.out.println("size: " + intHeap.size());
	System.out.println(intHeap.toStringBreadthFirst());
	System.out.println("peek: " + intHeap.peek());
    }
}
