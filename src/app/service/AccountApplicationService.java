package app.service;

import app.dto.AccountView;

import java.math.BigDecimal;
import java.util.List;

public class AccountApplicationService {
    public final AccountService accountService;

    public AccountApplicationService(AccountService accountService) {
        this.accountService = accountService;
    }


    public String createAccount(String ownerName) {
        return accountService.createAccount(ownerName);
    }

    public AccountView deposit(String accountId, BigDecimal amount) {
        accountService.deposit(accountId, amount);
        return accountService.viewAccount(accountId);
    }

    public AccountView withdraw(String accountId, BigDecimal amount) {
        accountService.withdraw(accountId, amount);
        return accountService.viewAccount(accountId);
    }

    public AccountView viewAccount(String accountId) {
        return accountService.viewAccount(accountId);
    }

    public List<AccountView> listAccounts() {
        return accountService.listAccounts();
    }
}
