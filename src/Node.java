import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Node {
    public ArrayList<Node> children = new ArrayList<Node>();
    public Node parent;
    public int[] puzzle;
    public int zeroPosition = 0;
    public int rows;
    public int columns;
    public int depth = 0;
    public int heuristicScore = 0;
    public int maxDepth = 20;
    public String moves = "";

    public Node(int[] unsolvedPuzzle, int rows, int columns){
        puzzle = new int[rows*columns];
        this.rows = rows;
        this.columns = columns;
        setPuzzle(unsolvedPuzzle);
        //System.arraycopy(unsolvedPuzzle, 0, this.puzzle, 0, puzzle.length);
        copyArrays(this.puzzle, unsolvedPuzzle);
    }

    /*
    private void setPuzzle(int[] unsolvedPuzzle) {
        System.arraycopy(unsolvedPuzzle, 0, this.puzzle, 0, puzzle.length);
    } */

    public void setPuzzle(int[] p){
        for (int i = 0; i < p.length; i++) {
            this.puzzle[i] = p[i];
        }
    }

    private void copyArrays(int[] a, int[] b){
        for (int i = 0; i < b.length; i++) {
            a[i] = b[i];
        }
    }

    public boolean checkCorectenss(){
        boolean isCorrect = true;
        int current = this.puzzle[0];
        for (int i = 1; i < puzzle.length -1 ; i++) {
           // if(current == 0 && i == puzzle.length)
            if(current > this.puzzle[i])
                isCorrect = false;
            current = this.puzzle[i];
        }

        if(puzzle[puzzle.length-1] != 0)
            isCorrect = false;
        return isCorrect;
    }
    public boolean isIdentical(int[] p){
        boolean isIdentical = true;
        for (int i = 0; i < p.length; i++) {
            if(this.puzzle[i] != p[i]){
                isIdentical = false;
            }
        }
        return isIdentical;
    }
    public void moveUp(int[] p, int i){
        if(i - columns >= 0 && this.moves != "D"){
            int[] tempPuzzle = new int[rows*columns];
            copyArrays(tempPuzzle, p);

            int temp = tempPuzzle[i-columns];
            tempPuzzle[i-columns] = tempPuzzle[i];
            tempPuzzle[i] = temp;
           // swap(tempPuzzle[i-columns], tempPuzzle[i]);

            Node child = new Node(tempPuzzle, this.rows, this.columns);
            child.moves = "U";
            children.add(child);
            child.parent = this;

        }
    }

    public void moveDown(int[] p, int i){
        if(i + columns < puzzle.length &&  this.moves != "U"){
            int[] tempPuzzle = new int[rows*columns];
            copyArrays(tempPuzzle, p);
            int temp = tempPuzzle[i+columns];
            tempPuzzle[i+columns] = tempPuzzle[i];
            tempPuzzle[i] = temp;
            //swap(tempPuzzle[i+columns], tempPuzzle[i]);

            Node child = new Node(tempPuzzle, this.rows, this.columns);
            child.moves = "D";
            children.add(child);
            child.parent = this;

        }
    }

    public void moveLeft(int[] p, int i){
        if(i % columns > 0 && this.moves != "R"){
            int[] tempPuzzle = new int[rows*columns];
            copyArrays(tempPuzzle, p);
            int temp = tempPuzzle[i-1];
            tempPuzzle[i-1] = tempPuzzle[i];
            tempPuzzle[i] = temp;
            //swap(tempPuzzle[i-1], tempPuzzle[i]);

            Node child = new Node(tempPuzzle, this.rows, this.columns);
            child.moves = "L";
            children.add(child);
            child.parent = this;

        }
    }

    public void moveRight(int[] p, int i){
        if(i % columns < columns - 1 &&  this.moves != "L"){
            int[] tempPuzzle = new int[rows*columns];
            copyArrays(tempPuzzle, p);

            int temp = tempPuzzle[i+1];
            tempPuzzle[i+1] = tempPuzzle[i];
            tempPuzzle[i] = temp;
            //swap(tempPuzzle[i+1], tempPuzzle[i]);

            Node child = new Node(tempPuzzle, this.rows, this.columns);
            child.moves = "R";
            this.children.add(child);
            child.parent = this;

        }
    }
    public void nextLayer(ArrayList<String> moves){
        if(this.depth < maxDepth) {
            for (int i = 0; i < puzzle.length; i++) {
                if (puzzle[i] == 0)
                    zeroPosition = i;
            }
            for (int i = 0; i < 4; i++) {
                if (moves.get(i).equals("Up"))
                    moveUp(puzzle, zeroPosition);
                else if (moves.get(i).equals("Down"))
                    moveDown(puzzle, zeroPosition);
                else if (moves.get(i).equals("Left"))
                    moveLeft(puzzle, zeroPosition);
                else if (moves.get(i).equals("Right"))
                    moveRight(puzzle, zeroPosition);

            }
            for (Node neighbour : children)
                neighbour.depth = this.depth + 1;
        }
        /*
            moveRight(puzzle,zeroPosition);
            moveLeft(puzzle,zeroPosition);
            moveUp(puzzle,zeroPosition);
            moveDown(puzzle,zeroPosition); */
    }
    public void nextLayer(){
        for (int i = 0; i < puzzle.length ; i++) {
            if (puzzle[i] == 0)
                zeroPosition = i;
        }
        moveRight(puzzle,zeroPosition);
        moveLeft(puzzle,zeroPosition);
        moveUp(puzzle,zeroPosition);
        moveDown(puzzle,zeroPosition);

        for (Node neighbour : children)
            neighbour.depth = this.depth + 1;
    }
    public void printOutPuzzle(){
        System.out.println();
        int current = 0;
        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < columns ; j++) {
                System.out.print(puzzle[current] + " ");
                current ++;
            }
            System.out.println();
            
        }
    }

    public void swap(int a, int b){
        int temp = a;
        a = b;
        b = temp;
    }

    public void setZeroPosition() {
        if (puzzle == null)
            return;

        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i] == 0)
                zeroPosition = i;
        }
    }
}
