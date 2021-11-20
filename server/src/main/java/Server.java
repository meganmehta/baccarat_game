import java.io.Serializable;

class BaccaratInfo implements Serializable{
    double betAmount;
    String userBetChoice;
    BaccaratGame game = new BaccaratGame();

    //Receives the game object from client and other data members needed
    game = getIntent().getSerializableExtra("GamePlay");
    betAmount = getIntent().getSerializableExtra("Bet");
    userBetChoice = getIntent().getSerializableExtra("Choice");

    //list has to inclue:
    //- how many clients are connected to the server.
    //- The results of each game played by any client.
    //- how much the a client bet on each game
    //- how much a client won or lost on each game
    //- if a client drops off the server.
    //- if a new client joins the server.
    //- is the client playing another hand.

    //BaccaratInfo class should only contain information like the bidding amount,
    // who the user bet on, and the
    // results of the game that has been played in the server
    // (who won, what cards were dealt, total winnings, etc.).

}
