import java.util.ArrayList;
import java.util.Collections;
//Class that takes care of creating new deck, generating it, shuffling, dealing hands and deck size
public class BaccaratDealer
{
	ArrayList<Card> deck;
	//Hearts, spades, diamonds, clubs, function that generates deck in order given
	public void generateDeck()
	{
		deck = new ArrayList<Card>();
		
		for(int i = 1; i < 53; i++)
		{
			if(i < 14)
			{
				deck.add(new Card("Heart", i));
			}
			else if(i < 27)
			{
				deck.add(new Card("Spade", i - 13));
			}
			else if(i < 40)
			{
				deck.add(new Card("Diamond", i - 26));
			}
			else if(i < 53)
			{
				deck.add(new Card("Club", i - 39));
			}
		}
	}
	//Function that deals a hand, it returns sublist of 2 card objects, removes 2 objects from the deck
	public ArrayList<Card> dealHand()
	{
		ArrayList<Card> tmp = new ArrayList<Card>(deck.subList(0, 2));
		deck.remove(0);
		deck.remove(0);
		return tmp;
	}
	//Function that draws one card and removes it from the deck, returns single card object
	public Card drawOne()
	{
		Card tmp = deck.get(0);
		deck.remove(0);
		return tmp;
	}
	//Function that shuffles deck using collections.shuffle
	public void shuffleDeck()
	{
		Collections.shuffle(deck);
	}
	//Function that returns deck size
	public int deckSize()
	{
		return deck.size();
	}
	//Our helper function to display current deck, for testing in main
	public void printDeck()
	{
		for(int i = 0; i < deck.size(); i++)
		{
			System.out.println(deck.get(i).suite + " " + deck.get(i).value);
		}
	}
}
