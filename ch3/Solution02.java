/* 
3.2 Stack Min:
	How would you design a stack which, in addition to push and pop, has a 
	function min which returns the minimum element? Push, pop and min should 
	all operate in O(1) time.
*/

import java.io.*;
import java.util.*;

/*	Solution

Analysis: 
	The stack is a LIFO (first in last out) data structure. So in the 
	situation of the question, the minimum number will be updated when push()
	or pop() happen. If there is a minimum value and all the other elements push
	later are greater than it, the minimum one will be in the bottom of the stack
	until it's popped out. So it's a stable state and every element that is on 
	the upper stack could knows the minimum element under it.

Assumption: 
	The stack is just used for storing integer

Implementation:
	Use an additional stack to indicate the minimum element for all elements. 

	If the new element pushed is less than the current min, push its value to the 
	assistant stack, other wise it will share the current min.

	If the element pop is greater than current min, which means the min still in 
	the original stack, than do nothing. Otherwise (equal), pop the current min
	so that all the elements in the original stack will share the next min.


	Time: O(1), push, pop and min
	Space: O(n)
*/
class StackMin extends ArrayDeque<Integer>  {
	//ArrayDeque is preferred while Stack after Java 7 is obsolete
	ArrayDeque<Integer> minStack = new ArrayDeque<Integer>(); //stack to store min

	@Override
	public void push(Integer e) {
		super.push(e); //original stack

		//store min in min stack
		if (minStack.isEmpty() || e < this.min()) { //first node or new min element
			minStack.push(e);
		}
	}

	@Override
	public Integer pop() {
		Integer e = super.pop(); //original stack

		//current min is popped from original stack, update min stack
		if (e == this.min()) {
			minStack.pop();
		}

		return e;
	}

	public Integer min() {
		if (minStack.isEmpty()) {
			return Integer.MAX_VALUE;
		} else {
			return minStack.peek();
		}
	}
}


public class Solution02 {
	public static void main(String[] args) {
		System.out.println("3.2 Stack Min");

		test();
	}

	private static void test() {
		StackMin stack = new StackMin();

		stack.push(1);
		stack.push(3);
		stack.push(2);

		System.out.println("Min: " + stack.min());
		System.out.println("Pop: " + stack.pop());
	}
}