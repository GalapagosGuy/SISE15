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
            if (alg.solutionPath.size() > 0) {
                stats.type += alg.getClass() + " ";
                stats.type += alg.genericMoves[i];
                String solutionMoves = "";
                for (int j = alg.solutionPath.size() - 1; j >= 0; j--) {
                    solutionMoves += alg.solutionPath.get(j).moves;
                }
                stats.moves = solutionMoves;
                stats.solutionLength = solutionMoves.length();

                for (int j = alg.solutionPath.size() - 1; j >= 0; j--) {
                    //solution.get(j).printOutPuzzle();
                }
            } else {
                System.out.println("No solution was found");
            }
            alg.solutionPath.clear();
            stats.display();
        }
    }

}
