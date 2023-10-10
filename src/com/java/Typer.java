package src.com.java;

public class Typer {
    
    /**
     * Waits for a specified number of milliseconds.
     * @param millis
     */
    private static void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Prints out a string with a scrolling text effect.
     * @param str
     */
    public static void typeString(String str) {
        String[] strChars = str.split("", 0);
        for(String character : strChars) {
            System.out.print(character);
            wait(20);
        }
    }

    /**
     * Prints out a string with a scrolling text effect with a provided delay between each character.
     * @param str
     * @param characterDelay
     */
    public static void typeString(String str, int characterDelay) {
        String[] strChars = str.split("", 0);
        for(String character : strChars) {
            System.out.print(character);
            wait(characterDelay);
        }
    }


    /**
     * Prints out a string character by character to give it a scrolling text effect, followed by printing a new line once the string is fully printed.
     * @param str
     */
    public static void typeStringln(String str) {
        typeString(str);
        System.out.println("\n");
    } 

    /**
     * Prints out multiple with a scrolling text effect, each being followed by a new line and waiting 2 seconds before going to the next one.
     * @param arr
     */
    public static void typeStrings(String[] arr) {
        for(String str : arr) {
            typeString(str);
            wait(2000);
            System.out.println("\n");
        }
    }

    /**
     * Prints out multiple strings with a scrolling text effect, each being followed by a new line and waiting a specified delay before going to the next one
     * @param arr
     * @param delay
     */
    public static void typeStrings(String[] arr, int delay) {
        for (String str : arr) {
            typeString(str);
            wait(delay);
            System.out.println("\n");
        }
    }
}
