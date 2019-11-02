import java.util.ArrayList;
//Main class to run the logic behind the game
public class BaccaratGameLogic
{
	//Function that determines who won and returns the string, follows the rules of game
	public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2)
	{
		int total1 = handTotal(hand1);
		int total2 = handTotal(hand2);
		
		if(total1 > 7 || total2 > 7)
		{
			if(total1 > total2)
			{
				return "Player";
			}
			else if(total1 < total2)
			{
				return "Banker";
			}
			else
			{
				return "Draw";
			}
		}
		else
		{
			if(total1 > total2)
			{
				return "Player";
			}
			else if(total1 < total2)
			{
				return "Banker";
			}
			else
			{
				return "Draw";
			}
		}
	}
	//Function that returns the total value of hand ignoring cards that are > 9 which would have 0 value, plus it reduces the total value by 10 each time total is > 9
	public int handTotal(ArrayList<Card> hand)
	{
		int size = hand.size();
		int total = 0;
		
		for(int i = 0; i < size; i++)
		{
			if(hand.get(i).value > 9)
			{
				
			}
			else
			{
				total = total + hand.get(i).value;
			}
			
			if(total > 9)
			{
				total = total - 10;
			}
		}
		
		return total;
	}
	//Function that returns true if banker should draw another card and false if he does not, follows the rule of the game
	public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard)
	{
		int total = handTotal(hand);
		
		if(total > 6)
		{
			return false;
		}
		else if(total < 3)
		{
			return true;
		}
		else
		{
			if(playerCard == null && total < 6)
			{
				return true;
			}
			else if(playerCard == null)
			{
				return false;
			}
			else
			{
				if(total == 3 && playerCard.value != 8)
				{
					return true;
				}
				else if(total == 4 && 1 < playerCard.value && playerCard.value < 8)
				{
					return true;
				}
				else if(total == 5 && 3 < playerCard.value && playerCard.value < 8)
				{
					return true;
				}
				else if(total == 6 && 5 < playerCard.value && playerCard.value < 8)
				{
					return true;
				}
			}
		}
		
		return false;
	}
	//Function that returns true if player should draw another card and false if he does not
	public boolean evaluatePlayerDraw(ArrayList<Card> hand)
	{
		if(handTotal(hand) < 6)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
