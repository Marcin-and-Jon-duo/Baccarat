import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
//Testing class for BaccaratGame class, tests a single method
class GameTest
{
	BaccaratGame b;
	
	@Test
	void testEvaluateWinnings1()
	{
		b = new BaccaratGame();
		b.betted = "Banker";
		b.winner = "Banker";
		b.currentBet = 100;
		
		assertEquals(90, b.evaluateWinnings(), "Betting 100 on 'Banker' does not return 90 winnings");
	}
	
	@Test
	void testEvaluateWinnings2()
	{
		b = new BaccaratGame();
		b.betted = "Draw";
		b.winner = "Draw";
		b.currentBet = 100;
		
		assertEquals(700, b.evaluateWinnings(), "Betting 100 on 'Banker' does not return 700 winnings");
	}
}
