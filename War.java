import java.util.ArrayList;
/**
 * Simulates a simple card game.
 */
public class War {
   
    public static void main(String[] args) {

        // create and shuffle the deck
        Deck deck = new Deck();
        System.out.println(deck);
        deck.shuffle();
        System.out.println(deck);
        // divide the deck into piles
        Pile p1 = new Pile();
        p1.addDeck(deck.subdeck(0, 25));
        Pile p2 = new Pile();
        p2.addDeck(deck.subdeck(26, 51));

        // while both piles are not empty
        while (!p1.isEmpty() && !p2.isEmpty()) {
            System.out.println("*****New Round*****");
            Deck.Card c1 = p1.popCard();
            Deck.Card c2 = p2.popCard();
            System.out.println("Player 1 plays: " + c1);
            System.out.println("Player 2 plays: " + c2);

            // compare the cards
            int diff = c1.getRank() - c2.getRank();
            if (diff > 0) {
                p1.addCard(c1);
                p1.addCard(c2);
            } else if (diff < 0) {
                p2.addCard(c1);
                p2.addCard(c2);
            } else {
                // it's a tie...draw four more cards
                System.out.println("It's a tie!");

                // Check if both piles have enough cards to resolve the tie
                if (p1.size() < 4 || p2.size() < 4) {
                    System.out.println("One of the piles does not have enough cards for a tie-breaker. The game ends.");
                    break;
                }

                // Store the tie cards
                ArrayList<Deck.Card> tieCards = new ArrayList<>();
                tieCards.add(c1);
                tieCards.add(c2);

                // Draw three more cards from each pile
                for (int i = 0; i < 3; i++) {
                    tieCards.add(p1.popCard());
                    tieCards.add(p2.popCard());
                }

                // Draw the tie-breaker card from each pile
                Deck.Card tieBreaker1 = p1.popCard();
                Deck.Card tieBreaker2 = p2.popCard();
                tieCards.add(tieBreaker1);
                tieCards.add(tieBreaker2);

                System.out.println("Tie-breaker cards: " + tieBreaker1 + " vs " + tieBreaker2);

                // Compare the tie-breaker cards
                if (tieBreaker1.getRank() > tieBreaker2.getRank()) {
                    for (Deck.Card card : tieCards){
                        p1.addCard(card);
                    }
                    System.out.println("Player 1 wins the tie-breaker and takes all the cards!");
                } else if (tieBreaker1.getRank() < tieBreaker2.getRank()) {
                    for (Deck.Card card : tieCards){
                        p2.addCard(card);
                    }
                    System.out.println("Player 2 wins the tie-breaker and takes all the cards!");
                } else {
                    System.out.println("It's another tie! Drawing more cards...");
                }
                }
            }

        // display the winner
        if (p2.isEmpty()) {
            System.out.println("Player 1 wins!");
        } else {
            System.out.println("Player 2 wins!");
        }
    }
}
