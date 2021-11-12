import java.util.ArrayList;
import java.lang.String;

public class BaccaratGameLogic {

    //The method whoWon will evaluate two hands at the end of the game and return a string
    //depending on the winner: “Player”, “Banker”, “Draw”
    public static String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2){
        int playerScore = handTotal(hand1);
        int bankerScore = handTotal(hand2);

        String result = "";

        if ((bankerScore == 8 || bankerScore == 9) && (playerScore == 8 || playerScore == 9)){
            result = "Draw";
        }

        else if (bankerScore == 8 || bankerScore == 9){
            result = "Banker";
        }

        else if (playerScore == 8 || playerScore == 9){
            result =  "Player";
        }

        return result;

    }

    public static int handTotal(ArrayList<Card> hand){
        int points = 0;

        //player cards
        int card1Val = hand.get(0).getValue();
        int card2Val = hand.get(1).getValue();

        //score card1
        if (card1Val >= 10) {
            points += 0;
        }
        else if (card1Val ==  1) {
            points += 1;
        }
        else{
            points += card1Val;
        }

        //score card2
        if (card2Val >= 10) {
            points += 0;
        }
        else if (card2Val ==  1) {
            points += 1;
        }
        else{
            points += card2Val;
        }

        return points;

    }

    //The methods evaluateBankerDraw
    //and evaluatePlayerDraw will return true if either one should be dealt a third card,
    //otherwise return false
    public static boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard){
        int bankerPoints = handTotal(hand);

        if (bankerPoints <= 2){
            return true;
        }

        else if (bankerPoints >= 7){
            return false;
        }
        //DOUBLE CHECK THESE CONDITIONS !!! (with chart)
        else if (bankerPoints >= 3 && bankerPoints <= 6){
            if (bankerPoints == 3 && playerCard.getValue() < 10){
                return true;
            }
            else if (bankerPoints == 4 && (playerCard.getValue() >= 2 && playerCard.getValue() <= 7)){
               return true;
            }
            else if (bankerPoints == 5 && (playerCard.getValue() >= 4 && playerCard.getValue() <= 7)){
                return true;
            }
            else if (bankerPoints == 6 && (playerCard.getValue() == 6 || playerCard.getValue() == 7)){
                return true;
            }
        }
        return false;

    }
    public static boolean evaluatePlayerDraw(ArrayList<Card> hand){
        int playerPoints = handTotal(hand);

        if (playerPoints <= 5){
            return true;
        }
        else if (playerPoints == 6 || playerPoints == 7){
            return false;
        }

        return false;

    }


}