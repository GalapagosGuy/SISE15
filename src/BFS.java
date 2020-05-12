import java.lang.reflect.Array;
import java.util.ArrayList;

public class BFS {

    public BFS(){

    }
    public ArrayList<Node> solve(Node root){
        ArrayList<Node> solutionPath = new ArrayList<Node>();
        ArrayList<Node> openList = new ArrayList<Node>();
        ArrayList<Node> closedList = new ArrayList<Node>();

        openList.add(root);
        boolean solutionFound = false;

        while(openList.size() > 0 && !solutionFound){
            Node currentNode = openList.get(0);
            closedList.add(currentNode);
            openList.remove(0);

            currentNode.nextLayer();
           // currentNode.printOutPuzzle();
            for (int i = 0; i < currentNode.children.size() ; i++) {
                Node currentChild = currentNode.children.get(i);
                if(currentChild.checkCorectenss()){
                    System.out.println("Solution found");
                    solutionFound = true;
                    solutionPath(solutionPath, currentChild);
                }
                if(!contains(openList, currentChild) && !contains(closedList, currentChild)){
                    openList.add(currentChild);
                }

            }
        }
        return solutionPath;
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
           currentNode = currentNode.parent;
           path.add(currentNode);
       }
   }
}
