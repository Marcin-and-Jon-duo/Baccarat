import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
//Testing class for Card class, testing constructor
class CardTest
{
	@Test
	void testCons()
	{
		Card test = new Card("Spade", 5);
		assertEquals("Spade", test.suite, "Wrong suite in constructor");
		assertEquals(5, test.value, "Wrong value in constructor");
	}
}
