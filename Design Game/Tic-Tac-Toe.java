import java.util.*;

// --------------------------------------------------
// Player
// --------------------------------------------------

class Player {

    private final String name;
    private final char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}


// --------------------------------------------------
// Rule Checker
// --------------------------------------------------

interface RuleChecker {

    boolean check(Player player, Board board);
}


// --------------------------------------------------
// Row Rule
// --------------------------------------------------

class RowRuleChecker implements RuleChecker {

    @Override
    public boolean check(Player player, Board board) {

        char symbol = player.getSymbol();
        int size = board.getSize();

        for (int row = 0; row < size; row++) {

            boolean rowWin = true;

            for (int col = 0; col < size; col++) {

                if (board.getCell(row, col) != symbol) {
                    rowWin = false;
                    break;
                }
            }

            if (rowWin) {
                return true;
            }
        }

        return false;
    }
}


// --------------------------------------------------
// Column Rule
// --------------------------------------------------

class ColumnRuleChecker implements RuleChecker {

    @Override
    public boolean check(Player player, Board board) {

        char symbol = player.getSymbol();
        int size = board.getSize();

        for (int col = 0; col < size; col++) {

            boolean columnWin = true;

            for (int row = 0; row < size; row++) {

                if (board.getCell(row, col) != symbol) {
                    columnWin = false;
                    break;
                }
            }

            if (columnWin) {
                return true;
            }
        }

        return false;
    }
}


// --------------------------------------------------
// Diagonal Rule
// --------------------------------------------------

class DiagonalRuleChecker implements RuleChecker {

    @Override
    public boolean check(Player player, Board board) {

        char symbol = player.getSymbol();
        int size = board.getSize();

        // Main diagonal
        boolean mainDiagonalWin = true;

        for (int i = 0; i < size; i++) {

            if (board.getCell(i, i) != symbol) {
                mainDiagonalWin = false;
                break;
            }
        }

        if (mainDiagonalWin) {
            return true;
        }


        // Anti-diagonal
        boolean antiDiagonalWin = true;

        for (int i = 0; i < size; i++) {

            if (board.getCell(i, size - 1 - i) != symbol) {
                antiDiagonalWin = false;
                break;
            }
        }

        return antiDiagonalWin;
    }
}


// --------------------------------------------------
// Board
// --------------------------------------------------

class Board {

    private static final char EMPTY_CELL = ' ';

    private final int size;
    private final char[][] grid;
    private int moveCount;

    public Board(int size) {

        if (size <= 0) {
            throw new IllegalArgumentException("Board size must be greater than zero");
        }

        this.size = size;
        this.grid = new char[size][size];
        this.moveCount = 0;

        initializeBoard();
    }

    private void initializeBoard() {

        for (int row = 0; row < size; row++) {

            for (int col = 0; col < size; col++) {

                grid[row][col] = EMPTY_CELL;
            }
        }
    }

    public boolean placeValue(int row, int col, char symbol) {

        if (!isValidCell(row, col)) {

            System.out.println("Cell selected is out of bounds. Choose again!");

            return false;
        }

        if (!isEmpty(row, col)) {

            System.out.println("Cell is already filled. Choose again!");

            return false;
        }

        grid[row][col] = symbol;
        moveCount++;

        return true;
    }

    public boolean isFull() {

        return moveCount == size * size;
    }

    public char getCell(int row, int col) {

        return grid[row][col];
    }

    public int getSize() {

        return size;
    }

    private boolean isValidCell(int row, int col) {

        return row >= 0 &&
               row < size &&
               col >= 0 &&
               col < size;
    }

    private boolean isEmpty(int row, int col) {

        return grid[row][col] == EMPTY_CELL;
    }

    public void printBoard() {

        for (int row = 0; row < size; row++) {

            for (int col = 0; col < size; col++) {

                System.out.print("[" + grid[row][col] + "]");
            }

            System.out.println();
        }

        System.out.println();
    }
}


// --------------------------------------------------
// Tic-Tac-Toe Game
// --------------------------------------------------

class TicTacToeGame {

    private final Board board;

    private final Player player1;
    private final Player player2;

    private Player currentPlayer;

    private final List<RuleChecker> ruleCheckers;

    private boolean gameOver;

    public TicTacToeGame(
        int boardSize,
        Player player1,
        Player player2
    ) {

        this.board = new Board(boardSize);

        this.player1 = player1;
        this.player2 = player2;

        this.currentPlayer = player1;

        this.ruleCheckers = List.of(
            new RowRuleChecker(),
            new ColumnRuleChecker(),
            new DiagonalRuleChecker()
        );

        this.gameOver = false;
    }

    public void playTurn(int row, int col) {

        if (gameOver) {

            System.out.println("Game has already ended.");
            return;
        }

        System.out.println(currentPlayer.getName() + " attempts to place '" + currentPlayer.getSymbol() + "' at " + row + ", " + col);

        boolean success = board.placeValue(
            row,
            col,
            currentPlayer.getSymbol()
        );
      
        if (!success) {
            return;
        }
        board.printBoard();

        if (isWin()) {
            System.out.println(currentPlayer.getName() + " wins the game!");
            gameOver = true;
            return;
        }

        if (board.isFull()) {
            System.out.println("Game has been drawn.");
            gameOver = true;
            return;
        }
        switchPlayer();
    }

    private boolean isWin() {
        for (RuleChecker ruleChecker : ruleCheckers) {
            if (ruleChecker.check(currentPlayer, board)) {
                return true;
            }
        }
        return false;
    }

    private void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }
}


// --------------------------------------------------
// Main
// --------------------------------------------------

class Main {

    public static void main(String[] args) {

        Player player1 = new Player("Alice", 'X');

        Player player2 = new Player("Bob", 'O');
      
        TicTacToeGame game = new TicTacToeGame(3,player1,player2);
      
        game.playTurn(0, 0);
        game.playTurn(1, 1);
        game.playTurn(0, 1);
        game.playTurn(1, 2);
        game.playTurn(0, 2);
    }
}
