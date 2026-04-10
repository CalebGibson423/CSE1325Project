package theLocalLoop;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Sort {
    
    //sorting -----

    public static void sortByName(LinkedList<Event> eventList){

        Collections.sort(eventList, nameCompare);
    }

    public static void sortByOrganizer(LinkedList<Event> eventList){
  
        Collections.sort(eventList, organizerCompare);
    }

    public static void sortByDuration(LinkedList<Event> eventList){
        
        Collections.sort(eventList, durationCompare);
    }

    //filtering -----

    public static LinkedList<Event> filterByType(LinkedList<Event> eventList, String targetType){
    
        LinkedList<Event> eventListType = new LinkedList<>();

        for(Event event : eventList){
            for(String type: event.getTypes()){

                if(type.equalsIgnoreCase(targetType)){
                    eventListType.add(event);
                }
            }
        }

        return eventListType;
    }

    public static void filterByOrganizer(LinkedList<Event> eventList, String targetOrganizer){
        
        LinkedList<Event> eventListOrganizer = new LinkedList<>();

        for(Event event : eventList){
            
            if(event.getOrganizer().equalsIgnoreCase(targetOrganizer)){
                eventListOrganizer.add(event);
            }
        }
    }

    //comparators -----

    //comparator for names
    static Comparator<Event> nameCompare = new Comparator<Event>() {
        @Override
        public int compare(Event e1, Event e2) {
            return e1.getName().compareToIgnoreCase(e2.getName());
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
