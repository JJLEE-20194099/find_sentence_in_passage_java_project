package radixtree;

public class TreeEdge {
	public String label;
	public TreeNode nextNode;
	
	public TreeEdge(String label) {
		this.label = label;
		this.nextNode = new TreeNode(true);
	}
	
	public TreeEdge(String label, TreeNode nextNode) {
		this.label = label;
		this.nextNode = nextNode;
	}
}
