/* 
4.7 Builde Order:
	You are given a list of projects and a list of dependencies (which is a list
	of projects, where the first project is dependent on the second project). All
	of a project's dependencies must be built before the project is. Find a build
	order that will allow the projects to be built. If there is no valid build
	order, return an error.

	EXAMPLE
	Input:
		projects: a, b, c, d, e, f
		dependencies: (d, a), (b, f), (d, b), (a, f), (c, d)
	Output: f, e, a, b, d, c
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
		neighbors.add(id);
	}
}

class DirectedGraph {
	public Map<Integer, GraphNode> nodes = new HashMap<Integer, GraphNode>();
	public int numOfNodes = 0;

	public void addNode(int id) {
		this.nodes.put(id, new GraphNode(id));
		this.numOfNodes++;
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

public class Solution07 {
	public static void main(String[] args) {
		System.out.println("4.7 Build Order");

		test();
	}

	private static void test() {
		DirectedGraph g = generateGraph();
		List<Integer> result = buildOrder(g);
		
		for (Integer i : result) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	//generate graph
	/*
	Input:
		projects: 0, 1, 2, 3, 4, 5
		dependencies: (3, 0), (1, 5), (3, 1), (0, 5), (2, 3)
	Output:
		4, 5, 1, 0, 3, 2
	*/
	private static DirectedGraph generateGraph() {
		DirectedGraph g = new DirectedGraph();

		for (int i = 0; i <= 5; i++)
			g.addNode(i);

		g.addEdge(0, 3);
		g.addEdge(5, 1);
		g.addEdge(1, 3);
		g.addEdge(5, 0);
		g.addEdge(3, 2);

		return g;
	}

	/*	Solution: Topological sort
	
	Assumptions:
		The graph has no cycle, i.e., it must has solution.

		The nodes' id is from 0 to |V|-1

	Implementation:
		Find the nodes with indegree 0, which means it has no dependencies. 
		Remove it from the graph and update indegree of its neighbors. Repeat 
		steps above until all the nodes are removed.

		Time: O(|V|+|E|) for initialize indegree[], O(|V|+|E|) for remove nodes 
			  and update neighbors
		Space: O(n) for indegree[] and queue
	*/
	private static List<Integer> buildOrder(DirectedGraph g) {
		List<Integer> result = new ArrayList<Integer>();
		if (g == null) 
			return result;

		//initialize indegree
		int[] indegree = new int[g.numOfNodes];
		for (int i = 0; i < g.numOfNodes; i++) {
			for (Integer nid : g.getNode(i).neighbors) {
				indegree[nid]++;
			}
		}

		//init queue
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] <= 0) {
				queue.offer(i);
			}
		}

		while (!queue.isEmpty()) {
			int nodeId = queue.poll();

			result.add(nodeId);

			removeNode(g, nodeId, indegree, queue);
		}

		return result;
	}

	private static void removeNode(DirectedGraph g, int nodeId, int[] indegree, 
		ArrayDeque<Integer> queue) {

		GraphNode node = g.getNode(nodeId);
		for (Integer nid : node.neighbors) {
			indegree[nid]--;

			if (indegree[nid] <= 0)
				queue.offer(nid);
		}
	}
}