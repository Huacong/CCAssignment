/* 
3.3 Stack of Plates:
	Imagine a (literal) stack of plates. If the stack gets too high, it might 
	topple. Therefore, in real life, we would likely start a new stack when
	the previous stack exceeds some threshold. Implement a data structure
	SetOfStacks that mimics this. SetOfStacks should be composed of several
	stacks and should create a new stack once the previous one exceeds capacity.
	SetOfStacks.push() and SetOfStacks.pop() should behave identically to a 
	single stack (that is, pop() should return the same values as it would if
	there were just a single stack).

	FOLLOW UP:
	Implement a function popAt(int index) which performs a pop operation on a 
	specific sub-stack.
*/

import java.io.*;
import java.util.*;

/*	Solution

Analysis: 
	Just use bunch of stacks, when one hits the limit, create a new one to
	increase the total capacity.

Assumption:
	The stack is just used for storing integer

	The capacity of each stack is limit, but the total capacity is unlimit

	Substack could be not full, which means after popAt(), a certain stack could
	leave space unused until that whole stack is empty and garbage collected(manually)

Implementation:
	Use ArrayList to maintain stacks

	Time: O(1) for push and pop, O(1) average for popAt() (may cause garbage collected)
	Space: O(1)
*/

class SetOfStacks {
	List<ArrayDeque<Integer>> setOfStacks;
	int capacity; //the threshold for each sub stack

	SetOfStacks(int capacity) {
		setOfStacks = new ArrayList<ArrayDeque<Integer>>();

		if (capacity < 1)
			throw new IllegalArgumentException();

		this.capacity = capacity;
	}

	public void push(Integer e) {
		//get the last stack to push
		ArrayDeque<Integer> lastStack = this.getLastStack();

		//need a new stack
		if (lastStack == null || lastStack.size() >= this.capacity) {
			lastStack = new ArrayDeque<Integer>();
			setOfStacks.add(lastStack);
		}

		lastStack.push(e);
	}

	public Integer pop() {
		//get the last stack to pop
		ArrayDeque<Integer> lastStack = getLastStack();
		if (lastStack == null)
			throw new EmptyStackException();

		Integer e = lastStack.pop();

		//manually collect the space used by last stack
		if (lastStack.isEmpty())
			setOfStacks.remove(setOfStacks.size()-1);

		return e;
	}

	//index start from 0
	public Integer popAt(int index) {
		if (index + 1 > setOfStacks.size())
			throw new IllegalArgumentException();

		ArrayDeque<Integer> stack = setOfStacks.get(index);
		Integer e = stack.pop();

		//manually collect the space used by empty stack, leaves no holes in the middle
		if (stack.isEmpty())
			setOfStacks.remove(index);

		return e;
	}

	private ArrayDeque<Integer> getLastStack() {
		if (this.setOfStacks.size() > 0)
			return this.setOfStacks.get(this.setOfStacks.size()-1);
		else
			return null;
	}
}

public class Solution03 {
	public static void main(String[] args) {
		System.out.println("3.3 Stack of Plates");
		test();
	}

	private static void test() {
		SetOfStacks setOfStacks = new SetOfStacks(1);//stack's capacity is 1

		//push 0 to 4
		for (int i = 0; i <= 4 ; i++) {
			setOfStacks.push(i);
		}
		
		//test pop()
		System.out.println("Last element of all: " + setOfStacks.pop());

		//test popAt()
		System.out.println("Last element of 2nd stack: " + setOfStacks.popAt(2));
	}
}