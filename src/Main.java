import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {




        String testvocab = "testvocab.txt";
        String swedish = "vocabulary.txt";
        String english = "sowpods.txt";

        int MAX_DEPTH = 12;


                                        //VARIABLE FELDS//
        //-----------------------------------------------------------------------------------//
        String FIELD = "snleeeiokktctaba";
        String activeVocab = english;
        //-----------------------------------------------------------------------------------//

        System.out.printf("Starting Service\n");

        double lapTime = System.currentTimeMillis();
        double totalTime = lapTime;

        Matrix M = new Matrix(FIELD);
        MatrixPermuter MP = new MatrixPermuter(M, MAX_DEPTH);

        // SET VOCABULARY
        MP.readVocabulary(activeVocab);
        MP.permutateMatrix();

        System.out.println(" -- Permutations finished after " + (System.currentTimeMillis() - lapTime) + "ms");
        lapTime = System.currentTimeMillis();

        Point tl;
        Point tr;
        Point bl = new Point();
        Point br = new Point();


        System.out.println("Getting Mouse Position");
        System.out.println(" -- Please put mouse in top left corner");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tl = MouseInfo.getPointerInfo().getLocation();
        System.out.println(" -- Please put mouse in top right corner");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tr = MouseInfo.getPointerInfo().getLocation();
        tr.setLocation(tr.getX(), tl.getY());

        bl.setLocation(tl.getX(), tl.getY() - (tl.getX() - tr.getX()));
        br.setLocation(tr.getX(), tr.getY() - (tl.getX() - tr.getX()));



        SequenceTransformer SQT = new SequenceTransformer(tl, tr, bl, br);
        for (Tile tile: M.getTileArray()) {
            SQT.addSequences(tile.getSequences());
        }
        ArrayList<ArrayDeque<Point>> coordinateSequences = SQT.transformSequences();

        System.out.println("Playing game...");

        try {
            Robot robot = new Robot();
            robot.mouseMove((int) tl.getX(), (int)tl.getY());
            try {
                Thread.sleep(50);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            for(ArrayDeque<Point> sequence: coordinateSequences) {

                boolean startTile = true;
                for(Point p: sequence) {
                    robot.mouseMove((int)p.getX(), (int)p.getY());
                    if(startTile) {
                        robot.mousePress(InputEvent.BUTTON1_MASK);
                        startTile = false;
                    }
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }

        System.out.println("Game finished in " + (System.currentTimeMillis() - lapTime + "ms"));
        System.out.println("Total runtime: " + (System.currentTimeMillis() - totalTime) + "ms");
        System.out.println("Total runtime waiting: " + (System.currentTimeMillis() - totalTime - 3000) + "ms");


    }
}
