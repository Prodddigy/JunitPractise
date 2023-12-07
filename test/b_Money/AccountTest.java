package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {//correct
		/*
		here we check if addtimedpayment exists with boolean values
		and if the removeTimedPayment actually removes the payment
		 */
		Money SEK100 = new Money(10000, SEK);
		testAccount.addTimedPayment("666",1,2,SEK100,SweBank,"Alice");
		assertTrue(testAccount.timedPaymentExists("666"));
		testAccount.removeTimedPayment("666");
		assertFalse(testAccount.timedPaymentExists("666"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {//failed but fixed
		/*
		here we check if timedPayment throws exception when toaccount is non-existant
		and if tick() makes changes in the balance even after a correct addition of timedpayment
		 */
		Money SEK100 = new Money(10000, SEK);
		testAccount.addTimedPayment("666",0,0,SEK100,SweBank,"E");
		testAccount.tick();

		assertEquals(10000000,testAccount.getBalance()*100,0);
		testAccount.addTimedPayment("111",0,0,SEK100,SweBank,"Alice");
		testAccount.tick();

		assertEquals(10000000-10000,testAccount.getBalance()*100,0);
	}

	@Test
	public void testAddWithdraw() {//correct
		/*
		here we check if we can withdraw money from an account
		 */
		Money SEK100 = new Money(10000, SEK);
		testAccount.withdraw(SEK100);
		assertEquals(10000000-10000,testAccount.getBalance()*100,0);
	}
	
	@Test
	public void testGetBalance() {//correct
		/*
		here we check if we can deposit and check with getbalance if the expected amount is the same
		as acutal
		 */
		Money SEK100 = new Money(10000, SEK);
		testAccount.deposit(SEK100);
		assertEquals(10000000+10000,testAccount.getBalance()*100,0);
	}
}
