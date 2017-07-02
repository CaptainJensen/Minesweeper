package Mine;

/**
 * Created by Jensen on 6/30/17.
 */
public enum Difficulty {

    EASY (11,11,5),
    MEDIUM(15,18,20),
    HARD(17,24,30),
    FUN(4,2,1);

    private final int rows;
    private final int cols;
    private final int numOfBombs;


    Difficulty(int rows, int cols, int numOfBombs) {
        this.rows = rows;
        this.cols = cols;
        this.numOfBombs = numOfBombs;

    }

    public int getRows() {return rows;}
    public int getCols() { return cols;}
    public int getNumOfBombs() {return numOfBombs;}



}
