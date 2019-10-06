import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;

public class MatrixPermuter {
    private Matrix M;
    private int maxDepth;
    private HashMap<String, String> vocabulary;
    private HashMap<String, Boolean> masterDict;

    public MatrixPermuter(Matrix M, int maxDepth) {
        this.M = M;
        this.maxDepth = maxDepth;
        vocabulary = new HashMap<>();
        masterDict = new HashMap<>();
    }


    public void readVocabulary(String path) {
        File file = new File(path);
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {
                vocabulary.put(sc.nextLine(), "");
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void permutateMatrix() {
        for (Tile tile: M.getTileArray()) {
            getWords(tile);
        }
    }

    private void getWords(Tile tile) {
        ArrayDeque<Tile> tileSequence = new ArrayDeque<>();
        branch(tile, tile, "", tileSequence, 0);
        resetTiles();
    }

    private void resetTiles() {
        for(Tile tile: M.getTileArray()) {
            tile.reset();
        }
    }

    private void branch(Tile original, Tile currentTile, String sequence, ArrayDeque<Tile> tileSequence, int depth) {
        currentTile.visit();
        tileSequence.add(currentTile);
        depth++;
        sequence = sequence + currentTile.getValue();

        if (vocabulary.containsKey(sequence) && !masterDict.containsKey(sequence)) {
            masterDict.put(sequence, true);
            original.addWord(sequence);
            original.addSequence(tileSequence);

        }

        if(this.maxDepth <= depth) {
            currentTile.reset();
            return;
        } else {
            for(Tile tile: currentTile.getNeighbours()) {
                if(!tile.isVisited()) {
                    branch(original, tile, sequence, tileSequence.clone(), depth);
                }
            }
        }
        currentTile.reset();
    }
}
