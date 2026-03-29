package cse1325project;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeParseException.DateTimeFormatter;

public class Main
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);
    int choice = 0;
    LocalDate currentDate = LocalDate.now();
    String strCurrentDate = currentDate.format(DATE_FORMATTER);

    //Starter Output
    System.out.println("Welcome to the Local Loop!");
    System.out.println("To get started...");
  
    do
    {
      LinkedList<Event> eventList = loadEvents();     //Create list of current events in file

      System.out.println("Choose an option: ");
      System.out.println("1. View Local Events");
      System.out.println("2. Add Event");
      System.out.println("3. Edit Event");
      System.out.println("4. Delete Event");
      System.out.println("5. Exit");
      choice = input.nextInt()
      input.nextLine();

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
              displayEvents(displayChoice, strCurrentDate);
            }

            //Show Events for Later Date
            else if(displayChoice == 4)
            {
              validDisplay = true;
              YearMonth ym = null;    //Holds valid month-year

              //Keep asking for month year until valid input
              while(ym == null)
              {
                System.out.print("Please enter the month and year you'd like to view events for(MM-yyyy): ");
                String disDate = input.nextLine();
                
                try
                {
                  ym = YearMonth.parse(disDate, dayYearFormat);       //Parse Input
                }
                catch(DateTimeParseException e)
                {
                  System.out.println("Invalid Date Format. Please use MM-yyyy...");
                }
              }

              //Call Method to display events for MM-yyyy format
              displayEvents(displayChoice, disDate);     
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
  public static void displayEvents(int displayChoice, String disDate)
  {
    LinkedList<Event> eventsToDisplay = new LinkedList<Event>();
    switch(displayChoice)
    {
      //View Events Happening TODAY
      case 1:
        LocalDate currentDate = LocalDate.parse(disDate, DATE_FORMATTER);
        //Iterate through all events
        for(Event event : eventList)
        {
          //Check if todays date is equal to event date in the list
          if(currentDate.isEqual(LocalDateTime.of(event.getDate())))
          {
              eventsToDisplay.add(event);
          }
        }
        break;

      //View Events Happening this WEEK
      case 2:
        LocalDate currentDate = LocalDate.parse(disDate, DATE_FORMATTER);
        WeekFields = weekFields.of(Locale.getDefault());           //Default Locale
        int curWeek = currentDate.get(weekFields.weekOfWeekBasedYear());
        //Iterate through all events
        for(Event event : eventList)
        {
          int eventWeek = (event.getDate()).get(weekFields.weekOfWeekBasedYear());
          if(curWeek == eventWeek)
          {
            eventsToDisplay.add(event);
          }
        }
        break;
      
      //View Events Happening this MONTH
      case 3:
          break;

      //View Events Happening During a GIVEN month and year
      case 4:
          break;

      
      default:
          System.out.println("Invalid option");
    }
    System.out.println();
    for(Event event : eventsToDisplay)
    {
        System.out.println(event.toString());
        System.out.println("-".repeat(40));
    }

  }

  //Getting user input to ADD Event
  public static void userAdd(Scanner input)
  {
    LinkedList<Event> events = new LinkedList<Event>();

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
    
    events.add(new Event(name, date, time, duration, types, format, organizer, password, location));

    FileManager.saveEvents(events);
  }

}

    
  
