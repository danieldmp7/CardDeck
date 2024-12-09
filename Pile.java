import java.util.ArrayList;

/**
 * A pile of playing cards (of variable size).
 */
public class Pile {
    //I have Card as a nested class inside Deck so I change "Card" to "Deck.Card"
    private ArrayList<Deck.Card> cards;

    /**
     * Constructs an empty pile of cards.
     */
    public Pile() {
        this.cards = new ArrayList<Deck.Card>();
    }

    /**
     * Adds a card to the bottom of the pile.
     */
    public void addCard(Deck.Card card) {
        this.cards.add(card);
    }

    /**
     * Copies an entire deck into the pile.
     */
    public void addDeck(Deck deck) {
        for (Deck.Card card : deck.getCards()) {
            this.cards.add(card);
        }
    }

    /**
     * Returns true if this pile has no cards.
     */
    public boolean isEmpty() {
        return this.cards.isEmpty();
    }

    /**
     * Removes a card from the top of the pile.
     */
    public Deck.Card popCard() {
        return this.cards.remove(0);
    }

    public int size() {
        return cards.size();
    }

}
