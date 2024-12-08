package ie.atu.sw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountManagerTest {
	private static AccountManager accountManager;
	private static BankDeposits bankDeposits;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		bankDeposits = new BankDeposits();
		accountManager= new AccountManager(bankDeposits);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testFindAccountThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			accountManager.findAccount(null);
		});
	}
	
	@Test
	void testFindAccountSuccess() {
		accountManager.addAccount("John", 10000);
		assertNotNull( accountManager.findAccount("John"));
	}

}
