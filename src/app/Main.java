package app;

import app.menu.Menu;
import app.service.AccountApplicationService;
import app.service.AccountService;

public class Main {
    static void main() {
        AccountService accountService = new AccountService();
        AccountApplicationService applicationService = new AccountApplicationService(accountService);
        Menu menu = new Menu(applicationService);
        menu.start();
    }
}
