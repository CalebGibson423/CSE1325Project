package theLocalLoop;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.time.*;

//Valid Types and Formats
import theLocalLoop.Constants.ValidType;
import theLocalLoop.Constants.ValidFormat;

//DateTime Formatters
import static theLocalLoop.Constants.DateTimeFormatters.dateFormatter;
import static theLocalLoop.Constants.DateTimeFormatters.timeFormatter;

/**
 * FileManager class for handling file input and output operations related to events. <br>
 * Contains methods for saving events to a file, loading events from a file, adding an event to the file, editing an event in the file, and deleting an event from the file. <br>
 */
public class FileManager 
{

    /**
     * Private constructor to prevent instantiation.
     */
    private FileManager() {
        // Prevent instantiation
    }

    /** The name of the file to save events to. */
    public static final String FILE_NAME = "events.txt";

    /**
     * Saves a list of events to a file called "events.txt". Each event is saved in the following format: <br>
     * name | date | time | duration | type(s) | format | organizer | password | location <br>
     * @param events
     * LinkedList of Event objects representing the list of events to save to the file.
     */
    public static void saveEvents(LinkedList<Event> events)
    {

        //Sort events by date and time before saving
        Sort.sortByDateTime(events);

        //Try Open/Create a file called "events.txt"
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) 
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
            System.out.println("\nAn error occurred: " + e.getMessage());
        }
    }

    /**
     * Loads events from a file called "events.txt" and returns them as a LinkedList of Event objects.
     * @return
     * LinkedList of Event objects representing the events loaded from the file. If the file does not exist or an error occurs while reading the file, an empty LinkedList is returned.
     */
    public static LinkedList<Event> loadEvents()
    {
        //Try open file
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) 
        {
            
            LinkedList<Event> events = new LinkedList<Event>();

            String line;
            //Reads the file line by line
            while ((line = reader.readLine()) != null) 
            {
                //Splits the line (event info)
                String[] eventParts = line.split(" \\| ");

                //check if line is in the correct format
                if(eventParts.length != 9){
                    continue;
                }

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
                        System.out.println("\nInvalid type ignored: " + t);
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
            System.out.println("\nAn error occurred: " + e.getMessage());
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

    /**
     * Edits an event in the file by first loading the current events from the file, finding the specified event in the list and updating its details, then saving the updated list back to the file. <br>
     * @param event
     * Event object representing the event to edit in the file. 
     * @param input
     * Scanner object for user input to determine which attribute of the event to edit and the new value for that attribute. 
     */
    public static void editEvent(Event event, Scanner input) // Attribute of event to change depends on user's choice
    {
        boolean editing = true;

        //Check if event is password protected and if so, ask user for password and check if it is correct before allowing them to edit the event
        if (!event.getPassword().isEmpty()) {

            String attempt = InputValidator.getRequiredString(input,"\nThis event is password protected. Enter password to continue: ");

            if (!attempt.equals(event.getPassword())) {

                System.out.println("\nIncorrect password. Access denied.");
                return;
            }

            System.out.println("\nPassword accepted. You may now edit the event.");
        }

        while(editing){

            MenuManager.printEditMenu();
            int choice = InputValidator.getValidInt(input, "Please enter your selection (10 to finish editing): ", 1, 10);
            
            if(choice == 10){
                editing = false;
                System.out.println("\nFinished editing event.");
                continue;
            }

            switch (choice){
            case 1: // Edit name
                String newName = InputValidator.getRequiredString(input, "Enter the new name: ");
                event.setName(newName);
                System.out.println("\nEvent name updated successfully to '" + newName + "'.");
                break;

            case 2: // Edit date
                LocalDate newDate = InputValidator.getValidDate(input, "Enter the new date (MM-dd-yyyy): ");
                event.setDate(newDate);
                System.out.println("\nEvent date updated successfully to '" + newDate.format(dateFormatter) + "'.");
                break;

            case 3: // Edit time
                LocalTime newTime = InputValidator.getValidTime(input, "Enter the new time (HH:mm, e.g. 14:30 for 2:30 PM): ");
                event.setTime(newTime);
                System.out.println("\nEvent time updated successfully to '" + newTime.format(timeFormatter) + "'.");
                break;

            case 4: // Edit duration
                double newDuration = InputValidator.getValidDouble(input, "Enter the new duration in hours (e.g. 1.5): ");
                event.setDuration(newDuration);
                System.out.println("\nEvent duration updated successfully to '" + newDuration + " hours'.");
                break;

            case 5: // Edit types
                ArrayList<ValidType> newTypes = InputValidator.getValidTypes(input, "Enter the new types/tags (separated by commas): ");

                event.setTypes(newTypes);
                System.out.println("\nEvent types updated successfully to '" + String.join(", ", newTypes.stream().map(Enum::name).toList()) + "'.");
                break;

            case 6: // Edit format
                ValidFormat newFormat = InputValidator.getValidFormat(input, "Enter the new format (In person / Virtual / Hybrid): ");

                event.setFormat(newFormat);
                System.out.println("\nEvent format updated successfully to '" + newFormat.name() + "'.");
                break;

            case 7: // Edit Organizer
                String newOrganizer = InputValidator.getRequiredString(input, "Enter the new organizer: ");
                event.setOrganizer(newOrganizer);
                System.out.println("\nEvent organizer updated successfully to '" + newOrganizer + "'.");
                break;

            case 8: // Edit password
                
                String newPassword = InputValidator.getValidPassword(input, "Enter the new password (or leave blank if not needed): ");
                event.setPassword(newPassword);
                
                if(newPassword.isEmpty()) {
                    newPassword = "N/A";
                }

                System.out.println("\nEvent password updated successfully to '" + newPassword + "'.");
                break;

            case 9: // Edit location
                String newLocation = InputValidator.getRequiredString(input, "Enter the new location: ");
                event.setLocation(newLocation);
                System.out.println("\nEvent location updated successfully to '" + newLocation + "'.");
                break;
            }    
        }
    }
}