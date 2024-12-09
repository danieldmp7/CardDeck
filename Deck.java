import java.util.Random;
/**
 * A deck of playing cards (of fixed length).
 */
public class Deck {

    // This is a class variable so we don't have to create
    // a new Random object every time we call randomInt.
    private static Random random = new Random();

    //Compiling erros WITHOUT the class Card.
    //CREATING CLASS CARD 
    public class Card {
        private final int rank;
        private final int suit; 

        public int getRank(){
            return this.rank; 
        }

        public Card() {
            rank = 0;
            suit = 0;
        }//end-default constructor
        
        public Card (int rank, int suit) {
            this.rank = rank;
            this.suit = suit;
        }//end constructor

         public static final String[] RANKS = {
            null, "Ace", "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "Jack", "Queen", "King"};

        public static final String[] SUITS = {
            "Clubs", "Diamonds", "Hearts", "Spades"};

        //compareTo method
        public int compareTo(Card that){
            if (this.suit < that.suit) {
                    return -1;
                }
                if (this.suit > that.suit) {
                    return 1;
                }
                if (this.rank < that.rank) {
                    return -1;
                }
                if (this.rank > that.rank) {
                    return 1;
                }
                return 0;
        }

        @Override
        public String toString() {
            return RANKS[rank] + " of " + SUITS[suit];
        }
    }
    //
    //END OF CLASS CARD 
    //

    private Card[] cards;
    /**
     * Constructs a standard deck of 52 cards.
     */
    public Deck() {
        this.cards = new Card[52];
        int index = 0;
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                this.cards[index] = new Card(rank, suit);
                index++;
            }
        }
    }

    /**
     * Constructs a deck of n cards (all null).
     */
    public Deck(int n) {
        this.cards = new Card[n];
    }

    /**
     * Gets the internal cards array.
     */
    public Card[] getCards() {
        return this.cards;
    }

    /**
     * Displays each of the cards in the deck.
     */
    public void print() {
        for (Card card : this.cards) {
            System.out.println(card);
        }
    }

    /**
     * Returns a string representation of the deck.
     */
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (Card card : this.cards){
            text.append(Card.RANKS[card.rank]);
            text.append(" of ");
            text.append(Card.SUITS[card.suit]);
            text.append("\n");
        }
        return text.toString();
    }

    /**
     * Randomly permutes the array of cards.
     */
    public void shuffle() {
        for (int i = 0; i < this.cards.length; i++){
            int randIdx = randomInt(i, this.cards.length -1);
            swapCards(i, randIdx);
        }
    }

    /*
     * Class variable for Random
     */
    public static final Random RANDOM = new Random();

    /**
     * Chooses a random number between low and high, including both.
     */
    private static int randomInt(int low, int high) {
       return low + RANDOM.nextInt(high - low + 1);
    }

    /**
     * Swaps the cards at indexes i and j.
     */
    private void swapCards(int i, int j) {
        Card temp = cards[i];
        cards[i] = cards[j];
        cards[j] = temp;
    }

    /**
     * Sorts the cards (in place) using selection sort.
     */
    public void selectionSort() {
        for (int i = 0; i < cards.length -1; i++){
            int lowest = indexLowest(i, cards.length -1);
            swapCards(i, lowest); 
        }
    }

    /**
     * Finds the index of the lowest card
     * between low and high inclusive.
     */
    private int indexLowest(int low, int high) {
        int lowestIdx = low;
        for (int i = low ; i <= high; i++){
            if(cards[i].compareTo(cards[lowestIdx]) < 0){
                lowestIdx = i;
            }
        }
        return lowestIdx;
    }

    /**
     * Returns a subset of the cards in the deck.
     */
    public Deck subdeck(int low, int high) {
        Deck sub = new Deck(high - low + 1);
        for (int i = 0; i < sub.cards.length; i++) {
            sub.cards[i] = this.cards[low + i];
        }
        return sub;
    }

    /**
     * Combines two previously sorted subdecks.
     */
    private static Deck merge(Deck d1, Deck d2) {
    /*Form two decks with about 10 cards each, and sort them so they are face
      up with the lowest cards on top. Place the decks in front of you.
    */
   Deck merged = new Deck(d1.cards.length + d2.cards.length);
   int idx1 = 0;
   int idx2 = 0; 
   int mergedIdx = 0;

   /*Compare the top card from each deck and choose the lower one. Flip it
   over and add it to the merged deck.
   */

  while (idx1 < d1.cards.length && idx2 < d2.cards.length){
    if (d1.cards[idx1].compareTo(d2.cards[idx2]) <= 0) { 
        merged.cards[mergedIdx] = d1.cards[idx1];
        idx1++;
        } else {
            merged.cards[mergedIdx] = d2.cards[idx2];
            idx2++;
        }
        mergedIdx++;
  } 

    /*Repeat step 2 until one of the decks is empty. Then take the remaining
    cards and add them to the merged deck.
    */ 

   while (idx1 <d1.cards.length) {
        merged.cards[mergedIdx] = d1.cards[idx1];
        idx1++;
        mergedIdx++;
    } 
    while(idx2 < d2.cards.length) {
        merged.cards[mergedIdx] = d2.cards[idx2];
        idx2++;
        mergedIdx++;
    }
    return merged;
}

    /**
     * Returns a sorted copy of the deck using selection sort.
     */
    public Deck almostMergeSort() {
       //devide deck
       int len = this.cards.length/2;
       Deck d1 = this.subdeck(0, len - 1);
       Deck d2 = this.subdeck(len, this.cards.length - 1);

       //selectionsort to sort halves and merge
       d1.selectionSort();
       d2.selectionSort();
       return merge(d1,d2);
    }

    /**
     * Returns a sorted copy of the deck using merge sort.
     */
    public Deck mergeSort() {
        //check if deck has 0 or 1 cards. If yes return it
        if(this.cards.length == 0 || this.cards.length ==1){
            return this; 
        } 
        //devide the deck into subdecks
            int len = this.cards.length/2;
            Deck d1 = this.subdeck(0, len - 1);
            Deck d2 = this.subdeck(len, this.cards.length - 1);

        //use merge sort
        d1 = d1.mergeSort();
        d2 = d2.mergeSort();
        //merge sorted subdecks and return
        return merge(d1,d2);     
    }

    /**
     * Reorders the cards (in place) using insertion sort.
     */
    public void insertionSort() {
    }

    /**
     * Helper method for insertion sort.
     */
    private void insert(Card card, int i) {
    }
    
    public static void main(String[] args){
       //Make new deck
        Deck deck = new Deck();
        System.out.println("******Before shuffling (New Deck)*******");
        System.out.println(deck +"\n");       

        //Shuffle to make things fun
        System.out.println("*******Shuffling*******");
        deck.shuffle();
        System.out.println(deck +"\n");

        //Selection Sort
        System.out.println("******Selection Sort*******");
        deck.selectionSort();
        System.out.println(deck +"\n");
        
        //Merge method
        //Split subdecks
        System.out.println("*********Creating 2 sorted subdecks********\n");
        Deck d1 = deck.subdeck(0,deck.cards.length/2 - 1);
        Deck d2 = deck.subdeck(deck.cards.length/2,deck.cards.length-1);
        
        //sort subdecks
        d1.selectionSort();
        d2.selectionSort();
        System.out.println("**First Sorted subdeck** \n" + d1 + "\n");
        System.out.println("**Second Sorted subdeck** \n" + d2 + "\n");
        
        //merge
        System.out.print("****Merge****\n");
        Deck merged = merge(d1,d2);
        System.out.println(merged);

        //almostMergeSort
        System.out.println("****almostMergeSort*****");
        Deck ams = deck.almostMergeSort();
        System.out.println(ams);
        
        //shuffle
        System.out.println("***Shuffle again***\n");
        deck.shuffle();
        System.out.println(deck);

        //Merge sort
        System.out.println("****Merge Sort*****\n");
        Deck newMergeSort = deck.mergeSort();
        System.out.println(newMergeSort);
    }
}
