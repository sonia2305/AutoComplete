package dictionary;

public interface Dictionary {
	
	/**
	 * Add a word to the Dictionary
	 * @param word
	 */
	public void addWord(String word);
	
	/**
	 * Check whether a text is a valid word 
	 * @param text
	 * @return
	 */
	public boolean isWord(String text);
	
	/**
	 * Calculate the size of the Dictionary
	 * @return
	 */
	public int size();

}
