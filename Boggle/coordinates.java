import java.util.ArrayList;
import java.util.List;

public class coordinates {
    private char letter;
    private int x;
    private int y;

    public coordinates(int x, int y, char letter, coordinates[][] board) {
        this.x = x;
        this.y = y;
        this.letter = letter;
    }

    public char getLetter() {
        return this.letter;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public List<coordinates> getNeighbors(coordinates[][] board) {
        return findNeighbors(this.x, this.y, board);
    }

    public List<coordinates> findNeighbors(int x, int y, coordinates[][] board) { // returns a list of coordinate instances that are neighbors of the current position
        List<coordinates> neighbors = new ArrayList<>();
        // adds all the neighbors to the left of the current position
        if (x - 1 >= 0) { 
            for (int i = y - 1; i <= y + 1; i++) {
                if (i >= 0 && i < 4) {
                    neighbors.add(board[x - 1][i]);
                }
            }
        }
        // adds all the neighbors to the right of the current position
        if (x + 1 < 4) {
            for (int i = y - 1; i <= y + 1; i++) {
                if (i >= 0 && i < 4) {
                    neighbors.add(board[x + 1][i]);
                }
            }
        }
        // adds all the neighbors in column of the current position except for the current position
        for (int i = y - 1; i <= y + 1; i+=2) { 
            if (i >= 0 && i < 4) {
                neighbors.add(board[x][i]);
            }
        }
        return neighbors;
    }

    @Override
    public String toString() {
        return (String.valueOf(this.letter));
    }
}

