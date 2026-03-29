import java.util.*;
import java.time.*;

public class Main
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);
    LinkedList<Event> events = new LinkedList<Event>();

    //declare variables
    String name = "";//name of event
    LocalDate date = LocalDate.of(2002, 04, 23); //date of event
    LocalTime time = LocalTime.of(15, 30); //time of event
    double duration = 0.0; //how long the event will last
    ArrayList<String> types = new ArrayList<>(); //tags for the event
    String format = ""; //how the event will take place  

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


    
    events.add(new Event(name, date, time, duration, types, format));

    FileManager.saveEvents(events);

    System.out.println(events.get(0));
    
    input.close();
  }
}

    
  