package traversal.findStrongConnectivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import traversal.findStrongConnectivity.entity.AbstractGraph.Edge;
import traversal.findStrongConnectivity.entity.UnweightedGraph;

/**
 * Tarjan算法
 * @author 卧龙
 *
 */
public class bootstrap {
    public static int count = 1;   //用于对图中顶点遍历的次序进行计数
    public static int[] DFN;    //记录图中每个节点的DFS遍历的时间戳(即次序)
    public static int[] Low;   //记录每个顶点的所在树的根节点编号
    public static boolean[] inStack;  //用于记录当前节点是否在栈中
    public static Stack<Integer> stack; //用于存放访问的节点
    
    public static void main(String[] args) {
    	stack = new Stack<Integer>();
    	
    	int numberOfVertices =(int)(Math.random() * 6 + 5);
    	DFN = new int[numberOfVertices];
    	Low = new int[numberOfVertices];
    	inStack = new boolean[numberOfVertices];
    	
    	List<Edge> edges = createEdges(numberOfVertices);
    	UnweightedGraph<Integer> g = new UnweightedGraph<>(edges, numberOfVertices);
    	g.printEdges();
    	dfs(g, 0);
    }
    
    private static List<Edge> createEdges(int numberOfVertices){
    	List<Edge> edges = new ArrayList<>();
    	int[][] temp = new int[numberOfVertices][numberOfVertices];
    	
    	for(int i = 0; i < numberOfVertices; i++) {
    		Map<Integer, Edge> map = new HashMap<>();
    		int numberOfEdge = (int)(Math.random() * (numberOfVertices - 1) + 1);
    		for(int j = 0; j < numberOfEdge; j++) {
    			int neighbor = (int)(Math.random() * numberOfVertices);
    			while(neighbor == i) {
    				neighbor = (int)(Math.random() * numberOfVertices);
    			}
    			
    			if(map.containsKey(neighbor)) continue;
    			
    			if(temp[neighbor][i] != 0) continue;
    			
    			temp[i][neighbor] = 1;
    			map.put(neighbor, new Edge(i, neighbor));
    		}
    		
    		edges.addAll(map.values());
    	}
    	
    	return edges;
    }
 
    
    public static void dfs(UnweightedGraph<Integer> g, int start) {
        DFN[start] = count++;
        Low[start] = DFN[start];
        stack.push(start);
        inStack[start] = true;
        int j = start;
        for(int i = 0;i < g.getNeighbors(start).size();i++) {
            j = g.getNeighbors(start).get(i);
            if(DFN[j] == 0) {  //顶点j未被遍历
                dfs(g, j);
                Low[start] = Math.min(Low[start], Low[j]);
            } else if(inStack[j]) {
                Low[start] = Math.min(Low[start], DFN[j]);
            }
        }
        if(DFN[start] == Low[start]) {
            System.out.print("强连通分量：");
             do {
                j = stack.pop();
                System.out.print(j+" ");
                inStack[j] = false;
            } while(start != j);
            System.out.println();
        }
        return;
    }
}
