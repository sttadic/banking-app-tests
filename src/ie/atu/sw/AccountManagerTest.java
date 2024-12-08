package ie.atu.sw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class AccountManagerTest {
	private static AccountManager accountManager;
	private static BankDeposits bankDeposits;

	@BeforeAll
	static void setUpBeforeClass() {
		System.out.println("Start unit tests in AccountManagerTest suite");
	}
	
	@BeforeEach
	void setUp() {
		bankDeposits = new BankDeposits();
		accountManager = new AccountManager(bankDeposits);
		accountManager.addAccount("Alice", 1000);
		accountManager.addAccount("Bob", 500);
	}
	
	@AfterEach
	void tearDown(TestInfo testInfo) {
		System.out.println("Test completed - " + testInfo.getDisplayName());
	}

	@AfterAll
	static void tearDownAfterClass() {
		System.out.println("All tests in AccountManagerTest completed");
	}

	
	@ParameterizedTest
	@ValueSource(strings = {"Ann", "123", "null"})
	void testFindAccountThrowsException(String accHolder) {
		assertThrows(IllegalArgumentException.class, () -> {
			accountManager.findAccount(accHolder);
		});
	}
	
	@Test
	void testFindAddAccountSuccess() {
		accountManager.addAccount("Tim", 1500);
		assertNotNull(accountManager.findAccount("Tim"));
	}
	
	@Test
	@Timeout(value = 1, unit=TimeUnit.MILLISECONDS)
	void testDepositGetBalanceSuccess() {
		assertTrue(accountManager.deposit("Alice", Double.MAX_VALUE));
		assertEquals(Double.MAX_VALUE, accountManager.getBalance("Alice"));
	}
	
	@ParameterizedTest
	@ValueSource(doubles = {0, -0.1, -Double.MIN_VALUE})
	void testDepositFail(double depAmount) {
		assertFalse(accountManager.deposit("Alice", depAmount));
	}
	
	@Test
	void testWithdrawThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			accountManager.withdraw("Alice", 0);
		});
	}
	
	@ParameterizedTest
	@CsvSource({"Alice, 0.1", "Bob, 500"})
	void testWithdrawSuccess(String accHolder, double witAmount) {
		assertTrue(accountManager.withdraw(accHolder, witAmount));
	}
	
	@ParameterizedTest
	@CsvSource({"Alice, 1001", "Bob, 500.01"})
	void testWithdrawFail(String accHolder, double witAmount) {
		assertFalse(accountManager.withdraw(accHolder, witAmount));
	}
}
