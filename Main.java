import java.util.*;
import java.time.*;
import java.time.format.*;

public static void main(String[] args)
{
  Scanner input = new Scanner(System.in);

  LinkedList<Event> events = new LinkedList<Event>();

  String event_name = "marathon";
  int start_day = 23;
  int start_month = 4;
  int start_year = 2026;
  double duration = 4.50;
  ArrayList<String> event_types = "exercise"

  Event(event_name, start_day, start_month, start_year, duration, event_types);
  System.out.print(events);
  // Event event = ???
  // events.add(new Event(event_name, event_type, ...))
}
