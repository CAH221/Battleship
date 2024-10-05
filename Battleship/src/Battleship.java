//****************************************************//
//* Author:1717859                                    *//
//* Week:2                                           *//
//*                                                  *//
//* Description: This class implements a simple      *//
//*              Battleship game, including ship     *//
//*              setup, collision detection, and     *//
//*              game status checks.                 *//
//*              The player is given a limited       *//
//*              number of attempts to sink all      *//
//*              the ships.                          *//
//*                                                  *//
//* Date: 05/10/2024                                 *//
//****************************************************//

import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Battleship {

    // Array to store all the ships used in the game
    public static Ship[] boat = new Ship[6];
    // Array to track populated grid positions
    public static int[] populated = new int[100];
    // Track total number of successful hits by player
    public static int totalHits = 0;
    // Track the number of incorrect guesses by player
    public static int incorrectGuesses = 0;
    // Define maximum number of allowed incorrect guesses
    public static final int MAX_INCORRECT_GUESSES = 20;
    // Track the number of ship collisions during setup
    public static int numOfCollisions = 0;
    // Random number generator to place ships randomly
    private Random random = new Random();
    // CollisionManager handles collision checks and grid placements
    private CollisionManager collisionManager = new CollisionManager();

    // Constructor to initialize the Battleship game
    public Battleship() {
        System.out.println("Setting up ships");
        shipSetup();
    }

    // Sets up the ships for the game
    private void shipSetup() {
        // Initialize ships
        for (int i = 0; i < 6; i++) {
            boat[i] = new Ship();
        }

        // Set ship lengths (e.g., aircraft carrier, submarines, etc.)
        boat[0].setLength(4);
        boat[1].setLength(3);
        boat[2].setLength(3);
        boat[3].setLength(2);
        boat[4].setLength(2);
        boat[5].setLength(2);

        // Randomly position each ship on the game grid
        for (int i = 0; i < 6; i++) {
            // Set the ship's orientation: horizontal or vertical
            boat[i].setPose(getRandomInRange(0, 1));
            // Find an appropriate location for the ship
            locateShip(i);
            System.out.println("Number of collisions = " + numOfCollisions);

            // Print collision grid for debugging purposes
            collisionManager.printCollisionGrid();

            // Place the ship on the board, considering collisions
            collisionManager.placeShipOnGrid(boat[i]);
        }
    }

    // Locate and position a specific ship on the game board
    public void locateShip(int boatID) {
        do {
            int length = boat[boatID].getLength();
            int x;

            // Determine if the ship should be placed horizontally or vertically
            if (boat[boatID].getPose() == 0) { // Horizontal
                do {
                    x = getRandomInRange(0, 99);
                } while (x % 10 > (10 - length)); // Ensure it fits horizontally in the row
            } else { // Vertical
                x = getRandomInRange(0, 99 - (10 * (length - 1))); // Ensure it fits vertically within the grid
            }

            // Set the starting location of the ship
            boat[boatID].setStartLocation(x);

        } while (!collisionManager.checkCollision(boat[boatID])); // Repeat until a collision-free position is found
    }

    // Get a random integer in the specified range (inclusive)
    public int getRandomInRange(int start, int end) {
        return start + random.nextInt(end - start + 1);
    }

    // Check if the game is over by determining if all ships have been sunk
    public static boolean isGameOver() {
        return totalHits == 16; // Game is over when all ship segments are hit (4 + 3 + 3 + 2 + 2 + 2 = 16)
    }

    // Check if the player has lost by exceeding the maximum number of incorrect guesses
    public static boolean isLost() {
        return incorrectGuesses >= MAX_INCORRECT_GUESSES;  // Player loses if incorrect guesses exceed the limit
    }

    // Main method to launch the Battleship game
    public static void main(String[] args) {
        // Initialize the game
        Battleship myGame = new Battleship();
        int rows = 10;
        int cols = 10;

        // Create and display the game board
        GameBoard gt = new GameBoard(rows, cols);
        gt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gt.pack();
        gt.setVisible(true);
    }
}
