import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.time.*;

public class FileManager {

    //Write's the list of events to a file
    public static void saveEvents(LinkedList<Event> events)
    {
        //Try Open/Create a file called "events.txt"
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("events.txt"));) 
        {
            //Loop through events
            for(int i = 0; i < events.size(); i++)
            {
                //For each event, write event info in the following format
                writer.write(
                    events.get(i).getName() + "/" +
                    events.get(i).getDate().format(Constants.dateFormatter) + "/" +
                    events.get(i).getTime().format(Constants.timeFormatter) + "/" +
                    events.get(i).getDuration() + "/" +
                    String.join(",", events.get(i).getTypes()) + "/" +
                    events.get(i).getFormat()
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
    public static ArrayList<Event> loadEvents()
    {
        //Try open file
        try (BufferedReader reader = new BufferedReader(new FileReader("events.txt"))) 
        {
            
            ArrayList<Event> events = new ArrayList<Event>();

            String line;
            //Reads the file line by line
            while ((line = reader.readLine()) != null) 
            {
                //Splits the line (event info)
                String[] eventParts = line.split("/");

                //Put event info into proper types
                String name = eventParts[0];
                LocalDate date = LocalDate.parse(eventParts[1], Constants.dateFormatter);
                LocalTime time = LocalTime.parse(eventParts[2], Constants.timeFormatter);
                double duration = Double.parseDouble(eventParts[3]);

                //Handles the "types" list and puts all types into an ArrayList
                String[] typesArray = eventParts[4].split(",");
                ArrayList<String> typesList = new ArrayList<>();
                //Adds each type to the ArrayList
                for(int i = 0; i < typesArray.length; i++)
                {
                    typesList.add(typesArray[i]);
                }

                //Puts event format into String
                String format = eventParts[5];

                //Creates new event object
                Event newEvent = new Event(name, date, time, duration, typesList, format); 
                events.add(newEvent);                   //Adds the event read from the line to a list
            }

            return events;              //Returns event list

        } 
        //If file fails
        catch (IOException e) 
        {
            System.out.println("An error occurred: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
