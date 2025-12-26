package app.io;

import java.math.BigDecimal;
import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt(String prompt) {
        System.out.print(prompt);
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static BigDecimal readBigDecimal(String prompt) {
        System.out.print(prompt);
        BigDecimal value = scanner.nextBigDecimal();
        scanner.nextLine();
        return value;
    }
}
