import java.util.ArrayList;
import java.lang.String;

public class BaccaratGame {
    ArrayList<Card> playerHand;
    ArrayList<Card> bankerHand;
    BaccaratDealer theDealer;
    BaccaratGameLogic logic;
    double currentBet;
    double totalWinnings;
    String betOn;

    //This method will determine if the user won or lost their bet and return the amount won or
    //lost based on the value in currentBet.
    public double evaluateWinnings(){
    	String winner = logic.whoWon(playerHand, bankerHand);
    	
    	if((betOn == winner && winner == "Player") || (betOn == winner && winner == "Banker")) {
    		totalWinnings += currentBet;
    	}
    	else if(betOn == winner && winner == "Draw") {
    		totalWinnings += currentBet*8;
    	}
    	else {
    		totalWinnings -= currentBet;
    	}
    	return totalWinnings;
    }
}
