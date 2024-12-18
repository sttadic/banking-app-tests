package ie.atu.sw;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BankDepositsTest {
	private BankDeposits bankDeposits;
	
	@BeforeAll
	static void setUpBeforeClass() {
		System.out.println("Start unit tests in BankDeposits suite");
	}
	
	@BeforeEach
	void setUp() {
		bankDeposits = new BankDeposits();
		bankDeposits.depositToBank(1000);
	}
	
	@AfterEach
	void tearDown(TestInfo testInfo) {
		System.out.println("Test completed - " + testInfo.getDisplayName());
	}
	
	@AfterAll
	static void tearDownAfterClass() {
		System.out.println("All tests in BankDeposits completed\n");
	}
	
	
	@Test
	void testGetTotalDeposits() {
		assertEquals(1000, bankDeposits.getTotalDeposits());
	}
	
	@Test
	void testDepositToBankSuccess() {
		bankDeposits.depositToBank(1000);
		assertEquals(2000, bankDeposits.getTotalDeposits());
	}
	
	@ParameterizedTest
	@ValueSource(doubles = {-100, 0, -Double.MIN_VALUE})
	@Timeout(value = 5, unit=TimeUnit.MILLISECONDS)
	void testDepositToBankInvalidAmount(double depAmount) {
		bankDeposits.depositToBank(depAmount);
		assertEquals(1000, bankDeposits.getTotalDeposits());	// total deposits unchanged
	}

	@ParameterizedTest
	@ValueSource(doubles = {-10, 0, 1000.01, Double.MAX_VALUE})
	void testWithdrawFromBankInvalidWithdrawalAmounts(double witAmount) {
		bankDeposits.withdrawFromBank(witAmount);
		assertEquals(1000, bankDeposits.getTotalDeposits());	// total deposits unchanged
	}
	
	@Test
	void testWithdrawFromBankSuccess() {
		bankDeposits.withdrawFromBank(1000);
		assertEquals(0, bankDeposits.getTotalDeposits());
	}
}
