package theLocalLoop;
import java.util.*;

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
      choice = input.nextInt(); //Get user choice
      input.nextLine();

      MenuManager.handleChoice(choice, input, eventList); //Handle user choice
    }

    //Before exiting, save events to file
    FileManager.saveEvents(eventList);

    //Exit Message
    System.out.println("Thank you for using the Local Loop!");

  }
}

    
  
