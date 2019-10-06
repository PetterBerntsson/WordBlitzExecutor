import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class SequenceTransformer {

    ArrayList<ArrayDeque<Tile>> simpleSequences;
    ArrayList<ArrayDeque<Point>> coordinateSequences;

    private Point tl;
    private Point tr;
    private Point bl;
    private Point br;
    private double x_mid;
    private double y_mid;
    private double tileSize;

    public SequenceTransformer(Point tl, Point tr, Point bl, Point br) {
        this.tl = tl;
        this.tr = tr;
        this.bl = bl;
        this.br = br;

        simpleSequences = new ArrayList<>();
        coordinateSequences = new ArrayList<>();
    }

    public void addSequences(ArrayList<ArrayDeque<Tile>> sequences) {
        this.simpleSequences.addAll(sequences);
    }

    public ArrayList<ArrayDeque<Point>> transformSequences() {
        makeCoordinateGrid();

        for(ArrayDeque<Tile> sequence: simpleSequences) {
            ArrayDeque<Point> coordinateSequence = new ArrayDeque<>();

            for(Tile tile: sequence) {
                Point p = new Point( (int)(tile.getX_pos()*tileSize + tl.getX()),  (int) (tile.getY_pos()*tileSize + tl.getY()));
                tile.setPos(p);
                coordinateSequence.add(p);
            }

            coordinateSequences.add(coordinateSequence);
        }

        return coordinateSequences;
    }

    private void makeCoordinateGrid() {
        x_mid = (tl.getX() + tr.getX())/2;
        y_mid = (tl.getY() + bl.getY())/2;
        tileSize = (tr.getX() - tl.getX())/3;
    }

}
