import java.io.Serializable;
import java.util.ArrayList;

class BaccaratInfo implements Serializable{
    double betAmount;
    String userBetChoice;
    //ArrayList<Card> playerHand;
    //ArrayList<Card> bankerHand;

    //It should play through one hand and then send the client all of
    //the information for that game:
    //• Initial Banker and Player hand
    //• If either the Banker or Player got an extra card and what it was
    //• The result of the game based on the clients bet (did they win or lose and how
    //much)


    //list has to inclue:
    //- how many clients are connected to the server.
    //- The results of each game played by any client.
    //- how much the a client bet on each game
    //- how much a client won or lost on each game
    //- if a client drops off the server.
    //- if a new client joins the server.
    //- is the client playing another hand.

    //use clientConnection.send(c1.getText())?

}