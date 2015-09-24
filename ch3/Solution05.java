/* 
3.5 Sort Stack:
	Write a program to sort a stack such that the smallest items are on the top.
	You can use an additional temporary stack, but you may not copy the elements into
	any other data structure (such as an array). The stack supports the following 
	operations: push, pop, peek, and isEmpty.
*/

import java.io.*;
import java.util.*;

public class Solution05 {
	public static void main(String[] args) {
		System.out.println("3.5 Sort Stack");
		test();
	}

	private static void test() {
		ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
		int n = 4;

		System.out.println("Before sort: ");
		for (int i = n; i > 0; i--) {//push integers in decending order
			System.out.println(i);
			stack.push(i);
		}

		sortStack(stack);

		System.out.println("After sort: ");
		for (int i = n; i > 0; i--) //check if the integers are in accending order
			System.out.println(stack.pop());
	}

	/*	Solution

Analysis:
		Use the additional stack as a sorted stack (decending order), than insert 
		element from the original stack to the sorted stack. To find the proper
		location to insert, the element greater than the insert element could be
		put back to the original stack, and moved back after the inserted element
		is in the right place.

		Time: O(n^2)
		Space: O(n), the additional temporary stack
	*/
	private static void sortStack(ArrayDeque<Integer> originalStack) {
		ArrayDeque<Integer> sortedStack = new ArrayDeque<Integer>();

		while (!originalStack.isEmpty()) {
			Integer e = originalStack.pop();

			//find the location to insert e in sortedStack, the element greater
			//then e will be push back to the original Stack
			while(!sortedStack.isEmpty() && sortedStack.peek() > e)
				originalStack.push(sortedStack.pop());

			sortedStack.push(e);
		}

		//move all the element from sortedStack (decending order) to original 
		//stack (ascending order)
		while (!sortedStack.isEmpty())
			originalStack.push(sortedStack.pop());
	}
}