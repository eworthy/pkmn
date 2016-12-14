/**
 *
 */
package basicFunctions;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author ellen
 *
 */
public class UserInput {

    public static final String[] CHOICES = {"Yes", "No"};
    public static final int YES = 1;
    public static final int NO = 2;

    /**
     * @param keyInput
     */
    public static void printDots(Scanner keyInput) {
        printALine(keyInput, "...");
    }

    /**
     * @param keyInput
     */
    public static void printALine(Scanner keyInput, String lineToPrint) {
        System.out.println(lineToPrint);
        keyInput.nextLine();
    }

    public static int makeMenu(Scanner keyInput, String[] menuArray) {
        for (int i = 1; i <= menuArray.length; i++) {
            System.out.println("    " + i + ".  " + menuArray[i - 1]);
        }

        int result;
        result = getInt(keyInput, "What will you do? ");
        for (;;) {
            if (result >= 1 && result <= menuArray.length) {
                System.out.println("");
                return result;
            } else {
                result = getInt(keyInput, "Invalid: retry ");
            }
        }
    }

    /**
     * Display a menu of possible functions the user can request.
     *
     * @param keyInput: the Scanner to read
     * @param menuTitle: the heading above the menu
     * @param menuArray: the array of menu choices
     * @return: an integer code representing the user's choice
     */
    public static int makeMenu(Scanner keyInput, String menuTitle, String[] menuArray) {
        System.out.println(menuTitle);
        return makeMenu(keyInput, menuArray);
    }

    /**
     * Makes a simple yes-no menu.
     *
     * @param keyInput scanner
     * @param menuTitle heading for the menu
     * @return true if user enters "YES"
     */
    public static boolean yesNoMenu(Scanner keyInput, String menuTitle) {
        return makeMenu(keyInput, menuTitle, CHOICES) == YES;
    }
    
    /**
     * Make a menu from an array list of menuItems
     * @param keyInput
     * @param menuArray
     * @return 
     */
    public static int makeMenu(Scanner keyInput, ArrayList<MenuItem> menuArray) {
        for (int i = 1; i <= menuArray.size(); i++) {
            System.out.println("    " + i + ".  " + menuArray.get(i - 1));
        }

        int result;
        result = getInt(keyInput, "What will you do? ");
        for (;;) {
            if (result >= 1 && result <= menuArray.size()) {
                System.out.println("");
                return result;
            } else {
                result = getInt(keyInput, "Invalid: retry ");
            }
        }
    }

    /**
     * Gets an integer from the user and discards the rest of the line
     *
     * @param keyInput: the scanner
     * @param prompt: the prompt
     * @return the entered integer
     */
    public static int getInt(Scanner keyInput, String prompt) {
        System.out.print(prompt);
        int entry = keyInput.nextInt();
        keyInput.nextLine(); // throw away the rest of the line
        return entry;
    }

    /**
     * Gets a string from the user and discards the rest of the line
     *
     * @param keyInput: the scanner
     * @param prompt: the prompt
     * @return the entered String
     */
    public static String getString(Scanner keyInput, String prompt) {
        System.out.print(prompt);
        String entry = keyInput.nextLine();
        entry = entry.trim();
        return entry;
    }

    public static String[] convertToMakeMenuArray(String[] menuArray) {
        String[] s = new String[menuArray.length + 1];
        for (int i = 0; i < menuArray.length; i++) {
            s[i] = menuArray[i];
        }
        s[s.length - 1] = "Back";
        return s;
    }

    public static Scanner keyInput = new Scanner(System.in);
}
