import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class DFS {

    public ArrayList<Node> solutionPath;
    public Stats solve(Node root, ArrayList<String> moves){
        Stats stats = new Stats();
        Deque<String> stack = new ArrayDeque<String>();
        long startTime = System.nanoTime();
        solutionPath = new ArrayList<Node>();
        ArrayList<Node> openList = new ArrayList<Node>();
        ArrayList<Node> closedList = new ArrayList<Node>();

        openList.add(root);
        boolean solutionFound = false;

        while(openList.size() > 0 && !solutionFound){
            Node currentNode = openList.get(0);
            stats.processedNodes++;
            closedList.add(currentNode);
            openList.remove(0);

                currentNode.nextLayer(moves);
            // currentNode.printOutPuzzle();
            for (int i = 0; i < currentNode.children.size() ; i++) {
                Node currentChild = currentNode.children.get(i);
                if(currentChild.checkCorectenss()){
                    System.out.println("Solution found");
                    solutionFound = true;
                    System.out.println("Depth of solution: " + currentChild.depth);
                    solutionPath(solutionPath, currentChild);
                }
                    openList.add(0, currentChild);
                    stats.visitedNodes++;

                /*
                if(!contains(openList, currentChild) && !contains(closedList, currentChild)){
                    openList.add(0, currentChild);
                } */

            }
        }
        stats.time = ((System.nanoTime() - startTime) / 1000000.0f);
        System.out.println("Time: " + stats.time);
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

