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
        Board boardForAStar = new Board("Puzzles/4x4_01_00001.txt");
        int[] puzzle4x4 = {
                1, 2, 3, 4,
                5, 6, 7 ,8,
                10, 13, 11, 12,
                0, 9, 14, 15,
        };



        for (int i = 0; i < 8 ; i++) {
            Node rootNode = new Node(boardForAStar.getBoardFields(),4,4);
            ArrayList<String> moves = new ArrayList<String>();
            if( i == 0)
                moves = movesConverstion("RDUL");
            if( i == 1)
                moves = movesConverstion("RDLU");
            if( i == 2)
                moves = movesConverstion("DRUL");
            if( i == 3)
                moves = movesConverstion("DRLU");
            if( i == 4)
                moves = movesConverstion("LUDR");
            if( i == 5)
                moves = movesConverstion("LURD");
            if( i == 6)
                moves = movesConverstion("ULDR");
            if( i == 7)
                moves = movesConverstion("ULRD");

            DFS dfs = new DFS();
            ArrayList<Node> solution = dfs.solve(rootNode, moves);

            if(solution.size() > 0){

                for (int j = solution.size() - 1; j >= 0; j--) {
                    solution.get(j).printOutPuzzle();
                }
            }
            else{
                System.out.println("No solution was found");
            }
            solution.clear();
            moves.clear();

        }
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
