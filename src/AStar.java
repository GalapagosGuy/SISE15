import java.util.ArrayList;

/* useful links
https://algorithmsinsight.wordpress.com/graph-theory-2/a-star-in-general/implementing-a-star-to-solve-n-puzzle/
https://blog.goodaudience.com/solving-8-puzzle-using-a-algorithm-7b509c331288
http://csis.pace.edu/~benjamin/teaching/cs627/webfiles/Astar.pdf
 */

public class AStar extends Algorithm {

    public boolean useHamming = true;

    ArrayList<Node> openList;
    boolean solutionFound = false;

    public void solve(Board board, String heuristics) {

        openList = new ArrayList<Node>();
        useHamming = heuristics.equals("hamm");

        Node currentNode = new Node(board.getBoardFields(), board.getWidth(), board.getHeight());
        currentNode.parent = null;
        currentNode.setZeroPosition();

        processNode(currentNode);

        while (openList.size() != 0 && !solutionFound) {

            currentNode = getNodeFromOpenList();

            checkNode(currentNode);

            openList.remove(currentNode);
        }

        System.out.println("Solution:");
        DebugDraw(currentNode);
    }

    private void DebugDraw(Node node) {
        for (int i = 0; i < node.puzzle.length; i++) {
            if ( i % node.rows == 0)
                System.out.println("");
            System.out.print(node.puzzle[i] + " ");
        }

        System.out.println("\n----");
    }

    private void checkNode(Node node) {

        if (checkSolution(node))
            return;


        node.nextLayer();

        for (Node neighbour : node.children) {
            processNode(neighbour);
        }

    }

    private boolean checkSolution(Node node) {

        solutionFound = true;

        for (int i = 0; i < node.puzzle.length - 1; i++) {
            if (node.puzzle[i] != i + 1)
                solutionFound = false;
        }

        if (node.puzzle[node.puzzle.length - 1] != 0)
            solutionFound = false;

        return solutionFound;
    }

    private Node getNodeFromOpenList() {
        int nodeWithLowestHeuristicScore = 0;

        for (int i = 1; i < openList.size(); i++)
            if (openList.get(i).heuristicScore < openList.get(i).heuristicScore)
                nodeWithLowestHeuristicScore = i;

        return openList.get(nodeWithLowestHeuristicScore);
    }

    private void processNode(Node node) {

        if (node != null) {

            node.heuristicScore = node.depth;
            node.heuristicScore += useHamming ? hammingScore(node) : manhattanScore(node);

            openList.add(node);
        }

    }

    private int hammingScore(Node node) {

        int score = 0;

        for (int i = 0; i < node.puzzle.length; i++) {
            if (node.puzzle[i] == 0 || node.puzzle[i] == i + 1)
                continue;

            score++;
        }

        return score;
    }

    private int manhattanScore(Node node) {

        int score = 0;

        for (int i = 0; i < node.puzzle.length; i++) {
            if (node.puzzle[i] == i + 1 || node.puzzle[i] == 0)
                continue;

            int finalPositionInArray = findFinalPositionInArrayByValue(node, node.puzzle[i]);

            int finalX = (int)(finalPositionInArray / node.rows);
            int finalY = (int)(finalPositionInArray / node.columns);

            int currentFieldX = (int)(i / node.rows);
            int currentFieldY = (int)(i / node.columns);

            score += Math.abs(finalX - currentFieldX) + Math.abs(finalY - currentFieldY);
        }

        return score;
    }

    private int findFinalPositionInArrayByValue(Node node, int value) {
        for(int i = 0; i < node.puzzle.length; i++) {
            if (i == value - 1)
                return i;
        }

        return node.puzzle.length - 1;
    }

}

/*
        Board board = new Board("");
        board.setWidth(4);
        board.setHeight(4);
        board.setBoardFields(generated);
        board.setEmptyField(14);

        AStar astar = new AStar();
        astar.solve(board);
 */
