import java.lang.String;

public class Card {
    String suite;
    int value;

    public Card(String theSuite, int theValue){
        this.suite = theSuite;
        this.value = theValue;
    }

    public void setSuite(String suiteVal) {
        this.suite = suiteVal;
    }

    public void setValue(String numVal) {
        this.value = numVal;
    }

}