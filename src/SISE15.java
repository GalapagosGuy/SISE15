import java.util.ArrayList;

public class SISE15 {

    private static String strategyType;
    private static String strategyParam;
    private static String sourceFilePath;
    private static String resultFilePath;
    private static String resultAdditionalInfoFilePath;

    public static void main(String[] args) {
        /*
        prepareProgram(args);

        Board board = new Board(sourceFilePath);

        Algorithm algorithm = prepareAlgorithm(strategyType);

        if (algorithm == null)
            return;
        */
        //execute algorithm

        int[] patheticPuzzle = {
                1,2,3,
                0,5,6,
                4,7,8
        };


        int[] unsolvedPuzzle = {
                1,2,4,
                3,0,5,
                7,6,8
        };

        int[] simplePuzzle = {
                2,3,0,
                1,4,5,
                7,8,6
        };

        int[] simplePuzzle2 = {
            1,4,2,
            3,0,5,
            6,7,8
        };

        int[] generated = {
                1, 2, 3, 4,
                5, 6, 7, 8,
                10, 13, 11, 12,
                9, 14, 0, 15,
        };

        Board board = new Board("");
        board.setWidth(4);
        board.setHeight(4);
        board.setBoardFields(generated);
        board.setEmptyField(14);

        AStar astar = new AStar();
        astar.solve(board);

        /*Node rootNode = new Node(simplePuzzle,3,3);
        BFS bfs = new BFS();
        ArrayList<Node> solution = bfs.solve(rootNode);

        if(solution.size() > 0){

            for (int i = solution.size() - 1; i >= 0; i--) {
                solution.get(i).printOutPuzzle();
            }
        }
        else{
            System.out.println("No solution was found");
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
                return new BreadthFirst();
            case "dfs":
                return new DepthFirst();
            case "astr":
                return new AStar();
        }

        return null;
    }

}
