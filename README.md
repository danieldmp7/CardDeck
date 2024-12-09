# War Card Game
This repository contains a Java implementation of the classic card game "War." The game simulates two players battling with a shuffled deck of cards to collect all cards. The implementation includes a solution for handling ties, where players draw additional cards until a winner is determined or the game ends.
Features
Features:

**War Class:**

The main game logic that simulates the War card game between two players.
Players start with half the deck each, and in each round, they draw one card from their pile and compare the ranks.
In case of a tie, both players draw three more cards from each pile and then compare one tie-breaker card.
If a player does not have enough cards to resolve a tie, the game ends.
The game continues until one player runs out of cards, and the winner is declared.

**Deck Class:**

Represents a standard deck of 52 playing cards.
Includes a Card class for individual cards with properties: rank and suit.
Ability to shuffle the deck randomly.
Sorting methods, including selection sort, merge sort, and almost merge sort.
Methods to split the deck into subdecks, merge subdecks, and display card information.

**Pile Class:**

Represents a pile of cards using an ArrayList.
Supports adding cards, checking if the pile is empty, and popping cards from the top of the pile.
Can accept a full deck and remove cards from the pile.

**Game Logic:**

The deck is shuffled, and then it is divided between the two players.
The game continues with each player playing the top card from their pile.
If the cards are of the same rank, additional cards are drawn and compared to determine a winner.
The game ends when one player wins all the cards or one player cannot resolve a tie due to insufficient cards.
