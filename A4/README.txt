Julia Rieger
A4

No known bugs/limitations

Part 1
Design: I designed my data structure to be 2 FILO stacks to feed into
	each other, creating a FIFO structure, or queue. I use enqueue and
	dequeue to simulate the movement of an actual queue in stacks.
	
enqueue implementation: To enqueue in a queue means to add an element
			at the back of the queue. To simulate this in the
			stacks I pushed the element onto stack 1. The top of
			stack 1 acts as the rear of my queue because of its
			FILO nature. One design issue I encountered was that I
			needed to make sure s2 was empty before enqueueing onto
			s1. If I enqueued onto s1 without shifting everything
			from s2 over first, it would be like inserting into the
			middle of a queue, and created problems. Before I did
			operations, I made sure all elements the queue were either
			all in s1 or all in s2 so as not to mess up the order of
			the queue.
			
dequeue implementation: To dequeue in a queue means to remove the first element
			of the queue. Because a queue is FIFO and a stack is
			FILO, I had to first move all elements from stack 1
			to stack 2 (they are now in the correct queue order in
			stack 2, whereas in stack 1 they were in reverse order).
			and then I popped off the top of stack 2 (since it is in
			the correct order of the queue, the top of stack 2 is also
			the front of the queue).
			
Big-O analysis
int size(): O(1)
boolean isEmpty(): O(1)
void enqueue(E e): O(n)
E first(): O(n)
E dequeue(): O(n)
String toString(): O(n)
