package util;

import java.util.Scanner;

public class InputUtil {

    private static Scanner scanner = new Scanner(System.in);

    public static String inputString(String info) {
        System.out.print(info + ": ");
        String data = scanner.nextLine();
        return data;
    }

    public static int inputInt(String info) {
        System.out.print(info + ": ");
        int data = scanner.nextInt();
        return data;
    }

    public static void resetInput() {
        scanner.nextLine();
    }

}
