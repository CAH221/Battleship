//****************************************************//
//* Author:1717859                                    *//
//* Week:2                                           *//
//*                                                  *//
//* Description: This class represents the game      *//
//*              board for the Battleship game.      *//
//*              It uses a GUI with buttons to       *//
//*              allow players to interact with      *//
//*              the game. Players can select grid   *//
//*              cells to guess the locations of     *//
//*              ships.                              *//
//*                                                  *//
//* Date: 05/10/2024                                 *//
//****************************************************//

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame implements ActionListener {

    // Array of buttons representing the game board
    public static JButton[] button = new JButton[100];
    // Array to keep track of guessed positions on the board
    public static boolean[] guessed = new boolean[100];

    // Constructor to create the game board with specified rows and columns
    public GameBoard(int rows, int cols) {
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(rows, cols, 0, 0));

        // Initialize each button on the game board
        for (int i = 0; i < rows * cols; i++) {
            button[i] = new JButton(""); // Create a new button
            button[i].setPreferredSize(new Dimension(50, 50)); // Set size of each button
            button[i].addActionListener(this); // Add action listener to handle button clicks
            button[i].setActionCommand(Integer.toString(i)); // Set action command to identify the button index
            button[i].setBackground(Color.BLUE); // Set the default color to blue (representing water)

            button[i].setOpaque(true); // Make button opaque so background color shows
            pane.add(button[i]); // Add the button to the pane
        }
    }

    // Method to handle button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = Integer.parseInt(e.getActionCommand()); // Get the index of the button that was clicked

        if (guessed[index]) {
            // If the player has already guessed this position, display a message
            JOptionPane.showMessageDialog(this, "You have already selected this square. Please select another.");
            // Increment the number of incorrect guesses
            Battleship.incorrectGuesses++;

            // Check if the player has exceeded the maximum number of incorrect guesses
            if (Battleship.isLost()) {
                JOptionPane.showMessageDialog(this, "Game Over! You've made too many incorrect guesses.");
                System.exit(0); // End the game
            }
            return; // Return early to avoid further processing
        }

        guessed[index] = true; // Mark this button as guessed

        if (Battleship.populated[index] == 1) {
            // If the guessed position contains part of a ship, mark it as a hit
            button[index].setBackground(Color.RED); // Change button color to red for hit
            Battleship.totalHits++;

            // Check if all ships have been sunk (win condition)
            if (Battleship.isGameOver()) {
                JOptionPane.showMessageDialog(this, "You win! All ships have been sunk.");
                System.exit(0); // End the game
            }
        } else {
            // If the guessed position does not contain a ship, mark it as a miss
            button[index].setBackground(Color.GRAY); // Change button color to gray for miss
            Battleship.incorrectGuesses++;

            // Check if the player has exceeded the maximum number of incorrect guesses
            if (Battleship.isLost()) {
                JOptionPane.showMessageDialog(this, "Game Over! You've made too many incorrect guesses.");
                System.exit(0); // End the game
            }
        }
    }
}
