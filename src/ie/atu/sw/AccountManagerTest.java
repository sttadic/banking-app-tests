package ie.atu.sw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AccountManagerTest {
	private static AccountManager accountManager;
	private static BankDeposits bankDeposits;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		bankDeposits = new BankDeposits();
		accountManager= new AccountManager(bankDeposits);
	}
	
	@BeforeEach
	void setUp() throws Exception {
		accountManager.addAccount("Alice", 1000);
		accountManager.addAccount("Bob", 500);
	}
	
	@AfterEach
	void tearDown(TestInfo testInfo) throws Exception {
		System.out.println("Test completed - " + testInfo.getDisplayName());
	}

	@AfterAll
	static void tearDownAfterClass(TestInfo testInfo) throws Exception {
		System.out.println("All tests completed in " + testInfo.getDisplayName());
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
	void testDepositGetBalanceSuccess() {
		assertTrue(accountManager.deposit("Alice", 1000));
		assertEquals(2000, accountManager.getBalance("Alice"));
	}
	
	@ParameterizedTest
	@ValueSource(doubles = {-500.0d, 0d, -0.1d})
	void testDepositFail(double depAmount) {
		assertFalse(accountManager.deposit("Alice", depAmount));
	}
	
	
}
