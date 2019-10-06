import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Tile {

    private ArrayList<String> words;
    private ArrayList<ArrayDeque<Tile>> sequences;


    private ArrayList<Tile> neighbours;
    private char value;

    private int x_pos;
    private int y_pos;

    private Point pos;

    private boolean visited;

    public Tile(char value, int x_pos, int y_pos) {
        neighbours = new ArrayList<>();
        words = new ArrayList<>();
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.value = value;
        visited = false;
        sequences = new ArrayList<>();
    }


    public char getValue() {
        return this.value;
    }

    public void setNeighbour(Tile neighbour) {
        this.neighbours.add(neighbour);
    }

    public ArrayList<Tile> getNeighbours() {
        return neighbours;
    }

    public void setPosition(int x_pos, int y_pos) {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
    }

    public int getX_pos() {
        return x_pos;
    }
    public int getY_pos() {
        return y_pos;
    }




    public void visit() {
        visited = true;
    }

    public void reset() {
        visited = false;
    }

    public boolean isVisited() {
        return visited;
    }

    public void addWord(String word) {

        words.add(word);
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public ArrayList<ArrayDeque<Tile>> getSequences() {
        return sequences;
    }

    public void addSequence(ArrayDeque<Tile> sequence) {
        sequences.add(sequence);
    }

    public void setPos(Point p) {
        this.pos = p;
    }

    public Point getPos() {
        return pos;
    }


}
