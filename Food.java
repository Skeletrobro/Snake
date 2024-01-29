/*  Name: Baron Ping-Yeh Hsieh
 *  PennKey:  bpyhsieh
 *  Execution: N/A, this class is meant to be used by other classes
 *
 *  Description: A class representing the food object of the Snake Game. The class
 *  constructs/draws the food at randomized (x, y) positions on the board 
 *  using Math.random, updates its position to another random location upon
 *  collision with snake, and contains getter functions for the food's position
 *  so they could be accessed to check its collision with the snake in
 *  Board.java.
 *
 */
public class Food {
    private double cellWidth, cellHeight;
    private int rowPos, colPos;
    private double width, height;

    // Constructor of the food class, which initializes the
    // food's fields with the same names as the inputs
    // to those values.
    public Food(double width, double height, int rowPos, int colPos, 
    double cellWidth, double cellHeight) {
        this.width = width;
        this.height = height;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.rowPos = rowPos;
        this.colPos = colPos;
    }
    /**
     *  Input: Takes in no arguments
     *  Output: Void function, returns no output
     *
     
     *  Function: Updates the food's (x,y) position to a new random location
     *  in the grid upon collision with the snake's head.
     */
    public void updateCollision() {
        colPos = (int) (Math.random() * width / cellWidth);
        rowPos = (int) (Math.random() * height / cellHeight);
    }

    /**
     *  Input: Takes in no arguments
     *  Output: Void function, returns no output
     *
     
     *  Function: Draws out the food on the board using and
     *  imported image file that displays blackberries as the food
     */
    public void draw() {
        PennDraw.setPenColor(PennDraw.ORANGE);
        PennDraw.picture(colPos * cellWidth + cellWidth / 2, rowPos * cellHeight +
        cellHeight / 2, "Sprite-0003.png", cellWidth, cellHeight);
    }


    /**
     *  Input: Takes in no arguments
     *  Output: Returns the current integer row position value
     *  of the food
     *
     
     *  Function: Getter Function that allows the accessing of
     *  of the food's current row position
     */
    public int getRowPos() {
        return rowPos;
    }

    /**
     *  Input: Takes in no arguments
     *  Output: Returns the current integer column position value
     *  of the food in the board
     *
     
     *  Function: Getter Function that allows the accessing of
     *  of the food's current column position in the board
     */
    public int getColPos() {
        return colPos;
    }
}