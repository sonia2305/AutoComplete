package autocomplete;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TrieNode {

	private boolean endsWord;
	private String endWordText;
	private Map<Character, TrieNode> children;
	
	public TrieNode() {
		children = new HashMap<>();
		endsWord = false;
		endWordText = "";
	}

	public TrieNode(String wordText) {
		this();
		this.endWordText = wordText;
	}

	public TrieNode addNext(Character nextChar){
		if(this.children.containsKey(nextChar)){
			return this.children.get(nextChar);
		}
		
		TrieNode nextNode = new TrieNode(this.endWordText + nextChar);
		this.children.put(nextChar, nextNode);
		return nextNode;
	}
	
	public TrieNode getNext(Character nextChar){
		return this.children.get(nextChar);
	}
	
	public Set<Character> getNextValidCharacters(){
		return this.children.keySet();
	}
	
	public String getEndWordText() {
		return endWordText;
	}

	public Map<Character, TrieNode> getChildren() {
		return children;
	}
	
	public boolean isEndsWord() {
		return endsWord;
	}

	public void setEndsWord(boolean endsWord) {
		this.endsWord = endsWord;
	}
	
}
