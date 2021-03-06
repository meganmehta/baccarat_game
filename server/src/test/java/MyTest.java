import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

class MyTest {
	private BaccaratDealer dealer;
	private BaccaratGameLogic logic;
	private BaccaratGame game;

	@BeforeEach
	void init() {
		dealer = new BaccaratDealer();
		logic = new BaccaratGameLogic();
		game = new BaccaratGame();
		dealer.generateDeck();
	}


	//testing BaccaratDealer
	@Test
	void generateDeckTest1() {
		assertEquals(52, dealer.deck.size(), "generate not working");
	}

	@Test
	void generateDeckTest2() {
		assertEquals("S", dealer.suits.get(3), "generate not working");
	}

	@Test
	void dealHandTest1() {
		ArrayList<Card> output = dealer.dealHand();
		assertEquals(2, output.size(), "dealHand not working");
	}

	@Test
	void dealHandTest2() {
		ArrayList<Card> output = dealer.dealHand();
		assertEquals(50, dealer.deck.size(), "dealHand not working");
	}

	@Test
	void drawOneTest1() {
		Card output = dealer.drawOne();
		assertNotNull(output.getSuit(), "drawOne not working");
	}

	@Test
	void drawOneTest2() {
		Card output = dealer.drawOne();
		assertNotNull(output.getValue(), "drawOne not working");
	}

	@Test
	void shuffleDeckTest1() {
		dealer.shuffleDeck();
		Card test = new Card("C", 1); //check to see if first card is different
		assertNotEquals(test, dealer.deck.get(0), "shuffleDeck not working");
	}

	@Test
	void shuffleDeckTest2() {
		dealer.shuffleDeck();
		Card test = new Card("S", 13); //check to see if last card is different
		assertNotEquals(test, dealer.deck.get(51), "shuffleDeck not working");
	}

	@Test
	void deckSizeTest1() {
		Card testCard = dealer.drawOne();
		int test = dealer.deckSize();
		assertEquals(51, test, "deckSize not working");

	}

	@Test
	void deckSizeTest2() {
		ArrayList<Card> testHand = dealer.dealHand();
		int test = dealer.deckSize();
		assertEquals(50, test, "deckSize not working" );
	}

	//testing BaccaratGameLogic
	@Test
	void handTotalTest1() {
		Card testCard1 = new Card("S", 4);
		Card testCard2 = new Card("H", 11);
		ArrayList<Card> testHand = new ArrayList<Card>();
		testHand.add(testCard1);
		testHand.add(testCard2);
		int score = logic.handTotal(testHand);
		assertEquals(4, score, "handTotal not working");

	}

	@Test
	void handTotalTest2() {
		Card testCard1 = new Card("S", 1);
		Card testCard2 = new Card("C", 9);
		ArrayList<Card> testHand = new ArrayList<Card>();
		testHand.add(testCard1);
		testHand.add(testCard2);
		int score = logic.handTotal(testHand);
		assertEquals(10, score, "handTotal not working" );
	}

	@Test
	void evaluatePlayerDrawTest1() {
		Card testCard1 = new Card("S", 4);
		Card testCard2 = new Card("H", 11);
		ArrayList<Card> testHand = new ArrayList<Card>();
		testHand.add(testCard1);
		testHand.add(testCard2);
		boolean testEval = logic.evaluatePlayerDraw(testHand);
		//4 points means true
		assertEquals(true, testEval, "evaluatePlayerDraw not working");

	}

	@Test
	void evaluatePlayerDrawTest2()  {
		Card testCard1 = new Card("S", 3);
		Card testCard2 = new Card("C", 3);
		ArrayList<Card> testHand = new ArrayList<Card>();
		testHand.add(testCard1);
		testHand.add(testCard2);
		boolean testEval = logic.evaluatePlayerDraw(testHand);
		assertEquals(false, testEval, "evaluatePlayerDraw not working" );
	}

	@Test
	void evaluateBankerDrawTest1() {
		Card testCard1 = new Card("S", 1);
		Card testCard2 = new Card("H", 11);
		ArrayList<Card> testHand = new ArrayList<Card>();
		testHand.add(testCard1);
		testHand.add(testCard2);
		Card playerCard = new Card("C", 3);
		boolean testEval = logic.evaluateBankerDraw(testHand, playerCard);
		//1 point means true, ignore playerCard val
		assertEquals(true, testEval, "evaluateBankerDraw not working");

	}

	@Test
	void evaluateBankerDrawTest2()  {
		Card testCard1 = new Card("S", 3);
		Card testCard2 = new Card("C", 3);
		ArrayList<Card> testHand = new ArrayList<Card>();
		testHand.add(testCard1);
		testHand.add(testCard2);
		Card playerCard = new Card("C", 7);
		//banker score = 6, player card val = 7 -> should be true
		boolean testEval = logic.evaluateBankerDraw(testHand, playerCard);
		assertEquals(true, testEval, "evaluateBankerDraw not working" );
	}

	@Test
	void whoWonTest1()  {
		Card h1testCard1 = new Card("S", 3);
		Card h1testCard2 = new Card("C", 3);
		ArrayList<Card> testHand1 = new ArrayList<Card>();
		testHand1.add(h1testCard1);
		testHand1.add(h1testCard2);

		Card h2testCard1 = new Card("S", 3);
		Card h2testCard2 = new Card("H", 5);
		ArrayList<Card> testHand2 = new ArrayList<Card>();
		testHand2.add(h2testCard1);
		testHand2.add(h2testCard2);

		//h1 score = 6, h2 score = 8, Banker
		String testEval = logic.whoWon(testHand1, testHand2);
		assertEquals("Banker", testEval, "whoWon not working" );
	}

	@Test
	void whoWonTest2()  {
		Card h1testCard1 = new Card("S", 8);
		Card h1testCard2 = new Card("C", 11);
		ArrayList<Card> testHand1 = new ArrayList<Card>();
		testHand1.add(h1testCard1);
		testHand1.add(h1testCard2);

		Card h2testCard1 = new Card("S", 4);
		Card h2testCard2 = new Card("H", 4);
		ArrayList<Card> testHand2 = new ArrayList<Card>();
		testHand2.add(h2testCard1);
		testHand2.add(h2testCard2);

		//h1score = 8, h2score = 8, so draw
		String testEval = logic.whoWon(testHand1, testHand2);
		assertEquals("Draw", testEval, "whoWon not working" );

	}

	//testing BaccaratGame - TODO!
	@Test
	void evaluateWinningsTest1()  {
		game.setBetAmount(20);
		game.setBetOn("Banker");
		double output = 0;
		double testWinnings = game.evaluateWinnings();
		String testEval = logic.whoWon(game.playerHand, game.bankerHand);
		if (testEval == "Banker"){
			output = 20;
		}
		else{
			output = -20;
		}
		assertEquals(output, testWinnings, "evaluateWinnings not working" );
	}

	@Test
	void evaluateWinningsTest2()  {
		game.setBetAmount(50);
		game.setBetOn("Player");
		double output = 0;
		//h1 score = 6, h2 score = 8, Banker
		double testWinnings = game.evaluateWinnings();
		String testEval = logic.whoWon(game.playerHand, game.bankerHand);
		if (testEval == "Player"){
			output = 50;
		}
		else{
			output = -50;
		}
		assertEquals(output, testWinnings, "evaluateWinnings not working" );
	}

}
