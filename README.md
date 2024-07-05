# TicTacToe-using-java

## Table of Contents
1. [Game Description](#game-description)
2. [Features](#features)
3. [Game Instructions](#game-instructions)
4. [Code Explanation](#code-explanation)

## Game Description
Tic-Tac-Toe is a simple two-player game where each player takes turns marking a cell in a 3x3 grid with their symbol (X or O). The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row wins the game. If all nine cells are filled without a winner, the game ends in a tie.

## Features
- Two-player game (Player X and Player O)
- 3x3 grid interface
- Visual feedback for current player's turn
- Highlighting of winning tiles
- Score tracking for both players
- Reset button to restart the game

## Game Instructions
1. Run the game to open the Tic-Tac-Toe window.
2. Player X starts the game by clicking any cell on the 3x3 grid.
3. Players take turns clicking empty cells to place their marks.
4. The game checks for a winner or a tie after each turn.
   - If a player wins, the winning tiles are highlighted in green, and the winner's score is updated.
   - If the game ends in a tie, the tiles are highlighted in orange.
5. Click the "Reset" button to clear the board and start a new game.

## Code Explanation

### Main Application
```java
public class App {
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
    }
}