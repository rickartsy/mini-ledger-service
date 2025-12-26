package app.exception;

public class AccountNotFoundException extends AccountException{
    public AccountNotFoundException(String accountId) {
        super("Account not found: " + accountId);
    }
}
