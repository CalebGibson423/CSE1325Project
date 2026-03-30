package theLocalLoop;
import java.util.*;
import java.time.*;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.time.temporal.WeekFields;
import static theLocalLoop.Constants.*;

public class Main
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);
    int choice = 0;
    LocalDate currentDate = LocalDate.now();
    String strCurrentDate = currentDate.format(dateFormatter);
    LinkedList<Event> eventList = FileManager.loadEvents();     //Create list of current events in file

    //Starter Output
    System.out.println("Welcome to the Local Loop!");
    System.out.println("To get started...");
  
    do
    {
      eventList = FileManager.loadEvents();

      //Print User Options
      System.out.println("Choose an option: ");
      System.out.println("1. View Local Events");
      System.out.println("2. Add Event");
      System.out.println("3. Edit Event");
      System.out.println("4. Delete Event");
      System.out.println("5. Exit");
      choice = input.nextInt();
      input.nextLine();
      
      //Use Switch to View/Add/Edit/Delete Events
      switch(choice)
      {
        //View Events
        case 1:
          int displayChoice = 0;
          boolean validDisplay = false;

          while(!validDisplay)
          {
            System.out.println("\nWould you like to see events ");
            System.out.println("1. Happening today");
            System.out.println("2. Happening this week");
            System.out.println("3. Happening this month");
            System.out.println("4. Happening at a later date");
            displayChoice = input.nextInt();
            input.nextLine();

            //Show Events for today / current week / current month
            if(displayChoice == 1 || displayChoice == 2 || displayChoice == 3)
            {
              validDisplay = true;
              //Call Method to display MM-dd-yyyy format
              displayEvents(displayChoice, strCurrentDate, eventList);
            }

            //Show Events for Later Date
            else if(displayChoice == 4)
            {
              validDisplay = true;
              YearMonth ym = null;    //Holds valid month-year
              String disDate = "";

              //Keep asking for month year until valid input
              while(ym == null)
              {
                System.out.print("Please enter the month and year you'd like to view events for(MM-yyyy): ");
                disDate = input.nextLine();
                
                try
                {
                  ym = YearMonth.parse(disDate, monthYearFormatter);       //Parse Input
                }
                catch(DateTimeParseException e)
                {
                  System.out.println("Invalid Date Format. Please use MM-yyyy...");
                }
              }

              //Call Method to display events for MM-yyyy format
              displayEvents(displayChoice, disDate, eventList);     
            }

            else
            {
              System.out.println("Invalid Input...");
            }
          }
          //End View Events Case
          break;

        //Add Event
        case 2:
          break;

        //Edit Event
        case 3:
          break;
        
        //Delete Event
        case 4:
          break;

        //Exit
        case 5:
          break;
        
        //Invalid Input Catch
        default:
          System.out.println("Invalid Input, Enter 1, 2, 3, 4, or 5...");
      }
    }
    while(choice != 5);

    input.close();
  }

  //Display Events
  public static void displayEvents(int displayChoice, String disDate, LinkedList<Event> eventList)
  {
    //Create List to add events to display
    LinkedList<Event> eventsToDisplay = new LinkedList<Event>();
    LocalDate currentDate = null;

    if(displayChoice != 4)
    {
      //Put date String into LocalDate format
      currentDate = LocalDate.parse(disDate, dateFormatter);
    }

    //Check How User Wanted Events Displayed
    switch(displayChoice)
    {
      //View Events Happening TODAY
      case 1:
        //Iterate through all events
        for(Event event : eventList)
        {
          //Check if todays date is equal to event date in the list
          if(currentDate.isEqual(event.getDate()))
          {
              eventsToDisplay.add(event);
          }
        }
        break;

      //View Events Happening this WEEK
      case 2:
        WeekFields weekFields = WeekFields.of(Locale.getDefault());           //Default Locale
        //Get Current Week and Year
        int curWeek = currentDate.get(weekFields.weekOfWeekBasedYear());
        int curYear = currentDate.get(weekFields.weekBasedYear());

        //Iterate through all events
        for(Event event : eventList)
        {
          //Get week of event
          int eventWeek = (event.getDate()).get(weekFields.weekOfWeekBasedYear());
          int eventYear = (event.getDate()).get(weekFields.weekBasedYear());
          
          if(curWeek == eventWeek && curYear == eventYear)
          {
            eventsToDisplay.add(event);
          }
        }
        break;
      
      //View Events Happening this MONTH
      case 3:
        //Iterate through all events
        for(Event event : eventList)
        {
          //If 
          if((currentDate.getMonthValue() == (event.getDate()).getMonthValue()) && 
            (currentDate.getYear() == (event.getDate()).getYear()))
            {
              eventsToDisplay.add(event);
            }
        }
        break;

      //View Events Happening During a GIVEN month and year
      case 4:
        //Put display date String into YearMonth format
        YearMonth searchDate = YearMonth.parse(disDate, monthYearFormatter);

        //Iterate through all events
        for(Event event : eventList)
        {
          LocalDate eventDate = event.getDate();
          YearMonth eventYm = YearMonth.from(eventDate);
          if(searchDate.equals(eventYm))
          {
            eventsToDisplay.add(event);
          }
        }
        break;
    }

    //Print Events
    System.out.println();

    //If no events were found within the scope
    if(eventsToDisplay.isEmpty())
    {
      System.out.println("No events found.");
    }
    //If events were found
    else
    {
      for(Event event : eventsToDisplay)
      {
          System.out.println(event.toString());
          System.out.println("-".repeat(40));
      }
    }
  }


  //Getting user input to ADD Event
  public static void userAdd(Scanner input, LinkedList<Event> eventList)
  {
    

    //declare variables
    String name = "";//name of event
    LocalDate date = LocalDate.of(2002, 04, 23); //date of event
    LocalTime time = LocalTime.of(15, 30); //time of event
    double duration = 0.0; //how long the event will last
    ArrayList<String> types = new ArrayList<>(); //tags for the event
    String format = ""; //how the event will take place 
    String organizer = "";
    String password = ""; 
    String location = "";

    //enter name of event
    System.out.print("Enter the name of the event: ");
    name = input.nextLine();

    //enter duration of event
    System.out.print("Enter the duration of the event(Hour(s).Minute(s): ");
    duration = input.nextDouble();
    input.nextLine();

    //enter format of event
    System.out.print("Enter the format of this event(In person/Virtual/Hybrid): ");
    format = input.nextLine();

    //enter type of event
    String check = "";
    do
    {
      System.out.print("Enter the type(s) of event this is: ");
      String addString = ""; //buffer string to be added to types  
      addString = input.nextLine();
      types.add(addString);
      
      System.out.print("Would you like to add another type?(y/n): ");
      check = input.nextLine();
    }
    while(check.equalsIgnoreCase("y"));

    //enter organizer of event
    System.out.print("Who is the organizer(s) of this event?: ");
    organizer = input.nextLine();

    //enter password for user editing events
    System.out.print("Enter a password for future modification of this event: ");
    password = input.nextLine();

    //enter event location
    System.out.println("Enter event location: ");
    location = input.nextLine();
    
    FileManager.addEvent(new Event(name, date, time, duration, types, format, organizer, password, location));
  }
  
}

    
  