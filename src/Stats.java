public class Stats {

    public int solutionLength = 0;
    public String moves = "";
    public int visitedNodes = 0;
    public int processedNodes = 0;
    public int maxRecursion = 0;
    public float time = 0.0f;
    public String type = "";

    public void display() {

        System.out.println("-----STATS-----");
        System.out.println("Solution length: " + solutionLength);
        System.out.println("Moves: " + moves);
        System.out.println("Heuristic/Moves set: " + type);
        System.out.println("Visited nodes: " + visitedNodes);
        System.out.println("Processed nodes: " + processedNodes);
        System.out.println("Max recursion: " + maxRecursion);
        System.out.println("Time: " + time);
        System.out.println("----------------");
        System.out.println();

    }
}
