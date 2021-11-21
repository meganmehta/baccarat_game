import java.lang.String;
import java.io.Serializable;

public class Card implements Serializable{
    String suit;
    int value;

    public Card(String theSuit, int theValue){
        this.suit = theSuit;
        this.value = theValue;
    }

    public void setSuit(String suitVal) {
        this.suit = suitVal;
    }

    public void setValue(int numVal) {
        this.value = numVal;
    }

    public String getSuit() {
        return this.suit;
    }

    public int getValue() {
       return this.value;
    }

}