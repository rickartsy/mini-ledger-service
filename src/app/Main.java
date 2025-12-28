package app;

import app.menu.Menu;
import app.service.AccountService;

public class Main {
    static void main() {
        AccountService service = new AccountService();
        Menu menu = new Menu(service);
        menu.start();
    }
}
