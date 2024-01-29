/*  Name: Baron Ping-Yeh Hsieh
 *  PennKey: bpyhsieh
 *
 *  Execution: java SnakeGame
 *
 *  Description: The class represents the snake game as a whole.
 *  It initializes the fields of the board object, 
 *  checks the condition of the game (start, during game, and game over)
 *  and displays the respective screen for the game condition, refreshes the 
 *  highscore upon relaunch (I used files to record the highscore, so I zero'd the
 *  recorded number in the file for every launch of the game, while for restart the
 *  highScore remains the same), and handles the user input for restarting the 
 *  game after the game over screen is displayed. 
 *
 */
public class SnakeGame {
    public static void main(String[] args) {
        if (args.length != 0) { // otherwise, print usage and exit
            System.out.println("Usage: java SnakeGame [width height]");
            return;
        }

        // Rezeroing the recorded high score upon game launch
        Out scoreFile = new Out("highScore.txt");
        scoreFile.println(0);

        // Initializing the board and canvas while also setting
        // the gameStart state to false so the main menu screen
        // is first displayed
        int width = 600;
        int height = 600;
        boolean gameStart = false;
        Board board = new Board(width, height);
        PennDraw.setCanvasSize(width, height);

        // Set the scale in terms of width and height;
        // hopefully gives "rounder" numbers for sizes & speeds than
        // using the default scale of 0-1.
        PennDraw.setXscale(0, width);
        PennDraw.setYscale(0, height);

        // While the game snake game hasn't started yet, the 
        // main menu would be drawn out, and unless once the
        // program receives the user input of the c key,
        // the program exits the main menu and starts the 
        // snake game.
        while (!gameStart) {
            board.drawMainMenu();
            if (PennDraw.hasNextKeyTyped()) {
                char keyPressed = PennDraw.nextKeyTyped();
                if (keyPressed == 'c' || keyPressed == 'C') {
                    gameStart = true;
                }
            }
        }
        
        while (gameStart) {

            // Once the game has started, if the player does not
            // encounter a game over condition, the game continues
            // its normal running cycle. The game also constantly
            // detects the current gameSpeed, and if the gameSpeed
            // changes, the game framerate (snake movement speed)
            // changes as well. 
            while (!board.gameOver()) {
                PennDraw.enableAnimation(board.getGameSpeed());  
                PennDraw.clear();
                board.update();
                board.draw();
                PennDraw.advance();
            }

            // Once the player encounters a game over condition
            // (gameOver boolean == true), the game over screen
            // is displayed. The game would enter aa new cycle 
            // of gameplay if the user inputs the r key.
            board.drawGameCompleteScreen();
            if (PennDraw.hasNextKeyTyped()) {
                char keyPressed = PennDraw.nextKeyTyped();
                if (keyPressed == 'r' || keyPressed == 'R') {
                    board = new Board(width, height);
                }
            }
        }
    }
    
}