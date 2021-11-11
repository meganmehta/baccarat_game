import java.util.ArrayList;
import java.lang.String;
import java.util.*;
import java.util.Random;

public class BaccaratDealer {

    ArrayList<Card> deck = new Card[52];
    ArrayList<String> suites;

    //generateDeck will generate a new standard 52 card deck where each card is an
    //instance of the Card class in the ArrayList<Card> deck.
    public void generateDeck(){
        //add suite values to list
        suites.add("Clubs");
        suites.add("Diamonds");
        suites.add("Hearts");
        suites.add("Spades");

        //total cards
        for (int i = 0; i < deck.length; i++){
            //suite values
            for (int j = 0; j < suites.length; j++){
                //add int values
                for (int k = 1; k < 14; k++){
                    //create card and link with deck list
                    deck[i] = new Card(suites[j],k)
                }
            }
        }

    };

    //dealHand will deal two cards
    //and return them in an ArrayList<Card>
    public ArrayList<Card> dealHand(){
        ArrayList<Card> hand = new ArrayList<>(2);
        shuffleDeck();
        hand.add(deck.size - 1); //add last card in shuffled deck
        deck.remove(deck.size - 1); //remove card from deck
        hand.add(deck.size - 1);
        deck.remove(deck.size - 1);
    };

    //drawOne will deal a single card and return it.
    public Card drawOne(){
        Random rand = new Random();
        int num = rand.nextInt(52); //gets random integer
        Card drawedCard = deck[num];
        deck.remove(deck[num]);
        return drawedCard //do we delete card from deck after it's been returned?

    };

    //shuffleDeck will create a new deck of 52 cards and “shuffle”; randomize the cards in that ArrayList<Card>
    public void shuffleDeck(){
        //use java Collections class
        Collections.shuffle(deck);
    };

    // deckSize will just return how many cards are in this.deck at any given time.
    public int deckSize(){
        return this.deck.length;
    };


}