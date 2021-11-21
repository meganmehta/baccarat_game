import java.io.Serializable;
import java.util.ArrayList;

//BaccaratInfo class for Server
public class BaccaratInfo implements Serializable{
    double betAmount;
    String userBetChoice;
    ArrayList<Card> playerHand = new ArrayList<Card>();;
    ArrayList<Card> bankerHand = new ArrayList<Card>();;
    Card extraPlayerCard;
    Card extraBankerCard;
    String winner;
    Boolean newGame;
    double totalWinnings;

    //When a client connects to the server, the server will wait to receive a message from the
    //client to play. That message should include the amount they bet and what they bet on:
    //Banker, Player or Draw
    public BaccaratInfo(double betAmount, String userBetChoice,
                        ArrayList<Card> playerHand, ArrayList<Card> bankerHand,
                        Card extraPlayerCard, Card extraBankerCard,
                        String winner, Boolean newGame, double totalWinnings){

        this.playerHand = playerHand;
        this.bankerHand = bankerHand;
        this.extraBankerCard = this.extraBankerCard;
        this.extraPlayerCard = this.extraPlayerCard;
        this.betAmount = betAmount;
        this.userBetChoice = userBetChoice;
        this.winner = winner;
        this.newGame = newGame;
        this.totalWinnings = totalWinnings;
    }


}
