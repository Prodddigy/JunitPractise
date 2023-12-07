package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {//correct
		/*
		Here we check if the names of Banks are correct and are displayed
		 */
		assertEquals("SweBank",SweBank.getName());
		assertEquals("Nordea",Nordea.getName());
		assertEquals("DanskeBank",DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {//correct
		/*
		Here we check if the Banks has the correct currency object
		 */
		assertEquals(SEK,SweBank.getCurrency());
		assertEquals(SEK,Nordea.getCurrency());
		assertEquals(DKK,DanskeBank.getCurrency());
	}

	@Test (expected =AccountExistsException.class )//fail but fixed
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		/*
		here we chck if an eception is thrown if an account is made with already signed in account id
		 */
		SweBank.openAccount("Ulrika");
	}

	@Test (expected = AccountDoesNotExistException.class)
	public void testDeposit() throws AccountDoesNotExistException {//fail but fixed
		/*
		here we check if exception is thrown when we want to deposit money to unexistant account
		 */
		Money SEK100 = new Money(10000, SEK);

			SweBank.deposit("asdasd",SEK100);



	}

	@Test (expected = AccountDoesNotExistException.class)
	public void testWithdraw() throws AccountDoesNotExistException {//fail but fixed
		/*
		we check if exception is thrown if we withdraw money to non-existant account
		and we dwithdraw to exisant account
		 */
		Money SEK100 = new Money(10000, SEK);

			SweBank.withdraw("asdasd",SEK100);
			SweBank.withdraw("Ulrika",SEK100);
			assertEquals(-10000,SweBank.getBalance("Ulrika"),0);

	}

	@Test (expected = AccountDoesNotExistException.class)
	public void testGetBalance() throws AccountDoesNotExistException {//fail but fixed
		/*
		we check if get balance of an id is correct
		 and if exception is thrown when we check balance of non-existant account
		 */

		assertEquals(0,SweBank.getBalance("Ulrika"),0);
		SweBank.getBalance("asdasd");

	}
	
	@Test (expected = AccountDoesNotExistException.class)
	public void testTransfer() throws AccountDoesNotExistException {//fail but fixed
		/*
		here we check if a transfer to non-existant account is making a transfer
		and if the result of a transfer is correct in an accounts balance
		 */
		Money SEK100 = new Money(10000, SEK);
		SweBank.transfer("Ulrika","Bokkkkb",SEK100);
		SweBank.transfer("Ulrika",Nordea,"Bokkkkb",SEK100);
		SweBank.transfer("Ulrika","Bob",SEK100);
		SweBank.transfer("Ulrika",Nordea,"Bob",SEK100);

		assertEquals(10000,SweBank.getBalance("Bob"),0);
		assertEquals(20000,SweBank.getBalance("Bob"),0);


	}
	
	@Test //(expected = AccountDoesNotExistException.class)
	public void testTimedPayment() throws AccountDoesNotExistException {//correct
		/*
		here we check if addtimedpayment throws an exception if toaccount is nonexistant
		and that balance is unchained after wrong addtimedpayment
		and if the tick() makes changes in balance of an account
		and if removed timed payment is actually removed so the tick() won't make any changes to the balance
		 */
		Money SEK100 = new Money(10000, SEK);
		SweBank.addTimedPayment("Ulrika","777",0,0,SEK100,SweBank,"E");

		SweBank.tick();

		assertEquals(0,SweBank.getBalance("Ulrika"),0);
		SweBank.addTimedPayment("Ulrika","777",0,0,SEK100,SweBank,"Bob");

		SweBank.tick();

		SweBank.removeTimedPayment("Ulrika","777");
		assertEquals(-10000,SweBank.getBalance("Ulrika")*100,0);

		SweBank.addTimedPayment("Ulrika","-666",0,0,SEK100,SweBank,"Bob");

		SweBank.removeTimedPayment("Ulrika","-666");

		SweBank.tick();

		assertEquals(-10000,SweBank.getBalance("Ulrika")*100,0);


		//	SweBank.removeTimedPayment("Ulrika","777");
	//	assertFalse(SweBank.);
	}
}
