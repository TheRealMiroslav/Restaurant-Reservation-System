package org.mns.ui;

import java.util.Scanner;

/**
 * Pomocné metody pro formátování CLI výstupů.
 * Centralizuje všechny výpisové konstanty a formátovací logiku.
 */
public class CliHelper {

    public static final String SEPARATOR = "─".repeat(50);
    public static final String SEPARATOR_DOT = "·".repeat(50);

    // Barvy (ANSI)
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";

    private CliHelper() {
    }

    /**
     * Vypíše formátovaný nadpis sekce.
     *
     * @param text Text nadpisu.
     */
    public static void title(String text) {
        System.out.println();
        System.out.println(SEPARATOR);
        System.out.println(BOLD + "  " + text + RESET);
        System.out.println(SEPARATOR);
    }

    /**
     * Vypíše podnadpis sekce.
     *
     * @param text Text podnadpisu.
     */
    public static void subtitle(String text) {
        System.out.println();
        System.out.println(YELLOW + text + RESET);
        System.out.println(SEPARATOR_DOT);
    }

    /**
     * Vypíše zprávu o úspěchu (zeleně).
     *
     * @param text Text zprávy.
     */
    public static void success(String text) {
        System.out.println(GREEN + "✓ " + text + RESET);
    }

    /**
     * Vypíše chybovou zprávu (červeně).
     *
     * @param text Text chyby.
     */
    public static void error(String text) {
        System.out.println(RED + "✗ " + text + RESET);
    }

    /**
     * Vypíše informativní zprávu (azurově).
     *
     * @param text Text zprávy.
     */
    public static void info(String text) {
        System.out.println(CYAN + "ℹ " + text + RESET);
    }

    /**
     * Vynechá jeden řádek ve výstupu.
     */
    public static void nextLine() {
        System.out.println();
    }

    /**
     * Bezpečné čtení čísla ze vstupu. Při chybném vstupu vrátí -1.
     */
    public static int readNumber(Scanner sc) {
        try {
            String input = sc.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Převede číselné hodnocení na hvězdičkovou reprezentaci.
     *
     * @param hodnoceni Číselné hodnocení (0-5).
     * @return Řetězec s hvězdičkami a číselnou hodnotou.
     */
    public static String toStars(double hodnoceni) {
        int full = (int) Math.round(hodnoceni);
        return "★".repeat(Math.clamp(full, 0, 5)) + "☆".repeat(Math.max(0, 5 - Math.min(5, full))) + String.format(" (%.1f)", hodnoceni);
    }
}