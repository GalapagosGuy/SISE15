import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Board {

    int width = 0;
    int height = 0;
    int emptyField = 0;
    int[] boardFields;

    public Board(String sourceFilePath) {
        this.prepare(sourceFilePath);
    }

    private void prepare(String sourceFilePath) {

        try {
            Scanner scanner = new Scanner(new File(sourceFilePath));

            if (scanner.hasNextInt())
                width = scanner.nextInt();

            if (scanner.hasNextInt())
                height = scanner.nextInt();

            boardFields = new int[width * height];

            int boardIndex = 0;

            while (scanner.hasNextInt()) {

                boardFields[boardIndex] = scanner.nextInt();

                if (boardFields[boardIndex] == 0)
                    emptyField = boardIndex;

                boardIndex++;
            }

            scanner.close();

        } catch (FileNotFoundException exc) {
            System.out.println("File not found!");
        }

    }

}
