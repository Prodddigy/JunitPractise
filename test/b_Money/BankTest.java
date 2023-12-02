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
		assertEquals("SweBank",SweBank.getName());
		assertEquals("Nordea",Nordea.getName());
		assertEquals("DanskeBank",DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {//correct
		assertEquals(SEK,SweBank.getCurrency());
		assertEquals(SEK,Nordea.getCurrency());
		assertEquals(DKK,DanskeBank.getCurrency());
	}

	@Test (expected =AccountExistsException.class )//fail but fixed
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		SweBank.openAccount("Ulrika");
	}

	@Test (expected = AccountDoesNotExistException.class)
	public void testDeposit() throws AccountDoesNotExistException {//fail but fixed
		Money SEK100 = new Money(10000, SEK);

			SweBank.deposit("asdasd",SEK100);



	}

	@Test (expected = AccountDoesNotExistException.class)
	public void testWithdraw() throws AccountDoesNotExistException {//fail but fixed
		Money SEK100 = new Money(10000, SEK);

			SweBank.withdraw("asdasd",SEK100);
			SweBank.withdraw("Ulrika",SEK100);
			assertEquals(-10000,SweBank.getBalance("Ulrika"),0);

	}

	@Test (expected = AccountDoesNotExistException.class)
	public void testGetBalance() throws AccountDoesNotExistException {//fail but fixed

		assertEquals(0,SweBank.getBalance("Ulrika"),0);
		SweBank.getBalance("asdasd");

	}
	
	@Test (expected = AccountDoesNotExistException.class)
	public void testTransfer() throws AccountDoesNotExistException {//fail but fixed
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
