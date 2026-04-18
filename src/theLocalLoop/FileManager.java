package theLocalLoop;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.time.*;

//Valid Types and Formats
import theLocalLoop.Constants.ValidType;
import theLocalLoop.Constants.ValidFormat;

//DateTime Formatters
import static theLocalLoop.Constants.DateTimeFormatters.dateFormatter;
import static theLocalLoop.Constants.DateTimeFormatters.timeFormatter;

/**
 * FileManager class for handling file input and output operations related to events. <br>
 * Contains methods for saving events to a file, loading events from a file, adding an event to the file, and deleting an event from the file. <br>
 */
public class FileManager 
{

    /**
     * Saves a list of events to a file called "events.txt". Each event is saved in the following format: <br>
     * name | date | time | duration | type(s) | format | organizer | password | location <br>
     * @param events
     * LinkedList of Event objects representing the list of events to save to the file.
     */
    public static void saveEvents(LinkedList<Event> events)
    {
        //Try Open/Create a file called "events.txt"
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("events.txt"))) 
        {
            //Loop through events
            for(int i = 0; i < events.size(); i++)
            {
                //check if password is empty
                String password = "";
                if(events.get(i).getPassword().isEmpty()) {
                    password = "N/A";
                } else {
                    password = events.get(i).getPassword();
                }
                //For each event, write event info in the following format
                writer.write(
                    events.get(i).getName() + " | " +
                    events.get(i).getDate().format(dateFormatter) + " | " +
                    events.get(i).getTime().format(timeFormatter) + " | " +
                    events.get(i).getDuration() + " | " +
                    String.join(",", events.get(i).getTypes().stream().map(Enum::name).toList()) + " | " +
                    events.get(i).getFormat().name() + " | " +
                    events.get(i).getOrganizer() + " | " + 
                    password + " | " +
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
                String[] eventParts = line.split(" \\| ");

                //Put event info into proper types
                String name = eventParts[0];
                LocalDate date = LocalDate.parse(eventParts[1], dateFormatter);
                LocalTime time = LocalTime.parse(eventParts[2], timeFormatter);
                double duration = Double.parseDouble(eventParts[3]);

                //Handles the "types" list and puts all types into an ArrayList<ValidType>
                ArrayList<ValidType> typesList = new ArrayList<>();
                String[] typesArray = eventParts[4].split(",");

                for (String t : typesArray) {
                    try {
                        typesList.add(ValidType.valueOf(t.trim().toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid type ignored: " + t);
                    }
                }

                //Puts event format into ValidFormat
                ValidFormat format = ValidFormat.valueOf(eventParts[5].trim().toUpperCase());

                //Puts event organizer into String
                String organizer = eventParts[6];

                //Puts event password into String
                String password = eventParts[7];

                if(password.equals("N/A")){
                    password = "";
                }

                //Puts event location into String
                String location = eventParts[8];

                //Creates new event object
                Event newEvent = new Event(name, date, time, duration, typesList, format, organizer, password, location); 
                events.add(newEvent); //Adds the event read from the line to a list
            }

            return events; //Returns event list

        } 
        //If file fails
        catch (IOException e) 
        {
            System.out.println("An error occurred: " + e.getMessage());
            return new LinkedList<>();
        }
    }

    /**
     * Adds an event to the file by first loading the current events from the file, adding the new event to the list, then saving the updated list back to the file.
     * @param event
     * Event object representing the event to add to the file.
     * @return
     * LinkedList of Event objects representing the updated list of events after adding the new event.
     */
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
    
        saveEvents(updatedEvents); //Update file with new event
        return updatedEvents; //Return new event list with added event
    } 

    /**
     * Deletes an event from the file by first loading the current events from the file, removing the specified event from the list, then saving the updated list back to the file.
     * @param event
     * Event object representing the event to delete from the file.
     */
    public static void deleteEvent(Event event)
    {
        /*Whenever you ask a user which event they want to delete in main, loop through event names and hosts to determine if they have chosen the correct event to delete.
        We could also implement passwords to each event, that way only the host can access an event to edit or delete.
        If we implement servers, maybe there will be a way to recognize clients but idk how to do that. 
        */

        LinkedList<Event> eventList = loadEvents();     //Create list of current events in file
        eventList.removeIf(e -> e.equals(event));       //Remove event from list
        saveEvents(eventList);                          //Save list with changes to file
    }

    //Edit Event in list
    public static void editEvent(Event event, int choice, String edit) // Attribute of event to change depends on user's choice
    {
        LinkedList<Event> eventList = loadEvents();
        for (int i = 0; i < eventList.size(); i++)
        {
            Event current = eventList.get(i);
            if (current.equals(event))
            {
                switch (choice)
                {
                    case 1: // Edit name
                        String newName = edit;
                        current.setName(newName);
                        break;

                    case 2: // Edit date
                        LocalDate newDate = LocalDate.parse(edit, dateFormatter);
                        current.setDate(newDate);
                        break;

                    case 3: // Edit time
                        LocalTime newTime = LocalTime.parse(edit, timeFormatter);
                        current.setTime(newTime);
                        break;

                    case 4: // Edit duration
                        double newDuration = Double.parseDouble(edit);
                        current.setDuration(newDuration);
                        break;

                    case 5: // Edit types
                        ArrayList<ValidType> newTypes = new ArrayList<>();

                        String[] splitTypes = edit.split(",\\s*");
                        for (String type : splitTypes) {
                            try {
                                newTypes.add(ValidType.valueOf(type.trim().toUpperCase()));
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid type ignored: " + type);
                            }
                        }

                        current.setTypes(newTypes);
                        break;

                    case 6: // Edit format
                        ValidFormat newFormat;
                        try {
                            newFormat = ValidFormat.valueOf(edit.trim().toUpperCase());
                            current.setFormat(newFormat);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid format ignored: " + edit);
                        }
                        break;

                    case 7: // Edit Organizer
                        String newOrganizer = edit;
                        current.setOrganizer(newOrganizer);
                        break;

                    case 8: // Edit password?
                        String newPassword = edit;
                        current.setPassword(newPassword);
                        break;

                    case 9: // Edit location
                        String newLocation = edit;
                        current.setLocation(newLocation);
                }
                eventList.set(i, current);
                break;
            }
        }
        saveEvents(eventList);
    }
}