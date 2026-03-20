import java.util.*;
import java.time.*;
import java.time.format.*;

public class Main
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);

    LinkedList<Event> events = new LinkedList<Event>();

    String event_name = "Marathon";
    int start_day = 23;
    int start_month = 4;
    int start_year = 2026;
    double duration = 4.50;
    ArrayList<String> event_types = new ArrayList<>();
    String format = "Online";        
    event_types.add("Educational");
    event_types.add("Social");
    event_types.add("Gaming");
    event_types.add("water");
    
    // Event event = ???
    events.add(new Event(event_name, start_day, start_month, start_year, duration, event_types, format));
    System.out.println(events.get(0));
    
  }
}
