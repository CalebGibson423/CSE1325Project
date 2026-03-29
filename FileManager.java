package cse1325project;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.time.*;
import java.time.format.DateTimeFormatter;
import static cse1325project.Constants.*;

public class FileManager 
{

    //Write's the list of events to a file
    public static void saveEvents(LinkedList<Event> events)
    {
        //Try Open/Create a file called "events.txt"
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("events.txt"))) 
        {
            //Loop through events
            for(int i = 0; i < events.size(); i++)
            {
                //For each event, write event info in the following format
                writer.write(
                    events.get(i).getName() + " | " +
                    events.get(i).getDate().format(DATE_FORMATTER) + " | " +
                    events.get(i).getTime().format(TIME_FORMATTER) + " | " +
                    events.get(i).getDuration() + " | " +
                    String.join(",", events.get(i).getTypes()) + " | " +
                    events.get(i).getFormat() + " | " +
                    events.get(i).getOrganizer() + " | " + 
                    events.get(i).getPassword() + " | " +
                    events.get(i).getLocation()
                );

                //Move to next line for the next event
                writer.newLine();
            }

        } 
        //If something goes wrong
        catch (IOException e) 
        {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    //Read events from a file
    public static LinkedList<Event> loadEvents()
    {
        //Try open file
        try (BufferedReader reader = new BufferedReader(new FileReader("events.txt"))) 
        {
            
            LinkedList<Event> events = new LinkedList<Event>();

            String line;
            //Reads the file line by line
            while ((line = reader.readLine()) != null) 
            {
                //Splits the line (event info)
                String[] eventParts = line.split("|");

                //Put event info into proper types
                String name = eventParts[0];
                LocalDate date = LocalDate.parse(eventParts[1], DATE_FORMATTER);
                LocalTime time = LocalTime.parse(eventParts[2], TIME_FORMATTER);
                double duration = Double.parseDouble(eventParts[3]);

                //Handles the "types" list and puts all types into an ArrayList
                String[] typesArray = eventParts[4].split(",");
                ArrayList<String> typesList = new ArrayList<>();

                for(int i = 0; i < typesArray.length; i++)
                {
                    typesList.add(typesArray[i]);
                }

                //Puts event format into String
                String format = eventParts[5];

                //Puts event organizer into String
                String organizer = eventParts[6];

                //Puts event password into String
                String password = eventParts[7];

                //Puts event location into String
                String location = eventParts[8];

                //Creates new event object
                Event newEvent = new Event(name, date, time, duration, typesList, format, organizer, password, location); 
                events.add(newEvent);                   //Adds the event read from the line to a list
            }

            return events;              //Returns event list

        } 
        //If file fails
        catch (IOException e) 
        {
            System.out.println("An error occurred: " + e.getMessage());
            return new LinkedList<>();
        }
    }

    //Add event to list
    public static LinkedList<Event> addEvent(Event event)
    {
    
        //Create list of current events to update
        LinkedList<Event> updatedEvents = loadEvents();
        //Get dateTime of event
        LocalDateTime eventDateTime = LocalDateTime.of(event.getDate(), event.getTime());

        boolean eventAdded = false;

        //Iterate through all events
        for(int i=0; i < updatedEvents.size(); i++)
        {
            //Get events at the current index
            Event current = updatedEvents.get(i);

            //Get DateTime at the current index
            LocalDateTime currentDateTime = LocalDateTime.of(current.getDate(), current.getTime());

            //If we find the correct place to add the event
            if(eventDateTime.isBefore(currentDateTime))
            {
                updatedEvents.add(i, event);
                eventAdded = true;
                break;
            }
        }

        //Add event at the end if list is empty or event is the latest time and date
        if(!eventAdded)
        {
            updatedEvents.add(event);
        }
    
        saveEvents(updatedEvents);      //Update file with new event
        return updatedEvents;           //Return new event list with added event
    } 

    //Delete Event from list
    public static void deleteEvent(Event event)
    {
        /*Whenever you ask a user which event they want to delete in main, loop through event names and hosts to determine if they have chosen the correct event to delete.
        We could also implement passwords to each event, that way only the host can access an event to edit or delete.
        If we implement servers, maybe there will be a way to recognize clients but idk how to do that. 
        */

        LinkedList<Event> eventList = loadEvents();     //Create list of current events in file
        eventList.remove(event);                        //Remove event from list
        saveEvents(eventList);                          //Save list with changes to file

    }
}
