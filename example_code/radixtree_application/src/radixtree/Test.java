package radixtree;

import java.io.BufferedReader;
import java.io.FileReader;

public class Test {
	
	public void buildRadixTree(RadixTree radixTree, String sentence) {
		String[] words = sentence.split(" ");
    	for (String word: words) {
    		if (word.length() != 0) {
    			word = word.replaceAll("[^a-zA-Z]", "").toString();		
    			radixTree.addWord(word.replaceAll("[^a-zA-Z]", ""));
    		}
    	}
	}
	
	public static void main(String[] args) {
//    	RadixTree radixTree = new RadixTree();
    	
//    	Test Radix Tree
//    	radixTree.addWord("SAMI");
//    	radixTree.addWord("SAMSUNG");
//    	radixTree.addWord("SYSTEM");
//    	radixTree.addWord("SAMIHUST");
//    	radixTree.addWord("HUNGARY");
//    	radixTree.addWord("H");
//    	radixTree.addWord("HUNTING");
//    	
//    	System.out.println(radixTree.searchWord("HUNTING"));
//    	radixTree.deleteWord("HUNTING");
//    	System.out.println(radixTree.searchWord("HUNTING"));
//    	radixTree.addWord("HUHU");
//    	System.out.println(radixTree.searchWord("HUNTING"));
//    	System.out.println(radixTree.searchWord(""));
//    	
//    	radixTree.print();

    	try {
    		FileReader reader = new FileReader("D:\\long.lt20194099\\Project1\\example_code\\passage.txt");
        	BufferedReader buff = new BufferedReader(reader);
        	String res = "";
        	String line = "";
        	while((line = buff.readLine()) != null) {
        		res = res + " " + line;
        	}
        	
        	String[] sentences = res.split("\\.");
//        	System.out.println(sentences.length);
//        	for (String sentence: sentences) {
//        		System.out.println(sentence);
//        	}
        	
        	
        	RadixTree[] sentenceRadixTrees = new RadixTree[sentences.length];
        	
       
        	
        	for (int i = 0; i < sentences.length; i++) {
        		sentenceRadixTrees[i] = new RadixTree();
        		new Test().buildRadixTree(sentenceRadixTrees[i], sentences[i]);
        	}
        	
        	for (RadixTree radixTree: sentenceRadixTrees) {
        		radixTree.print();
        		System.out.println("----------------------");
        	}
        	
        	String sentence = "but emotionally to function as";
        	String[] words = sentence.split(" ");
        	
        	for (int i = 0; i < sentences.length; i++) {
        		RadixTree radixTree = sentenceRadixTrees[i];
        		boolean mark = true;
        		for (String word: words) {
        			if (!radixTree.searchWord(word)) {
        				mark = false;
        				continue;
        			}
        		}
        		if (mark) {
        			System.out.println("Sentence appears in passage, " + String.valueOf(i + 1) + "th sentence");
        		}
        		
        	}
        	
        	
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
}
