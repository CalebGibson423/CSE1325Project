package theLocalLoop;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Sort {
    
    //Returns a sorted and filtered list of events based on user choices
    public static LinkedList<Event> sortAndFilter(LinkedList<Event> events, int sortChoice, int filterChoice, Scanner input){

        //Create a working list to sort and filter
        LinkedList<Event> workingList = new LinkedList<>(events);

        //Handle filtering choice
        switch(filterChoice)
        {
            case 1: //Filter by Type
                System.out.print("Enter the type you would like to filter by: ");
                String targetType = input.nextLine();

                workingList = filterByType(events, targetType);
                break;

            case 2: //Filter by Organizer
                System.out.print("Enter the organizer you would like to filter by: ");
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

    public static void sortByName(LinkedList<Event> eventList){

        Collections.sort(eventList, nameCompare);
    }

    public static void sortByDateTime(LinkedList<Event> eventList){

        Collections.sort(eventList, dateTimeCompare);
    }

    public static void sortByOrganizer(LinkedList<Event> eventList){
  
        Collections.sort(eventList, organizerCompare);
    }

    public static void sortByDuration(LinkedList<Event> eventList){
        
        Collections.sort(eventList, durationCompare);
    }

    //-----filtering -----

    public static LinkedList<Event> filterByType(LinkedList<Event> eventList, String targetType){
    
        LinkedList<Event> eventListType = new LinkedList<>();

        for(Event event : eventList){
            for(String type: event.getTypes()){

                if(type.equalsIgnoreCase(targetType)){
                    eventListType.add(event);
                    break;
                }
            }
        }

        return eventListType;
    }

    public static LinkedList<Event> filterByOrganizer(LinkedList<Event> eventList, String targetOrganizer){
        
        LinkedList<Event> eventListOrganizer = new LinkedList<>();

        for(Event event : eventList){
            
            if(event.getOrganizer().equalsIgnoreCase(targetOrganizer)){
                eventListOrganizer.add(event);
            }
        }

        return eventListOrganizer;
    }

    public static LinkedList<Event> filterByFormat(LinkedList<Event> eventList, String targetFormat){
        
        LinkedList<Event> eventListOrganizer = new LinkedList<>();

        for(Event event : eventList){
            
            if(event.getFormat().equalsIgnoreCase(targetFormat)){
                eventListOrganizer.add(event);
            }
        }

        return eventListOrganizer;
    }

    //----- comparators -----

    //comparator for names
    static Comparator<Event> nameCompare = new Comparator<Event>() {
        @Override
        public int compare(Event e1, Event e2) {
            return e1.getName().compareToIgnoreCase(e2.getName());
        }
    };

    //comparator for date time
    static Comparator<Event> dateTimeCompare = new Comparator<Event>() {
        
        @Override
        public int compare(Event e1, Event e2) {

            LocalDateTime e1DateTime = LocalDateTime.of(e1.getDate(), e1.getTime());
            LocalDateTime e2DateTime = LocalDateTime.of(e2.getDate(), e2.getTime());

            return e1DateTime.compareTo(e2DateTime);
        }
    };

    //comparator for Organizer
    static Comparator<Event> organizerCompare = new Comparator<Event>() {
        @Override
        public int compare(Event e1, Event e2) {
            return e1.getOrganizer().compareToIgnoreCase(e2.getOrganizer());
        }
    };

    //comparator for duration
    static Comparator<Event> durationCompare = new Comparator<Event>() {
        @Override
        public int compare(Event e1, Event e2) {
            return Double.compare(e1.getDuration(), e2.getDuration());
        }
    };

}
