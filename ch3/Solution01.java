/* 
3.1 Three in One:
	Descirbe how you could use a single array to implement three stacks.
*/

import java.io.*;
import java.util.*;

/*	Solution

Analysis:
	Divide the array into 3 equal size sub-array, and use them as the backup 
	array of the stacks.

Assumption:
	The stacks are fix-size, i.e. without resize. Because resize makes the 
	implementation less efficient, and here we care more about efficiency.

	The stacks only store integer
		
	Time: O(1) for push()/pop()
	Space: O(1), the array in the requirements doesn't count
*/

class MultiStack {
	private int[] array; //backup array for the stacks
	private int stackCapacity;
	private int[] startIndex = new int[3]; //start index for each stack
	private int[] sizes = new int[3]; //size of each stack

	public MultiStack(int numOfStacks, int capacity) {
		this.array = new int[numOfStacks * capacity];

		for (int i = 0; i < numOfStacks; i++) {
			this.startIndex[i] = i * capacity;
			this.sizes[i] = 0;
		}

		this.stackCapacity = capacity;
	}

	//ret: true if success, false if no more space available
	public boolean push(int stackIndex, Integer e) {
		//check if there is space available
		if (this.sizes[stackIndex] >= this.stackCapacity)
			return false;

		//push element
		int pushIndex = this.lastElementsIndex(stackIndex) + 1;
		this.array[pushIndex] = e;

		this.sizes[stackIndex]++;

		return true;
	}

	public Integer pop(int stackIndex) {
		//check if there is element to pop
		if (this.isEmpty(stackIndex))
			throw new EmptyStackException();

		//pop element
		int popIndex = this.lastElementsIndex(stackIndex);
		Integer e = this.array[popIndex];

		this.sizes[stackIndex]--;

		return e;
	}

	public Integer peek(int stackIndex) {
		//check if there is element to peek
		if (this.isEmpty(stackIndex))
			throw new EmptyStackException();

		//read the top of this stack
		int popIndex = this.lastElementsIndex(stackIndex);
		Integer e = this.array[popIndex];

		return e;
	}

	public boolean isEmpty(int stackIndex) {
		return this.sizes[stackIndex] == 0;
	}

	public int size(int stackIndex) {
		return this.sizes[stackIndex];
	}

	private int lastElementsIndex(int stackIndex) {
		return this.startIndex[stackIndex] + this.sizes[stackIndex] - 1;
	}
}

public class Solution01 {
	public static void main(String[] args) {
		System.out.println("3.1 Three in One");
		test();
	}

	//get input
	private static void test() {
		MultiStack stack = new MultiStack(3, 3);

		//add 0 to 4
		for (int i = 0; i <= 2; i++)
			stack.push(2, i);

		System.out.println("Push to a full stack: " + stack.push(2, 3));
		System.out.println("Peek: " + stack.peek(2));
		System.out.println("Pop: " + stack.pop(2));
		System.out.println("Peek: " + stack.peek(2));
		System.out.println("Size: " + stack.size(2));
		System.out.println("IsEmpty: " + stack.isEmpty(2));

		System.out.println("Peek empty stack: " + stack.peek(0));
	}
}