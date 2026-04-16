package theLocalLoop;
import java.util.*;

/**
 * Main class for The Local Loop application. 
 * This class initializes the application, loads events from a file, and manages the main menu loop where users can interact with the application. 
 * It also ensures that events are loaded and saved from/to the file when the application starts and exits, respectively.
 */
public class Main
{

  public static Scanner input = new Scanner(System.in);

  public static void main(String[] args)
  {
    //Load events from file into list
    LinkedList<Event> eventList = FileManager.loadEvents();     

    //load sample events into list if file is empty
    if (eventList.isEmpty()){
      TestData.loadSampleEvents(eventList);
    }
    
    //Welcome Message
    System.out.println("Welcome to the Local Loop!");
  
    //Main Loop
    int choice = 0;
    while(choice != 7)
    {
      MenuManager.printMenu(); //Print menu options
      choice = InputValidator.getValidInt(input, "Please enter your selection: ", 1, 7);
      
      MenuManager.handleChoice(choice, input, eventList); //Handle user choice
    }

    //Before exiting, save events to file
    FileManager.saveEvents(eventList);

    //Exit Message
    System.out.println("Thank you for using the Local Loop!");

  }
}

    
  
