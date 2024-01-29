/*  Name: Baron Ping-Yeh Hsieh
 *  PennKey:  bpyhsieh
 *  Execution: N/A, this class is meant to be used by other classes
 *
 *  Description: A class representing the board in which the Snake game
 *  takes place. The class formats the board like a grid 
 *  while objects like food and snake parts occupy indiviudal
 *  grids on the board. Aside from constructing the game board,
 *  the class also receives the player's inputs (WASD) to control the
 *  snake or switch the game into hard (H) or normal mode (N key), done by
 *  manipulating the frame rate of enableAnimation, checks the collision of the 
 *  snake with the canva's boundaries and the food, updates the player's score
 *  and highScore based on how many foods are eaten by the snake, keeps track 
 *  of the game's losing condition (collide with boundaries or body), and draws
 *  the main menu and game over screen for the game.
 *
 */
public class Board {

    private static int numRows, numCols;
    private static double width, height;
    private static double cellWidth, cellHeight;
    private int rowPos, colPos;
    private Snake snake;
    private Food food;
    private int score, highScore;
    private int gameSpeed;

    /**
     *  Constructor of the Board object. Initializes the fields
     *  of the board object, as well as constructing the 
     *  snake and food objects that would be utilized
     *  in the board methods to create the behaviors 
     *  of the game.
     */
    public Board(double boardWidth, double boardHeight) {
        width = boardWidth;
        height = boardHeight;
        cellWidth = 30;
        cellHeight = cellWidth;
        rowPos = (int) (Math.random() * width / cellWidth);
        colPos = (int) (Math.random() * height / cellHeight);
        food = new Food(width, height, rowPos, colPos, cellWidth, cellHeight);
        snake = new Snake((int) (width / cellWidth / 2), 
        (int) (height / cellHeight / 2), cellWidth, cellHeight);
        numRows = (int) (width / cellHeight);
        numCols = (int) (height / cellHeight);
        In inStream = new In("highScore.txt");
        highScore = inStream.readInt();
        gameSpeed = 15;
    }

    /**
     *  Input: Takes in no arguments
     *  Output: Void function, returns no output
     *
     *
     *  Function: A function that draws out the the board
     *  as well as calling the draw methods of snake and food
     *  to draw these game objects alongside the board. 
     */
    public void draw() {
        PennDraw.clear();
        PennDraw.setFontBold();
        PennDraw.setFontSize(30);
        PennDraw.setPenColor(PennDraw.ORANGE);
        PennDraw.text(450, 570, "Score:" + "  " + score);
        PennDraw.text(150, 570, "HighScore:" + "  " + highScore);
        food.draw();
        snake.draw();
        PennDraw.advance();
    }

    /**
     *  Input: Takes in no arguments
     *  Output: Void function, returns no output
     *
     *
     *  Function: A function that checks whether the snake's head has
     *  collided with the food that spawned on the board (if the position
     *  of the snake's head is the same with the position of the food)
     *  If the collision happened, the score and the ssnake's body segments
     *  would be incremented, while the food would disappear and generate 
     *  in a new position in the board.
     */
    public void testFoodCollision() {
        int snakeHeadXPos = snake.getHeadCol();
        int snakeHeadYPos = snake.getHeadRow();
        rowPos = food.getRowPos();
        colPos = food.getColPos();
        if (colPos == snakeHeadXPos && rowPos == snakeHeadYPos) {
            food.updateCollision();
            snake.incrementBodyNum();
            score++;
        }
    }

    /**
     *  Input: Takes in no arguments
     *  Output: Void function, returns no output
     *
     *
     *  Function: A function that checks whether the food
     *  was generated on the body segments of the snake.
     *  If so, the food would be generated to a new random location
     *  on the board.
     */
    public void foodRespawn() {
        for (int i = 1; i < snake.getBodyNum(); i++) {
            if ((snake.getSnakeBodyX()[i] == food.getColPos()) && 
            (snake.getSnakeBodyY()[i] == food.getRowPos())) {
                food.updateCollision();
            }
        }
    }

    /**
     *  Input: Takes in no arguments
     *  Output: A boolean value that represents whether
     *  the snake's head collided with the borders of the board.
     *
     *  Function: A function that checks whether the snake's
     *  head have collided with the borders of the board. If
     *  so, the method returns a true boolean tht would trigger
     *  a game over for the game.
     *  
     */
    public boolean testBoardCollision() {
        int snakeHeadXPos = snake.getHeadCol();
        int snakeHeadYPos = snake.getHeadRow();
        return snakeHeadXPos * cellWidth + cellWidth / 2 <= 0 || 
        snakeHeadXPos * cellWidth + cellWidth / 2 >= width || 
        snakeHeadYPos * cellHeight + cellHeight / 2 <= 0 || 
        snakeHeadYPos * cellHeight + cellHeight / 2 >= height;
    }

    /**
     *  Input: Takes in no arguments
     *  Output: A boolean value that represents whether
     *  the game encounters aa game over situation
     *
     *  Function: A function that checks whether the game
     *  has encountered a game over situation (snake collides with
     *  board borders or collides with self). If so, return A
     *  true boolean that would be used to stop the gameplay
     *  and cut to the game over screen in SnakeGame.java
     *  
     */
    public boolean gameOver() {
        return snake.testSelfCollision() || testBoardCollision();
    }

    /**
     *  Input: Takes in no arguments
     *  Output: Void function, returns no output
     *
     
     *  Function: The function reads the input 
     *  from the player to set the direction of the snake's
     *  movement (setting the x y velocities of the snake) with
     *  the WASD keys. The function also reads the H and N key
     *  inputs of the player to trigger hardmode (set to gameSpeed
     *  twice as the default value) or switch back to normal mode
     *  (set the gameSpeed back to the default value). The function
     *  then calls the update function of the snake and checks/handles
     *  the collision behavior between the snake and food objects
     */
    public void update() {
        if (PennDraw.hasNextKeyTyped()) {
            char keyPressed = PennDraw.nextKeyTyped();
            if ((keyPressed == 'd' || keyPressed == 'D') && 
            (snake.getXVel() != -1)) {
                snake.setYVel(0);
                snake.setXVel(1);
            }
            else if ((keyPressed == 'a' || keyPressed == 'A') && 
            (snake.getXVel() != 1)) {
                snake.setYVel(0);
                snake.setXVel(-1);
            }
            else if ((keyPressed == 'w' || keyPressed == 'W') && 
            (snake.getYVel() != -1)) {
                snake.setXVel(0);
                snake.setYVel(1);
            }
            else if ((keyPressed == 's' || keyPressed == 'S') && 
            (snake.getYVel() != 1)) {
                snake.setXVel(0);
                snake.setYVel(-1);
            }
            else if ((keyPressed == 's' || keyPressed == 'S') && 
            (snake.getYVel() != 1)) {
                snake.setXVel(0);
                snake.setYVel(-1);
            }
            else if (keyPressed == 'h' || keyPressed == 'H') {
                gameSpeed = 30;
            }
            else if (keyPressed == 'n' || keyPressed == 'N') {
                gameSpeed = 15;
            }
        }
        snake.update(); 
        foodRespawn();
        testFoodCollision();
    }

    /**
     *  Input: Takes in no arguments
     *  Output: Void function, returns no output
     *
     *
     *  Function: A function that draws out the main menu 
     *  screen for the game, called in SnakeGame.java
     *  before the gameplaay cycle starts (gameStart = false)
     */
    public void drawMainMenu() {
        PennDraw.setFontSize(40);
        PennDraw.setFontBold();
        PennDraw.setPenColor(PennDraw.ORANGE);
        PennDraw.text(0.5 * width, 0.7 * height, "Snake!");
        PennDraw.setFontSize(30);
        PennDraw.text(0.5 * width, 0.6 * height, "Gotta Eat Them All!");
        PennDraw.text(0.5 * width, 0.5 * height, "Excluding Yourself!");
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(0.5 * width, 0.35 * height, "Press C to Start");
        PennDraw.setFontSize(18);
        PennDraw.text(0.5 * width, 0.2 * height, "Press H to Switch to Hard Mode");
        PennDraw.text(0.5 * width, 0.15 * height, 
        "Press N to Switch Back to Normal Mode");
    }

    /**
     *  Input: Takes in no arguments
     *  Output: Void function, returns no output
     *
     *
     *  Function: A function that draws out the game over 
     *  screen as well as updates the current highscore of the game
     *  if players got a new highscore in this cycle of gameplay (done through
     *  overwriting the highscore value in the highScore.txt file).
     *  If the game encounters a gameOver condition(board.gameOver() == true), 
     *  the function is called in SnakeGame.java. 
     */
    public void drawGameCompleteScreen() {
        PennDraw.clear();
        if (gameOver()) {
            if (score > highScore) {
                highScore = score;
                Out scoreFile = new Out("highScore.txt");
                scoreFile.println(highScore);
            }

            PennDraw.setFontSize(40);
            PennDraw.setFontBold();
            PennDraw.setPenColor(PennDraw.ORANGE);
            PennDraw.text(0.5 * width, 0.65 * height, "Game Over");
            PennDraw.setFontSize(30);
            PennDraw.text(0.5 * width, 0.55 * height, "Score:" + " " + score);
            PennDraw.text(0.5 * width, 0.45 * height, "HighScore:" +  
            " " + highScore);
            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.text(0.5 * width, 0.3 * height, "Press R to Restart");
        }
        PennDraw.advance();
    }

    /**
     *  Input: Takes in no arguments
     *  Output: Returns the integer value that represents
     *  animation framerate / snake movement speed
     *  that the game is running on.
     *
     
     *  Function: Getter Function that allows the accessing 
     *  of the animation framerate / snake movement speed of the 
     *  game, which is called in SnakeGame.java to toggle between
     *  hard and normal mode of the game
     */
    public int getGameSpeed() {
        return gameSpeed;
    }

}
