package traversal.findStrongConnectivity.entity;

public interface Graph<V> {
	public int getSize();
	
	public java.util.List<V> getVertices();

	public V getVertex(int index);
	
	public int getIndex(V v);

	public java.util.List<Integer> getNeighbors(int index);
	
	public int[][] getAdjacencyMatrix();
	
	public void printAdjacencyMatrix();
}
