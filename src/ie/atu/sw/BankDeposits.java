package ie.atu.sw;

public class BankDeposits {
	// Tracks total deposits at bank-wide level (across all accounts in the bank)
	private double totalDeposits;
	
	public BankDeposits() {
		this.totalDeposits = 0;
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
		if (amount > 0 && amount <= totalDeposits) totalDeposits -= amount;
	}
	
	/**
     * Gets the total deposits available in the bank.
     * @return The total deposits.
     */
	public double getTotalDeposits() {
		return totalDeposits;
	}
}
