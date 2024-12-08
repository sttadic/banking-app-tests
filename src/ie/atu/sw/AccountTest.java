package ie.atu.sw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.TestInfo;

class AccountTest {
	private Account account;
	
	@BeforeAll
	static void setUpBeforeClass() {
		System.out.println("Start unit tests in AccountTest suite");
	}
	
	@BeforeEach
	void setUp() {
		this.account = new Account("Stjepan", 1000);
	}
	
	@AfterEach
	void tearDown(TestInfo testInfo) {
		System.out.println("Test completed - " + testInfo.getDisplayName());
	}
	
	@AfterAll
	static void tearDownAfterClass() {
		System.out.println("All tests in AccountTest completed");
	}

	
	@Test
	void testConstructor() {
		assertEquals("Stjepan", account.getAccountHolder());
		assertEquals(1000, account.getBalance());
		assertEquals(0, account.getLoan());
	}
	
	@ParameterizedTest
	@CsvSource({"John, 2000, 0", "Ann, 500, 200"})
	void testAccountGetters(String accountHolder, double balance, double loan) {
		Account acc = new Account(accountHolder, balance);
		acc.approveLoan(loan);
		assertEquals(accountHolder, acc.getAccountHolder());
		assertEquals(balance, acc.getBalance());
		assertEquals(loan, acc.getLoan());
	}
	
	@Test
	void testDeposit() {
		account.deposit(500);
		assertEquals(1500, account.getBalance());
	}
	
	@Test
	void testWithdrawSuccess() {
		boolean result = account.withdraw(200);
		assertTrue(result);
		assertEquals(800, account.getBalance());
	}
	
	@Test
	void testWithdrawInsufficientFunds() {
		boolean result = account.withdraw(1200);
		assertFalse(result);
		assertEquals(1000, account.getBalance());
	}
	
	@Test
	void testApproveLoan() {
		account.approveLoan(500);
		assertEquals(500, account.getLoan());
	}

	@Test
	void testRepayLoanSuccess() {
		account.approveLoan(500);
		boolean result = account.repayLoan(300);
		assertTrue(result);
		assertEquals(200, account.getLoan());
	}
	
	@Test
	void testRepayLoanExceedingLoanAmount() {
		account.approveLoan(500);
		boolean result = account.repayLoan(500.01);
		assertFalse(result);
		assertEquals(500, account.getLoan());
		assertEquals(1000, account.getBalance());
	}
}
