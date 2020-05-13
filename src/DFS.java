import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class DFS extends Algorithm{


    public Stats solve(Board unsolvedBoard, String strategyParam){
        Stats stats = new Stats();
        stats.type +=  getClass().getName() + " ";
        stats.type += strategyParam;
        stats.maxRecursion = 0;
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
            for (int i = 0; i < openList.size(); i++) {
                if(currentNode.depth < openList.get(i).depth)
                    currentNode = openList.get(i);
            }

            stats.processedNodes++;
            closedList.add(currentNode);
            openList.remove(currentNode);

            currentNode.nextLayer(movesSet);

            for (int i = 0; i < currentNode.children.size() ; i++) {
                Node currentChild = currentNode.children.get(i);
                if(currentChild.depth > stats.maxRecursion)
                    stats.maxRecursion = currentChild.depth;
                if(currentChild.checkCorectenss()){
                    //System.out.println("Solution found");
                    solutionFound = true;
                   // System.out.println("Depth of solution: " + currentChild.depth);
                    //stats.maxRecursion = currentChild.depth;
                    solutionPath(solutionPath, currentChild);
                }
                    openList.add(currentChild);
                    stats.visitedNodes++;
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
                //solutionPath.get(j).printOutPuzzle();
            }
        } else {
            System.out.println("No solution was found");
        }
        stats.time = ((System.nanoTime() - startTime) / 1000000.0f);
        //stats.display();
        //System.out.println("Time: " + stats.time);
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
        //System.out.println("-- Solution path --");
        Node currentNode = node;

        path.add(currentNode);
        while(currentNode.parent != null){
            //System.out.print(currentNode.moves + " ");
            //System.out.println();
            currentNode = currentNode.parent;
            path.add(currentNode);
        }
    }
}

