/* 
3.4 Queue via Stacks:
	Implement a MyQueue class which implements a queue using two stacks.
*/

import java.io.*;
import java.util.*;

/*	Solution

Analysis:
	Use tailStack to hold the elements in the tail, which makes add()/offer() O(1)
	Use headStack to hold the elements in the head, which makes remove()/poll() O(1)

	When remove()/poll() and headStack is empty, move the elements from tailStack1 to 
	headStack, which makes the elements on the bottom of tailStack become the top of 
	headStack, and implement queue's features.

Assumption:
	The queue is just used for storing integer

	Time: O(1) for add()/offer(), average O(1) for remove()/poll(), average O(1) for 
		  element()/peek()
	Space: O(1), two stacks in the requirement don't count
*/

class QueueViaStack {
	private ArrayDeque<Integer> headStack = new ArrayDeque<Integer>();
	private ArrayDeque<Integer> tailStack = new ArrayDeque<Integer>();

	public void add(Integer e) {
		tailStack.push(e); //NullPointerException will be throw by ArrayDeque
	}

	public void offer(Integer e) {
		tailStack.push(e); //NullPointerException will be throw by ArrayDeque
	}

	//return null if empty
	public Integer poll() {
		if (this.size() < 0)
			return null;

		//move element from tailStack to headStack
		if (headStack.isEmpty()) { 
			while (!tailStack.isEmpty())
				headStack.push(tailStack.pop());
		}

		return headStack.pop();
	}

	//throw exception if empty
	public Integer remove() {
		if (this.size() < 0)
			throw new NoSuchElementException();

		//move element from tailStack to headStack
		if (headStack.isEmpty()) { 
			while (!tailStack.isEmpty())
				headStack.push(tailStack.pop());
		}

		return headStack.pop();
	}

	//return null if empty
	public Integer peek() {
		if (this.size() < 0)
			return null;

		//move element from tailStack to headStack
		if (headStack.isEmpty()) { 
			while (!tailStack.isEmpty())
				headStack.push(tailStack.pop());
		}

		return headStack.peek();
	}

	//throw exception if empty
	public Integer element() {
		if (this.size() < 0)
			throw new NoSuchElementException();

		//move element from tailStack to headStack
		if (headStack.isEmpty()) { 
			while (!tailStack.isEmpty())
				headStack.push(tailStack.pop());
		}

		return headStack.peek();
	}

	public boolean isEmpty() {
		return headStack.isEmpty() && tailStack.isEmpty();
	}

	public int size() {
		return headStack.size() + tailStack.size();
	}
}



public class Solution04 {
	public static void main(String[] args) {
		System.out.println("3.4 Queue via Stacks");
		test();
	}

	//get input
	private static void test() {
		QueueViaStack queue = new QueueViaStack();

		//add 0 to 4
		for (int i = 0; i <= 4; i++)
			queue.offer(i);

		System.out.println("Poll: " + queue.poll());
		System.out.println("Size: " + queue.size());

		queue.offer(5);
		System.out.println("Poll: " + queue.poll());
	}
}