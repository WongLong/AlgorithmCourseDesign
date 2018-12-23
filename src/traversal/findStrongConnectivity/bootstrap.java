package traversal.findStrongConnectivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import traversal.findStrongConnectivity.entity.AbstractGraph.Edge;
import traversal.findStrongConnectivity.entity.UnweightedGraph;

/**
 * Tarjan�㷨
 * @author ����
 *
 */
public class bootstrap {
    public static int count = 1;   //���ڶ�ͼ�ж�������Ĵ�����м���
    public static int[] DFN;    //��¼ͼ��ÿ���ڵ��DFS������ʱ���(������)
    public static int[] Low;   //��¼ÿ��������������ĸ��ڵ���
    public static boolean[] inStack;  //���ڼ�¼��ǰ�ڵ��Ƿ���ջ��
    public static Stack<Integer> stack; //���ڴ�ŷ��ʵĽڵ�
    
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
            if(DFN[j] == 0) {  //����jδ������
                dfs(g, j);
                Low[start] = Math.min(Low[start], Low[j]);
            } else if(inStack[j]) {
                Low[start] = Math.min(Low[start], DFN[j]);
            }
        }
        if(DFN[start] == Low[start]) {
            System.out.print("ǿ��ͨ������");
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
