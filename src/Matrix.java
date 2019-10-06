import java.util.ArrayList;

public class Matrix {

    private Tile[][] tiles;
    private int size;

    public Matrix(String input) {
        size = (int) Math.sqrt(input.length());
        tiles = new Tile[size][size];
        makeTiles(input.toCharArray());
        makeRelations();
    }

    public void makeRelations() {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                setNeighbours(tiles[i][j]);
            }
        }
    }

    private void setNeighbours(Tile tile) {
        int x_start = -1;
        int x_end = 1;
        int y_start = -1;
        int y_end = 1;

        // Check X-wise edges
        if(tile.getX_pos() + x_start  < 0) {
            x_start = 0;
        } else if(tile.getX_pos() + x_end > size-1) {
            x_end = 0;
        }

        // Check Y-wise edges
        if(tile.getY_pos() + y_start  < 0) {
            y_start = 0;
        } else if(tile.getY_pos() + y_end > size-1) {
            y_end = 0;
        }

        for(int i = x_start; i <=x_end; i++) {
            for(int j = y_start; j <=y_end; j++) {
                if(!(i == 0 && j == 0)) {
                    tile.setNeighbour(tiles[tile.getX_pos() + i][tile.getY_pos() + j]);
                }
            }
        }
    }

    public void makeTiles(char[] chars) {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                tiles[i][j] = new Tile(chars[i*size + j], i, j);
            }
        }
    }

    public ArrayList<Tile> getTileArray() {
        ArrayList<Tile> tileArray = new ArrayList<>();
        for(Tile[] tx: this.tiles) {
            for(Tile ty: tx) {
                tileArray.add(ty);
            }
        }
        return tileArray;
    }

}
