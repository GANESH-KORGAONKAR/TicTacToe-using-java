import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    // Define the dimensions of the board and frame
    int boardWidth = 600;
    int boardHeight = 650; // 50px for the text panel on top

    // Create the main components of the game
    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel controlPanel = new JPanel(); // Panel for control buttons like reset

    // Create a 3x3 grid of buttons for the board
    JButton[][] board = new JButton[3][3];
    // Define the players
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;

    // Scorekeeping variables
    int scoreX = 0;
    int scoreO = 0;
    JLabel scoreLabel = new JLabel();

    JButton resetButton = new JButton("Reset");

    // Constructor to initialize the game
    TicTacToe() {
        // Set up the main frame
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Setting up the text label at the top
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel, BorderLayout.CENTER);

        // Setting up the score label
        scoreLabel.setBackground(Color.darkGray);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setText("X: 0 | O: 0");
        scoreLabel.setOpaque(true);
        textPanel.add(scoreLabel, BorderLayout.SOUTH);

        frame.add(textPanel, BorderLayout.NORTH);

        // Setting up the board panel
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel, BorderLayout.CENTER);

        // Adding buttons to the board panel
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                // Adding action listener to each tile button
                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return; // Do nothing if the game is over
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText().equals("")) {
                            tile.setText(currentPlayer); // Set the current player's symbol
                            turns++;
                            checkWinner(); // Check if there's a winner or tie
                            if (!gameOver) {
                                // Switch to the other player
                                currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                        }
                    }
                });
            }
        }

        // Setting up the control panel
        controlPanel.setLayout(new FlowLayout());
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame(); // Reset the game when reset button is clicked
            }
        });
        controlPanel.add(resetButton);
        frame.add(controlPanel, BorderLayout.SOUTH);
    }

    // Method to check for a winner or a tie
    void checkWinner() {
        // Check horizontal lines
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText().equals("")) continue; // Skip empty cells
            if (board[r][0].getText().equals(board[r][1].getText()) &&
                board[r][1].getText().equals(board[r][2].getText())) {
                setWinner(board[r][0], board[r][1], board[r][2]); // Set winner if all three cells in a row are the same
                gameOver = true;
                updateScore(); // Update the score for the winning player
                return;
            }
        }

        // Check vertical lines
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText().equals("")) continue; // Skip empty cells
            if (board[0][c].getText().equals(board[1][c].getText()) &&
                board[1][c].getText().equals(board[2][c].getText())) {
                setWinner(board[0][c], board[1][c], board[2][c]); // Set winner if all three cells in a column are the same
                gameOver = true;
                updateScore(); // Update the score for the winning player
                return;
            }
        }

        // Check diagonal lines
        if (board[0][0].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][2].getText()) &&
            !board[0][0].getText().equals("")) {
            setWinner(board[0][0], board[1][1], board[2][2]); // Set winner if all three cells in a diagonal are the same
            gameOver = true;
            updateScore(); // Update the score for the winning player
            return;
        }

        if (board[0][2].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][0].getText()) &&
            !board[0][2].getText().equals("")) {
            setWinner(board[0][2], board[1][1], board[2][0]); // Set winner if all three cells in the other diagonal are the same
            gameOver = true;
            updateScore(); // Update the score for the winning player
            return;
        }

        // Check for a tie
        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]); // Set tie if all cells are filled and no winner
                }
            }
            gameOver = true;
        }
    }

    // Method to update the winner's score
    void updateScore() {
        if (currentPlayer.equals(playerX)) {
            scoreX++; // Increment score for player X
        } else {
            scoreO++; // Increment score for player O
        }
        scoreLabel.setText("X: " + scoreX + " | O: " + scoreO); // Update the score label
    }

    // Method to reset the game board
    void resetGame() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText(""); // Clear all cells
                board[r][c].setBackground(Color.darkGray);
                board[r][c].setForeground(Color.white);
            }
        }
        gameOver = false; // Reset game state
        turns = 0;
        currentPlayer = playerX; // Set current player to X
        textLabel.setText(currentPlayer + "'s turn."); // Update the text label
    }

    // Method to set the winning tiles
    void setWinner(JButton tile1, JButton tile2, JButton tile3) {
        tile1.setForeground(Color.green); // Highlight the winning tiles
        tile1.setBackground(Color.gray);
        tile2.setForeground(Color.green);
        tile2.setBackground(Color.gray);
        tile3.setForeground(Color.green);
        tile3.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner!"); // Display the winner message
    }

    // Method to set the tiles in case of a tie
    void setTie(JButton tile) {
        tile.setForeground(Color.orange); // Highlight the tiles for a tie
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!"); // Display the tie message
    }

    // Main method to start the game
    public static void main(String[] args) {
        new TicTacToe();
    }
}
