package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK",SEK.getName());
		assertEquals("DKK",DKK.getName());
		assertEquals("EUR",EUR.getName());
	}
	
	@Test
	public void testGetRate() {
		assertEquals(0.15,SEK.getRate(),0);
		assertEquals(0.20,DKK.getRate(),0);
		assertEquals(1.5,EUR.getRate(),0);
	}
	
	@Test
	public void testSetRate() {
		SEK.setRate(0.16);
		DKK.setRate(0.21);
		EUR.setRate(1.6);
		assertEquals(0.16,SEK.getRate(),0);
		assertEquals(0.21,DKK.getRate(),0);
		assertEquals(1.6,EUR.getRate(),0);
	}
	
	@Test
	public void testGlobalValue() {
		assertEquals(0.15*50*100,SEK.universalValue(50),0);
	}
	
	@Test
	public void testValueInThisCurrency() {
		assertEquals(SEK.getRate() * DKK.getRate() * 50 * 100,SEK.valueInThisCurrency(50,DKK),0);
	}

}
