import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//Testing class for BaccaratGameLogic class, testing all methods
class GameLogicTest
{
	BaccaratGame b;
	
	@BeforeEach
	void init()
	{
		b = new BaccaratGame();
		b.theDealer = new BaccaratDealer();
		b.theDealer.generateDeck();
		b.playerHand = new ArrayList<Card>();
		b.bankerHand = new ArrayList<Card>();
		b.gameLogic = new BaccaratGameLogic();
		b.playerHand = b.theDealer.dealHand();
		b.bankerHand = b.theDealer.dealHand();
	}
	
	@Test
	void testWhoWon1()
	{
		assertEquals("Banker", b.gameLogic.whoWon(b.playerHand, b.bankerHand));
	}
	
	@Test
	void testWhoWon2()
	{
		ArrayList<Card> tmp = new ArrayList<Card>();
		ArrayList<Card> tmp2 = new ArrayList<Card>();
		tmp.add(new Card("Heart", 5));
		tmp.add(new Card("Heart", 4));
		tmp2.add(new Card("Heart", 2));
		tmp2.add(new Card("Heart", 6));
		
		assertEquals("Player", b.gameLogic.whoWon(tmp, tmp2), "Natural 9 for player didn't win against 8 natural for banker");
	}
	
	@Test
	void testHandTotal1()
	{
		assertEquals(3, b.gameLogic.handTotal(b.playerHand), "Player hand total is not 3, first 2 cards should be 1, 2");
	}
	
	@Test
	void testHandTotal2()
	{
		assertEquals(7, b.gameLogic.handTotal(b.bankerHand), "Banker hand total is not 7, 3rd and 4th card should be 3, 4");
	}
	
	@Test
	void testEvaluateBankerDraw1()
	{
		assertFalse(b.gameLogic.evaluateBankerDraw(b.bankerHand, null), "Banker drew a card");
	}
	
	@Test
	void testEvaluateBankerDraw2()
	{
		ArrayList<Card> tmp = new ArrayList<Card>();
		tmp.add(new Card("Heart", 1));
		tmp.add(new Card("Spade", 1));
		
		assertTrue(b.gameLogic.evaluateBankerDraw(tmp, null), "Banker did not drew a card");
	}
	
	@Test
	void testEvaluatePlayerDraw1()
	{
		assertTrue(b.gameLogic.evaluatePlayerDraw(b.playerHand), "Player did not drew a card");
	}
	
	@Test
	void testEvaluatePlayerDraw2()
	{
		ArrayList<Card> tmp = new ArrayList<Card>();
		tmp.add(new Card("Heart", 5));
		tmp.add(new Card("Heart", 4));
		
		assertFalse(b.gameLogic.evaluatePlayerDraw(tmp), "Player drew a card");
	}
}
