package autocomplete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import dictionary.Dictionary;

public class AutoCompleteDictionary implements Dictionary, AutoComplete{
	private TrieNode root;
	private int size;
	private Queue<String> predictionQueue;
	private List<String> predictions;

	public AutoCompleteDictionary() {
		root = new TrieNode();
		size = 0;
	}

	private List<String> predictCompletions(String prefix) {
		TrieNode node = root;
		char[] letters = prefix.toCharArray();
		
		for(char letter: letters){
			if(node.getChildren().containsKey(letter)){
				node = node.getNext(letter);
			}else{
				return null;
			}
		}
		
		HashMap<Character, TrieNode> children = (HashMap) node.getChildren();
		
		for(char nextChar: children.keySet()){
			node = children.get(nextChar);
			if(node.isEndsWord()){
				predictions.add(node.getEndWordText());
			}

			predictionQueue.add(node.getEndWordText());
		}
		
		return predictions;
	}
	
	@Override
	public List<String> predictCompletions(String prefix, int numOfCompletions) {
		predictionQueue = new LinkedList<String>();		
		predictions = new ArrayList<>();
		predictionQueue.add(prefix);
		
		while(!predictionQueue.isEmpty() && (predictions.size() < numOfCompletions)){
			predictCompletions(predictionQueue.remove());
		}

		print(predictions);
		return predictions;
	}

	@Override
	public void addWord(String word) {
		char[] letters = word.toCharArray();
		TrieNode node = root;
		
		for(char letter: letters){
			if(node.getChildren().containsKey(letter)){
				node = node.getNext(letter);
			}else{
				node = node.addNext(letter);
				increaseSize(1);
			}
		}
		node.setEndsWord(true);
		
	}

	@Override
	public boolean isWord(String text) {
		char[] letters = text.toCharArray();
		TrieNode node = root;
		
		for(char letter: letters){
			if(node.getChildren().containsKey(letter)){
				node = node.getNext(letter);
			}else{
				return false;
			}
		}
		
		return node.isEndsWord();
	}

	@Override
	public int size() {
		return this.size;
	}
	
	private void increaseSize(int size){
		this.size += size;
	}

	@Override
	public void print(List<String> predictions) {
		for(String prediction: predictions)
			System.out.print(prediction+" ");
		
	}

}
