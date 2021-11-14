import java.util.ArrayList;
import java.lang.String;

public class BaccaratGame {
    ArrayList<Card> playerHand;
    ArrayList<Card> bankerHand;
    BaccaratDealer theDealer = new BaccaratDealer();
    BaccaratGameLogic logic = new BaccaratGameLogic();
    double currentBet;
    double totalWinnings;
    String betOn;

	public void setBetAmount(double betAmount){
		this.currentBet = betAmount;
	}

	public void setBetOn(String chosenPlayer){
		this.betOn = chosenPlayer;
	}

    //This method will determine if the user won or lost their bet and return the amount won or
    //lost based on the value in currentBet.
    public double evaluateWinnings(){

		//create deck + deal hands
		theDealer.generateDeck();
		this.playerHand = theDealer.dealHand();
		this.bankerHand = theDealer.dealHand();

		if (logic.evaluatePlayerDraw(playerHand) == true){
			Card newCard1 = theDealer.drawOne();
			playerHand.add(newCard1);
		}

		if (logic.evaluateBankerDraw(bankerHand, playerHand.get(playerHand.size() - 1)) == true){
			Card newCard2 = theDealer.drawOne();
			bankerHand.add(newCard2);
		}

    	String winner = logic.whoWon(playerHand, bankerHand);

		//determine winnings
		if (winner == "Player"){
			if (betOn == "Player"){
				totalWinnings += currentBet;
			}
			else{
				totalWinnings += 0;
			}
		}
		else if (winner == "Banker"){
			if (betOn == "Banker"){
				totalWinnings += currentBet;
			}
			else{
				totalWinnings += 0;
			}
		}
		else if (winner == "Draw"){
			if (betOn == "Player"){
				totalWinnings += currentBet;
			}
			else if (betOn == "Banker"){
				totalWinnings += currentBet;
			}
		}

    	/*if((betOn == winner && winner == "Player") || (betOn == winner && winner == "Banker")) {
    		totalWinnings += currentBet;
    	}
    	else if(betOn == winner && winner == "Draw") { //can't bet on a draw
    		totalWinnings += currentBet*8;
    	}
    	else {
    		totalWinnings -= currentBet; //can't be negative wins, the winnings would just be 0
    	}*/
    	return totalWinnings;
    }
}
