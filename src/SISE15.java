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
        algorithm.solve(board, strategyParam);
        /*
        Board boardForAStar = new Board("Puzzles/4x4_05_00001.txt");
        int[] puzzle4x4 = {
                1, 2, 3, 4,
                5, 6, 0 ,7,
                9, 10, 11, 8,
                13, 14, 15, 12,
        };


        float averageTime = 0.0f;

        DFS dfs = new DFS();
        solve8(dfs, boardForAStar);

        BFS bfs = new BFS();
        solve8(bfs, boardForAStar);


        AStar astar = new AStar();
        astar.solve(boardForAStar, "hamm");

        astar = new AStar();
        astar.solve(boardForAStar, "manh");
        */


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
