import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

    public static void saveEvents(ArrayList<Event> events){
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("events.txt"));) {

            for(int i = 0; i < events.size(); i++){
                writer.write(
                    events.get(i).getName() + "/" +
                    events.get(i).getDay() + "/" +
                    events.get(i).getMonth() + "/" +
                    events.get(i).getYear() + "/" +
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
                int day = Integer.parseInt(eventParts[1]);
                int month = Integer.parseInt(eventParts[2]);
                int year = Integer.parseInt(eventParts[3]);
                double duration = Double.parseDouble(eventParts[4]);

                String[] typesArray = eventParts[5].split(",");
                ArrayList<String> typeArrayList = new ArrayList<>();

                for(int i = 0; i < typesArray.length; i++){
                    typeArrayList.add(typesArray[i]);
                }
              
                String format = eventParts[6];

                Event newEvent = new Event(name, day, month, year, duration, typeArrayList, format);
                events.add(newEvent);
            }

            return events;

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
