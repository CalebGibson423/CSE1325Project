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
  
  public static void userEdit(Scanner input, LinkedList<Event> eventList)
  {
    System.out.print("Enter the name of the event you want to edit: ");
    String nameOfEditedEvent = input.nextLine();

    for (Event event : eventList){
      if (event.getName() == nameOfEditedEvent){
        int editChoice = 0;
        boolean validEdit = false;
        while (!validEdit)
        {
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
          editChoice = input.nextInt();
          input.nextLine();
          
          if (editChoice >= 1 && editChoice <= 9){
            validEdit = true;
            System.out.print("\nEnter your edit: ");
            String edit = input.nextLine();
            FileManager.editEvent(event, editChoice, edit);
          }
        }
        break;
      }
    }
  }
}
}

    
  
