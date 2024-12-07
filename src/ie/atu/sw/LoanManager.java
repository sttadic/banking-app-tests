package ie.atu.sw;

public class LoanManager {
    private BankDeposits bankDeposits;
    
    public LoanManager(BankDeposits bankDeposits) {
    	this.bankDeposits = bankDeposits;
    }
    
	
	/**
     * Approves a loan for an account holder.
     * @param accountHolder The name of the account holder.
     * @param loanAmount The loan amount.
     * @return True if the loan is approved, otherwise false.
     */
    public boolean approveLoan(Account account, double loanAmount) {
        if (account == null || loanAmount > bankDeposits.getTotalDeposits()) return false;
        account.approveLoan(loanAmount);
        bankDeposits.withdrawFromBank(loanAmount);;
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
            bankDeposits.depositToBank(amount);;
            return true;
        }
        return false;
    }
}
