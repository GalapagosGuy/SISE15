import java.lang.reflect.Array;
import java.util.ArrayList;

public class BFS {
    ArrayList<Node> solutionPath;

    public Stats solve(Node root, ArrayList<String> moves){
        Stats stats = new Stats();
        long startTime = System.nanoTime();
        solutionPath = new ArrayList<Node>();
        ArrayList<Node> openList = new ArrayList<Node>();
        ArrayList<Node> closedList = new ArrayList<Node>();

        openList.add(root);
        boolean solutionFound = false;

        while(openList.size() > 0 && !solutionFound){
            Node currentNode = openList.get(0);
            closedList.add(currentNode);
            stats.processedNodes++;
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
                if(!contains(openList, currentChild) && !contains(closedList, currentChild)){
                    openList.add(currentChild);
                    stats.visitedNodes++;
                }

            }
        }
        stats.time = (System.nanoTime() - startTime) / 1000.0f / 1000.0f;
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
       Node currentNode = node;

       path.add(currentNode);
       while(currentNode.parent != null){
           currentNode = currentNode.parent;
           path.add(currentNode);
       }
   }
}
