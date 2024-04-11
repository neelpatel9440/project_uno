/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * The class that models your game. You should create a more specific child of this class and instantiate the methods
 * given.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public abstract class Game {

    private final String name; // The title of the game
    private ArrayList<Player> players; // The players of the game
    private int currentPlayerIndex; // Index of the current player

    public Game(String name) {
        this.name = name;
        players = new ArrayList<>();
        currentPlayerIndex = 0;
    }

    /**
     * @return the name of the game
     */
    public String getName() {
        return name;
    }

    /**
     * @return the players of this game
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Add a player to the game
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Start the game
     */
    public void start() {
        System.out.println("Starting " + name + " game...");
        play();
        declareWinner();
    }

    /**
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /**
     * Move to the next player's turn
     */
    public void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    /**
     * Play the game. This might be one method or many method calls depending on your game.
     */
    public abstract void play();

    /**
     * When the game is over, use this method to declare and display a winning player.
     */
    public abstract void declareWinner();

}