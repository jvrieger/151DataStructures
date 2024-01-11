public class DoubleStack<E> {

    private E[] theArray; //stores two stacks, one starts from front, two from back
    private int top1; //top index of stack one, grows from front towards middle
    private int top2; //top index of stack two, grows from back towards middle

    public DoubleStack(int capacity) {
	
	theArray = (E[]) new Object[capacity];
	top1 = -1;
	top2 = theArray.length;
    }

    public void push(int stackId, E e) {

	if (top2 - top1 >= 1) {
	    //resize to double size array
	}

	if (stackId==1) {
	    //put e where index + 1 is, increment index
	    theArray[top1+1] = e;
	    top1++;
	}

	if (stackId==2) {
	    //put e where index - 1 is, decrement index
	    theArray[top2-1] = e;
	    top2--;
	}
    }

    public E pop(int stackId) {
	
	if (stackId==1) {
	    if (isEmpty(1)) {
		return null;
	    }
	    E temp = theArray[top1];
	    theArray[top1] = null;
	    top1--;
	    return temp;
	}

	if (stackId==2) {
	    if (isEmpty(2)) {
		return null;
	    }
	    E temp = theArray[top2];
	    theArray[top2] = null;
	    top2++;
	    return temp;
	}

	return null;
    }

    public E top(int stackId) {

	if (stackId==1) {
	    if (isEmpty(1)) {
		return null;
	    }
	    return theArray[top1];
	}

	if (stackId==2) {
	    if (isEmpty(2)) {
		return null;
	    }
	    return theArray[top2];
	}
	
	return null;
    }

    public int size(int stackId) {

        if (stackId==1) {
	    return top1+1;
	}

	if (stackId==2) {
	    return theArray.length-top2;
	}
	
	return -1;
    }

    public boolean isEmpty(int stackId) {

	if (stackId==1) {
	    if (top1 == -1) {
		return true;
	    }
	    else {
		return false;
	    }
	}

	if (stackId==2) {
	    if (top2 == theArray.length) {
		return true;
	    }
	    else {
		return false;
	    }
	}
	    
	return false;
    }

    public void printStack(int stackId) {
	if (stackId==1) {
	    for (int i = 0; i < top1+1; i++) {
		System.out.println(theArray[i]);
	    }
	}

	if (stackId==2) {
	    for (int i = top2; i < theArray.length; i++) {
		System.out.println(theArray[i]);
	    }
	}
    }
}
