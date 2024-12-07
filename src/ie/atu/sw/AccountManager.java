package ie.atu.sw;

import java.util.*;

public class AccountManager {
	private List<Account> accounts;
	private BankDeposits bankDeposits;
	
	public AccountManager(BankDeposits bd) {
		this.accounts = new ArrayList<Account>();
		this.bankDeposits = bd;
	}
	
	
	 /**
     * Helper method to find an account by account holder's name.
     * @param accountHolder The name of the account holder.
     * @return The Account object if found, otherwise null.
     */
    public Account findAccount(String accountHolder) {
        for (Account account : accounts) {
            if (account.getAccountHolder().equals(accountHolder)) {
                return account;
            }
        }
        return null;
    }

    /**
     * Adds a new account with an initial deposit.
     * @param accountHolder The name of the new account holder.
     * @param initialDeposit The initial deposit amount.
     */
    public void addAccount(String accountHolder, double initialDeposit) {
        accounts.add(new Account(accountHolder, initialDeposit));
        bankDeposits.depositToBank(initialDeposit);
    }

    /**
     * Deposits money into an account.
     * @param accountHolder The name of the account holder.
     * @param amount The deposit amount.
     * @return True if the deposit is successful, otherwise false.
     */
    public boolean deposit(String accountHolder, double amount) {
        Account account = findAccount(accountHolder);
        if (account == null || amount <= 0) return false;
        account.deposit(amount);
        bankDeposits.depositToBank(amount);
        return true;
    }

    /**
     * Withdraws money from an account.
     * @param accountHolder The name of the account holder.
     * @param amount The withdrawal amount.
     * @return True if the withdrawal is successful, otherwise false.
     */
    public boolean withdraw(String accountHolder, double amount) {
        Account account = findAccount(accountHolder);
        if (account == null || amount <= 0) return false;
        if (account.withdraw(amount)) {
        	bankDeposits.withdrawFromBank(amount);
            return true;
        }
        return false;
    }
    
    /**
     * Gets the balance of a specific account holder.
     * @param accountHolder The name of the account holder.
     * @return The balance if the account exists, otherwise null.
     */
    public Double getBalance(String accountHolder) {
        Account account = findAccount(accountHolder);
        return account != null ? account.getBalance() : null;
    }
}
