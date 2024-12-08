package ie.atu.sw;

/**
 * This program simulates a simple banking application. It allows:
 * - Adding new accounts with an initial deposit.
 * - Depositing and withdrawing money from accounts.
 * - Approving and repaying loans for account holders.
 * - Tracking the total deposits available in the bank.
 * 
 * The program uses a list of Account objects to manage account data.
 */
public class BankingApp {
	private AccountManager accountManager;
	private LoanManager loanManager;
	private BankDeposits bankDeposits;
	
	public BankingApp() {
		this.bankDeposits = new BankDeposits();
		this.loanManager = new LoanManager(bankDeposits);
		this.accountManager = new AccountManager(bankDeposits);
		
	}

	public static void main(String[] args) {
	    // Create a new banking application instance
	    BankingApp bank = new BankingApp();

	    // Add accounts
	    bank.accountManager.addAccount("Alice", 1000);
	    bank.accountManager.addAccount("Bob", 500);

	    try {
	    	// Test deposits
		    System.out.println("Depositing 200 to Alice: " + bank.accountManager.deposit("Alice", 200)); // Should return true
		    System.out.println("Alice's balance: " + bank.accountManager.getBalance("Alice")); // Should be 1200
		    
		    // Test withdrawals
		    System.out.println("Withdrawing 300 from Bob: " + bank.accountManager.withdraw("Bob", 300)); // Should return true
		    System.out.println("Bob's balance: " + bank.accountManager.getBalance("Bob")); // Should be 200
		    
		    // Test loan approval
		    Account alice = bank.accountManager.findAccount("Alice");
		    System.out.println("Approving a loan of 400 for Alice: " + bank.loanManager.approveLoan(alice, 400)); // Should return true
		    System.out.println("Alice's loan: " + alice.getLoan()); // Should be 400
		    
		    // Test loan repayment
		    System.out.println("Repaying 200 of Alice's loan: " + bank.loanManager.repayLoan(alice, 200)); // Should return true
		    System.out.println("Alice's remaining loan: " + alice.getLoan()); // Should be 200
		    
		    // Check total deposits in the bank
		    System.out.println("Total deposits in the bank: " + bank.bankDeposits.getTotalDeposits());
	    } catch (IllegalArgumentException e) {
	    	System.out.println(e.getMessage());
	    } catch (IllegalStateException e) {
	    	System.out.println(e.getMessage());
	    }
	}
}

