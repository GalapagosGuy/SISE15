import java.util.ArrayList;

public abstract class Algorithm {

    public ArrayList<Node> solutionPath;
    public abstract Stats solve(Board unsolvedBoard, String strategyParam);
    String[] genericMoves = {
            "RDUL", "RDLU", "DRUL", "DRLU", "LUDR", "LURD", "ULDR", "ULRD"
    };

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
        System.out.println(arrayMoves);
        return arrayMoves;
    }
}
