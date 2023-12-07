package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		/*
		here we check if getamount returns the correct double value
		 */
		assertEquals(10000/100,SEK100.getAmount(),0);
		assertEquals(1000/100,EUR10.getAmount(),0);
		assertEquals(2000/100,EUR20.getAmount(),0);
	}

	@Test
	public void testGetCurrency() {
		/*
		here we check if getcurrency returns a correct string
		 */


		assertEquals("SEK",SEK100.getCurrency().getName());
		assertEquals("EUR",EUR10.getCurrency().getName());
		assertEquals("EUR",EUR20.getCurrency().getName());
	}

	@Test
	public void testToString() {
		/*
		here we check if tostring of a money object is correct
		 */
		assertEquals("100 SEK",SEK100.toString());
		assertEquals("10 EUR",EUR10.toString());
		assertEquals("20 EUR",EUR20.toString());
	}

	@Test
	public void testGlobalValue() {
		/*
			here we check if universalValue returns the correct result of this transition
		 */
		assertEquals((EUR20.getCurrency().getRate() * EUR20.getAmount())*100,EUR20.universalValue(),0);
	}

	@Test
	public void testEqualsMoney() {
		/*
		here we check if equalsmoney shows if different or equal moneys result in corresponfing boolean values
		 */
		assertFalse(SEK100.equals(EUR20));
		assertTrue(SEK0.equals(EUR0));
	}

	@Test
	public void testAdd() {//100 + (0.15*1.5*10 *100)   SEK100.getAmount() + EUR10.getCurrency().valueInThisCurrency(EUR10.getAmount(),EUR10.getCurrency())
		//System.out.println(SEK100.add(EUR10).getAmount()+"montest");
		/*
		here we check if add results in correct additon even if different currencies are envolved
		and check if the reuslt is in the expected currency
		 */
		assertEquals((100 + ((1.5*10 )/0.15)),SEK100.add(EUR10).getAmount(),0);
		assertEquals(SEK,SEK100.add(EUR10).getCurrency());
	}

	@Test
	public void testSub() {
		/*
		here we check if sub results in correct subtraction even if different currencies are envolved
		and check if the reuslt is in the expected currency
		 */

		assertEquals((100 - ((1.5*10 )/0.15)),SEK100.sub(EUR10).getAmount(),0);
		assertEquals(SEK,SEK100.sub(EUR10).getCurrency());
	}

	@Test
	public void testIsZero() {
		/*
		here we check if the money object is or is not equal to zero
		 */
		assertTrue(SEK0.isZero());
		assertFalse(SEK100.isZero());
	}

	@Test
	public void testNegate() {
		/*
		here we check if we can succefully negate an amount in money object
		 */
		assertEquals(-100,SEK100.negate().getAmount(),0);
	}

	@Test
	public void testCompareTo() {
		/*
		here we check if comparison of moneys works
		0 is for equal
		1 is when object invoking the method is greater than money in arguments
		-1 is when object in arguments is higher than the object invoking this method
		 */
		assertEquals(0,SEK0.compareTo(EUR0));
		assertEquals(1,SEK100.compareTo(SEK0));
		assertEquals(-1,SEK0.compareTo(SEK100));
	}
}
