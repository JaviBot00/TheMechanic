package view;

import java.util.Scanner;

/**
 * Utility class for UI formatting and input handling.
 * Provides methods for printing formatted tables, menus, and handling user
 * input.
 */
public class UIUtils {

    private static final int COLUMN_WIDTH = 20;
    private static final Scanner scanner = new Scanner(System.in);

    // ============ Color/Style Constants ============
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";

    /**
     * Prints a title with formatting.
     */
    public static void printTitle(String title) {
        System.out.println("\n" + BOLD + CYAN + "╔" + "═".repeat(60) + "╗" + RESET);
        System.out.printf(BOLD + CYAN + "║ %-58s ║\n" + RESET, title);
        System.out.println(BOLD + CYAN + "╚" + "═".repeat(60) + "╝" + RESET);
    }

    /**
     * Prints a section header.
     */
    public static void printHeader(String header) {
        System.out.println("\n" + BOLD + GREEN + header + RESET);
        System.out.println(GREEN + "─".repeat(header.length()) + RESET);
    }

    /**
     * Prints a success message.
     */
    public static void printSuccess(String message) {
        System.out.println(GREEN + "✓ " + message + RESET);
    }

    /**
     * Prints an error message.
     */
    public static void printError(String message) {
        System.out.println(RED + "✗ " + message + RESET);
    }

    /**
     * Prints a warning message.
     */
    public static void printWarning(String message) {
        System.out.println(YELLOW + "⚠ " + message + RESET);
    }

    /**
     * Prints an info message.
     */
    public static void printInfo(String message) {
        System.out.println(CYAN + "ℹ " + message + RESET);
    }

    /**
     * Prints a table header line.
     */
    public static void printTableLine() {
        System.out.println("─".repeat(22 * COLUMN_WIDTH / 10));
    }

    /**
     * Prints a CSV data line formatted as a table row.
     */
    public static void printTableRow(String csvLine) {
        String[] columns = csvLine.split(";");
        for (String column : columns) {
            System.out.printf("| %-" + COLUMN_WIDTH + ".20s ", column);
        }
        System.out.println("|");
    }

    /**
     * Prints CSV data formatted as a table.
     */
    public static void printTable(String csvData) {
        if (csvData == null || csvData.isEmpty()) {
            printWarning("No data to display");
            return;
        }
        String[] lines = csvData.split("\n");
        for (String line : lines) {
            if (!line.isEmpty()) {
                printTableRow(line);
            }
        }
        System.out.println("\n" + BOLD + "End of listing" + RESET + "\n");
    }

    /**
     * Reads an integer from user input.
     */
    public static int readInt(String prompt) {
        System.out.print(BOLD + prompt + RESET);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            printError("Invalid integer input");
            return readInt(prompt);
        }
    }

    /**
     * Reads a float from user input.
     */
    public static float readFloat(String prompt) {
        System.out.print(BOLD + prompt + RESET);
        try {
            return Float.parseFloat(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            printError("Invalid decimal input");
            return readFloat(prompt);
        }
    }

    /**
     * Reads a string from user input.
     */
    public static String readString(String prompt) {
        System.out.print(BOLD + prompt + RESET);
        return scanner.nextLine().trim();
    }

    /**
     * Reads a line of input from user.
     */
    public static String readLine(String prompt) {
        System.out.print(BOLD + prompt + RESET);
        return scanner.nextLine();
    }

    /**
     * Shows a yes/no confirmation dialog.
     */
    public static boolean confirm(String message) {
        System.out.print(BOLD + YELLOW + message + " (y/n): " + RESET);
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y") || response.equals("yes");
    }

    /**
     * Waits for user to press Enter.
     */
    public static void pause() {
        System.out.print(BOLD + "Press Enter to continue..." + RESET);
        scanner.nextLine();
    }

    /**
     * Clears the console (works on most terminals).
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Prints a menu with numbered options.
     */
    public static void printMenu(String title, String... options) {
        printTitle(title);
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%2d. %s\n", i + 1, options[i]);
        }
        System.out.println(" 0. Back");
        System.out.println("-1. Exit");
    }

    /**
     * Formats currency to EUR format.
     */
    public static String formatCurrency(float amount) {
        return String.format("€%.2f", amount);
    }

    /**
     * Formats a percentage with one decimal.
     */
    public static String formatPercentage(float percentage) {
        return String.format("%.1f%%", percentage);
    }
}
