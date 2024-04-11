/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * A class to be used as the base Card class for the project. Must be general enough to be instantiated for any Card
 * game. Students wishing to add to the code should remember to add themselves as a modifier.
 *
 * @author dancye
 */
public class Card {

    private UnoColor color;
    private GroupOfCards.UnoValue value;

    public Card(UnoColor color, GroupOfCards.UnoValue value) {
        this.color = color;
        this.value = value;
    }

    public UnoColor getColor() {
        return color;
    }

    public GroupOfCards.UnoValue getValue() {
        return value;
    }

    public boolean matches(Card otherCard) {
        return color == otherCard.getColor() || value == otherCard.getValue();
    }

    @Override
    public String toString() {
        return value + " of " + color;
    }
}