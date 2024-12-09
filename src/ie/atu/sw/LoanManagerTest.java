package ie.atu.sw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LoanManagerTest {
	private LoanManager loanManager;
	private BankDeposits bankDeposits;
	private AccountManager accountManager;
	
	@BeforeAll
	static void setUpBeforeClass() {
		System.out.println("Start unit tests in LoanManagerTest suite");
	}
	
	@BeforeEach
	void setUp() {
		bankDeposits = new BankDeposits();
		accountManager = new AccountManager(bankDeposits);
		accountManager.addAccount("Alice", 1000);
		accountManager.addAccount("Bill", 500);
		loanManager = new LoanManager(bankDeposits);
	}
	
	@AfterEach
	void tearDown(TestInfo testInfo) {
		System.out.println("Test completed - " + testInfo.getDisplayName());
	}
	
	@AfterAll
	static void tearDownAfterClass() {
		System.out.println("All tests in LoanManagerTest completed");
	}
	
	
	@ParameterizedTest
	@ValueSource(doubles = {1500.01, Double.MAX_VALUE})		// total deposits = 1500
	@Timeout(value = 8, unit=TimeUnit.MILLISECONDS)
	void testApproveLoanThrowsExceptionLoanExceedesDeposits(double loanAmount) {
		assertThrows(IllegalStateException.class, () -> {
			loanManager.approveLoan(accountManager.findAccount("Alice"), loanAmount);
			assertEquals(1500, bankDeposits.getTotalDeposits());
		});
	}
	
	@ParameterizedTest
	@CsvSource({"Alice, 1500", "Bill, 0.1"})				// total deposits = 1500
	@Timeout(value = 5, unit=TimeUnit.MILLISECONDS)
	void testApproveLoanSuccess(String accHolder, double loanAmount) {
		loanManager.approveLoan(accountManager.findAccount(accHolder), loanAmount);
	}
}
