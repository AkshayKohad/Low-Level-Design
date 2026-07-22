import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;


// --------------------------------------------------
// Player
// --------------------------------------------------

class Player {
    private final String name;
    private int position;

    public Player(String name) {
        this.name = name;
        this.position = 0;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void moveTo(int position) {
        this.position = position;
    }
}


// --------------------------------------------------
// Dice
// --------------------------------------------------

class Dice {
    private final int sides;
    private final Random random;

    public Dice(int sides) {
        if (sides <= 0) {
            throw new IllegalArgumentException(
                "Dice must have at least one side"
            );
        }

        this.sides = sides;
        this.random = new Random();
    }

    public int roll() {
        return random.nextInt(sides) + 1;
    }
}


// --------------------------------------------------
// Board
// --------------------------------------------------

class Board {
    private final int size;
    private final Map<Integer, Integer> jumps;

    public Board(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(
                "Board size must be positive"
            );
        }

        this.size = size;
        this.jumps = new HashMap<>();
    }

    public int getSize() {
        return size;
    }

    public boolean addJump(int source, int destination) {

        // Source and destination should be within board boundaries
        if (source < 1 || source > size ||
            destination < 1 || destination > size) {
            return false;
        }

        // A position cannot jump to itself
        if (source == destination) {
            return false;
        }

        // A source cannot have multiple jumps
        if (jumps.containsKey(source)) {
            return false;
        }

        jumps.put(source, destination);
        return true;
    }

    public int getDestination(int position) {
        return jumps.getOrDefault(position, position);
    }
}


// --------------------------------------------------
// Game Engine
// --------------------------------------------------

class GameEngine {
    private final Dice dice;
    private final Board board;
    private final Queue<Player> players;

    public GameEngine(
        Dice dice,
        Board board,
        Queue<Player> players
    ) {
        if (dice == null) {
            throw new IllegalArgumentException(
                "Dice cannot be null"
            );
        }

        if (board == null) {
            throw new IllegalArgumentException(
                "Board cannot be null"
            );
        }

        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException(
                "At least one player is required"
            );
        }

        this.dice = dice;
        this.board = board;
        this.players = players;
    }

    public void play() {

        System.out.println("Starting Game of Snake and Ladder");

        while (true) {

            // Get the current player
            Player currentPlayer = players.poll();

            int currentPosition = currentPlayer.getPosition();

            int diceNumber = dice.roll();

            int candidatePosition = currentPosition + diceNumber;

            // Player cannot move beyond the board
            if (candidatePosition > board.getSize()) {

                System.out.println(
                    "Player " +
                    currentPlayer.getName() +
                    " needs only " +
                    (board.getSize() - currentPosition) +
                    ", but got " +
                    diceNumber +
                    ". Try in next attempt."
                );

            } else {

                int nextPosition = board.getDestination(candidatePosition);

                // Snake
                if (nextPosition < candidatePosition) {

                    System.out.println(
                        "OOPS! " +
                        currentPlayer.getName() +
                        " got trapped in a Snake."
                    );
                }

                // Ladder
                else if (nextPosition > candidatePosition) {
                    System.out.println(
                        "HURRAY! " +
                        currentPlayer.getName() +
                        " climbed a Ladder."
                    );
                }

                // Player wins
                if (nextPosition == board.getSize()) {

                    System.out.println(
                        "Player " +
                        currentPlayer.getName() +
                        " Won the game!"
                    );

                    return;
                }


                // Move player
                currentPlayer.moveTo(nextPosition);

                System.out.println(
                    "Player " +
                    currentPlayer.getName() +
                    " next position is " +
                    nextPosition +
                    " from " +
                    currentPosition
                );
            }

            // Add player back to the queue
            players.offer(currentPlayer);
        }
    }
}


// --------------------------------------------------
// Main
// --------------------------------------------------

public class Main {

    public static void main(String[] args) {

        Queue<Player> players = new LinkedList<>();

        players.offer( new Player("Akshay"));

        players.offer(new Player("Vedanti"));

        Board board = new Board(100);

        board.addJump(20, 30);  // Ladder
        board.addJump(90, 20);  // Snake

        Dice dice =  new Dice(6);

        GameEngine game =  new GameEngine(dice,board,players);
      
        game.play();
    }
}
