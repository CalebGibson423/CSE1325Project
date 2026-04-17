package theLocalLoop;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class is responsible for validating user input throughout the application. <br>
 * It provides methods to ensure that inputs are of the correct type and format, such as integers, doubles, non-empty strings, specific options, valid passwords, and valid tags. By centralizing input validation in this class, we can maintain cleaner code and ensure consistent validation across different parts of the application.
 */
public class InputValidator {

    /**
     * Prompts the user for an integer input and validates that it falls within a specified range. <br>
     * Checks for integer in {@link #printMenu}, {@link #printDisplayMenu}, {@link #printFilterMenu}, and {@link #printSortMenu}
     * @param input
     * The Scanner object used to read user input.
     * @param prompt
     * The message displayed to the user when asking for input.
     * @param min
     * The minimum acceptable integer value (inclusive).
     * @param max
     * The maximum acceptable integer value (inclusive).
     * @return
     * The valid integer input provided by the user.
     */
    public static int getValidInt(Scanner input, String prompt, int min, int max) {//for print menu
        int value = 0;
        boolean valid = false;

        while(!valid) {
            System.out.print(prompt);
            String line = input.nextLine().trim();

            try {
                value = Integer.parseInt(line);//take the user input as a string and turn it into an int
                if(value < min || value > max) {//value has to be between min and max
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
                else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
        return value;
    }

    /**
     * Prompts the user for a double input and validates that it is a positive number. <br>
     * Checks for double in {@link #addEvent} in {@link EventManager}
     * @param input
     * The Scanner object used to read user input.
     * @param prompt
     * The message displayed to the user when asking for input.
     * @return
     * The valid double input provided by the user.
     */
    public static double getValidDouble(Scanner input, String prompt) {
        double value = 0.0;
        boolean valid = false;

        while(!valid) {
            System.out.print(prompt);
            String line = input.nextLine().trim();

            try {
                value = Double.parseDouble(line);
                if(value <= 0) {
                    System.out.println("Value must be greater than 0.");
                }
                else {
                    valid = true;
            }   
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter an actual number(e.g. 1.5).");
            }
        }

        return value;
    }

    /**
     * Prompts the user for a non-empty string input. <br>
     * Checks for non-empty string in {@link #addEvent} in {@link EventManager} for name, organizer, and location
     * @param input
     * The Scanner object used to read user input.
     * @param prompt
     * The message displayed to the user when asking for input.
     * @return
     * The valid non-empty string input provided by the user.
     */
    public static String getRequiredString(Scanner input, String prompt) {
        String value = "";

        while(value.isEmpty()) {
            System.out.print(prompt);
            value = input.nextLine().trim();

            if(value.isEmpty()) {
                System.out.println("This field cannot be blank.");
            }
        }
        return value;
    }

    /**
     * Prompts the user to select an option from a predefined list of valid options. <br>
     * Checks for specific options in {@link #addEvent} in {@link EventManager} for type of event
     * @param input
     * The Scanner object used to read user input.
     * @param prompt
     * The message displayed to the user when asking for input.
     * @param options
     * An array of valid options that the user can choose from. The input will be validated against this list, ignoring case sensitivity.
     * @return
     * The valid option selected by the user, returned in the same case as defined in the options array.
     */
    public static String getValidOption(Scanner input, String prompt, String[] options) {
        String value = "";
        boolean valid = false;

        while(!valid) {
            System.out.print(prompt);
            value = input.nextLine().trim();
            for(String option : options) {
                if(option.equalsIgnoreCase(value)) {
                    valid = true;
                    value = option;
                    break;
                }
            }
            if(!valid) {
                System.out.print("Invalid option. Please enter one of: ");
                System.out.println(String.join(", ", options));
            }
        }
        return value;
    }

    /**
     * Prompts the user for a password input and validates that it meets specific criteria, such as minimum length, presence of uppercase letters, numbers, and symbols. <br>
     * Checks for password in {@link #addEvent} in {@link EventManager}
     * @param input
     * The Scanner object used to read user input.
     * @param prompt
     * The message displayed to the user when asking for input.
     * @return
     * The valid password input provided by the user. 
     */
    public static String getValidPassword(Scanner input, String prompt) {
        String value = "";
        boolean valid = false;

        while(!valid) {
            System.out.print(prompt);
            value = input.nextLine().trim();

            if(value.isEmpty()) {
                valid = true;//password can still be empty
            }
            else if(value.length() < 8) {
                System.out.println("Password must at least be 8 characters.");
            }
            else if(!value.matches(".*[A-Z].*")) {
                System.out.println("Password must have at least one capital letter.");
            }
            else if(!value.matches(".*[0-9].*")) {
                System.out.println("Password must contain at least one number.");
            }
            else if(!value.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?].*")) {
                System.out.println("Password must contain at least one symbol.");
            }
            else {
                valid = true;
            }
        }
        return value;
    }

    /**
     * Prompts the user to enter a list of tags and validates that each tag is included in a predefined list of valid tags. <br>
     * @param input
     * The Scanner object used to read user input.
     * @param prompt
     * The message displayed to the user when asking for input.
     * @param ValidTags
     * An array of valid tags that the user can choose from. The input will be validated against this list, ignoring case sensitivity. <br>
     * Users should enter tags separated by commas (e.g., "Music, Art, Technology").
     * @return
     * An ArrayList of valid tags selected by the user, returned in the same case as defined in the ValidTags array. <br>
     * If any entered tag is invalid, the user will be prompted to re-enter the tags until all are valid and at least one tag is provided.
     */
    //used for valid tags in addevents for eventmanageer
    public static ArrayList<String> getValidTags(Scanner input, String prompt, String[] ValidTags) {
        ArrayList<String> tags = new ArrayList<>();
        boolean valid = false;

        System.out.println("Valid Tags: " + String.join(", ", ValidTags));

        while(!valid) {
            System.out.print(prompt);
            String line = input.nextLine().trim();
            String[] entered = line.split(",");//split the tags at , because they should be entered as tag, tag, etc

            tags.clear();//reset if there was a retry

            boolean allValid = true;

            for(String tag: entered) {
                String trimmed = tag.trim();//trim every tag of whitespace at the end or before the string
                boolean found = false;

                for(String validTag: ValidTags) {
                    if(validTag.equalsIgnoreCase(trimmed)) {
                        tags.add(validTag);
                        found = true;
                        break;
                    }
                }
                
                if(!found) {
                    System.out.println("\"" + trimmed + "\" is not a valid tag.");
                    allValid = false;
                }
            }

            if(allValid && !tags.isEmpty()) {
                valid = true;
            }
            else if(tags.isEmpty()) {
                System.out.println("Please enter at least one tag.");
            }
        }
        return tags;
    }
}
