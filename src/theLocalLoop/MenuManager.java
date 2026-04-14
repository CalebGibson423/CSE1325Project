package theLocalLoop;

import java.util.LinkedList;
import java.util.Scanner;

public class MenuManager {
    
    //Prints menu for user options
    public static void printMenu() { //Prints menu for user options{
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. View Local Events");
        System.out.println("2. Search Local Events");
        System.out.println("3. Sort and filter Events");
        System.out.println("4. Add Event");
        System.out.println("5. Edit Event");
        System.out.println("6. Delete Event");
        System.out.println("7. Exit");
        System.out.print("Please enter your selection: ");
  }

  public static void printDisplayMenu() { //Prints menu for display options
        System.out.println("\nWould you like to see-   ");
        System.out.println("1. Events happening today");
        System.out.println("2. Events happening this week");
        System.out.println("3. Events happening this month");
        System.out.println("4. Events happening at a later date");
        System.out.println("5. All events");
        System.out.print("Please enter your selection: ");
  }

  public static void printFilterMenu() { //Prints menu for filtering options
        System.out.println("\nHow would you like to filter the events?   ");
        System.out.println("1. By Type");
        System.out.println("2. By Organizer");
        System.out.println("3. No filtering");
        System.out.print("Please enter your selection: ");
  }

  public static void printSortMenu() { //Prints menu for sorting options
        System.out.println("\nHow would you like to sort the events?   ");
        System.out.println("1. By Name");
        System.out.println("2. By Date and Time");
        System.out.println("3. By Organizer");
        System.out.println("4. By Duration");
        System.out.print("Please enter your selection: ");
  }

  //handle user choice for menu options
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
