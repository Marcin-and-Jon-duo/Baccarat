import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//Test class for BaccaratDealer class, testing all methods
class DealerTest
{
	BaccaratDealer b;
	
	@BeforeEach
	void init()
	{
		b = new BaccaratDealer();
		b.generateDeck();
	}
	
	@Test
	void testGenereteDeck()
	{
		assertEquals(52, b.deck.size(), "Invalid deck size generated");
	}
	
	@Test
	void testGenerateDeck2()
	{
		b.generateDeck();
		assertEquals(52, b.deck.size(), "Generate deck doesn't make a new deck but adds more cards in");
	}
	
	@Test
	void testDealHand1()
	{
		ArrayList<Card> tmp = b.dealHand();
		assertEquals("Heart", tmp.get(0).suite, "Wrong card suite");
		assertEquals(1, tmp.get(0).value, "Wrong card value");
		assertEquals("Heart", tmp.get(1).suite, "Wrong card suite");
		assertEquals(2, tmp.get(1).value, "Wrong card value");
	}
	
	@Test
	void testDealHand2()
	{
		ArrayList<Card> tmp = b.dealHand();
		ArrayList<Card> tmp2 = b.dealHand();
		assertEquals("Heart", tmp.get(0).suite, "Wrong card suite");
		assertEquals(1, tmp.get(0).value, "Wrong card value");
		assertEquals("Heart", tmp.get(1).suite, "Wrong card suite");
		assertEquals(2, tmp.get(1).value, "Wrong card value");
		assertEquals("Heart", tmp2.get(0).suite, "Wrong card suite");
		assertEquals(3, tmp2.get(0).value, "Wrong card value");
		assertEquals("Heart", tmp2.get(1).suite, "Wrong card suite");
		assertEquals(4, tmp2.get(1).value, "Wrong card value");
	}
	
	@Test
	void testDrawOne1()
	{
		Card tmp = b.drawOne();
		assertEquals("Heart", tmp.suite, "Wrong card suite");
		assertEquals(1, tmp.value, "Wrong card value");
	}
	
	@Test
	void testDrawOne2()
	{
		Card tmp = b.drawOne();
		Card tmp2 = b.drawOne();
		assertEquals("Heart", tmp.suite, "Wrong card suite");
		assertEquals(1, tmp.value, "Wrong card value");
		assertEquals("Heart", tmp2.suite, "Wrong card suite");
		assertEquals(2, tmp2.value, "Wrong card value");
	}
	
	@Test
	void testShuffleDeck1()
	{
		b.shuffleDeck();
		assertEquals(52, b.deck.size(), "Shuffle changed the size of deck");
	}
	
	@Test
	void testShuffleDeck2()
	{
		BaccaratDealer b2 = new BaccaratDealer();
		b2.generateDeck();
		b.shuffleDeck();
		boolean test;
		
		if(b.deck.get(0).suite == b2.deck.get(0).suite && b.deck.get(0).value == b2.deck.get(0).value && b.deck.get(1).suite == b2.deck.get(1).suite && b.deck.get(1).value == b2.deck.get(1).value)
		{
			test = false;
		}
		else
		{
			test = true;
		}
		
		assertTrue(test, "The first 2 elements are same in unshuffled deck and shuffled deck");
	}
	
	@Test
	void testDeckSize1()
	{
		assertEquals(52, b.deckSize(), "Wrong deck size");
	}
	
	@Test
	void testDeckSize2()
	{
		BaccaratDealer b2 = new BaccaratDealer();
		b2.deck = new ArrayList<Card>();
		
		assertEquals(0, b2.deckSize(), "The new deck is not empty");
	}
}
