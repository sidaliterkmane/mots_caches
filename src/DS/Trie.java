package DS;

import java.util.HashMap;
import java.util.Map;

// Classe directement prise des notes de cours 3.1 Arbres Généraux, slide 17, Trie.java
// Incluant ses fonctionnalités

// Trie class for a standard Trie with character nodes
public class Trie {

    private static class TrieNode {
        private Map<Character, TrieNode> children; // Map for children nodes
        private boolean isWord; // Boolean for marking word nodes; remove the prefix restriction

        // Constructor
        public TrieNode() {
            children = new HashMap<>();
            this.isWord = false; // a node is not storing a word by default
        }

        @Override
        public String toString() {
            return "Node< " + this.children.size() + " children, is a word? " + this.isWord + " >";
        }
    }

    private final TrieNode root = new TrieNode(); // root of the Trie

    public void insert( String word ) {
        // insert a word
        TrieNode node = root; // take the root
        for (char c: word.toCharArray()) {
            // if there is no node with char c in the children, create it
            if (node.children.get(c) == null) node.children.put(c, new TrieNode());
            node = node.children.get(c); // move to it to process the next char
        }
        node.isWord = true; // last node accessed was for the last char of word
    }

    // constructor with initial list of words
    public Trie(String[] words) { for (String word: words) this.insert(word);}
}
