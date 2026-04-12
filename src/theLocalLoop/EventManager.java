package theLocalLoop;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class EventManager {

    //Handles user choice for how they want to view events
    public static void displayChoice(LinkedList<Event> events, Scanner input){
        
        MenuManager.printDisplayMenu(); //Print display options
        
        int displayChoice = input.nextInt();
        input.nextLine();
        
        displayEvents(displayChoice, events, input);
    }

    //Handles user choice for displaying events based on time frame
    public static void displayEvents(int displayChoice, LinkedList<Event> events, Scanner input){
        
        LocalDate today = LocalDate.now();
        int i = 1; //Counter for events displayed

        if(events.isEmpty()){
            System.out.println("\nNo events to display...");
            return;
        }

        boolean foundEvent = false; //Flag to check if any events are found for the chosen time frame
        
        switch(displayChoice){

            case 1: //Events Happening Today
                System.out.println("\n--- Events Happening Today ---");

                for(Event e: events){

                    //Check if event date is equal to today's date
                    if(e.getDate().isEqual(today)){
                        foundEvent = true;
                        System.out.println(i + ") " + e.toSummaryString());
                        i++; //Increment counter
                    }
                }

                if(!foundEvent){
                    System.out.println("No events happening today...");
                }

                break;

            case 2: //Events Happening This Week
                System.out.println("\n--- Events Happening This Week ---");
                LocalDate weekFromToday = today.plusDays(7);
            
                for(Event e: events){
                    if( //Check if event date is after yesterday and before a week from today
                        e.getDate().isAfter(today.minusDays(1)) 
                        && e.getDate().isBefore(weekFromToday)){

                        foundEvent = true;
                        System.out.println(i + ") " + e.toSummaryString());
                        i++; //Increment counter
                    }
                }

                if(!foundEvent){
                    System.out.println("No events happening this week...");
                }

                break;

            case 3: //Events Happening This Month
                System.out.println("\n--- Events Happening This Month ---");
                LocalDate monthFromToday = today.plusDays(30);
                
                for(Event e: events){
                    if( //Check if event date is after yesterday and before a month from today
                        e.getDate().isAfter(today.minusDays(1)) 
                        && e.getDate().isBefore(monthFromToday)){

                        foundEvent = true;
                        System.out.println(i + ") " + e.toSummaryString());
                        i++; //Increment counter
                    }
                }

                if(!foundEvent){
                    System.out.println("No events happening this month...");
                }

                break;

            case 4: //Events Happening at a Later Date
                System.out.println("\n--- Events Happening at a Later Date ---");
                int year = 0;
                int month = 0;

                //get user input for month and year
                System.out.print("Enter a year (YYYY): ");
                year = input.nextInt();
                input.nextLine();

                System.out.print("Enter a month (1-12): ");
                month = input.nextInt();
                input.nextLine();

                //Create a LocalDate object for the first day of the specified month and year
                LocalDate userDate = LocalDate.of(year, month, 1);

                for(Event e: events){
                    if(e.getDate().getYear() == userDate.getYear() && e.getDate().getMonthValue() == userDate.getMonthValue()){
                        
                        foundEvent = true;
                        System.out.println(i + ") " + e.toSummaryString());
                        i++; //Increment counter
                    }
                }

                if(!foundEvent){
                    System.out.println("No events happening at a the specified month and year...");
                }

                break;
           
            case 5: //All Events
                System.out.println("\n--- All Events ---");
                
                for(Event e: events){
                    foundEvent = true;
                    System.out.println(i + ") " + e.toSummaryString());
                    i++; //Increment counter
                }
                
                if(!foundEvent){
                    System.out.println("No events to display...");
                }

                break;

            default: //Invalid Input Catch

                System.out.println("Invalid Input, Enter 1, 2, 3, or 4, or 5...");
                return;
        }
    }

    public static void searchEvents(LinkedList<Event> events, Scanner input){
        
        String eventName = "";
        boolean found = false;
        int i = 1; //Counter for events displayed

        System.out.print("Enter the name of the event you would like to search for: ");
        eventName = input.nextLine();

        //search through events to find event with the same name and display its details
        for(Event e: events){

            if(eventName.equalsIgnoreCase(e.getName())){     
                found = true;
                
                System.out.println("\n--- Event(s) Found ---");
                System.out.println(i + ") " );
                System.out.println(e.toString());
                i++; //Increment counter

                break;
            }
        }

        if(!found){
            System.out.println("Event not found.");
        }
    }

    //Handles user choice for how they want to filter and sort events, then displays results
    public static void filterAndSortEvents(LinkedList<Event> events, Scanner input){
       
        int i = 1; //Counter for events displayed

        MenuManager.printSortMenu(); //Print sorting options
        int sortChoice = input.nextInt();
        input.nextLine();

        MenuManager.printFilterMenu(); //Print filtering options
        int filterChoice = input.nextInt();
        input.nextLine();

        //Get sorted and filtered list based on user choices
        LinkedList<Event> result = Sort.sortAndFilter(events, sortChoice, filterChoice, input);

        //Display sorted and filtered results
        System.out.println("\n--- Sorted and Filtered Events ---");
        
        for(Event e: result){
            System.out.println(i + ") " + e.toSummaryString());
            i++; //Increment counter
        }
    }

    //Let user add event to list
    public static void addEvent(LinkedList<Event> events, Scanner input){

        //declare variables
        String name = ""; //name of event
        LocalDate date = null; //date of event
        LocalTime time = null; //time of event
        String addDate = ""; //User date given
        String addTime = ""; //User time given
        double duration = 0.0; //how long the event will last
        ArrayList<String> types = new ArrayList<>(); //tags for the event
        String format = ""; //how the event will take place 
        String organizer = ""; //who is hosting the event
        String password = ""; //password for event if needed
        String location = ""; //where the event is taking place

        //enter name of event
        System.out.print("Enter the name of the event: ");
        name = input.nextLine();

        //Enter date of event
        //Keep asking for date until valid input    
        while(date == null)
        {
          System.out.print("Enter event date (MM-dd-yyyy): ");
          addDate = input.nextLine();
          
          try
          {
            date = LocalDate.parse(addDate, Constants.dateFormatter); //Parse Input
          }
          catch(Exception e)
          {
            System.out.println("Invalid Date Format. Please use MM-dd-yyyy...");
          }
        }

        //Enter time of event
        //Keep asking for time until valid input
        while(time == null)
        {
          System.out.print("Enter event time (HH:mm): ");
          addTime = input.nextLine();

          try
          {
            time = LocalTime.parse(addTime, Constants.timeFormatter); //Parse Input
          }
          catch(Exception e)
          {
            System.out.println("Invalid Time Format. Please use HH:mm...");
          }
        }

        //Enter duration of event
        System.out.print("Enter event duration in hours (e.g. 1.5):");
        duration = input.nextDouble();
        input.nextLine(); 

        //Enter types/tags for event
        System.out.print("Enter event types/tags (separated by commas): ");
        String typesInput = input.nextLine();
        String[] typesArray = typesInput.split(",");
        
        for(String type: typesArray){
            types.add(type.trim());
        }

        //Enter format of event
        System.out.print("Enter the format of this event (In person/Virtual/Hybrid): ");
        format = input.nextLine();

        //Enter organizer of event
        System.out.print("Enter the organizer of this event: ");
        organizer = input.nextLine();

        //Enter password for event if needed
        System.out.print("Enter a password for this event (or leave blank if not needed): ");
        password = input.nextLine();

        //Enter location of event
        System.out.print("Enter the location of this event: ");
        location = input.nextLine();

        //Create new event object and add to list
        Event newEvent = new Event(name, date, time, duration, types, format, organizer, password, location);
        events.add(newEvent);

    }

    //Let user edit event in list
    public static void editEvent(LinkedList<Event> events, Scanner input){
        //WIP
    }

    //Let user delete event from list
    public static void deleteEvent(LinkedList<Event> events, Scanner input){

        String eventName = "";
        boolean found = false;

        System.out.print("Enter the name of the event you would like to delete: ");
        eventName = input.nextLine();

        //search through events to find event with the same name and delete it
        for(int i = 0; i < events.size(); i++){
            if(eventName.equalsIgnoreCase(events.get(i).getName())){     
                found = true;
            }

            //If event is found, check if it has a password and if so, ask user for password before deleting
            if(found){
                if(events.get(i).getPassword() != null && !events.get(i).getPassword().isEmpty()){
                    System.out.print("This event is password protected. Please enter the password to delete: ");
                    String password = input.nextLine();

                    if(password.equals(events.get(i).getPassword())){
                        events.remove(i);
                        System.out.println("Event deleted successfully.");
                    }
                    else{
                        System.out.println("Incorrect password. Event not deleted.");
                    }
                }
                else{
                    events.remove(i);
                    System.out.println("Event deleted successfully.");
                }
            }
        }
    }
}
