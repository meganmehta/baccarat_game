import java.util.ArrayList;
import java.lang.String;
import java.util.*;
import java.util.Random;

public class BaccaratDealer {

    ArrayList<Card> deck = new ArrayList<Card>();
    ArrayList<String> suits = new ArrayList<String>();


    //generateDeck will generate a new standard 52 card deck where each card is an
    //instance of the Card class in the ArrayList<Card> deck.
    public void generateDeck(){
        //add suit values to list
        suits.add("C"); //Clubs
        suits.add("D"); //Diamonds
        suits.add("H"); //Hearts
        suits.add("S"); //Spades

        //total cards
        int cardCount = 0;
        while (cardCount < 52){
            for (int j = 0; j < suits.size(); j++){
                for (int k = 1; k < 14; k++){
                    Card c = new Card(suits.get(j), k);
                    deck.add(c);
                    cardCount++;
                }
            }
        }

    };

    //dealHand will deal two cards
    //and return them in an ArrayList<Card>
    public ArrayList<Card> dealHand(){
        ArrayList<Card> hand = new ArrayList<Card>();
        shuffleDeck();
        hand.add(deck.get(this.deck.size() - 1)); //add last card in shuffled deck
        deck.remove(this.deck.size() - 1); //remove card from deck
        hand.add(deck.get(this.deck.size() - 1));
        deck.remove(this.deck.size() - 1);
        return hand;

    };

    //drawOne will deal a single card and return it.
    public Card drawOne(){
        Random rand = new Random();
        int num = rand.nextInt(deck.size()-1) + 1; //gets random integer
        Card returnCard = deck.get(num);
        deck.remove(num);
        return returnCard; //do we delete card from deck after it's been returned?

    };

    //shuffleDeck will create a new deck of 52 cards and “shuffle”; randomize the cards in that ArrayList<Card>
    public void shuffleDeck(){
        //use java Collections class
        Collections.shuffle(deck);
    };

    // deckSize will just return how many cards are in this.deck at any given time.
    public int deckSize(){
        return this.deck.size();
    };


}