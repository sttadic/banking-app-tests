package ie.atu.sw;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * BankingApp class serves as a simple orchestrator, delegating functionality
 * to the AccountManager, LoanManager and BankDeposits classes. Since those
 * classes are tested, minimal testing is implemented here to ensure proper 
 * initialization of dependencies
 */

class BankingAppTest {

	@BeforeAll
	static void setUpBeforeClass() {
		System.out.println("Start unit tests in BankingAppTest suite");
	}
	
	@AfterEach
	void tearDown(TestInfo testInfo) {
		System.out.println("Test completed - " + testInfo.getDisplayName());
	}

	@AfterAll
	static void tearDownAfterClass() {
		System.out.println("All tests in BankingApp completed\n");
	}

	
	@Test
	void testBankingAppDependencies() {
		BankingApp bankingApp = new BankingApp();
		
		assertNotNull(bankingApp.getAccountManager());
		assertNotNull(bankingApp.getLoanManager());
		assertNotNull(bankingApp.getBankDeposits());
	}
}
