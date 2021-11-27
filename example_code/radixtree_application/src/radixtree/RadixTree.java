package radixtree;

public class RadixTree {
	private TreeNode root;
	
	public RadixTree() {
		root = new TreeNode(false);
	}
	
	
	private void printWordsByCurrentNode(TreeNode currentNode, String currentValue) {
		if (currentNode.isEndingNode) {
			System.out.println(currentValue);
		}
		
		for (TreeEdge edge: currentNode.edges.values()) {
			printWordsByCurrentNode(edge.nextNode, currentValue + edge.label);
		}
		
	}
	
	public void print() {
		printWordsByCurrentNode(root, "");
	}
	
	private int getWrongLetter(String word, String edgeWord) {
		int minLength = Math.min(word.length(), edgeWord.length());
		for (int i = 0; i < minLength; i++) {
			if (word.charAt(i) != edgeWord.charAt(i)) {
				return i;
			}
		}
		return -1;
	}
	
	public void addWord(String word) {
		TreeNode currentNode = root;
		
		int idx = 0;
		for (; idx < word.length();) {
			char currentChar = word.charAt(idx);
			TreeEdge currentEdge = currentNode.getEdgeByCharacter(currentChar);
			
			String currentString = word.substring(idx);
			
			if (currentEdge == null) {
				currentNode.edges.put(currentChar, new TreeEdge(currentString));
				break;
			}
			
			int partitionIdx = getWrongLetter(currentString, currentEdge.label);
			if (partitionIdx == -1) {
				if(currentString.length() == currentEdge.label.length()) {
					currentEdge.nextNode.isEndingNode = true;
					break;
				} else if (currentString.length() < currentEdge.label.length()) {
					String splitString = currentEdge.label.substring(currentString.length());
					currentEdge.label = currentString;
					TreeNode newNextNode = new TreeNode(true);
					TreeNode afterNewNextNode = currentEdge.nextNode;
					currentEdge.nextNode = newNextNode;
					newNextNode.addEdgeToTree(splitString, afterNewNextNode);
					break;
				} else {
					partitionIdx = currentEdge.label.length();
				}
			} else {
				String splitString = currentEdge.label.substring(partitionIdx);
				currentEdge.label = currentEdge.label.substring(0, partitionIdx);
				TreeNode nextNode = currentEdge.nextNode;
				currentEdge.nextNode = new TreeNode(false);
				currentEdge.nextNode.addEdgeToTree(splitString, nextNode);
			}
			
			
			currentNode = currentEdge.nextNode;
			idx += partitionIdx;
			
		}
	}
	
	private TreeNode deleteWord(TreeNode current, String word) {
		if(word.isEmpty()) {
			if (current.edges.isEmpty() && current != root) {
				return null;
			}
			current.isEndingNode = false;
			return current;
		}
		
		char currentChar = word.charAt(0);
		TreeEdge edge = current.getEdgeByCharacter(currentChar);
		if (edge == null || !word.startsWith(edge.label)) {
			return current;
		}
		
		TreeNode deletedNode = deleteWord(edge.nextNode, word.substring(edge.label.length()));
		if (deletedNode == null) {
			current.edges.remove(currentChar);
			if (current.totalEdges() == 0 && !current.isEndingNode && current != root) {
				return null;
			}
		} else if (deletedNode.totalEdges() == 1 && !deletedNode.isEndingNode) {
			current.edges.remove(currentChar);
			for(TreeEdge nextDeletedNode: deletedNode.edges.values()) {
				current.addEdgeToTree(edge.label + nextDeletedNode.label, nextDeletedNode.nextNode);
			}
		}
		return current;
	}
	
	public void deleteWord(String word) {
		root = deleteWord(root, word);
	}
	
	public boolean searchWord(String word) {
		TreeNode currentNode = root;
		int idx = 0;
		while(idx < word.length()) {
			char currentChar = word.charAt(idx);
			TreeEdge edge = currentNode.getEdgeByCharacter(currentChar);
			if (edge == null) {
				return false;
			}
			String subString = word.substring(idx);
			if (subString.startsWith(edge.label) == false) {
				return false;
			}
			idx += edge.label.length();
			currentNode = edge.nextNode;
		}
		return currentNode.isEndingNode;
	}
	
	
}
