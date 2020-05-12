import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Board {

    private int width = 0;
    private int height = 0;
    private int emptyField = 0;
    private int[] boardFields;

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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getEmptyField() {
        return emptyField;
    }

    public void setEmptyField(int emptyField) {
        this.emptyField = emptyField;
    }

    public int[] getBoardFields() {
        return boardFields;
    }

    public void setBoardFields(int[] boardFields) {
        this.boardFields = boardFields;
    }
}
