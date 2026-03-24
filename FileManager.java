import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.time.*;

public class FileManager {

    public static void saveEvents(LinkedList<Event> events){
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("events.txt"));) {

            for(int i = 0; i < events.size(); i++){
                writer.write(
                    events.get(i).getName() + "/" +
                    events.get(i).getDate().format(Constants.dateFormatter) + "/" +
                    events.get(i).getTime().format(Constants.timeFormatter) + "/" +
                    events.get(i).getDuration() + "/" +
                    String.join(",", events.get(i).getTypes()) + "/" +
                    events.get(i).getFormat()
                );

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static ArrayList<Event> loadEvents(){

        try (BufferedReader reader = new BufferedReader(new FileReader("events.txt"))) {

            ArrayList<Event> events = new ArrayList<Event>();

            String line;
            while ((line = reader.readLine()) != null) {
                
                String[] eventParts = line.split("/");

                String name = eventParts[0];
                LocalDate date = LocalDate.parse(eventParts[1], Constants.dateFormatter);
                LocalTime time = LocalTime.parse(eventParts[2], Constants.timeFormatter);
                double duration = Double.parseDouble(eventParts[3]);

                String[] typesArray = eventParts[4].split(",");
                ArrayList<String> typesList = new ArrayList<>();

                for(int i = 0; i < typesArray.length; i++){
                    typesList.add(typesArray[i]);
                }
              
                String format = eventParts[5];

                Event newEvent = new Event(name, date, time, duration, typesList, format);
                events.add(newEvent);
            }

            return events;

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
