import java.util.ArrayList;
import java.lang.String;

public class BaccaratGame {
    ArrayList<Card> playerHand;
    ArrayList<Card> bankerHand;
	Card playerECard;
	Card bankerECard;
    BaccaratDealer theDealer = new BaccaratDealer();
    BaccaratGameLogic logic = new BaccaratGameLogic();
    double currentBet;
    double totalWinnings;
    String betOn, winner;

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
			this.playerECard = theDealer.drawOne();
			playerHand.add(playerECard);
		}

		if (logic.evaluateBankerDraw(bankerHand, playerHand.get(playerHand.size() - 1)) == true){
			this.bankerECard = theDealer.drawOne();
			bankerHand.add(bankerECard);
		}

    	this.winner = logic.whoWon(this.playerHand, this.bankerHand);
		System.out.println(this.winner);

		//determine winnings
		if (winner.equals("Player")){
			if (betOn.equals("Player")){
				totalWinnings += currentBet;
			}
			else{
				totalWinnings -= currentBet;
			}
		}
		else if (winner.equals("Banker")){
			if (betOn.equals("Banker")){
				totalWinnings += currentBet;
			}
			else{
				totalWinnings -= currentBet;
			}
		}
		else if (winner.equals("Draw")){
			if (betOn.equals("Player")){
				totalWinnings -= currentBet;
			}
			else if (betOn.equals("Banker")){
				totalWinnings -= currentBet;
			}
		}

    	return totalWinnings;
    }
}
