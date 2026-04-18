package theLocalLoop;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

import theLocalLoop.Constants.ValidFormat;
import theLocalLoop.Constants.ValidType;

/**
 * The Sort class provides methods for sorting and filtering a list of events based on user choices. 
 * It includes methods for sorting by name, date and time, organizer, and duration, as well as filtering by type and organizer.
 */
public class Sort {
    
    /**
     * Sorts and filters a list of events based on user choices for sorting and filtering criteria.
     * @param events
     * Current list of events.
     * @param sortChoice
     * User's choice for sorting criteria (1: Name, 2: Date and Time, 3: Organizer, 4: Duration).
     * @param filterChoice
     * User's choice for filtering criteria (1: Type, 2: Organizer, 3: No filtering).
     * @param input
     * Scanner object for user input.
     * @return
     * A new list of events with filtering and sorting choices applied.
     */
    public static LinkedList<Event> sortAndFilter(LinkedList<Event> events, int sortChoice, int filterChoice, Scanner input){

        //Create a working list to sort and filter
        LinkedList<Event> workingList = new LinkedList<>(events);

        //Handle filtering choice
        switch(filterChoice)
        {
            case 1: //Filter by Type
                System.out.print("Enter the type you would like to filter by: ");
                input.nextLine();
                String targetType = input.nextLine();

                workingList = filterByType(events, targetType);
                break;

            case 2: //Filter by Organizer
                System.out.print("Enter the organizer you would like to filter by: ");
                input.nextLine();
                String targetOrganizer = input.nextLine();

                workingList = filterByOrganizer(events, targetOrganizer);
                break;

            case 3: //No filtering
                break;
            
            default: //Invalid filtering choice catch
                System.out.println("Invalid filtering choice, please enter 1 or 2...");
        }

        //Handle sorting choice
        switch(sortChoice)
        {
            case 1: //Sort by Name
                sortByName(workingList);
                break;

            case 2: //Sort by Date and Time
                sortByDateTime(workingList);
                break;

            case 3: //Sort by Organizer
                sortByOrganizer(workingList);
                break;

            case 4: //Sort by Duration
                sortByDuration(workingList);
                break;

            default: //Invalid sorting choice catch
                break;
        }

        return workingList;
    }

    //----- sorting -----

    /**
     * Sorts a list of events by their names in alphabetical order.
     * @param events
     * Current list of events.
     */
    public static void sortByName(LinkedList<Event> events){

        Collections.sort(events, nameCompare);
    }

    /**
     * Sorts a list of events by their date and time in chronological order.
     * @param events
     * Current list of events.
     */
    public static void sortByDateTime(LinkedList<Event> events){

        Collections.sort(events, dateTimeCompare);
    }

    /**
     * Sorts a list of events by their organizers in alphabetical order.
     * @param events
     * Current list of events.
     */
    public static void sortByOrganizer(LinkedList<Event> events){
  
        Collections.sort(events, organizerCompare);
    }

    /**
     * Sorts a list of events by their duration in ascending order.
     * @param events
     * Current list of events.
     */
    public static void sortByDuration(LinkedList<Event> events){
        
        Collections.sort(events, durationCompare);
    }

    //-----filtering -----

    /**
     * Filters a list of events by a specified type and returns a new list containing only the events that match the target type, ignoring case sensitivity.
     * @param events
     * Current list of events.
     * @param targetType
     * The type to filter by, inputted by the user.
     * @return
     * A new list of events that match the target type.
     */
    public static LinkedList<Event> filterByType(LinkedList<Event> events, String targetType){
    
        LinkedList<Event> eventListType = new LinkedList<>();

            ValidType target;
            try {
                target = ValidType.valueOf(targetType.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid type: " + targetType);
                return eventListType;
            }

        for(Event event : events){
            for (ValidType type : event.getTypes()){

                if(type == target){
                    eventListType.add(event);
                    break;
                }
            }
        }

        return eventListType;
    }

    /**
     * Filters a list of events by a specified organizer and returns a new list containing only the events that match the target organizer, ignoring case sensitivity.
     * @param events
     * Current list of events.
     * @param targetOrganizer
     * The organizer to filter by, inputted by the user.
     * @return
     * A new list of events that match the target organizer.
     */
    public static LinkedList<Event> filterByOrganizer(LinkedList<Event> events, String targetOrganizer){
        
        LinkedList<Event> eventListOrganizer = new LinkedList<>();

        for(Event event : events){
            
            if(event.getOrganizer().equalsIgnoreCase(targetOrganizer)){
                eventListOrganizer.add(event);
            }
        }

        return eventListOrganizer;
    }

    /**
     * Filters a list of events by a specified format and returns a new list containing only the events that match the target format, ignoring case sensitivity.
     * @param events
     * Current list of events.
     * @param targetFormat
     * The format to filter by, inputted by the user.
     * @return
     * A new list of events that match the target format.
     */
    public static LinkedList<Event> filterByFormat(LinkedList<Event> events, String targetFormat){
        
        LinkedList<Event> eventListOrganizer = new LinkedList<>();

        try {
        ValidFormat tf = ValidFormat.valueOf(targetFormat.toUpperCase());

        for (Event event : events) {
            if (event.getFormat() == tf) {
                eventListOrganizer.add(event);
            }
        }

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid format filter: " + targetFormat);
        }

        return eventListOrganizer;
    }

    //----- comparators -----

    /**
     * Comparator for event names that compares two events based on their names in alphabetical order.
     */
    private static Comparator<Event> nameCompare = new Comparator<Event>() {
        @Override
        public int compare(Event e1, Event e2) {
            return e1.getName().compareToIgnoreCase(e2.getName());
        }
    };

    /**
     * Comparator for event date and time that compares two events based on their date and time in chronological order.
     */
    private static Comparator<Event> dateTimeCompare = new Comparator<Event>() {
        
        @Override
        public int compare(Event e1, Event e2) {

            LocalDateTime e1DateTime = LocalDateTime.of(e1.getDate(), e1.getTime());
            LocalDateTime e2DateTime = LocalDateTime.of(e2.getDate(), e2.getTime());

            return e1DateTime.compareTo(e2DateTime);
        }
    };

    /**
     * Comparator for event organizers that compares two events based on their organizers in alphabetical order.
     */
    private static Comparator<Event> organizerCompare = new Comparator<Event>() {
        @Override
        public int compare(Event e1, Event e2) {
            return e1.getOrganizer().compareToIgnoreCase(e2.getOrganizer());
        }
    };

    /**
     * Comparator for event duration that compares two events based on their duration in ascending order.
     */
    private static Comparator<Event> durationCompare = new Comparator<Event>() {
        @Override
        public int compare(Event e1, Event e2) {
            return Double.compare(e1.getDuration(), e2.getDuration());
        }
    };

}
