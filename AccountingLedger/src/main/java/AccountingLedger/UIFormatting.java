package AccountingLedger;

public class UIFormatting {

    // ==========================================
    // COLOR CONSTANTS
    // ==========================================

    public static final String RESET = "\u001B[0m";

    // Text Colors
    public static final String GOLD = "\u001B[38;2;214;173;85m";
    public static final String TAUPE = "\u001B[38;2;129;102;85m";
    public static final String WHITE = "\u001B[38;2;235;235;235m";
    public static final String SILVER = "\u001B[38;2;175;175;175m";

    // Status Colors
    public static final String RED = "\u001B[38;2;205;75;75m";
    public static final String GREEN = "\u001B[38;2;100;180;120m";

    // Background Colors
    public static final String BG_CHARCOAL = "\u001B[48;2;23;23;23m";
    public static final String BG_DARK_GRAY = "\u001B[48;2;51;51;51m";

    // ==========================================
    // UI FORMATTING CONSTANTS
    // ==========================================

    public static final String TOP_BORDER =
            "╔══════════════════════════════════════╗";

    public static final String BOTTOM_BORDER =
            "╚══════════════════════════════════════╝";

    public static final String DIVIDER =
            "╠══════════════════════════════════════╣";

    public static final String EMPTY_BORDER =
            "║                                      ║";

    public static final String SMALL_TOP_BORDER =
            "╔══════════════════════════════╗";

    public static final String SMALL_BOTTOM_BORDER =
            "╚══════════════════════════════╝";

    public static final String SMALL_DIVIDER =
            "╠══════════════════════════════╣";

    public static final String SMALL_EMPTY_BORDER =
            "║                              ║";

    // ==========================================
    // REUSABLE UI METHODS
    // ==========================================

    public static void printLHeader(String title) {

        System.out.println(GOLD + TOP_BORDER + RESET);

        System.out.println(GOLD + "║" + RESET +
                GOLD + centerText(title, 50) + RESET +
                GOLD + "║" + RESET);

        System.out.println(GOLD + BOTTOM_BORDER + RESET);
    }

    public static void printSHeader(String title) {

        System.out.println(GOLD + SMALL_TOP_BORDER + RESET);

        System.out.println(GOLD + "║" + RESET +
                GOLD + centerText(title, 30) + RESET +
                GOLD + "║" + RESET);

        System.out.println(GOLD + SMALL_BOTTOM_BORDER + RESET);
    }

    public static void printDivider() {

        System.out.println(TAUPE + DIVIDER + RESET);

    }

    public static void printMenuOption(String option, String description) {

        System.out.println(
                GOLD + "║ " + RESET +
                        GOLD + option + RESET +
                        WHITE + " " + description + RESET
        );

    }

    public static void printMessage(String message, String color) {

        System.out.println(SILVER + message + RESET);

    }

    // ==========================================
    // TEXT FORMATTING
    // ==========================================

    public static String centerText(String text, int width) {

        int padding = Math.max(0, width - text.length());

        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;

        return " ".repeat(leftPadding) +
                text +
                " ".repeat(rightPadding);

    }

}

