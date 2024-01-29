/*  Name: Baron Ping-Yeh Hsieh
 *  PennKey:  bpyhsieh
 *  Execution: N/A, this class is meant to be used by other classes
 *
 *  Description: A class representing the snake object of the game. The class
 *  first initializes and constructs the snake using arrays to
 *  represent each segment of the snake's body (including the head
 *  at index zero). Then the class updates the snake's position
 *  across the field using velocity as well as constantly checking
 *  whether the snake collided with itself. 
 *  
 *
 */
public class Snake {
    private double cellWidth, cellHeight;
    private int headRow, headCol;
    private int bodyNum;
    private int xVel, yVel;
    private static int[] snakeBodyX;
    private static int[] snakeBodyY;
    private Food food;
    

    /**
     *  Constructor of the Snake object. Initializes the
     *  snake's cellwidth and cellHeight with the same names as the inputs
     *  to those values. The array representing the snake body is initialized
     *  to be able to contain all avaialble grids on the board. The head's
     *  (index 0 of the snake body array) position is initailized with the 
     *  inputted headRow and headCol. bodyNum is initialized as 1 as there
     *  is only the snake's head in the beginning of the game.
     */
    public Snake(int headRow, int headCol, double cellWidth, double cellHeight) {
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        snakeBodyX = new int[400];
        snakeBodyY = new int[400];
        snakeBodyX[0] = headCol;
        snakeBodyY[0] = headRow;
        bodyNum = 1;
    }

    /**
     *  Input: Takes in no arguments
     *  Output: Void function, returns no output
     *
     
     *  Function: Updates the snake's (x,y) position with every 
     *  animation frame. For the snake's body, the later segment
     *  would inherit the position value of the segment 
     *  before it. For the snake's head, it's position would
     *  be incremented by xVel and yVel, with the velocity
     *  directions determined by the user's key inputs.
     */
    public void update() {
        for (int i = bodyNum; i > 0; i--) {
            snakeBodyX[i] = snakeBodyX[i - 1];
            snakeBodyY[i] = snakeBodyY[i - 1];
        }
        snakeBodyX[0] = snakeBodyX[0] + xVel;
        snakeBodyY[0] = snakeBodyY[0] + yVel;
    }

    /**
     *  Input: Takes in no arguments
     *  Output: Void function, returns no output
     *
     
     *  Function: A function that draws out the snake. The function
     *  iterates through every body segment of the snake, drawing
     *  the head as a square equvialent of the size of the grid
     *  with the color black while drawing the following body parts 
     *  the same wway except with the  color yellow.    
     */
    public void draw() {
        for (int i = 0; i < bodyNum; i++) {
            if (i == 0) {
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.filledRectangle(snakeBodyX[i] * cellWidth + cellWidth / 2, 
                snakeBodyY[i] * cellHeight + cellHeight / 2, cellWidth / 2, 
                cellHeight / 2);
            }
            else {
                PennDraw.setPenColor(PennDraw.ORANGE);
                PennDraw.filledRectangle(snakeBodyX[i] * cellWidth + cellWidth / 2, 
                snakeBodyY[i] * cellHeight + cellHeight / 2, cellWidth / 2, 
                cellHeight / 2);
            }
        } 
    }

    /**
     *  Input: Takes in no arguments
     *  Output: A boolean value that indicates whether if 
     *  the snake has collided with its own body
     *
     
     *  Function: A function that checks whether the snake has
     *  collided with any of its body parts (if the position
     *  of the snake's head equals to one of the positions of
     *  the snake's segment). 
     */
    public boolean testSelfCollision() {
        for (int i = 1; i < bodyNum; i++) {
            if ((snakeBodyX[0] == snakeBodyX[i]) && 
            (snakeBodyY[0] == snakeBodyY[i])) {
                return true;
            }
        }
        return false;
    }


    /**
     *  Input: An new integer value for the snake's x velocity
     *  Output: Void function, returns no output
     *
     
     *  Function: A setter method that allows the changing of the
     *  snake's x velocity, which is mainly used for changing 
     *  the snake's movement direction in the x axis 
     */
    public void setXVel(int xVel) {
        this.xVel = xVel;
    }


     /**
     *  Input: An new integer value for the snake's y velocity
     *  Output: Void function, returns no output
     *
     
     *  Function: A setter method that allows the changing of the
     *  snake's y velocity, which is mainly used for changing 
     *  the snake's movement direction in the y axis 
     */
    public void setYVel(int yVel) {
        this.yVel = yVel;
    }

    /**
     *  Input: Takes in no arguments
     *  Output: Returns the integer value that represents
     *  the current number of body segments the snake has
     *
     
     *  Function: Getter Function that allows the accessing of
     *  the number of segments the snake has
     */
    public int getBodyNum() {
        return bodyNum;
    }

    /**
     *  Input: Takes in no arguments
     *  Output: Void function, returns no output
     *
     
     *  Function: A method that increments the 
     *  number of body segments the snake has, which
     *  is called when the snake's head collides with
     *  a food object
     */
    public void incrementBodyNum() {
        bodyNum++;
    } 

    /**
     *  Input: Takes in no arguments
     *  Output: Returns the current integer x velocity value for
     *  the snake's movement in the board
     *
     
     *  Function: Getter Function that allows the accessing of
     *  of the snake's current x velocity
     */
    public int getXVel() {
        return xVel;
    }

    /**
     *  Input: Takes in no arguments
     *  Output: Returns the current integer y velocity value for
     *  the snake's movement in the board
     *
     
     *  Function: Getter Function that allows the accessing of
     *  of the snake's current y velocity
     */
    public int getYVel() {
        return yVel;
    }

    /**
     *  Input: Takes in no arguments
     *  Output: Returns the current integer Y position of the
     *  snake's head in the board
     *
     
     *  Function: Getter Function that allows the accessing of
     *  of the snake head's Y position
     */
    public int getHeadRow() {
        return snakeBodyY[0];
    }

    /**
     *  Input: Takes in no arguments
     *  Output: Returns the current integer X position of the
     *  snake's head in the board
     *
     
     *  Function: Getter Function that allows the accessing of
     *  of the snake head's X position
     */
    public int getHeadCol() {
        return snakeBodyX[0];
    }

    /**
     *  Input: Takes in no arguments
     *  Output: Returns the integer array that stores the
     *  x position of all segments of the snake's body
     *
     
     *  Function: Getter Function that allows the accessing of
     *  of the integer array that represents the integer array
     *  that stores the x position of all body parts of the snake
     */
    public int[] getSnakeBodyX() {
        return snakeBodyX;
    }

    /**
     *  Input: Takes in no arguments
     *  Output: Returns the integer array that stores the
     *  Y position of all segments of the snake's body
     *
     
     *  Function: Getter Function that allows the accessing of
     *  of the integer array that represents the integer array
     *  that stores the Y position of all body parts of the snake
     */
    public int[] getSnakeBodyY() {
        return snakeBodyY;
    }
}
