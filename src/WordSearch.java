// Packages que nous utilisons dans la classe "WordSearch".
import DS.Trie;
import java.util.List;
import java.util.TreeSet;

// Classe allant nous permettre de trouver la solution des mots cachés.
public class WordSearch {
    private Grid grid;  // Grille à résoudre. 
    private Trie trie;  // Trie pour trouver les solutions.

    // Constructeur.
    public WordSearch(Grid grid, List<String> wordList) {

        this.grid = grid;

        // Pour convertit wordList en un array et instancier le Trie.
        this.trie = new Trie(wordList.toArray(new String[0]));
    }

    // Puis nous allons comparer chaque cellule de la grille avec un noeud du Trie, si ca match,
    // on continue la recherche, sinon, on s'arrete

    /**
     * Méthode récursive qui traverse une grille et cherche les mots.
     * @param i - Rangée actuelle
     * @param j - Colonne actuelle
     * @param node - TrieNode actuelle
     * @param path - Chemin prit pour arriver à la cellule actuelle
     * @param results - Résultats dans le format requis (ordonné lexicographiquement)
     */
    private void search(int i, int j, Trie.TrieNode node, StringBuilder path, StringBuilder currentWord, 
    TreeSet<String> results) {

        char currentChar = grid.getCell(i, j).getValue();

        // Vérifie si le caractère de la cellule actuelle n'est pas présent dans le Trie.
        if (!node.children.containsKey(currentChar)) {
            return;
        }

        // Mise à jour du chemin avec une nouvelle cellule.
        StringBuilder newPath = new StringBuilder(path); 
        newPath.append("(").append(i).append(",").append(j).append(")->");

        // Ajout du caractère du mot actuellement cherché.
        currentWord.append(currentChar);
        
        // Pour que le préfixe du mot recherché contenant le caractère actuel devienne la valeur
        // associé au prochain noeud.
        Trie.TrieNode nextNode = node.children.get(currentChar);

        // Vérifie si un mot valide est formé.
        if (nextNode.isWord) {
            results.add(currentWord.toString() + " " + newPath.substring(0, newPath.length() - 2)); // Enlève le dernier "->".
        }

        // Cherche les voisins de la cellule actuelle.
        List<Cell> neighbors = grid.getNeighbors(grid.getCell(i, j));

        // Inclure la cellule actuelle en tant que voisin (en accordance avec la spécification)
        neighbors.add(grid.getCell(i, j));

        // Pour chaque voisin, (incluant la cellule elle-même), faire un appel recursif de la méthode "search".
        for (Cell neighbor : neighbors) {
            search(neighbor.getRow(), neighbor.getCol(), nextNode, new StringBuilder(newPath),
                    new StringBuilder(currentWord), results);
        }
    }

    /**
     * Initie la recherche de mot à partir de chaque cellule de la grille.
     *
     * @return TreeSet contenant les mots trouvés et leur chemin (en ordre lexicographique)
     */
    public TreeSet<String> findWords() {
        TreeSet<String> results = new TreeSet<>();
        int rows = grid.getRows();
        int cols = grid.getCols();

        // Commence la recherche à partir de chaque colonne (en ordre croissant) de la première 
        // rangée jusqu'à la dernière rangée.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                search(i, j, trie.root, new StringBuilder(), new StringBuilder(), results);
            }
        }
        return results;
    }
}
