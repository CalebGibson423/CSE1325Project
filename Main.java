import java.util.*;
import java.time.*;

public class Main
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);
    LinkedList<Event> events = new LinkedList<Event>();

    String name = "Marathon";
    LocalDate date = LocalDate.of(2002, 04, 23);
    LocalTime time = LocalTime.of(15, 30);
    double duration = 3;
    ArrayList<String> types = new ArrayList<>();
    String format = "Online";        
    types.add("Educational");
    types.add("Social");
    types.add("Gaming");
    types.add("water");
    
    events.add(new Event(name, date, time, duration, types, format));

    FileManager.saveEvents(events);

    System.out.println(events.get(0));
    
    input.close();
  }
}
