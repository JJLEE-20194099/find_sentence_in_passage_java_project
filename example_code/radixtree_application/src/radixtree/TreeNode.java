package radixtree;

import java.util.HashMap;

public class TreeNode {
    public boolean isEndingNode;
    public HashMap<Character, TreeEdge> edges;
    
    public TreeNode(boolean isEndingNode) {
    	this.isEndingNode = isEndingNode;
    	this.edges = new HashMap<>();
    }
    
    public void addEdgeToTree(String label, TreeNode nextNode) {
    	edges.put(label.charAt(0), new TreeEdge(label, nextNode));
    }
    
    public TreeEdge getEdgeByCharacter(char currentCharacter) {
    	return edges.get(currentCharacter);
    }
    
    
    public int totalEdges() {
    	return edges.size();
    }
    
}
