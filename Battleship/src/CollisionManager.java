//****************************************************//
//* Author:1717859                                    *//
//* Week:2                                           *//
//*                                                  *//
//* Description: This class manages ship collision   *//
//*              detection and placement in the      *//
//*              Battleship game. It ensures that    *//
//*              ships do not overlap during setup   *//
//*              and updates the game grid           *//
//*              accordingly.                        *//
//*                                                  *//
//* Date: 05/10/2024                                 *//
//****************************************************//

public class CollisionManager {

    // Array to keep track of grid positions that are already occupied by a ship
    public static int[] collision = new int[100];

    // Method to check if placing a ship causes a collision
    public boolean checkCollision(Ship ship) {
        int start = ship.getStartLocation();
        int length = ship.getLength();

        // Check for horizontal placement
        if (ship.getPose() == 0) { // Horizontal
            for (int i = start; i < start + length; i++) {
                // If the current position is already occupied, record a collision
                if (collision[i] != 0) {
                    Battleship.numOfCollisions++;
                    return false; // Return false as there is a collision
                }
            }
        } else { // Vertical placement
            for (int i = start; i < start + (length * 10); i += 10) {
                // If the current position is already occupied, record a collision
                if (collision[i] != 0) {
                    Battleship.numOfCollisions++;
                    return false; // Return false as there is a collision
                }
            }
        }

        // If no collision detected, add the ship to the collision array
        addShipToCollisionArray(ship);
        return true; // Return true as the placement is valid
    }

    // Method to add the ship's occupied positions to the collision array
    public void addShipToCollisionArray(Ship ship) {
        int start = ship.getStartLocation();
        int length = ship.getLength();

        // Mark the grid cells occupied by the ship as filled in the collision array
        if (ship.getPose() == 0) { // Horizontal
            for (int i = start; i < start + length; i++) {
                collision[i] = 1; // Mark the position as occupied
            }
        } else { // Vertical
            for (int i = start; i < start + (length * 10); i += 10) {
                collision[i] = 1; // Mark the position as occupied
            }
        }
    }

    // Method to place the ship on the game grid
    public void placeShipOnGrid(Ship ship) {
        int start = ship.getStartLocation();
        int length = ship.getLength();

        // Update the Battleship populated array to indicate ship positions
        if (ship.getPose() == 0) { // Horizontal
            for (int j = start; j < start + length; j++) {
                Battleship.populated[j] = 1; // Mark the position as occupied by a ship
            }
        } else { // Vertical
            for (int j = start; j < start + (length * 10); j += 10) {
                Battleship.populated[j] = 1; // Mark the position as occupied by a ship
            }
        }
    }

    // Method to print the current collision grid for debugging purposes
    public void printCollisionGrid() {
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                int cell = (r * 10) + c;
                System.out.print(collision[cell] + ", "); // Print the current cell value
            }
            System.out.println(""); // Move to the next row
        }
    }
}
