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
                2,3,0,
                1,4,5,
                7,8,6
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
        int[] puzzle4x4 = {
                1, 2, 3, 4,
                5, 6, 7 ,8,
                10, 13, 11, 12,
                0, 9, 14, 15,
        };
        int[] irregularPuzzle1x4 = {
                4,1,3,7,
                5,6,2,0,
        };
        Node rootNode = new Node(puzzle4x4,4,4);
        DFS bfs = new DFS();
        ArrayList<String> moves = new ArrayList<String>();
        moves.add("Right");
        moves.add("Up");
        moves.add("Down");
        moves.add("Left");
        ArrayList<Node> solution = bfs.solve(rootNode, moves);

        if(solution.size() > 0){

            for (int i = solution.size() - 1; i >= 0; i--) {
                solution.get(i).printOutPuzzle();
            }
        }
        else{
            System.out.println("No solution was found");
        }

        Board boardForAStar = new Board("");
        int[] generated = {
                1, 2, 3, 0,
                5, 6, 7, 4,
                9, 11, 12, 8,
                13, 10, 14, 15,
        };

        boardForAStar.setEmptyField(3);
        boardForAStar.setBoardFields(generated);
        boardForAStar.setHeight(4);
        boardForAStar.setWidth(4);

        AStar astar = new AStar();
        astar.solve(boardForAStar, "hamm");

        System.out.println("Manhattan");
        astar = new AStar();
        astar.solve(boardForAStar, "manh");

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
