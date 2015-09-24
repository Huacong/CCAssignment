/* 
4.1 Route Between Nodes:
	Given a directed graph, design an algorithm to find out whether there is a 
	route between two nodes.
*/

import java.io.*;
import java.util.*;

//Graph
class GraphNode {
	public int id;
	public List<Integer> neighbors = new ArrayList<Integer>();
	public boolean visited = false;

	public GraphNode(int id) {
		this.id = id;
	}

	public void addAdjacentNode(int id) {
		this.neighbors.add(id);
	}
}

class DirectedGraph {
	public Map<Integer, GraphNode> nodes = new HashMap<Integer, GraphNode>();

	public void addNode(int id) {
		this.nodes.put(id, new GraphNode(id));
	}

	//return null if not exists
	public GraphNode getNode(int id) {
		return this.nodes.get(id);
	}

	//add edge from node1 to node2
	public void addEdge(int id1, int id2) {
		GraphNode node1 = this.nodes.get(id1);
		node1.addAdjacentNode(id2);
	}
}

public class Solution01 {
	public static void main(String[] args) {
		System.out.println("4.1 Route Between Nodes");

		test1();
		test2();
	}

	private static void test1() {
		DirectedGraph g = generateGraph();
		//System.out.println(BFS(g, 0, 3));
		System.out.println(BFS(g, 3, 2));
	}

	private static void test2() {
		DirectedGraph g = generateGraph();
		//System.out.println(DFS(g, 0, 3));
		System.out.println(DFS(g, 3, 2));
	}

	private static DirectedGraph generateGraph() {
		DirectedGraph g = new DirectedGraph();

		for (int i = 0; i <= 3; i++)
			g.addNode(i);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(3, 1);

		return g;
	}

	/*	Solution 1

		Use BFS to traverse the tree

		Better for start and end in a short distance, compare with DFS

		Time: O(|V| + |E|)
		Space: O(|V|) for queue
	*/
	private static boolean BFS(DirectedGraph g, int startId, int endId) {
		//queue for BFS
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(startId);

		while(!queue.isEmpty()) {
			GraphNode node = g.getNode(queue.poll());

			if (node.id == endId) //reach the destination
				return true;
			
			//set visited to avoid cycle
			node.visited = true;

			for (Integer i : node.neighbors) {
				GraphNode neighbor = g.getNode(i);

				if (neighbor.visited == false)
					queue.offer(i);//enqueue its children
			}
		}

		return false;
	}

	/*	Solution 2

		Use DFS to traversal the tree

		Better for start and end in a long distance, compare with BFS

		Time: O(|V| + |E|)
		Space: O(|V|) for stack
	*/
	private static boolean DFS(DirectedGraph g, int startId, int endId) {
		//queue for BFS
		ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
		stack.push(startId);

		while(!stack.isEmpty()) {
			GraphNode node = g.getNode(stack.pop());

			if (node.id == endId) //reach the destination
				return true;
			
			//set visited to avoid cycle
			node.visited = true;

			//push the neighbors backward, mimic recursion's order
			for (int i = node.neighbors.size() - 1; i >= 0; i--) {
				int neighborId = node.neighbors.get(i);
				GraphNode neighbor = g.getNode(neighborId);

				if (neighbor.visited == false)
					stack.push(neighborId);//enqueue its children
			}
		}

		return false;
	}
}