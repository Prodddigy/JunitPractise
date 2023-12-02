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
		assertEquals(10000/100,SEK100.getAmount(),0);
		assertEquals(1000/100,EUR10.getAmount(),0);
		assertEquals(2000/100,EUR20.getAmount(),0);
	}

	@Test
	public void testGetCurrency() {
		assertEquals("100 SEK",SEK100.toString());
		assertEquals("10 EUR",EUR10.toString());
		assertEquals("20 EUR",EUR20.toString());
	}

	@Test
	public void testToString() {
		assertEquals("SEK",SEK100.getCurrency().getName());
		assertEquals("EUR",EUR10.getCurrency().getName());
		assertEquals("EUR",EUR20.getCurrency().getName());
	}

	@Test
	public void testGlobalValue() {
		assertEquals((EUR20.getCurrency().getRate() * EUR20.getAmount())*100,EUR20.universalValue(),0);
	}

	@Test
	public void testEqualsMoney() {
		assertFalse(SEK100.equals(EUR20));
		assertTrue(SEK0.equals(EUR0));
	}

	@Test
	public void testAdd() {//100 + (0.15*1.5*10 *100)   SEK100.getAmount() + EUR10.getCurrency().valueInThisCurrency(EUR10.getAmount(),EUR10.getCurrency())
		//System.out.println(SEK100.add(EUR10).getAmount()+"montest");
		assertEquals((100 + ((1.5*10 )/0.15)),SEK100.add(EUR10).getAmount(),0);
		assertEquals(SEK,SEK100.add(EUR10).getCurrency());
	}

	@Test
	public void testSub() {
		assertEquals((100 - ((1.5*10 )/0.15)),SEK100.sub(EUR10).getAmount(),0);
		assertEquals(SEK,SEK100.sub(EUR10).getCurrency());
	}

	@Test
	public void testIsZero() {
		assertTrue(SEK0.isZero());
		assertFalse(SEK100.isZero());
	}

	@Test
	public void testNegate() {
		assertEquals(-100,SEK100.negate().getAmount(),0);
	}

	@Test
	public void testCompareTo() {
		assertEquals(0,SEK0.compareTo(EUR0));
		assertEquals(1,SEK100.compareTo(SEK0));
		assertEquals(-1,SEK0.compareTo(SEK100));
	}
}
