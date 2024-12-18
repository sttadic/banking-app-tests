package ie.atu.sw;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
	AccountTest.class,
	AccountManagerTest.class,
	LoanManagerTest.class,
	BankDepositsTest.class,
	BankingAppTest.class
})
public class RunnerTest {

}
