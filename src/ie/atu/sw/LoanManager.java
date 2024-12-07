package ie.atu.sw;

public class LoanManager {
	// Tracks total deposits at bank-wide level (across all accounts in the bank)
	private double totalDeposits;
	
	public LoanManager() {
		this.totalDeposits = 0;
	}
	
	 /**
     * Gets the total deposits available in the bank.
     * @return The total deposits.
     */
    public double getTotalDeposits() {
        return totalDeposits;
    }
    
    
    /**
     * Increases totalDeopsits by deposit amount.
     * @param amount The deposit amount.
     */
	public void depositToBank(double amount) {
		if (amount > 0) totalDeposits += amount;
	}
	
	/**
     * Decreases totalDeopsits by withdrawal amount.
     * @param amount The withdrawal amount.
     */
	public void withdrawFromBank(double amount) {
		if (amount > 0 && amount <= totalDeposits) {
			totalDeposits -= amount;
		}
	}
	
	/**
     * Approves a loan for an account holder.
     * @param accountHolder The name of the account holder.
     * @param loanAmount The loan amount.
     * @return True if the loan is approved, otherwise false.
     */
    public boolean approveLoan(Account account, double loanAmount) {
        if (account == null || loanAmount > totalDeposits) return false;
        account.approveLoan(loanAmount);
        totalDeposits -= loanAmount;
        return true;
    }

    /**
     * Repays a part of the loan for an account holder.
     * @param accountHolder The name of the account holder.
     * @param amount The repayments amount.
     * @return True if the repayments is successful, otherwise false.
     */
    public boolean repayLoan(Account account, double amount) {
        if (account == null || amount <= 0) return false;
        if (account.repayLoan(amount)) {
            totalDeposits += amount;
            return true;
        }
        return false;
    }
}
