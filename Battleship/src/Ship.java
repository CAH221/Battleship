//****************************************************//
//* Author:1717859                                    *//
//* Week:2                                           *//
//*                                                  *//
//* Description: This class represents a Ship in the *//
//*              Battleship game. It contains        *//
//*              information about ship length,      *//
//*              starting location, and orientation. *//
//*              Methods are provided to set and     *//
//*              retrieve these properties.          *//
//*                                                  *//
//* Date: 05/10/2024                                 *//
//****************************************************//

public class Ship {
    // Length of the ship
    private int length;
    // Starting location of the ship on the grid
    private int startLocation;
    // Orientation of the ship (0 = horizontal, 1 = vertical)
    private int pose;

    // Constructor to initialize a ship with default invalid values
    public Ship() {
        this.length = -1;  // Default length of -1 to indicate it is uninitialized
        this.startLocation = -1;  // Default starting location of -1 to indicate it is uninitialized
        this.pose = -1;  // Default pose of -1 to indicate it is uninitialized
    }

    // Getter method for ship length
    public int getLength() {
        return length;
    }

    // Getter method for starting location of the ship
    public int getStartLocation() {
        return startLocation;
    }

    // Getter method for orientation of the ship
    public int getPose() {
        return pose;
    }

    // Setter method to assign the length of the ship
    public void setLength(int length) {
        this.length = length;
    }

    // Setter method to assign the starting location of the ship on the grid
    public void setStartLocation(int startLocation) {
        this.startLocation = startLocation;
    }

    // Setter method to assign the orientation (pose) of the ship
    public void setPose(int pose) {
        this.pose = pose;
    }
}
