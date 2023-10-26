import DS.Trie;
import java.util.List;
import java.util.TreeSet;

public class WordSearch {
    private Grid grid;
    private Trie trie;

    // Constructor
    public WordSearch(Grid grid, List<String> wordList) {
        this.grid = grid;
        // Convertit wordList en un array et instatie le Trie
        // Puis nous allons comparer chaque cellule de la grille avec un noeud du Trie, si ca match,
        // on continue la recherche, sinon, on s'arrete
        this.trie = new Trie(wordList.toArray(new String[0]));
    }

    /**
     * Methode recursive qui traverse une grille et cherche les mots
     * @param i - rangee actuelle
     * @param j - colonne actuelle
     * @param node - TrieNode actuelle
     * @param path - chemin prit pour arriver a la Cell actuelle
     * @param results - resultats dans le format requis, dans un TreeSet ils sont ordonnes lexicographiquement
     */
    private void search(int i, int j, Trie.TrieNode node, StringBuilder path, StringBuilder currentWord, TreeSet<String> results) {
        char currentChar = grid.getCell(i, j).getValue();

        // Verifie si le caractere de la cellule actuelle n'est pas present dans le Trie
        if (!node.children.containsKey(currentChar)) {
            return;
        }

        // Stringbuilder permet de plus facilement manipuler les strings
        StringBuilder newPath = new StringBuilder(path); // Clone le chemin actuel
        newPath.append("(").append(i).append(",").append(j).append(")->");

        currentWord.append(currentChar);

        Trie.TrieNode nextNode = node.children.get(currentChar);

        // Si un mot valide est forme
        if (nextNode.isWord) {
            results.add(currentWord.toString() + " " + newPath.substring(0, newPath.length() - 2)); // enleve le dernier "->"
        }

        // Cherche les voisins de la cellule actuelle, incluant elle-meme
        List<Cell> neighbors = grid.getNeighbors(grid.getCell(i, j));

        // Pour chaque voisin, (incluant la cellule elle-meme), faire un appel recursif
        for (Cell neighbor : neighbors) {
            search(neighbor.getRow(), neighbor.getCol(), nextNode, new StringBuilder(newPath),
                    new StringBuilder(currentWord), results);
        }
    }

    /**
     * Initie la recherche de mot a partir de chaque cellule de la grille
     *
     * @return TreeSet contenant les mots trouves et leur chemin (en ordre lexicographique)
     */
    public TreeSet<String> findWords() {
        TreeSet<String> results = new TreeSet<>();
        int rows = grid.getRows();
        int cols = grid.getCols();

        // Commence la recherche a partir de chaque cellule
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                search(i, j, trie.root, new StringBuilder(), new StringBuilder(), results);
            }
        }
        return results;
    }
}
