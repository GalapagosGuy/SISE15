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
        Board boardForAStar = new Board("Puzzles/4x4_05_00001.txt");
        int[] puzzle4x4 = {
                1, 2, 3, 4,
                5, 6, 0 ,7,
                9, 10, 11, 8,
                13, 14, 15, 12,
        };

        String[] genericMoves = {
                "RDUL", "RDLU", "DRUL", "DRLU", "LUDR", "LURD", "ULDR", "ULRD"
        };
        float averageTime = 0.0f;
        Stats stats = new Stats();
        for (int i = 0; i < 8; i++) {
            Node rootNode = new Node(boardForAStar.getBoardFields(), 4, 4);
            //Node rootNode = new Node(puzzle4x4,4,4);
            ArrayList<String> moves = new ArrayList<String>();
            moves = movesConverstion(genericMoves[i]);
            BFS dfs = new BFS();
            stats = dfs.solve(rootNode, moves);
            ArrayList<Node> solution = dfs.solutionPath;

            if (solution.size() > 0) {
                stats.movesSet = genericMoves[i];
                String solutionMoves = "";
                for (int j = solution.size() - 1; j >= 0; j--) {
                    solutionMoves += solution.get(j).moves;
                }
                stats.moves = solutionMoves;

                for (int j = solution.size() - 1; j >= 0; j--) {
                    //solution.get(j).printOutPuzzle();
                }
            } else {
                System.out.println("No solution was found");
            }
            solution.clear();
            moves.clear();
            stats.display();
        }


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
    public static ArrayList<String> movesConverstion(String moves){
        ArrayList<String> arrayMoves = new ArrayList<>();
        for (int i = 0; i < moves.length(); i++) {
            if(moves.toUpperCase().charAt(i) == 'R')
                arrayMoves.add("Right");
            if(moves.toUpperCase().charAt(i) == 'L')
                arrayMoves.add("Left");
            if(moves.toUpperCase().charAt(i) == 'U')
                arrayMoves.add("Up");
            if(moves.toUpperCase().charAt(i) == 'D')
                arrayMoves.add("Down");

        }
        return arrayMoves;
    }

}
