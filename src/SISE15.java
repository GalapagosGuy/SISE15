import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class SISE15 {

    private static String strategyType;
    private static String strategyParam;
    private static String sourceFilePath;
    private static String resultFilePath;
    private static String resultAdditionalInfoFilePath;

    public static void main(String[] args) {

        Stats bigStats = new Stats();
        String fileName = "Puzzles/4x4_01_00001.txt";
        int indexFile = 1;
        int compIndex = 1;


        //try {

            File file = new File(fileName);
            //Scanner scanner = new Scanner(new File(sourceFilePath));

            while(file.exists()) {

                //prepareProgram(args);

                Board board = new Board(fileName);
                //board.showBoard();

                Algorithm algorithm = prepareAlgorithm("dfs");

                if (algorithm == null)
                    return;

                //execute algorithm
                Stats statsResult = algorithm.solve(new Board(fileName), "ULRD");
                //System.out.println(statsResult.time);
                //System.out.println(statsResult.solutionLength);
                bigStats.solutionLength += statsResult.solutionLength;
                bigStats.maxRecursion += statsResult.maxRecursion;
                bigStats.processedNodes += statsResult.processedNodes;
                bigStats.visitedNodes += statsResult.visitedNodes;
                bigStats.time += statsResult.time;

                indexFile++;



                String newFileName = "Puzzles/4x4_01_";
                if (indexFile > 99)
                    newFileName += "00" + indexFile;
                else if (indexFile > 9)
                    newFileName += "000" + indexFile;
                else
                    newFileName += "0000" + indexFile;

                newFileName += ".txt";

                fileName = newFileName;
                file = new File(newFileName);
                System.out.println(newFileName);
            }

        indexFile--;
        float solutionLength = bigStats.solutionLength * 1.0f / (float)indexFile;
        float maxRecursion = bigStats.maxRecursion * 1.0f /  (float)indexFile;
        float processedNodes = bigStats.processedNodes * 1.0f /  (float)indexFile;
        float visitedNodes = bigStats.visitedNodes * 1.0f /  (float)indexFile;
        float time = bigStats.time * 1.0f /  (float)indexFile;

        try {
            FileWriter additionalInfoResult = new FileWriter("4x4_01_dfs_ULRD_stats.txt");

            if (solutionLength == 0 || solutionLength == -1)
                additionalInfoResult.write("-1");
            else {
                additionalInfoResult.write(solutionLength + "\n");
                additionalInfoResult.write(visitedNodes + "\n");
                additionalInfoResult.write(processedNodes + "\n");
                additionalInfoResult.write(maxRecursion + "\n");

                DecimalFormat df = new DecimalFormat("#.###");
                //df.setRoundingMode(RoundingMode.CEILING);

                additionalInfoResult.write(df.format(time));
            }

            additionalInfoResult.close();

        } catch (IOException e) {
            System.out.println("Writing to file (additional info result) failed!");
        }

        //bigStats.executeResultsToFile("4x4_01_astar_hamm.txt", "4x4_01_astar_hamm_stats.txt");

        /*} catch (IOException io) {
            System.out.println("XD");
        }*/



    }

    private static void prepareProgram(String[] args) {
        strategyType = args[0];
        strategyParam = args[1];
        sourceFilePath = args[2];
        resultFilePath = args[3];
        resultAdditionalInfoFilePath = args[4];
    }

    private static Algorithm prepareAlgorithm(String strategyType) {

        switch (strategyType) {
            case "bfs":
                return new BFS();
            case "dfs":
                return new DFS();
            case "astr":
                return new AStar();
        }

        return null;
    }


    public static void solve8(Algorithm alg, Board board){

        for (int i = 0; i < 8; i++) {

            Stats stats = new Stats();
            stats = alg.solve(board, alg.genericMoves[i]);

        }
    }

}
