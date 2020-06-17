import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class SISE15 {

    private static String strategyType;
    private static String strategyParam;
    private static String sourceFilePath;
    private static String resultFilePath;
    private static String resultAdditionalInfoFilePath;

    public static void main(String[] args) {

        prepareProgram(args);

        Board board = new Board(sourceFilePath);

        Algorithm algorithm = prepareAlgorithm(strategyType);

        if (algorithm == null)
            return;

        //execute algorithm
        Stats statsResult = algorithm.solve(board, strategyParam);
        statsResult.display();

        statsResult.executeResultsToFile(resultFilePath, resultAdditionalInfoFilePath);

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
