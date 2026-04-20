package theLocalLoop;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * The MenuManager class handles the display and processing of the main menu options for various methods.
 */
public class MenuManager {
    
  /**
   * Private constructor to prevent instantiation.
   */
  private MenuManager() {
      // Prevent instantiation
  }

  /**
   * Prints the main menu options to the console.
   */
  public static void printMenu() { 
    System.out.println("\n--- Main Menu ---");
    System.out.println("1. View Local Events");
    System.out.println("2. Search Local Events");
    System.out.println("3. Sort and filter Events");
    System.out.println("4. Add Event");
    System.out.println("5. Edit Event");
    System.out.println("6. Delete Event");
    System.out.println("7. Exit");
  }

  /**
   * Prints the display menu options to the console for viewing events based on different time frames.
   */
  public static void printDisplayMenu() { 
    System.out.println("\nWould you like to see-   ");
    System.out.println("1. Events happening today");
    System.out.println("2. Events happening in the next week");
    System.out.println("3. Events happening in the next month");
    System.out.println("4. Events happening at a later date");
    System.out.println("5. All events");
  }

  /**
   * Prints the filter menu options to the console for filtering events based on type, organizer, or no filtering.
   */
  public static void printFilterMenu() { 
    System.out.println("\nHow would you like to filter the events?   ");
    System.out.println("1. By Type");
    System.out.println("2. By Organizer");
    System.out.println("3. No filtering");
  }

  /** 
   * Prints the sort menu options to the console for sorting events based on name, date and time, organizer, or duration.
  */
  public static void printSortMenu() { 
    System.out.println("\nHow would you like to sort the events?   ");
    System.out.println("1. By Name");
    System.out.println("2. By Date and Time");
    System.out.println("3. By Organizer");
    System.out.println("4. By Duration");
  }

  /** 
   * Prints the edit menu options to the console for editing event attributes.
   */
  public static void printEditMenu() { 
    System.out.println("\nWhich attribute would you like to edit?");
    System.out.println("1. Name");
    System.out.println("2. Date");
    System.out.println("3. Time");
    System.out.println("4. Duration");
    System.out.println("5. Types");
    System.out.println("6. Format");
    System.out.println("7. Organizer");
    System.out.println("8. Password");
    System.out.println("9. Location");
    System.out.println("10. Exit");
  }

  /**
   * Handles the user's menu choice and calls the appropriate methods from the EventManager class based on the selected option.
   * @param choice
   * The user's menu choice as an integer.
   * @param input
   * A Scanner object for reading user input from the console.
   * @param events
   * A LinkedList of Event objects representing the list of events to be managed.
   */
  public static void handleChoice(int choice, Scanner input, LinkedList<Event> events) {

      switch(choice)
      {
        //View Events
        case 1:
          EventManager.displayChoice(events, input);
          break;

        case 2:
          EventManager.searchEvents(events, input);
          break;

        case 3:
          EventManager.filterAndSortEvents(events, input);
          break;

        //Add Event
        case 4:
          EventManager.addEvent(events, input);
          break;

        //Edit Event
        case 5:
          EventManager.editEvent(events, input);
          break;
        
        //Delete Event
        case 6:
          EventManager.deleteEvent(events, input);
          break;

        //Exit
        case 7:
          break;
        
        //Invalid Input Catch
        default:
          System.out.println("Invalid Input, Enter 1-7...");
      }  
  }
}
