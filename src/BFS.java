import java.lang.reflect.Array;
import java.util.ArrayList;

public class BFS extends  Algorithm{


    public Stats solve(Board unsolvedBoard, String strategyParam){
        Stats stats = new Stats();
        stats.type += getClass().getName() + " ";
        stats.type += strategyParam;
        ArrayList<String> movesSet = movesConverstion(strategyParam);

        long startTime = System.nanoTime();
        solutionPath = new ArrayList<Node>();
        ArrayList<Node> openList = new ArrayList<Node>();
        ArrayList<Node> closedList = new ArrayList<Node>();
        Node root = new Node(unsolvedBoard.getBoardFields(), unsolvedBoard.getHeight(), unsolvedBoard.getWidth());
        openList.add(root);
        boolean solutionFound = false;

        while(openList.size() > 0 && !solutionFound){
            Node currentNode = openList.get(0);
            closedList.add(currentNode);
            stats.processedNodes++;
            openList.remove(0);

            currentNode.nextLayer(movesSet);
            for (int i = 0; i < currentNode.children.size() ; i++) {
                Node currentChild = currentNode.children.get(i);
                if(currentChild.checkCorectenss()){
                    solutionFound = true;
                    stats.maxRecursion = currentChild.depth;
                    solutionPath(solutionPath, currentChild);
                }
                if(!contains(openList, currentChild) && !contains(closedList, currentChild)){
                    openList.add(currentChild);
                    stats.visitedNodes++;
                }

            }
        }
        if (solutionPath.size() > 0) {

            String solutionMoves = "";
            for (int j = solutionPath.size() - 1; j >= 0; j--) {
                solutionMoves += solutionPath.get(j).moves;
            }
            stats.moves = solutionMoves;
            stats.solutionLength = solutionMoves.length();

            for (int j = solutionPath.size() - 1; j >= 0; j--) {
                //solution.get(j).printOutPuzzle();
            }
        } else {
            System.out.println("No solution was found");
        }

        stats.time = (System.nanoTime() - startTime) / 1000.0f / 1000.0f;
        //stats.display();
        return stats;
    }

    public boolean contains(ArrayList<Node> list, Node n){
        boolean contains = false;

        for (int i = 0; i < list.size() ; i++) {
            if(list.get(i).isIdentical(n.puzzle))
                contains = true;
        }

        return contains;
    }

   public void solutionPath(ArrayList<Node> path, Node node){
       Node currentNode = node;

       path.add(currentNode);
       while(currentNode.parent != null){
           currentNode = currentNode.parent;
           path.add(currentNode);
       }
   }
}
