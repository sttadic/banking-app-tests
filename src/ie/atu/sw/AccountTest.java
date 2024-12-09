package ie.atu.sw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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
		System.out.println("All tests in AccountTest completed\n");
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
		assertTrue(account.withdraw(999.99));
	}
	
	@ParameterizedTest
	@ValueSource(doubles = {1000.01, 1100, Double.MAX_VALUE})
	void testWithdrawInsufficientFunds(double amount) {
		assertFalse(account.withdraw(1200));
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
		assertTrue(account.repayLoan(499));
		assertEquals(1, account.getLoan());
	}
	
	@ParameterizedTest
	@ValueSource(doubles = {500.01, 1000, Double.MAX_VALUE})
	void testRepayLoanExceedingLoanAmount(double amount) {
		account.approveLoan(500);
		assertFalse(account.repayLoan(amount));
		assertEquals(500, account.getLoan());
		assertEquals(1000, account.getBalance());
	}
}
