/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author User
 */
public class UnoGame {

    public static void main(String[] args) {
        UnoGame game = new UnoGame();
        game.play();
    }

    private List<Card> pile;
    private List<Card> discarded;
    private List<Card> playerHand;
    private Scanner scanner;

    public UnoGame() {
        pile = new ArrayList<>();
        discarded = new ArrayList<>();
        playerHand = new ArrayList<>();
        scanner = new Scanner(System.in);
        initializeDeck();
    }

    private void initializeDeck() {
        // Create and add Uno cards to the deck
        for (UnoColor color : UnoColor.values()) {
            for (GroupOfCards.UnoValue value : GroupOfCards.UnoValue.values()) {
                pile.add(new Card(color, value));
            }
        }
        // Shuffle the deck
        Collections.shuffle(pile);
    }

    private void dealCards(int numCards) {
        // Deal specified number of cards to the player
        for (int i = 0; i < numCards; i++) {
            playerHand.add(pile.remove(0));
        }
    }

private void play() {
    dealCards(7); // Deal initial 7 cards to the player
    discarded.add(pile.remove(0)); // Draw a card from the deck to start the discard pile

    // Game loop
    while (true) {
        Card topCard = discarded.get(discarded.size() - 1); // Get the top card of the discard pile
        System.out.println("Top card: " + topCard);
        System.out.println("Your hand: " + playerHand);

        // Player's turn
        System.out.println("Your hand:");
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.println(i + ": " + playerHand.get(i));
        }
        System.out.println("Enter the index of the card you want to play, 'draw' to draw a card, or 'exit' to exit the game:");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Exiting the game...");
            System.out.println("No Winner");
            break;
        } else if (input.equalsIgnoreCase("draw")) {
            playerHand.add(pile.remove(0));
            System.out.println("You drew a card.");
        } else {
            try {
                int index = Integer.parseInt(input);
                if (index < 0 || index >= playerHand.size()) {
                    throw new IndexOutOfBoundsException();
                }
                Card selectedCard = playerHand.get(index);
                if (selectedCard.matches(topCard)) {
                    playerHand.remove(index);
                    discarded.add(selectedCard);
                    System.out.println("You played: " + selectedCard);
                    if (playerHand.isEmpty()) {
                        System.out.println("Congratulations, you won!");
                        break;
                    }
                    // Add a random card of the same color from the deck to the discard pile
                    List<Card> sameColorCards = new ArrayList<>();
                    for (Card card : pile) {
                        if (card.getColor() == selectedCard.getColor()) {
                            sameColorCards.add(card);
                        }
                    }
                    Card randomCard = sameColorCards.get((int) (Math.random() * sameColorCards.size()));
                    pile.remove(randomCard);
                    discarded.add(randomCard);
                    System.out.println("Computer: " + randomCard);
                } else {
                    System.out.println("You can't play that card.");
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Invalid input, please try again.");
            }
        }

        // Computer's turn
        if (input.equalsIgnoreCase("draw")) {
            // If player drew a card, computer plays or draws a card
            playComputerTurn(topCard);
        }
    }

    scanner.close();
}

private void playComputerTurn(Card topCard) {
    // Simulate computer's turn
    // Check if the computer has a valid card to play
    boolean hasValidCard = false;
    for (Card card : playerHand) {
        if (card.matches(topCard)) {
            discarded.add(card);
            playerHand.remove(card);
            System.out.println("Computer played: " + card);
            hasValidCard = true;
            break;
        }
    }
    if (!hasValidCard) {
        // If the computer doesn't have a valid card, it draws a card
        playerHand.add(pile.remove(0));
        System.out.println("Computer drew a card.");
    }
}
}
