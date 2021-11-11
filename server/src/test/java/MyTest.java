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

	@BeforeEach
	void init() {
		dealer = new BaccaratDealer();
		dealer.generateDeck();
	}

	@Test
	void generateDeckTest1() {
		assertEquals(52, dealer.deck.size(), "generate not working");
	}

	@Test
	void generateDeckTest2() {
		assertEquals("Spades", dealer.suites.get(3), "generate not working");
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

	/*@Test
	void drawOneTest1() {
		Card output = dealer.drawOne();
		assertEquals(, output.getSuite(), "drawOne not working");
	}

	@Test
	void drawOneTest2() {
		ArrayList<Card> output = dealer.dealHand();
		assertEquals(50, dealer.deck.size(), "drawOne not working");
	}
*/



}
