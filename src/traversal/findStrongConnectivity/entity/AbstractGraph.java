package traversal.findStrongConnectivity.entity;

import java.util.*;

public abstract class AbstractGraph<V> implements Graph<V> {
	protected List<V> vertices;
	protected List<List<Integer>> neighbors;

	protected AbstractGraph(int[][] edges, V[] vertices) {
		this.vertices = new ArrayList<V>();

		for (V v : vertices) {
			this.vertices.add(v);
		}

		createAdjacencyLists(edges, vertices.length);
	}

	protected AbstractGraph(List<Edge> edges, List<V> vertices) {
		this.vertices = vertices;
		createAdjacencyLists(edges, vertices.size());
	}

	@SuppressWarnings("unchecked")
	protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
		vertices = new ArrayList<>();
		for (int i = 0; i < numberOfVertices; i++) {
			vertices.add((V) (new Integer(i)));
		}

		createAdjacencyLists(edges, numberOfVertices);
	}

	@SuppressWarnings("unchecked")
	protected AbstractGraph(int[][] edges, int numberOfVertices) {
		vertices = new ArrayList<>();
		for (int i = 0; i < numberOfVertices; i++) {
			vertices.add((V) (new Integer(i)));
		}

		createAdjacencyLists(edges, numberOfVertices);
	}

	private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
		neighbors = new ArrayList<>();

		for (int i = 0; i < numberOfVertices; i++) {
			neighbors.add(new ArrayList<>());
		}

		for (int i = 0; i < edges.length; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			neighbors.get(u).add(v);
		}
	}

	private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
		neighbors = new ArrayList<>();

		for (int i = 0; i < numberOfVertices; i++) {
			neighbors.add(new ArrayList<>());
		}

		for (Edge edge : edges) {
			neighbors.get(edge.u).add(edge.v);
		}
	}

	public int getSize() {
		return vertices.size();
	}

	public List<V> getVertices() {
		return vertices;
	}

	public V getVertex(int index) {
		return vertices.get(index);
	}

	public int getIndex(V v) {
		return vertices.indexOf(v);
	}

	public List<Integer> getNeighbors(int index) {
		return neighbors.get(index);
	}

	public int getDegree(int v) {
		return neighbors.get(v).size();
	}

	public int[][] getAdjacencyMatrix() {
		int[][] adjacencyMatrix = new int[getSize()][getSize()];

		for (int i = 0; i < neighbors.size(); i++) {
			for (int j = 0; j < neighbors.get(i).size(); j++) {
				int v = neighbors.get(i).get(j);
				adjacencyMatrix[i][v] = 1;
			}
		}

		return adjacencyMatrix;
	}

	public void printAdjacencyMatrix() {
		int[][] adjacencyMatrix = getAdjacencyMatrix();
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix[0].length; j++) {
				System.out.print(adjacencyMatrix[i][j] + " ");
			}

			System.out.println();
		}
	}

	public void printEdges() {
		for (int u = 0; u < neighbors.size(); u++) {
			System.out.print("Vertex " + u + ": ");
			for (int j = 0; j < neighbors.get(u).size(); j++) {
				System.out.print("(" + u + ", " + neighbors.get(u).get(j) + ")");
			}
			System.out.println();
		}
	}

	public static class Edge {
		public int u;
		public int v;

		public Edge(int u, int v) {
			this.u = u;
			this.v = v;
		}
	}
}
