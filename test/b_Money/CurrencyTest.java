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
		/*
		here we check if getname return correct string
		 */
		assertEquals("SEK",SEK.getName());
		assertEquals("DKK",DKK.getName());
		assertEquals("EUR",EUR.getName());
	}
	
	@Test
	public void testGetRate() {
		/*
		here we check if get rate returns correct rate
		 */
		assertEquals(0.15,SEK.getRate(),0);
		assertEquals(0.20,DKK.getRate(),0);
		assertEquals(1.5,EUR.getRate(),0);
	}
	
	@Test
	public void testSetRate() {
		/*
		here we check if setrate actually sets a new rate
		 */
		SEK.setRate(0.16);
		DKK.setRate(0.21);
		EUR.setRate(1.6);
		assertEquals(0.16,SEK.getRate(),0);
		assertEquals(0.21,DKK.getRate(),0);
		assertEquals(1.6,EUR.getRate(),0);
	}
	
	@Test
	public void testGlobalValue() {
		/*
		here we check if universalValue returns the correct result of this transition
		 */
		assertEquals(0.15*50*100,SEK.universalValue(50),0);
	}
	
	@Test
	public void testValueInThisCurrency() {
		/*
		here we check if valueInThisCurrency results in crrect transition from 50 DKK to a amount in SEK
		 */
		assertEquals(SEK.getRate() * DKK.getRate() * 50 * 100,SEK.valueInThisCurrency(50,DKK),0);
	}

}
