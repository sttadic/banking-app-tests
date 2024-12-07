package ie.atu.sw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountTest {
	private Account acc;
	
	@BeforeEach
	void setUp() {
		this.acc = new Account("Stjepan", 1000);
	}

	@Test
	void testConstructor() {
		assertEquals("Stjepan", acc.getAccountHolder());
		assertEquals(1000, acc.getBalance());
		assertEquals(0, acc.getLoan());
	}
	
	@Test
	void testGetAccountHolder() {
		assertEquals("Stjepan", acc.getAccountHolder());
	}
	
	@Test
	void testGetBalance() {
		assertEquals(1000, acc.getBalance());
	}
	
	@Test
	void testGetLoan() {
		assertEquals(0, acc.getLoan());
	}
	
	@Test
	void testDeposit() {
		acc.deposit(500);
		assertEquals(1500, acc.getBalance());
	}
	
	@Test
	void testWithdrawSuccess() {
		boolean result = acc.withdraw(200);
		assertTrue(result);
		assertEquals(800, acc.getBalance());
	}
	
	@Test
	void testWithdrawInsufficientFunds() {
		boolean result = acc.withdraw(1200);
		assertFalse(result);
		assertEquals(1000, acc.getBalance());
	}
	
	@Test
	void testApproveLoan() {
		acc.approveLoan(500);
		assertEquals(500, acc.getLoan());
	}

	@Test
	void testRepayLoanSuccess() {
		acc.approveLoan(500);
		boolean result = acc.repayLoan(300);
		assertTrue(result);
		assertEquals(200, acc.getLoan());
	}
	
	@Test
	void testRepayLoanFail() {
		acc.approveLoan(500);
		boolean result = acc.repayLoan(700);
		assertFalse(result);
		assertEquals(500, acc.getLoan());
		assertEquals(1000, acc.getBalance());
	}
	
	@AfterEach
	void tearDown() {
		System.out.println("Test completed");
	}

}
