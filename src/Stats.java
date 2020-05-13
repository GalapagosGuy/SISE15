import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

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

    public void executeResultsToFile(String resultFile, String additionalResultFile) {

        //main result
        try {
            FileWriter mainResult = new FileWriter(resultFile);

            if (solutionLength == 0 || solutionLength == -1)
                mainResult.write("-1");
            else {
                mainResult.write(solutionLength + "\n");
                mainResult.write(moves);
            }

            mainResult.close();

        } catch (IOException e) {
            System.out.println("Writing to file (main result) failed!");
        }

        //additional info result
        try {
            FileWriter additionalInfoResult = new FileWriter(additionalResultFile);

            if (solutionLength == 0 || solutionLength == -1)
                additionalInfoResult.write("-1");
            else {
                additionalInfoResult.write(solutionLength + "\n");
                additionalInfoResult.write(visitedNodes + "\n");
                additionalInfoResult.write(processedNodes + "\n");
                additionalInfoResult.write(maxRecursion + "\n");

                DecimalFormat df = new DecimalFormat("#.####");
                //df.setRoundingMode(RoundingMode.CEILING);

                additionalInfoResult.write(df.format(time));
            }

            additionalInfoResult.close();

        } catch (IOException e) {
            System.out.println("Writing to file (additional info result) failed!");
        }

    }
}
