package theLocalLoop;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

//Valid Types and Formats
import theLocalLoop.Constants.ValidType;
import theLocalLoop.Constants.ValidFormat;

//DateTime Formatters
import static theLocalLoop.Constants.DateTimeFormatters.dateFormatter;
import static theLocalLoop.Constants.DateTimeFormatters.timeFormatter;


/**
 * This class is responsible for validating user input throughout the application. <br>
 * It provides methods to ensure that inputs are of the correct type and format, such as integers, doubles, non-empty strings, specific options, valid passwords, and valid types. By centralizing input validation in this class, we can maintain cleaner code and ensure consistent validation across different parts of the application.
 */
public class InputValidator {

    /**
     * Private constructor to prevent instantiation.
     */
    private InputValidator() {
        // Prevent instantiation
    }
    
    /**
     * Prompts the user for an integer input and validates that it falls within a specified range. <br>
     * Checks for integer in PrintMenu, PrintDisplayMenu, PrintFilterMenu, and PrintSortMenu
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
                System.out.println("\nInvalid input. Please enter a whole number.");
            }
        }
        return value;
    }

    /**
     * Prompts the user for a double input and validates that it is a positive number. <br>
     * Checks for double in addEvent in EventManager
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
                System.out.println("\nInvalid Input. Please enter an actual number(e.g. 1.5).");
            }
        }

        return value;
    }

    /**
     * Prompts the user for a non-empty string input. <br>
     * Checks for non-empty string in addEvent in EventManager for name, organizer, and location
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
                System.out.println("\nThis field cannot be blank.");
            }
        }
        return value;
    }

    /**
     * Prompts the user to select an option from a predefined list of valid options. <br>
     * Checks for specific options in addEvent in EventManager for type of event
     * @param input
     * The Scanner object used to read user input.
     * @param prompt
     * The message displayed to the user when asking for input.
     * @return
     * The valid option selected by the user, returned in the same case as defined in the options array.
     */
    public static ValidFormat getValidFormat(Scanner input, String prompt) {

        ValidFormat[] formats = ValidFormat.values();

        System.out.println("Valid Formats:");
        for (int i = 0; i < formats.length; i++) {
            System.out.print(formats[i].getDisplayName());

            if (i < formats.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();

        while (true) {
            System.out.print(prompt);
            String line = input.nextLine().trim();

            if (line.isEmpty()) {
                System.out.println("\nPlease enter a format.");
                continue;
            }

            for (ValidFormat format : formats) {
                if (format.getDisplayName().equalsIgnoreCase(line)) {
                    return format;
                }
            }

            System.out.println("\nInvalid format. Please try again.\n");
        }
    }

    /**
     * Prompts the user for a password input and validates that it meets specific criteria, such as minimum length, presence of uppercase letters, numbers, and symbols. <br>
     * Checks for password in addEvent in EventManager
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
                System.out.println("\nPassword must at least be 8 characters.");
            }
            else if(!value.matches(".*[A-Z].*")) {
                System.out.println("\nPassword must have at least one capital letter.");
            }
            else if(!value.matches(".*[0-9].*")) {
                System.out.println("\nPassword must contain at least one number.");
            }
            else if(!value.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?].*")) {
                System.out.println("\nPassword must contain at least one symbol.");
            }
            else {
                valid = true;
            }
        }
        return value;
    }

    /**
     * Prompts the user to enter a list of types and validates that each type is included in part of the ValidType enum class. <br>
     * @param input
     * The Scanner object used to read user input.
     * @param prompt
     * The message displayed to the user when asking for input.
     * @return
     * An ArrayList of valid types selected by the user <br>
     * If any entered type is invalid, the user will be prompted to re-enter the types until all are valid and at least one type is provided.
     */
    //used for valid tags in addevents for eventmanageer
    public static ArrayList<ValidType> getValidTypes(Scanner input, String prompt) {
        
        ValidType[] validTypes = ValidType.values();

        System.out.println("Valid Types: " );

        for (int i = 0; i < validTypes.length; i++){
            System.out.print(validTypes[i].getDisplayName());

            if (i < validTypes.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println();

        while(true) {
            System.out.print(prompt);
            String line = input.nextLine().trim();

            if(line.isEmpty()) {
                System.out.println("\nPlease enter at least one type.");
                continue;
            }

            String[] entered = line.split(",\\s*");
            ArrayList<ValidType> temp = new ArrayList<>();
            boolean allValid = true;

            for(String tag: entered) {
                String trimmed = tag.trim();//trim every types of whitespace at the end or before the string
                boolean found = false;

                for(ValidType v: validTypes) {
                    if(v.getDisplayName().equalsIgnoreCase(trimmed)) {
                        temp.add(v);
                        found = true;
                        break;
                    }
                }
                
                if(!found) {
                    System.out.println("\"" + trimmed + "\" is not a valid type.");
                    allValid = false;
                }
            }

            if(allValid && !temp.isEmpty()) {
                return temp;
            }
            
            System.out.println("Please try again.\n");
        }
    }

    /**
     * Prompts the user to enter a date and validates that it is in the correct format (MM-dd-yyyy) and that it is not a past date. <br>
     * @param input
     * The Scanner object used to read user input.
     * @param prompt
     * The message displayed to the user when asking for input.
     * @return
     * The valid LocalDate input provided by the user. <br>
     */
    public static LocalDate getValidDate(Scanner input, String prompt) {

        LocalDate date = null;

        while (date == null) {

            System.out.print(prompt);
            String inputStr = input.nextLine().trim();

            try {
                date = LocalDate.parse(inputStr, dateFormatter);

                if (date.isBefore(LocalDate.now())) {
                    System.out.println("\nDate has already passed. Please enter a valid date.");
                    date = null;
                }

            } catch (Exception e) {
                System.out.println("\nInvalid Date Format. Please use MM-dd-yyyy...");
            }
        }

        return date;
    }

    /**
     * Prompts the user to enter a time and validates that it is in the correct format (HH:mm).
     * @param input
     * The Scanner object used to read user input.
     * @param prompt
     * The message displayed to the user when asking for input.
     * @return
     * The valid LocalTime input provided by the user.
     */
    public static LocalTime getValidTime(Scanner input, String prompt) {

        LocalTime time = null;

        while (time == null) {

            System.out.print(prompt);
            String inputStr = input.nextLine().trim();

            try {
                time = LocalTime.parse(inputStr, timeFormatter);
            } catch (Exception e) {
                System.out.println("\nInvalid Time Format. Please use HH:mm... (e.g. 14:30 for 2:30 PM)");
            }
        }

        return time;
    }
}
