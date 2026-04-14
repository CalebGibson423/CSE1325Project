package theLocalLoop;

import java.util.Scanner;
import java.util.ArrayList;

public class InputValidator {

    //checks for int in printMenu, printDisplayMenu, printFilterMenu, and printSortMenu
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

    //checks for double in addevent in EventManager
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

    //used in addevents in EventManager for name, organizer, and location
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

    //in add events for type of event
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

    //used in eventmanger addevent for password
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
