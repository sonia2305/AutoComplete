package app;

import autocomplete.AutoCompleteDictionary;
import dictionary.DictionaryLoader;

public class MyApp {

	public static void main(String[] args) {
		AutoCompleteDictionary dictionary = new AutoCompleteDictionary();
		String dictFile = "resources/dict.txt";
		
		DictionaryLoader.loadDictionary(dictionary, dictFile);
		dictionary.predictCompletions("son",30);

	}

}
