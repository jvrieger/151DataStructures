public class Main {
    public static void main(String[] args) {

	DoubleStack<Integer> stack = new DoubleStack<Integer>(10);

	stack.push(1, 1);
	stack.push(1, 2);
	stack.push(2, 10);
	stack.push(1, 3);
	stack.push(2, 9);
	stack.push(1, 4);
	stack.push(2, 8);
	stack.push(2, 7);
	stack.push(2, 6);
	stack.push(1, 5);

	System.out.println("Stack 1 after pushing");
	stack.printStack(1);
	System.out.println("Stack 2 after pushing");
	stack.printStack(2);

	stack.pop(1);
	stack.pop(2);

	System.out.println("Stack 1 after pop");
	stack.printStack(1);
	System.out.println("Stack 2 after pop");
	stack.printStack(2);

	System.out.println("Stack 1's top");
	System.out.println(stack.top(1));
	System.out.println("Stack 2's top");
	System.out.println(stack.top(2));

	System.out.println("Stack 1's size");
	System.out.println(stack.size(1));
	System.out.println("Stack 2's size");
	System.out.println(stack.size(2));

	System.out.print("Stack 1 is Empty: ");
	System.out.println(stack.isEmpty(1));
	System.out.print("Stack 2 is Empty: ");
	System.out.println(stack.isEmpty(2));
    }
}
